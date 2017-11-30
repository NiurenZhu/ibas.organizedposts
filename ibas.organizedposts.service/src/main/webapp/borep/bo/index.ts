/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 模块索引文件，此文件集中导出类
export * from "../../api/Datas";
export * from "./Ownership";
export * from "./Position";
export * from "./Post";

// 注册业务对象到工厂
import * as ibas from "ibas/index";
import { Ownership } from "./Ownership";
ibas.boFactory.register(Ownership.BUSINESS_OBJECT_CODE, Ownership);
import { Position } from "./Position";
ibas.boFactory.register(Position.BUSINESS_OBJECT_CODE, Position);
import { Post } from "./Post";
ibas.boFactory.register(Post.BUSINESS_OBJECT_CODE, Post);
