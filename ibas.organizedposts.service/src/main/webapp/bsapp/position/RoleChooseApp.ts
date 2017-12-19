/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import { BO_CODE_ROLE, IRole } from "3rdparty/initialfantasy/index";
import { PositionChooseApp } from "./PositionChooseApp";

/** 角色选择服务（用职位实现） */
export class RoleChooseApp extends PositionChooseApp {
    /** 应用标识 */
    static APPLICATION_ID: string = "0e37fb14-776a-4c56-bcc7-86e5e2d7a5c3";
    /** 应用名称 */
    static APPLICATION_NAME: string = "organizedposts_app_role_choose";
    /** 业务对象编码 */
    static BUSINESS_OBJECT_CODE: string = BO_CODE_ROLE;

    /** 构造函数 */
    constructor() {
        super();
        this.id = PositionChooseApp.APPLICATION_ID;// 使用职位
        this.name = RoleChooseApp.APPLICATION_NAME;
        this.boCode = RoleChooseApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
}
/** 角色选择服务映射 */
export class RoleChooseServiceMapping extends ibas.BOChooseServiceMapping {
    /** 构造函数 */
    constructor() {
        super();
        this.id = RoleChooseApp.APPLICATION_ID;
        this.name = RoleChooseApp.APPLICATION_NAME;
        this.boCode = RoleChooseApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 创建服务并运行 */
    create(): ibas.IBOChooseService<IRole> {
        return new RoleChooseApp();
    }
}
