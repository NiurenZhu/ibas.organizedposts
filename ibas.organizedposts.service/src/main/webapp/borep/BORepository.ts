/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedposts {
    export namespace bo {

        /** 业务对象仓库 */
        export class BORepositoryOrganizedPosts extends ibas.BORepositoryApplication implements IBORepositoryOrganizedPosts {

            /** 创建此模块的后端与前端数据的转换者 */
            protected createConverter(): ibas.IDataConverter {
                return new DataConverter;
            }

            /**
             * 查询 数据权限
             * @param fetcher 查询者
             */
            fetchOwnership(fetcher: ibas.IFetchCaller<bo.Ownership>): void {
                super.fetch(bo.Ownership.name, fetcher);
            }
            /**
             * 保存 数据权限
             * @param saver 保存者
             */
            saveOwnership(saver: ibas.ISaveCaller<bo.Ownership>): void {
                super.save(bo.Ownership.name, saver);
            }

            /**
             * 查询 职位
             * @param fetcher 查询者
             */
            fetchPosition(fetcher: ibas.IFetchCaller<bo.Position>): void {
                super.fetch(bo.Position.name, fetcher);
            }
            /**
             * 保存 职位
             * @param saver 保存者
             */
            savePosition(saver: ibas.ISaveCaller<bo.Position>): void {
                super.save(bo.Position.name, saver);
            }

            /**
             * 查询 岗位
             * @param fetcher 查询者
             */
            fetchPost(fetcher: ibas.IFetchCaller<bo.Post>): void {
                super.fetch(bo.Post.name, fetcher);
            }
            /**
             * 保存 岗位
             * @param saver 保存者
             */
            savePost(saver: ibas.ISaveCaller<bo.Post>): void {
                super.save(bo.Post.name, saver);
            }

        }
    }
}