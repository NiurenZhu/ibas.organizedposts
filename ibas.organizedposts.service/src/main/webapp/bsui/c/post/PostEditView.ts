/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as openui5 from "openui5/index";
import * as bo from "../../../borep/bo/index";
import { IPostEditView } from "../../../bsapp/post/index";

/**
 * 编辑视图-岗位
 */
export class PostEditView extends ibas.BOEditView implements IPostEditView {
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

    /** 绘制视图 */
    darw(): any {
        let that: this = this;
        this.table = new sap.ui.table.TreeTable("", {
            extension: new sap.m.Toolbar("", {
                content: [
                    new sap.m.ToolbarSpacer("", {}),
                    new sap.m.Label("", {
                        wrapping: true,
                        textAlign: "Right",
                        text: ibas.i18n.prop("bo_post_validdate")
                    }),
                    new sap.m.DatePicker("", {
                        width: "15%",
                    }).bindProperty("dateValue", {
                        path: "/Posts/0/validDate"
                    }),
                    new sap.m.Label("", {
                        wrapping: true,
                        textAlign: "Right",
                        text: ibas.i18n.prop("bo_post_invaliddate")
                    }),
                    new sap.m.DatePicker("", {
                        width: "15%",
                    }).bindProperty("dateValue", {
                        path: "/Posts/0/invalidDate"
                    }),
                ]
            }),
            expandFirstLevel: true,
            enableSelectAll: false,
            rowActionCount: 2,
            selectionBehavior: sap.ui.table.SelectionBehavior.Row,
            selectionMode: sap.ui.table.SelectionMode.Single,
            visibleRowCount: sap.ui.table.VisibleRowCountMode.Auto,
            visibleRowCountMode: sap.ui.table.VisibleRowCountMode.Auto,
            rowActionTemplate: new sap.ui.table.RowAction({
                items: [
                    new sap.ui.table.RowActionItem({
                        type: "Custom",
                        icon: "sap-icon://add",
                        text: ibas.i18n.prop("shell_data_add"),
                        press: function (oEvent: any): void {
                            that.fireViewEvents(that.addPostEvent,
                                // 获取绑定的对象
                                this.getBindingContext().getObject());
                        },
                    }),
                    new sap.ui.table.RowActionItem({
                        type: "Custom",
                        text: ibas.i18n.prop("shell_data_remove"),
                        icon: "sap-icon://delete",
                        press: function (oEvent: any): void {
                            that.fireViewEvents(that.removePostEvent,
                                // 获取绑定的对象
                                this.getBindingContext().getObject());
                        },
                    })
                ]
            }),
            rows: {
                path: "/",
                parameters: {
                    arrayNames: [
                        "Posts"
                    ]
                }
            },
            rowSettingsTemplate: new sap.ui.table.RowSettings("", {
            }).bindProperty("highlight", {
                path: "isDirty",
                formatter: function (value: boolean): sap.ui.core.MessageType {
                    if (value) {
                        return sap.ui.core.MessageType.Warning;
                    }
                    return sap.ui.core.MessageType.Success;
                }
            }),
            columns: [
                new sap.ui.table.Column("", {
                    label: ibas.i18n.prop("bo_post_postcode"),
                    template: new sap.m.Input("", {
                        width: "100%",
                        value: {
                            path: "postCode"
                        },
                    })
                }),
                new sap.ui.table.Column("", {
                    label: ibas.i18n.prop("bo_post_postname"),
                    template: new sap.m.Input("", {
                        width: "100%",
                        value: {
                            path: "postName"
                        },
                    })
                }),
                new sap.ui.table.Column("", {
                    label: ibas.i18n.prop("bo_post_position"),
                    template: new sap.m.Input("", {
                        width: "100%",
                        value: {
                            path: "position"
                        },
                        showValueHelp: true,
                        valueHelpOnly: true,
                        valueHelpRequest: function (): void {
                            that.fireViewEvents(that.choosePositionEvent,
                                // 获取绑定的对象
                                this.getBindingContext().getObject());
                        }
                    })
                }),
                new sap.ui.table.Column("", {
                    label: ibas.i18n.prop("bo_post_usercode"),
                    template: new sap.m.Input("", {
                        width: "100%",
                        value: {
                            path: "userCode"
                        },
                        showValueHelp: true,
                        valueHelpOnly: true,
                        valueHelpRequest: function (): void {
                            that.fireViewEvents(that.chooseUserEvent,
                                // 获取绑定的对象
                                this.getBindingContext().getObject());
                        }
                    })
                }),
            ],
        });
        this.page = new sap.m.Page("", {
            showHeader: false,
            subHeader: new sap.m.Toolbar("", {
                content: [
                    new sap.m.Button("", {
                        text: ibas.i18n.prop("shell_data_save"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://save",
                        press: function (): void {
                            that.fireViewEvents(that.saveDataEvent);
                        }
                    }),
                    new sap.m.Button("", {
                        text: ibas.i18n.prop("shell_data_delete"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://delete",
                        press: function (): void {
                            that.fireViewEvents(that.deleteDataEvent);
                        }
                    }),
                    new sap.m.ToolbarSeparator(""),
                    new sap.m.MenuButton("", {
                        text: ibas.i18n.prop("shell_data_new"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://create",
                        buttonMode: sap.m.MenuButtonMode.Split,
                        defaultAction: function (): void {
                            // 触发新建对象
                            that.fireViewEvents(that.createDataEvent, false);
                        },
                        menu: new sap.m.Menu("", {
                            items: [
                                new sap.m.MenuItem("", {
                                    text: ibas.i18n.prop("shell_data_new"),
                                    icon: "sap-icon://create"
                                }),
                                new sap.m.MenuItem("", {
                                    text: ibas.i18n.prop("shell_data_clone"),
                                    icon: "sap-icon://copy"
                                }),
                            ],
                            itemSelected: function (event: any): void {
                                let item: any = event.getParameter("item");
                                if (item instanceof sap.m.MenuItem) {
                                    if (item.getIcon() === "sap-icon://copy") {
                                        // 触发克隆对象
                                        that.fireViewEvents(that.createDataEvent, true);
                                    } else {
                                        // 触发新建对象
                                        that.fireViewEvents(that.createDataEvent, false);
                                    }
                                }
                            }
                        })
                    }),
                ]
            }),
            content: [this.table]
        });
        this.id = this.page.getId();
        return this.page;
    }
    private page: sap.m.Page;
    private table: sap.ui.table.TreeTable;
    /** 改变视图状态 */
    private changeViewStatus(data: bo.Post): void {
        if (ibas.objects.isNull(data)) {
            return;
        }
        // 新建时：禁用删除，
        if (data.isNew) {
            if (this.page.getSubHeader() instanceof sap.m.Toolbar) {
                openui5.utils.changeToolbarDeletable(<sap.m.Toolbar>this.page.getSubHeader(), false);
            }
        }
    }

    /** 显示数据 */
    showPost(data: bo.Post): void {

        this.table.setModel(new sap.ui.model.json.JSONModel({ Posts: [data] }));
        let binding: sap.ui.model.Binding = this.table.getBinding("rows");
        if (!ibas.objects.isNull(binding)) {
            if (binding instanceof sap.ui.model.TreeBinding) {
                binding.filter(
                    [
                        new sap.ui.model.Filter("isDeleted", sap.ui.model.FilterOperator.EQ, false)
                    ],
                    sap.ui.model.FilterType.Control
                );
            }
        }
        // 监听属性改变，并更新控件
        openui5.utils.refreshModelChanged(this.table, data);
        // 改变视图状态
        this.changeViewStatus(data);
    }
}
