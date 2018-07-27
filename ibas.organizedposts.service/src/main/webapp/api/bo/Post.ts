/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace bo {

        /** 岗位 */
        export interface IPost extends ibas.IBOSimple {

            /** 编码 */
            code: string;

            /** 名称 */
            name: string;

            /** 上级岗位 */
            belonging: number;

            /** 职位 */
            position: string;

            /** 用户 */
            userCode: string;

            /** 生效日期 */
            validDate: Date;

            /** 失效日期 */
            invalidDate: Date;

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

            /** 数据源 */
            dataSource: string;

            /** 实例号（版本） */
            logInst: number;

            /** 服务系列 */
            series: number;

            /** 创建用户 */
            createUserSign: number;

            /** 修改用户 */
            updateUserSign: number;

            /** 创建动作标识 */
            createActionId: string;

            /** 更新动作标识 */
            updateActionId: string;



            /** 岗位 */
            posts: IPosts;

        }


        /** 岗位 集合 */
        export interface IPosts extends ibas.IBusinessObjects<IPost> {

            /** 创建并添加子项 */
            create(): IPost;
        }

    }
}
