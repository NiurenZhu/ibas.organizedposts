/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
/// <reference path="../../3rdparty/ibas/index.d.ts" />
/// <reference path="../../3rdparty/openui5/index.d.ts" />
/// <reference path="../../index.d.ts" />
/// <reference path="./ownership/index.ts" />
/// <reference path="./position/index.ts" />
/// <reference path="./post/index.ts" />
namespace organizedposts {
    export namespace ui {
        /**
         * 视图导航
         */
        export class Navigation extends ibas.ViewNavigation {
            /**
             * 创建实例
             * @param id 应用id
             */
            protected newView(id: string): ibas.IView {
                let view: ibas.IView = null;
                switch (id) {
                    case app.OwnershipListApp.APPLICATION_ID:
                        view = new c.OwnershipListView();
                        break;
                    case app.OwnershipChooseApp.APPLICATION_ID:
                        view = new c.OwnershipChooseView();
                        break;
                    case app.OwnershipEditApp.APPLICATION_ID:
                        view = new c.OwnershipEditView();
                        break;
                    case app.PositionListApp.APPLICATION_ID:
                        view = new c.PositionListView();
                        break;
                    case app.PositionChooseApp.APPLICATION_ID:
                        view = new c.PositionChooseView();
                        break;
                    case app.PositionEditApp.APPLICATION_ID:
                        view = new c.PositionEditView();
                        break;
                    case app.PostListApp.APPLICATION_ID:
                        view = new c.PostListView();
                        break;
                    case app.PostChooseApp.APPLICATION_ID:
                        view = new c.PostChooseView();
                        break;
                    case app.PostEditApp.APPLICATION_ID:
                        view = new c.PostEditView();
                        break;
                    default:
                        break;
                }
                return view;
            }
        }
    }
}