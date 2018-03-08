/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace bo {

        /** 业务仓库 */
        export interface IBORepositoryOrganizedPosts extends ibas.IBORepositoryApplication {

            /**
             * 查询 数据权限
             * @param fetcher 查询者
             */
            fetchOwnership(fetcher: ibas.IFetchCaller<bo.IOwnership>): void;
            /**
             * 保存 数据权限
             * @param saver 保存者
             */
            saveOwnership(saver: ibas.ISaveCaller<bo.IOwnership>): void;

            /**
             * 查询 职位
             * @param fetcher 查询者
             */
            fetchPosition(fetcher: ibas.IFetchCaller<bo.IPosition>): void;
            /**
             * 保存 职位
             * @param saver 保存者
             */
            savePosition(saver: ibas.ISaveCaller<bo.IPosition>): void;

            /**
             * 查询 岗位
             * @param fetcher 查询者
             */
            fetchPost(fetcher: ibas.IFetchCaller<bo.IPost>): void;
            /**
             * 保存 岗位
             * @param saver 保存者
             */
            savePost(saver: ibas.ISaveCaller<bo.IPost>): void;
        }
    }

}
