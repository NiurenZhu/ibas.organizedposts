/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace app {

        /** 编辑应用-数据权限 */
        export class OwnershipEditApp extends ibas.BOEditApplication<IOwnershipEditView, bo.Ownership> {

            /** 应用标识 */
            static APPLICATION_ID: string = "e714b6c4-5762-4ee6-9f5b-2bbbd0105753";
            /** 应用名称 */
            static APPLICATION_NAME: string = "organizedposts_app_ownership_edit";
            /** 业务对象编码 */
            static BUSINESS_OBJECT_CODE: string = bo.Ownership.BUSINESS_OBJECT_CODE;
            /** 构造函数 */
            constructor() {
                super();
                this.id = OwnershipEditApp.APPLICATION_ID;
                this.name = OwnershipEditApp.APPLICATION_NAME;
                this.boCode = OwnershipEditApp.BUSINESS_OBJECT_CODE;
                this.description = ibas.i18n.prop(this.name);
            }
            /** 注册视图 */
            protected registerView(): void {
                super.registerView();
                // 其他事件
                this.view.deleteDataEvent = this.deleteData;
                this.view.createDataEvent = this.createData;
                this.view.addOwnershipItemEvent = this.addOwnershipItem;
                this.view.removeOwnershipItemEvent = this.removeOwnershipItem;
                this.view.chooseBusinessObjectEvent = this.chooseBusinessObject;
                this.view.chooseUserEvent = this.chooseUser;
            }
            /** 视图显示后 */
            protected viewShowed(): void {
                // 视图加载完成
                super.viewShowed();
                if (ibas.objects.isNull(this.editData)) {
                    // 创建编辑对象实例
                    this.editData = new bo.Ownership();
                    this.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_created_new"));
                }
                this.view.showOwnership(this.editData);
                this.view.showOwnershipItems(this.editData.ownershipItems.filterDeleted());
            }
            /** 运行,覆盖原方法 */
            run(): void;
            run(data: bo.Ownership): void;
            run(): void {
                let that: this = this;
                if (ibas.objects.instanceOf(arguments[0], bo.Ownership)) {
                    let data: bo.Ownership = arguments[0];
                    // 新对象直接编辑
                    if (data.isNew) {
                        that.editData = data;
                        that.show();
                        return;
                    }
                    // 尝试重新查询编辑对象
                    let criteria: ibas.ICriteria = data.criteria();
                    if (!ibas.objects.isNull(criteria) && criteria.conditions.length > 0) {
                        // 有效的查询对象查询
                        let boRepository: bo.BORepositoryOrganizedPosts = new bo.BORepositoryOrganizedPosts();
                        boRepository.fetchOwnership({
                            criteria: criteria,
                            onCompleted(opRslt: ibas.IOperationResult<bo.Ownership>): void {
                                let data: bo.Ownership;
                                if (opRslt.resultCode === 0) {
                                    data = opRslt.resultObjects.firstOrDefault();
                                }
                                if (ibas.objects.instanceOf(data, bo.Ownership)) {
                                    // 查询到了有效数据
                                    that.editData = data;
                                    that.show();
                                } else {
                                    // 数据重新检索无效
                                    that.messages({
                                        type: ibas.emMessageType.WARNING,
                                        message: ibas.i18n.prop("shell_data_deleted_and_created"),
                                        onCompleted(): void {
                                            that.show();
                                        }
                                    });
                                }
                            }
                        });
                        // 开始查询数据
                        return;
                    }
                }
                super.run.apply(this, arguments);
            }
            /** 待编辑的数据 */
            protected editData: bo.Ownership;
            /** 保存数据 */
            protected saveData(): void {
                this.busy(true);
                let that: this = this;
                let boRepository: bo.BORepositoryOrganizedPosts = new bo.BORepositoryOrganizedPosts();
                boRepository.saveOwnership({
                    beSaved: this.editData,
                    onCompleted(opRslt: ibas.IOperationResult<bo.Ownership>): void {
                        try {
                            that.busy(false);
                            if (opRslt.resultCode !== 0) {
                                throw new Error(opRslt.message);
                            }
                            if (opRslt.resultObjects.length === 0) {
                                // 删除成功，释放当前对象
                                that.messages(ibas.emMessageType.SUCCESS,
                                    ibas.i18n.prop("shell_data_delete") + ibas.i18n.prop("shell_sucessful"));
                                that.editData = undefined;
                            } else {
                                // 替换编辑对象
                                that.editData = opRslt.resultObjects.firstOrDefault();
                                that.messages(ibas.emMessageType.SUCCESS,
                                    ibas.i18n.prop("shell_data_save") + ibas.i18n.prop("shell_sucessful"));
                            }
                            // 刷新当前视图
                            that.viewShowed();
                        } catch (error) {
                            that.messages(error);
                        }
                    }
                });
                this.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("shell_saving_data"));
            }
            /** 删除数据 */
            protected deleteData(): void {
                let that: this = this;
                this.messages({
                    type: ibas.emMessageType.QUESTION,
                    title: ibas.i18n.prop(this.name),
                    message: ibas.i18n.prop("shell_delete_continue"),
                    actions: [ibas.emMessageAction.YES, ibas.emMessageAction.NO],
                    onCompleted(action: ibas.emMessageAction): void {
                        if (action === ibas.emMessageAction.YES) {
                            that.editData.delete();
                            that.saveData();
                        }
                    }
                });
            }
            /** 新建数据，参数1：是否克隆 */
            protected createData(clone: boolean): void {
                let that: this = this;
                let createData: Function = function (): void {
                    if (clone) {
                        // 克隆对象
                        that.editData = that.editData.clone();
                        that.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_cloned_new"));
                        that.viewShowed();
                    } else {
                        // 新建对象
                        that.editData = new bo.Ownership();
                        that.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_created_new"));
                        that.viewShowed();
                    }
                };
                if (that.editData.isDirty) {
                    this.messages({
                        type: ibas.emMessageType.QUESTION,
                        title: ibas.i18n.prop(this.name),
                        message: ibas.i18n.prop("shell_data_not_saved_continue"),
                        actions: [ibas.emMessageAction.YES, ibas.emMessageAction.NO],
                        onCompleted(action: ibas.emMessageAction): void {
                            if (action === ibas.emMessageAction.YES) {
                                createData();
                            }
                        }
                    });
                } else {
                    createData();
                }
            }
            /** 添加数据权限项事件 */
            private addOwnershipItem(): void {
                this.editData.ownershipItems.create();
                // 仅显示没有标记删除的
                this.view.showOwnershipItems(this.editData.ownershipItems.filterDeleted());
            }
            /** 删除数据权限项事件 */
            private removeOwnershipItem(items: bo.OwnershipItem[]): void {
                // 非数组，转为数组
                if (!(items instanceof Array)) {
                    items = [items];
                }
                if (items.length === 0) {
                    return;
                }
                // 移除项目
                for (let item of items) {
                    if (this.editData.ownershipItems.indexOf(item) >= 0) {
                        if (item.isNew) {
                            // 新建的移除集合
                            this.editData.ownershipItems.remove(item);
                        } else {
                            // 非新建标记删除
                            item.delete();
                        }
                    }
                }
                // 仅显示没有标记删除的
                this.view.showOwnershipItems(this.editData.ownershipItems.filterDeleted());
            }
            /** 选择用户 */
            private chooseUser(): void {
                let that: this = this;
                ibas.servicesManager.runChooseService<initialfantasy.bo.IUser>({
                    boCode: initialfantasy.bo.BO_CODE_USER,
                    criteria: [
                        new ibas.Condition("Activated", ibas.emConditionOperation.EQUAL, ibas.emYesNo.YES)
                    ],
                    chooseType: ibas.emChooseType.SINGLE,
                    onCompleted(selecteds: ibas.IList<initialfantasy.bo.IUser>): void {
                        that.editData.userCode = selecteds.firstOrDefault().code;
                    }
                });
            }
            /** 选择业务对象标识 */
            private chooseBusinessObject(): void {
                let that: this = this;
                let criteria: ibas.ICriteria = new ibas.Criteria();
                criteria.noChilds = true;
                let condition: ibas.ICondition = criteria.conditions.create();
                condition.alias = "Code";
                condition.value = ".";
                condition.operation = ibas.emConditionOperation.NOT_CONTAIN;
                ibas.servicesManager.runChooseService<initialfantasy.bo.IBOInformation>({
                    boCode: initialfantasy.bo.BO_CODE_BOINFORMATION,
                    criteria: criteria,
                    chooseType: ibas.emChooseType.SINGLE,
                    onCompleted(selecteds: ibas.IList<initialfantasy.bo.IBOInformation>): void {
                        that.editData.boCode = selecteds.firstOrDefault().code;
                    }
                });
            }

        }
        /** 视图-数据权限 */
        export interface IOwnershipEditView extends ibas.IBOEditView {
            /** 显示数据 */
            showOwnership(data: bo.Ownership): void;
            /** 删除数据事件 */
            deleteDataEvent: Function;
            /** 新建数据事件，参数1：是否克隆 */
            createDataEvent: Function;
            /** 添加数据权限项事件 */
            addOwnershipItemEvent: Function;
            /** 删除数据权限项事件 */
            removeOwnershipItemEvent: Function;
            /** 显示数据 */
            showOwnershipItems(datas: bo.OwnershipItem[]): void;
            /** 选择用户事件 */
            chooseUserEvent: Function;
            /** 选择业务对象事件 */
            chooseBusinessObjectEvent: Function;
        }
    }
}