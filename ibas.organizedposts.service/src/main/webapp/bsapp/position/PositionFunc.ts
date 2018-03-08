/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace app {
        export class PositionFunc extends ibas.ModuleFunction {
            /** 功能标识 */
            static FUNCTION_ID = "24776629-0c17-4239-a65b-09c0487d3220";
            /** 功能名称 */
            static FUNCTION_NAME = "organizedposts_func_position";
            /** 构造函数 */
            constructor() {
                super();
                this.id = PositionFunc.FUNCTION_ID;
                this.name = PositionFunc.FUNCTION_NAME;
                this.description = ibas.i18n.prop(this.name);
            }
            /** 默认功能 */
            default(): ibas.IApplication<ibas.IView> {
                let app: PositionListApp = new PositionListApp();
                app.navigation = this.navigation;
                return app;
            }
        }
    }
}