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
                    let formTop: sap.ui.layout.form.SimpleForm = new sap.ui.layout.form.SimpleForm("", {
                        editable: true,
                        content: [
                            new sap.ui.core.Title("", { text: ibas.i18n.prop("organizedposts_title_general") }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_usercode") }),
                            new sap.extension.m.RepositoryInput("", {
                                showValueHelp: true,
                                repository: initialfantasy.bo.BO_REPOSITORY_INITIALFANTASY,
                                dataInfo: {
                                    type: ibas.boFactory.classOf(initialfantasy.bo.User.BUSINESS_OBJECT_CODE),
                                    key: "Code",
                                    text: "Name"
                                },
                                valueHelpRequest: function (): void {
                                    that.fireViewEvents(that.chooseUserEvent);
                                }
                            }).bindProperty("bindingValue", {
                                path: "userCode",
                                type: new sap.extension.data.Alphanumeric({
                                    maxLength: 8
                                })
                            }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_bocode") }),
                            new sap.extension.m.RepositoryInput("", {
                                showValueHelp: true,
                                repository: initialfantasy.bo.BO_REPOSITORY_INITIALFANTASY,
                                dataInfo: {
                                    type: ibas.boFactory.classOf(initialfantasy.bo.BOInformation.BUSINESS_OBJECT_CODE),
                                    key: "Code",
                                    text: "Description"
                                },
                                valueHelpRequest: function (): void {
                                    that.fireViewEvents(that.chooseBusinessObjectEvent);
                                }
                            }).bindProperty("bindingValue", {
                                path: "boCode",
                                type: new sap.extension.data.Alphanumeric({
                                    maxLength: 30
                                })
                            }),
                            new sap.m.Label("", { text: ibas.i18n.prop("bo_ownership_defaultpermission") }),
                            new sap.extension.m.EnumSelect("", {
                                enumType: ibas.emAuthoriseType
                            }).bindProperty("bindingValue", {
                                path: "defaultPermission",
                                type: new sap.extension.data.AuthoriseType()
                            }),
                            new sap.ui.core.Title("", {}),
                        ]
                    });
                    let formOwnershipItem: sap.ui.layout.form.SimpleForm = new sap.ui.layout.form.SimpleForm("", {
                        editable: true,
                        content: [
                            new sap.ui.core.Title("", { text: ibas.i18n.prop("bo_ownershipitem") }),
                            this.tableOwnershipItem = new sap.extension.table.DataTable("", {
                                enableSelectAll: false,
                                visibleRowCount: sap.extension.table.visibleRowCount(8),
                                dataInfo: {
                                    code: bo.Ownership.BUSINESS_OBJECT_CODE,
                                    name: bo.OwnershipItem.name
                                },
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
                                                that.fireViewEvents(that.removeOwnershipItemEvent, that.tableOwnershipItem.getSelecteds());
                                            }
                                        })
                                    ]
                                }),
                                rows: "{/rows}",
                                columns: [
                                    new sap.extension.table.DataColumn("", {
                                        label: ibas.i18n.prop("bo_ownershipitem_itemsign"),
                                        template: new sap.extension.m.EnumSelect("", {
                                            enumType: bo.emOwnershipSign
                                        }).bindProperty("bindingValue", {
                                            path: "itemSign",
                                            type: new sap.extension.data.Enum({
                                                enumType: bo.emOwnershipSign
                                            })
                                        }),
                                    }),
                                    new sap.extension.table.DataColumn("", {
                                        label: ibas.i18n.prop("bo_ownershipitem_permission"),
                                        template: new sap.extension.m.EnumSelect("", {
                                            enumType: ibas.emAuthoriseType
                                        }).bindProperty("bindingValue", {
                                            path: "permission",
                                            type: new sap.extension.data.AuthoriseType()
                                        }),
                                    }),
                                ]
                            })]
                    });
                    return this.page = new sap.extension.m.DataPage("", {
                        showHeader: false,
                        dataInfo: {
                            code: bo.Ownership.BUSINESS_OBJECT_CODE,
                        },
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
                        content: [
                            formTop,
                            formOwnershipItem,
                        ]
                    });
                }

                private page: sap.extension.m.Page;
                private tableOwnershipItem: sap.extension.table.Table;

                /** 显示数据 */
                showOwnership(data: bo.Ownership): void {
                    this.page.setModel(new sap.extension.model.JSONModel(data));
                    // 改变页面状态
                    sap.extension.pages.changeStatus(this.page);
                }
                /** 显示数据-数据权限项 */
                showOwnershipItems(datas: bo.OwnershipItem[]): void {
                    this.tableOwnershipItem.setModel(new sap.extension.model.JSONModel({ rows: datas }));
                }
            }
        }
    }
}