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
             * 编辑视图-数据权限
             */
            export class OwnershipEditView extends ibas.BOEditView implements app.IOwnershipEditView {
                /** 删除数据事件 */
                deleteDataEvent: Function;
                /** 新建数据事件，参数1：是否克隆 */
                createDataEvent: Function;
                /** 添加数据权限项事件 */
                addOwnershipItemEvent: Function;
                /** 删除数据权限项事件 */
                removeOwnershipItemEvent: Function;
                /** 选择用户事件 */
                chooseUserEvent: Function;
                /** 选择业务对象事件 */
                chooseBusinessObjectEvent: Function;
                /** 绘制视图 */
                draw(): any {
                    let that: this = this;
                    this.form = new sap.ui.layout.form.SimpleForm("", {
                        editable: true, // 编辑模式影响行高
                        content: [
                            new sap.ui.core.Title("", { text: ibas.i18n.prop("organizedposts_title_general") }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_usercode") }),
                            new sap.m.Input("", {
                                type: sap.m.InputType.Text,
                                showValueHelp: true,
                                valueHelpOnly: true,
                                valueHelpRequest: function (): void {
                                    that.fireViewEvents(that.chooseUserEvent);
                                }
                            }).bindProperty("value", {
                                path: "/userCode"
                            }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_bocode") }),
                            new sap.m.Input("", {
                                type: sap.m.InputType.Text,
                                showValueHelp: true,
                                valueHelpOnly: true,
                                valueHelpRequest: function (): void {
                                    that.fireViewEvents(that.chooseBusinessObjectEvent);
                                }
                            }).bindProperty("value", {
                                path: "/boCode"
                            }),

                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_defaultpermission") }),
                            new sap.m.Select("", {
                                items: openui5.utils.createComboBoxItems(ibas.emAuthoriseType)
                            }).bindProperty("selectedKey", {
                                path: "/defaultPermission",
                                type: "sap.ui.model.type.Integer"
                            }),
                            new sap.ui.core.Title("", { text: ibas.i18n.prop("organizedposts_title_others") }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_objectkey") }),
                            new sap.m.Input("", {
                                enabled: false,
                                type: sap.m.InputType.Text
                            }).bindProperty("value", {
                                path: "/objectKey"
                            }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_objectcode") }),
                            new sap.m.Input("", {
                                enabled: false,
                                type: sap.m.InputType.Text
                            }).bindProperty("value", {
                                path: "/objectCode"
                            }),
                        ]
                    });
                    this.form.addContent(new sap.ui.core.Title("", { text: ibas.i18n.prop("bo_ownershipitem") }));
                    this.tableOwnershipItem = new sap.ui.table.Table("", {
                        toolbar: new sap.m.Toolbar("", {
                            content: [
                                new sap.m.Button("", {
                                    text: ibas.i18n.prop("shell_data_add"),
                                    type: sap.m.ButtonType.Transparent,
                                    icon: "sap-icon://add",
                                    press: function (): void {
                                        that.fireViewEvents(that.addOwnershipItemEvent);
                                    }
                                }),
                                new sap.m.Button("", {
                                    text: ibas.i18n.prop("shell_data_remove"),
                                    type: sap.m.ButtonType.Transparent,
                                    icon: "sap-icon://less",
                                    press: function (): void {
                                        that.fireViewEvents(that.removeOwnershipItemEvent,
                                            // 获取表格选中的对象
                                            openui5.utils.getSelecteds<bo.OwnershipItem>(that.tableOwnershipItem)
                                        );
                                    }
                                })
                            ]
                        }),
                        enableSelectAll: false,
                        selectionBehavior: sap.ui.table.SelectionBehavior.Row,
                        visibleRowCount: ibas.config.get(openui5.utils.CONFIG_ITEM_LIST_TABLE_VISIBLE_ROW_COUNT, 10),
                        rows: "{/rows}",
                        columns: [
                            new sap.ui.table.Column("", {
                                label: ibas.i18n.prop("bo_ownershipitem_itemsign"),
                                template: new sap.m.Select("", {
                                    width: "100%",
                                    items: openui5.utils.createComboBoxItems(bo.emOwnershipSign)
                                }).bindProperty("selectedKey", {
                                    path: "itemSign",
                                    type: "sap.ui.model.type.Integer"
                                })
                            }),
                            new sap.ui.table.Column("", {
                                label: ibas.i18n.prop("bo_ownershipitem_permission"),
                                template: new sap.m.Select("", {
                                    width: "100%",
                                    items: openui5.utils.createComboBoxItems(ibas.emAuthoriseType)
                                }).bindProperty("selectedKey", {
                                    path: "permission",
                                    type: "sap.ui.model.type.Integer"
                                })
                            }),
                        ]
                    });
                    this.form.addContent(this.tableOwnershipItem);
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
                /** 改变视图状态 */
                private changeViewStatus(data: bo.Ownership): void {
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
                private tableOwnershipItem: sap.ui.table.Table;

                /** 显示数据 */
                showOwnership(data: bo.Ownership): void {
                    this.form.setModel(new sap.ui.model.json.JSONModel(data));
                    // 监听属性改变，并更新控件
                    openui5.utils.refreshModelChanged(this.form, data);
                    // 改变视图状态
                    this.changeViewStatus(data);
                }
                /** 显示数据 */
                showOwnershipItems(datas: bo.OwnershipItem[]): void {
                    this.tableOwnershipItem.setModel(new sap.ui.model.json.JSONModel({ rows: datas }));
                    // 监听属性改变，并更新控件
                    openui5.utils.refreshModelChanged(this.tableOwnershipItem, datas);
                }
            }
        }
    }
}