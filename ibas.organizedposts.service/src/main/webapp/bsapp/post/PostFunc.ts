/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import { PostListApp } from "./PostListApp";

export class PostFunc extends ibas.ModuleFunction {

    /** 功能标识 */
    static FUNCTION_ID = "b8ef2748-c32a-45ee-83ad-064efbf95394";
    /** 功能名称 */
    static FUNCTION_NAME = "organizedposts_func_post";
    /** 构造函数 */
    constructor() {
        super();
        this.id = PostFunc.FUNCTION_ID;
        this.name = PostFunc.FUNCTION_NAME;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 默认功能 */
    default(): ibas.IApplication<ibas.IView> {
        let app: PostListApp = new PostListApp();
        app.navigation = this.navigation;
        return app;
    }
}
