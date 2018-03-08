/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace bo {

        /** 数据权限 */
        export interface IOwnership extends ibas.IBOSimple {

            /** 用户编码 */
            userCode: string;

            /** 对象编码 */
            boCode: string;

            /** 默认数据权限 */
            defaultPermission: ibas.emAuthoriseType;

            /** 激活 */
            activated: ibas.emYesNo;

            /** 对象编号 */
            objectKey: number;

            /** 对象类型 */
            objectCode: string;

            /** 创建日期 */
            createDate: Date;

            /** 创建时间 */
            createTime: number;

            /** 修改日期 */
            updateDate: Date;

            /** 修改时间 */
            updateTime: number;

            /** 实例号（版本） */
            logInst: number;

            /** 服务系列 */
            series: number;

            /** 数据源 */
            dataSource: string;

            /** 创建用户 */
            createUserSign: number;

            /** 修改用户 */
            updateUserSign: number;

            /** 创建动作标识 */
            createActionId: string;

            /** 更新动作标识 */
            updateActionId: string;


            /** 数据权限项集合 */
            ownershipItems: IOwnershipItems;


        }

        /** 数据权限项 集合 */
        export interface IOwnershipItems extends ibas.IBusinessObjects<IOwnershipItem> {

            /** 创建并添加子项 */
            create(): IOwnershipItem;
        }

        /** 数据权限项 */
        export interface IOwnershipItem extends ibas.IBOSimpleLine {

            /** 编号 */
            objectKey: number;

            /** 类型 */
            objectCode: string;

            /** 行号 */
            lineId: number;

            /** 实例号（版本） */
            logInst: number;

            /** 数据源 */
            dataSource: string;

            /** 创建日期 */
            createDate: Date;

            /** 创建时间 */
            createTime: number;

            /** 修改日期 */
            updateDate: Date;

            /** 修改时间 */
            updateTime: number;

            /** 创建用户 */
            createUserSign: number;

            /** 修改用户 */
            updateUserSign: number;

            /** 创建动作标识 */
            createActionId: string;

            /** 更新动作标识 */
            updateActionId: string;

            /** 参考1 */
            reference1: string;

            /** 参考2 */
            reference2: string;

            /** 数据权限项标记 */
            itemSign: emOwnershipSign;

            /** 数据权限项描述 */
            itemDescription: string;

            /** 数据权限 */
            permission: ibas.emAuthoriseType;

            /** 优先级 */
            priority: number;

            /** 参数 */
            parameter: string;


        }
    }
}

