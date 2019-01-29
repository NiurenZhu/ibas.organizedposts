/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace ui {
        export namespace c {
            /**
             * 编辑视图-岗位
             */
            export class PostEditView extends ibas.BOEditView implements app.IPostEditView {
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
                draw(): any {
                    let that: this = this;
                    this.form = new sap.ui.layout.form.SimpleForm("", {});
                    this.toolbar = new sap.m.Toolbar("", {
                        content: [
                            // new sap.m.ToolbarSpacer("", {}),
                            new sap.m.Label("", {
                                wrapping: true,
                                textAlign: "Right",
                                text: ibas.i18n.prop("bo_post_validdate")
                            }),
                            new sap.m.DatePicker("", {
                                width: "15%",
                            }).bindProperty("dateValue", {
                                path: "/Posts/0/validDate",
                                type: new openui5.datatype.DateTime({
                                    description: ibas.i18n.prop("bo_post_validdate"),
                                })
                            }),
                            new sap.m.Label("", {
                                wrapping: true,
                                textAlign: "Right",
                                text: ibas.i18n.prop("bo_post_invaliddate")
                            }),
                            new sap.m.DatePicker("", {
                                width: "15%",
                            }).bindProperty("dateValue", {
                                path: "/Posts/0/invalidDate",
                                type: new openui5.datatype.DateTime({
                                    description: ibas.i18n.prop("bo_post_invaliddate")
                                })
                            }),
                            new sap.m.ToolbarSpacer("", {}),
                        ]
                    });
                    this.table = new sap.ui.table.TreeTable("", {
                        toolbar: this.toolbar,
                        expandFirstLevel: true,
                        enableSelectAll: false,
                        selectionBehavior: sap.ui.table.SelectionBehavior.Row,
                        rowActionCount: 2,
                        selectionMode: sap.ui.table.SelectionMode.Single,
                        visibleRowCount: ibas.config.get(openui5.utils.CONFIG_ITEM_ITEM_TABLE_VISIBLE_ROW_COUNT, 10),
                        visibleRowCountMode: sap.ui.table.VisibleRowCountMode.Fixed,
                        rowActionTemplate: new sap.ui.table.RowAction("", {
                            items: [
                                new sap.ui.table.RowActionItem("", {
                                    type: "Custom",
                                    icon: "sap-icon://add",
                                    text: ibas.i18n.prop("shell_data_add"),
                                    press: function (oEvent: any): void {
                                        that.fireViewEvents(that.addPostEvent,
                                            // 获取绑定的对象
                                            this.getBindingContext().getObject());
                                    },
                                }),
                                new sap.ui.table.RowActionItem("", {
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
                                label: ibas.i18n.prop("bo_post_code"),
                                template: new sap.m.Input("", {
                                    width: "100%",
                                    value: {
                                        path: "code",
                                        type: new openui5.datatype.Alphanumeric({
                                            description: ibas.i18n.prop("bo_post_code"),
                                            notEmpty: true,
                                            maxLength: 8
                                        })
                                    },
                                })
                            }),
                            new sap.ui.table.Column("", {
                                label: ibas.i18n.prop("bo_post_name"),
                                template: new sap.m.Input("", {
                                    width: "100%",
                                    value: {
                                        path: "name"
                                    },
                                })
                            }),
                            new sap.ui.table.Column("", {
                                label: ibas.i18n.prop("bo_post_position"),
                                template: new sap.m.Input("", {
                                    width: "100%",
                                    value: {
                                        path: "position",
                                        type: new openui5.datatype.Alphanumeric({
                                            description: ibas.i18n.prop("bo_post_position"),
                                            notEmpty: true,
                                            maxLength: 8
                                        })
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
                                        path: "userCode",
                                        type: new openui5.datatype.Alphanumeric({
                                            description: ibas.i18n.prop("bo_post_usercode"),
                                            notEmpty: true,
                                            maxLength: 8
                                        })
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
                    this.form.addContent(this.table);
                    this.page = new sap.m.Page("", {
                        showHeader: false,
                        subHeader: new sap.m.Toolbar("", {
                            content: [
                                new sap.m.Button("", {
                                    text: ibas.i18n.prop("shell_data_save"),
                                    type: sap.m.ButtonType.Transparent,
                                    icon: "sap-icon://save",
                                    press: function (): void {
                                        let tableValidateResult: openui5.datatype.ValidateResult =
                                            openui5.utils.validateControlBoundProperty(that.table);
                                        let toolbarValidateResult: openui5.datatype.ValidateResult =
                                            openui5.utils.validateControlBoundProperty(that.toolbar);
                                        if (!(tableValidateResult.status && toolbarValidateResult.status)) {
                                            // alert(validateResult.message);
                                            return;
                                        }
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
                                    text: ibas.strings.format("{0}/{1}",
                                        ibas.i18n.prop("shell_data_new"), ibas.i18n.prop("shell_data_clone")),
                                    icon: "sap-icon://create",
                                    type: sap.m.ButtonType.Transparent,
                                    menu: new sap.m.Menu("", {
                                        items: [
                                            new sap.m.MenuItem("", {
                                                text: ibas.i18n.prop("shell_data_new"),
                                                icon: "sap-icon://create",
                                                press: function (): void {
                                                    // 创建新的对象
                                                    that.fireViewEvents(that.createDataEvent, false);
                                                }
                                            }),
                                            new sap.m.MenuItem("", {
                                                text: ibas.i18n.prop("shell_data_clone"),
                                                icon: "sap-icon://copy",
                                                press: function (): void {
                                                    // 复制当前对象
                                                    that.fireViewEvents(that.createDataEvent, true);
                                                }
                                            }),
                                        ],
                                    })
                                }),
                            ]
                        }),
                        content: [this.form]
                    });
                    this.id = this.page.getId();
                    return this.page;
                }
                private page: sap.m.Page;
                private form: sap.ui.layout.form.SimpleForm;
                private table: sap.ui.table.TreeTable;
                private toolbar: sap.m.Toolbar;
                /** 改变视图状态 */
                private changeViewStatus(data: bo.Post): void {
                    if (ibas.objects.isNull(data)) {
                        return;
                    }
                    // 新建时：禁用删除，
                    if (data.isNew) {
                        if (this.page.getSubHeader() instanceof sap.m.Toolbar) {
                            openui5.utils.changeToolbarSavable(<sap.m.Toolbar>this.page.getSubHeader(), true);
                            openui5.utils.changeToolbarDeletable(<sap.m.Toolbar>this.page.getSubHeader(), false);
                        }
                    }
                }

                /** 显示数据 */
                showPost(data: bo.Post): void {
                    let model: sap.ui.model.Model = this.table.getModel(undefined);
                    if (!ibas.objects.isNull(model)) {
                        // 已显示过数据,直接刷新即可
                        model.refresh(false);
                    } else {
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
                    }
                    // 改变视图状态
                    this.changeViewStatus(data);
                }

                /** 刷新页面model */
                refreshViewModel(): void {
                    let model: sap.ui.model.Model = this.table.getModel(undefined);
                    if (!ibas.objects.isNull(model)) {
                        model.refresh(false);
                    }
                }
            }
        }
    }
}