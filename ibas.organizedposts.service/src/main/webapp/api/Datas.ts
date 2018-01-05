/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 共享的数据
import {
	strings,
	MODULE_REPOSITORY_NAME_TEMPLATE,
} from "ibas/index";
/** 模块-标识 */
export const CONSOLE_ID: string = "d67cc3ce-2713-4de1-96d6-de884c1d68b3";
/** 模块-名称 */
export const CONSOLE_NAME: string = "OrganizedPosts";
/** 模块-版本 */
export const CONSOLE_VERSION: string = "0.1.0";
/** 配置值-组织方式 */
export const CONFIG_VALUE_ORGANIZATION_WAY: string = "post";
/** 配置值-数据权限方式 */
export const CONFIG_VALUE_OWNERSHIP_WAY: string = "post";
/** 业务仓库名称 */
export const BO_REPOSITORY_ORGANIZEDPOSTS: string = strings.format(MODULE_REPOSITORY_NAME_TEMPLATE, CONSOLE_NAME);
/** 业务对象编码-数据权限 */
export const BO_CODE_OWNERSHIP: string = "${Company}_OP_OWNERSHIP";
/** 业务对象编码-职位 */
export const BO_CODE_POSITION: string = "${Company}_OP_PSTN";
/** 业务对象编码-岗位 */
export const BO_CODE_POST: string = "${Company}_OP_POST";

/**
 * 所有权标记
 */
export enum emOwnershipSign {
    /**
     * 审批
     */
	APPROVAL,
	/**
	 * 同级
	 */
	EQUALLEVEL,
	/**
	 * 上级
	 */
	HIGHERLEVEL,
	/**
	 * 下级
	 */
	LOWERLEVEL,
	/**
	 * 其他
	 */
	OTHERS,
	/**
	 * 项目
	 */
	PROJECT,
	/**
	 * 自身
	 */
	SELF,
	/**
	 * 团队
	 */
	TEAMS,
}