/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import {
    FetchCaller,
    SaveCaller,
    IBORepositoryApplication
} from "ibas/index";
import * as bo from "./bo/index"

/** 业务仓库 */
export interface IBORepositoryOrganizedPosts extends IBORepositoryApplication {

    /**
     * 查询 数据权限
     * @param fetcher 查询者
     */
    fetchOwnership(fetcher: FetchCaller<bo.IOwnership>);
    /**
     * 保存 数据权限
     * @param saver 保存者
     */
    saveOwnership(saver: SaveCaller<bo.IOwnership>);

    /**
     * 查询 职位
     * @param fetcher 查询者
     */
    fetchPosition(fetcher: FetchCaller<bo.IPosition>);
    /**
     * 保存 职位
     * @param saver 保存者
     */
    savePosition(saver: SaveCaller<bo.IPosition>);

    /**
     * 查询 岗位
     * @param fetcher 查询者
     */
    fetchPost(fetcher: FetchCaller<bo.IPost>);
    /**
     * 保存 岗位
     * @param saver 保存者
     */
    savePost(saver: SaveCaller<bo.IPost>);


}
