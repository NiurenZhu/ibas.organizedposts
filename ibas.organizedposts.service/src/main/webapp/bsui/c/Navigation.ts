/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as ownershipApps from "../../bsapp/ownership/index";
import * as positionApps from "../../bsapp/position/index";
import * as postApps from "../../bsapp/post/index";
import * as ownershipViews from "./ownership/index";
import * as positionViews from "./position/index";
import * as postViews from "./post/index";

/**
 * 视图导航
 */
export default class Navigation extends ibas.ViewNavigation {

    /**
     * 创建实例
     * @param id 应用id
     */
    protected newView(id: string): ibas.IView {
        let view: ibas.IView = null;
        switch (id) {
            case ownershipApps.OwnershipListApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipListView();
                break;
            case ownershipApps.OwnershipChooseApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipChooseView();
                break;
            case ownershipApps.OwnershipEditApp.APPLICATION_ID:
                view = new ownershipViews.OwnershipEditView();
                break;
            case positionApps.PositionListApp.APPLICATION_ID:
                view = new positionViews.PositionListView();
                break;
            case positionApps.PositionChooseApp.APPLICATION_ID:
                view = new positionViews.PositionChooseView();
                break;
            case positionApps.PositionEditApp.APPLICATION_ID:
                view = new positionViews.PositionEditView();
                break;
            case postApps.PostListApp.APPLICATION_ID:
                view = new postViews.PostListView();
                break;
            case postApps.PostChooseApp.APPLICATION_ID:
                view = new postViews.PostChooseView();
                break;
            case postApps.PostEditApp.APPLICATION_ID:
                view = new postViews.PostEditView();
                break;
            default:
                break;
        }
        return view;
    }
}
