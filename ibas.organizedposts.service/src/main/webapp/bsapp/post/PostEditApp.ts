/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace app {
        /** 编辑应用-岗位 */
        export class PostEditApp extends ibas.BOEditApplication<IPostEditView, bo.Post> {
            /** 应用标识 */
            static APPLICATION_ID: string = "dbb431d1-b2c9-44fa-a594-60ddc396008d";
            /** 应用名称 */
            static APPLICATION_NAME: string = "organizedposts_app_post_edit";
            /** 业务对象编码 */
            static BUSINESS_OBJECT_CODE: string = bo.Post.BUSINESS_OBJECT_CODE;
            /** 构造函数 */
            constructor() {
                super();
                this.id = PostEditApp.APPLICATION_ID;
                this.name = PostEditApp.APPLICATION_NAME;
                this.boCode = PostEditApp.BUSINESS_OBJECT_CODE;
                this.description = ibas.i18n.prop(this.name);
            }
            /** 注册视图 */
            protected registerView(): void {
                super.registerView();
                // 其他事件
                this.view.deleteDataEvent = this.deleteData;
                this.view.createDataEvent = this.createData;
                this.view.addPostEvent = this.addPost;
                this.view.removePostEvent = this.removePost;
                this.view.chooseUserEvent = this.chooseUser;
                this.view.choosePositionEvent = this.choosePosition;
            }
            /** 视图显示后 */
            protected viewShowed(): void {
                // 视图加载完成
                super.viewShowed();
                if (ibas.objects.isNull(this.editData)) {
                    // 创建编辑对象实例
                    this.editData = new bo.Post();
                    this.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_created_new"));
                }
                this.view.showPost(this.editData);
            }
            /** 运行,覆盖原方法 */
            run(): void;
            run(data: bo.Post): void;
            run(): void {
                let that: this = this;
                if (ibas.objects.instanceOf(arguments[0], bo.Post)) {
                    let data: bo.Post = arguments[0];
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
                        boRepository.fetchPost({
                            criteria: criteria,
                            onCompleted(opRslt: ibas.IOperationResult<bo.Post>): void {
                                let data: bo.Post;
                                if (opRslt.resultCode === 0) {
                                    data = opRslt.resultObjects.firstOrDefault();
                                }
                                if (ibas.objects.instanceOf(data, bo.Post)) {
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
            protected editData: bo.Post;
            /** 保存数据 */
            protected saveData(): void {
                this.busy(true);
                let that: this = this;
                let boRepository: bo.BORepositoryOrganizedPosts = new bo.BORepositoryOrganizedPosts();
                boRepository.savePost({
                    beSaved: this.editData,
                    onCompleted(opRslt: ibas.IOperationResult<bo.Post>): void {
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
                        that.editData = new bo.Post();
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
            /** 添加岗位事件 */
            addPost(item: bo.Post): void {
                item.posts.create();
                this.view.showPost(this.editData);
            }
            /** 删除岗位事件 */
            removePost(item: bo.Post): void {
                function getParent(post: bo.Post): bo.Post {
                    if (post.posts.indexOf(item) >= 0) {
                        return post;
                    }
                    for (let child of post.posts) {
                        if (!ibas.objects.isNull(getParent(child))) {
                            return child;
                        }
                    }
                    return null;
                }
                if (item !== this.editData) {
                    let parent: bo.Post = getParent(this.editData);
                    if (!ibas.objects.isNull(parent)) {
                        if (item.isNew) {
                            // 新建的移除集合
                            parent.posts.remove(item);
                        } else {
                            // 非新建标记删除
                            item.delete();
                        }
                    }
                }
                this.view.showPost(this.editData);
            }
            /** 选择用户 */
            private chooseUser(item: bo.Post): void {
                if (ibas.objects.isNull(item)) {
                    return;
                }
                let that: this = this;
                ibas.servicesManager.runChooseService<initialfantasy.bo.IUser>({
                    boCode: initialfantasy.bo.BO_CODE_USER,
                    chooseType: ibas.emChooseType.SINGLE,
                    criteria: [
                        new ibas.Condition("Activated", ibas.emConditionOperation.EQUAL, ibas.emYesNo.YES)
                    ],
                    onCompleted(selecteds: ibas.IList<initialfantasy.bo.IUser>): void {
                        item.userCode = selecteds.firstOrDefault().code;
                        that.view.refreshViewModel();
                    }
                });
            }
            /** 选择业务对象标识 */
            private choosePosition(item: bo.Post): void {
                if (ibas.objects.isNull(item)) {
                    return;
                }
                let that: this = this;
                ibas.servicesManager.runChooseService<bo.Position>({
                    boCode: bo.BO_CODE_POSITION,
                    chooseType: ibas.emChooseType.SINGLE,
                    criteria: [
                        new ibas.Condition(bo.Position.PROPERTY_ACTIVATED_NAME, ibas.emConditionOperation.EQUAL, ibas.emYesNo.YES)
                    ],
                    onCompleted(selecteds: ibas.IList<bo.Position>): void {
                        item.position = selecteds.firstOrDefault().code;
                        that.view.refreshViewModel();
                    }
                });
            }
        }
        /** 视图-岗位 */
        export interface IPostEditView extends ibas.IBOEditView {
            /** 显示数据 */
            showPost(data: bo.Post): void;
            /** 删除数据事件 */
            deleteDataEvent: Function;
            /** 新建数据事件，参数1：是否克隆 */
            createDataEvent: Function;
            /** 添加岗位事件 */
            addPostEvent: Function;
            /** 删除岗位事件 */
            removePostEvent: Function;
            /** 选择用户事件 */
            chooseUserEvent: Function;
            /** 选择职位事件 */
            choosePositionEvent: Function;
            /** 刷新页面model */
            refreshViewModel(): void;
        }
    }
}