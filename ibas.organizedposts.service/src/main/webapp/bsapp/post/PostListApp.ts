/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "../../borep/bo/index";
import { BORepositoryOrganizedPosts } from "../../borep/BORepositories";
import { DataConverter4op } from "../../borep/DataConverters";
import { PostViewApp } from "./PostViewApp";
import { PostEditApp } from "./PostEditApp";

/** 列表应用-岗位 */
export class PostListApp extends ibas.BOListApplication<IPostListView, bo.Post> {

    /** 应用标识 */
    static APPLICATION_ID: string = "9d24a446-dbc6-46fd-94ce-0cfe1455cafa";
    /** 应用名称 */
    static APPLICATION_NAME: string = "organizedposts_app_post_list";
    /** 业务对象编码 */
    static BUSINESS_OBJECT_CODE: string = bo.Post.BUSINESS_OBJECT_CODE;
    /** 构造函数 */
    constructor() {
        super();
        this.id = PostListApp.APPLICATION_ID;
        this.name = PostListApp.APPLICATION_NAME;
        this.boCode = PostListApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 注册视图 */
    protected registerView(): void {
        super.registerView();
        // 其他事件
        this.view.editDataEvent = this.editData;
        this.view.deleteDataEvent = this.deleteData;
    }
    /** 启动应用 */
    run(...args: any[]): void {
        let criteria: ibas.ICriteria = arguments[0];
        if (ibas.objects.isNull(criteria)) {
            criteria = new ibas.Criteria();
            let condition: ibas.ICondition = criteria.conditions.create();
            condition.alias = bo.Post.PROPERTY_BELONGING_NAME;
            condition.value = "-1";
        }
        if (ibas.objects.instanceOf(criteria, ibas.Criteria)) {
            // 传入了查询，则使用
            this.view.query(criteria);
        } else {
            super.run();
        }
    }
    /** 视图显示后 */
    protected viewShowed(): void {
        // 视图加载完成
    }
    /** 查询数据 */
    protected fetchData(criteria: ibas.ICriteria): void {
        this.busy(true);
        let that: this = this;
        let boRepository: BORepositoryOrganizedPosts = new BORepositoryOrganizedPosts();
        boRepository.fetchPost({
            criteria: criteria,
            onCompleted(opRslt: ibas.IOperationResult<bo.Post>): void {
                try {
                    if (opRslt.resultCode !== 0) {
                        throw new Error(opRslt.message);
                    }
                    if (!that.isViewShowed()) {
                        that.show();
                    }
                    that.view.showData(opRslt.resultObjects);
                    that.busy(false);
                } catch (error) {
                    that.messages(error);
                }
            }
        });
        this.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("shell_fetching_data"));
    }
    /** 新建数据 */
    protected newData(): void {
        let app: PostEditApp = new PostEditApp();
        app.navigation = this.navigation;
        app.viewShower = this.viewShower;
        app.run();
    }
    /** 查看数据，参数：目标数据 */
    protected viewData(data: bo.Post): void {
        // 检查目标数据
        if (ibas.objects.isNull(data)) {
            this.messages(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_please_chooose_data",
                ibas.i18n.prop("shell_data_view")
            ));
            return;
        }
        let app: PostViewApp = new PostViewApp();
        app.navigation = this.navigation;
        app.viewShower = this.viewShower;
        app.run(data);

    }
    /** 编辑数据，参数：目标数据 */
    protected editData(data: bo.Post): void {
        // 检查目标数据
        if (ibas.objects.isNull(data)) {
            this.messages(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_please_chooose_data",
                ibas.i18n.prop("shell_data_edit")
            ));
            return;
        }
        let app: PostEditApp = new PostEditApp();
        app.navigation = this.navigation;
        app.viewShower = this.viewShower;
        app.run(data);
    }
    /** 删除数据，参数：目标数据集合 */
    protected deleteData(data: bo.Post): void {
        // 检查目标数据
        if (ibas.objects.isNull(data)) {
            this.messages(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_please_chooose_data",
                ibas.i18n.prop("shell_data_delete")
            ));
            return;
        }
        let beDeleteds: ibas.ArrayList<bo.Post> = new ibas.ArrayList<bo.Post>();
        if (data instanceof Array) {
            for (let item of data) {
                if (ibas.objects.instanceOf(item, bo.Post)) {
                    item.delete();
                    beDeleteds.add(item);
                }
            }
        }
        // 没有选择删除的对象
        if (beDeleteds.length === 0) {
            return;
        }
        let that: this = this;
        this.messages({
            type: ibas.emMessageType.QUESTION,
            title: ibas.i18n.prop(this.name),
            message: ibas.i18n.prop("shell_whether_to_delete", beDeleteds.length),
            actions: [ibas.emMessageAction.YES, ibas.emMessageAction.NO],
            onCompleted(action: ibas.emMessageAction): void {
                if (action === ibas.emMessageAction.YES) {
                    try {
                        let boRepository: BORepositoryOrganizedPosts = new BORepositoryOrganizedPosts();
                        let saveMethod: Function = function (beSaved: bo.Post): void {
                            boRepository.savePost({
                                beSaved: beSaved,
                                onCompleted(opRslt: ibas.IOperationResult<bo.Post>): void {
                                    try {
                                        if (opRslt.resultCode !== 0) {
                                            throw new Error(opRslt.message);
                                        }
                                        // 保存下一个数据
                                        let index: number = beDeleteds.indexOf(beSaved) + 1;
                                        if (index > 0 && index < beDeleteds.length) {
                                            saveMethod(beDeleteds[index]);
                                        } else {
                                            // 处理完成
                                            that.busy(false);
                                            that.messages(ibas.emMessageType.SUCCESS,
                                                ibas.i18n.prop("shell_data_delete") + ibas.i18n.prop("shell_sucessful"));
                                        }
                                    } catch (error) {
                                        that.messages(ibas.emMessageType.ERROR,
                                            ibas.i18n.prop("shell_data_delete_error", beSaved, error.message));
                                    }
                                }
                            });
                            that.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("shell_data_deleting", beSaved));
                        };
                        that.busy(true);
                        // 开始保存
                        saveMethod(beDeleteds.firstOrDefault());
                    } catch (error) {
                        that.busy(false);
                        that.messages(error);
                    }
                }
            }
        });
    }
    /** 获取服务的契约 */
    protected getServiceProxies(): ibas.IServiceProxy<ibas.IServiceContract>[] {
        return [
            new ibas.BOListServiceProxy({
                data: this.view.getSelecteds(),
                converter: new DataConverter4op()
            })
        ];
    }
}
/** 视图-岗位 */
export interface IPostListView extends ibas.IBOListView {
    /** 编辑数据事件，参数：编辑对象 */
    editDataEvent: Function;
    /** 删除数据事件，参数：删除对象集合 */
    deleteDataEvent: Function;
    /** 显示数据 */
    showData(datas: bo.Post[]): void;
    /** 获取选择的数据 */
    getSelecteds(): bo.Post[];
}
