/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
/// <reference path="../3rdparty/ibas/index.d.ts" />
/// <reference path="../3rdparty/initialfantasy/index.ts" />
/// <reference path="../api/index.ts" />
/// <reference path="./bo/Ownership.ts" />
/// <reference path="./bo/Position.ts" />
/// <reference path="./bo/Post.ts" />
/// <reference path="./DataConverter.ts" />
/// <reference path="./BORepository.ts" />

namespace organizedposts {
    export namespace bo {
        // 注册业务对象仓库到工厂
        ibas.boFactory.register(BO_REPOSITORY_ORGANIZEDPOSTS, BORepositoryOrganizedPosts);
        // 注册业务对象到工厂
        ibas.boFactory.register(Ownership.BUSINESS_OBJECT_CODE, Ownership);
        ibas.boFactory.register(Position.BUSINESS_OBJECT_CODE, Position);
        ibas.boFactory.register(Post.BUSINESS_OBJECT_CODE, Post);
    }
}