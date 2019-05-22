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
        export class Post extends ibas.BOSimple<Post> implements IPost {

            /** 业务对象编码 */
            static BUSINESS_OBJECT_CODE: string = BO_CODE_POST;
            /** 构造函数 */
            constructor() {
                super();
            }
            /** 映射的属性名称-岗位编码 */
            static PROPERTY_CODE_NAME: string = "Code";
            /** 获取-岗位编码 */
            get code(): string {
                return this.getProperty<string>(Post.PROPERTY_CODE_NAME);
            }
            /** 设置-岗位编码 */
            set code(value: string) {
                this.setProperty(Post.PROPERTY_CODE_NAME, value);
            }

            /** 映射的属性名称-岗位名称 */
            static PROPERTY_NAME_NAME: string = "Name";
            /** 获取-岗位名称 */
            get name(): string {
                return this.getProperty<string>(Post.PROPERTY_NAME_NAME);
            }
            /** 设置-岗位名称 */
            set name(value: string) {
                this.setProperty(Post.PROPERTY_NAME_NAME, value);
            }

            /** 映射的属性名称-上级岗位 */
            static PROPERTY_BELONGING_NAME: string = "Belonging";
            /** 获取-上级岗位 */
            get belonging(): number {
                return this.getProperty<number>(Post.PROPERTY_BELONGING_NAME);
            }
            /** 设置-上级岗位 */
            set belonging(value: number) {
                this.setProperty(Post.PROPERTY_BELONGING_NAME, value);
            }

            /** 映射的属性名称-职位 */
            static PROPERTY_POSITION_NAME: string = "Position";
            /** 获取-职位 */
            get position(): string {
                return this.getProperty<string>(Post.PROPERTY_POSITION_NAME);
            }
            /** 设置-职位 */
            set position(value: string) {
                this.setProperty(Post.PROPERTY_POSITION_NAME, value);
            }

            /** 映射的属性名称-用户 */
            static PROPERTY_USERCODE_NAME: string = "UserCode";
            /** 获取-用户 */
            get userCode(): string {
                return this.getProperty<string>(Post.PROPERTY_USERCODE_NAME);
            }
            /** 设置-用户 */
            set userCode(value: string) {
                this.setProperty(Post.PROPERTY_USERCODE_NAME, value);
            }

            /** 映射的属性名称-生效日期 */
            static PROPERTY_VALIDDATE_NAME: string = "ValidDate";
            /** 获取-生效日期 */
            get validDate(): Date {
                return this.getProperty<Date>(Post.PROPERTY_VALIDDATE_NAME);
            }
            /** 设置-生效日期 */
            set validDate(value: Date) {
                this.setProperty(Post.PROPERTY_VALIDDATE_NAME, value);
            }

            /** 映射的属性名称-失效日期 */
            static PROPERTY_INVALIDDATE_NAME: string = "InvalidDate";
            /** 获取-失效日期 */
            get invalidDate(): Date {
                return this.getProperty<Date>(Post.PROPERTY_INVALIDDATE_NAME);
            }
            /** 设置-失效日期 */
            set invalidDate(value: Date) {
                this.setProperty(Post.PROPERTY_INVALIDDATE_NAME, value);
            }

            /** 映射的属性名称-对象编号 */
            static PROPERTY_OBJECTKEY_NAME: string = "ObjectKey";
            /** 获取-对象编号 */
            get objectKey(): number {
                return this.getProperty<number>(Post.PROPERTY_OBJECTKEY_NAME);
            }
            /** 设置-对象编号 */
            set objectKey(value: number) {
                this.setProperty(Post.PROPERTY_OBJECTKEY_NAME, value);
            }

            /** 映射的属性名称-对象类型 */
            static PROPERTY_OBJECTCODE_NAME: string = "ObjectCode";
            /** 获取-对象类型 */
            get objectCode(): string {
                return this.getProperty<string>(Post.PROPERTY_OBJECTCODE_NAME);
            }
            /** 设置-对象类型 */
            set objectCode(value: string) {
                this.setProperty(Post.PROPERTY_OBJECTCODE_NAME, value);
            }

            /** 映射的属性名称-创建日期 */
            static PROPERTY_CREATEDATE_NAME: string = "CreateDate";
            /** 获取-创建日期 */
            get createDate(): Date {
                return this.getProperty<Date>(Post.PROPERTY_CREATEDATE_NAME);
            }
            /** 设置-创建日期 */
            set createDate(value: Date) {
                this.setProperty(Post.PROPERTY_CREATEDATE_NAME, value);
            }

            /** 映射的属性名称-创建时间 */
            static PROPERTY_CREATETIME_NAME: string = "CreateTime";
            /** 获取-创建时间 */
            get createTime(): number {
                return this.getProperty<number>(Post.PROPERTY_CREATETIME_NAME);
            }
            /** 设置-创建时间 */
            set createTime(value: number) {
                this.setProperty(Post.PROPERTY_CREATETIME_NAME, value);
            }

            /** 映射的属性名称-修改日期 */
            static PROPERTY_UPDATEDATE_NAME: string = "UpdateDate";
            /** 获取-修改日期 */
            get updateDate(): Date {
                return this.getProperty<Date>(Post.PROPERTY_UPDATEDATE_NAME);
            }
            /** 设置-修改日期 */
            set updateDate(value: Date) {
                this.setProperty(Post.PROPERTY_UPDATEDATE_NAME, value);
            }

            /** 映射的属性名称-修改时间 */
            static PROPERTY_UPDATETIME_NAME: string = "UpdateTime";
            /** 获取-修改时间 */
            get updateTime(): number {
                return this.getProperty<number>(Post.PROPERTY_UPDATETIME_NAME);
            }
            /** 设置-修改时间 */
            set updateTime(value: number) {
                this.setProperty(Post.PROPERTY_UPDATETIME_NAME, value);
            }

            /** 映射的属性名称-数据源 */
            static PROPERTY_DATASOURCE_NAME: string = "DataSource";
            /** 获取-数据源 */
            get dataSource(): string {
                return this.getProperty<string>(Post.PROPERTY_DATASOURCE_NAME);
            }
            /** 设置-数据源 */
            set dataSource(value: string) {
                this.setProperty(Post.PROPERTY_DATASOURCE_NAME, value);
            }

            /** 映射的属性名称-实例号（版本） */
            static PROPERTY_LOGINST_NAME: string = "LogInst";
            /** 获取-实例号（版本） */
            get logInst(): number {
                return this.getProperty<number>(Post.PROPERTY_LOGINST_NAME);
            }
            /** 设置-实例号（版本） */
            set logInst(value: number) {
                this.setProperty(Post.PROPERTY_LOGINST_NAME, value);
            }

            /** 映射的属性名称-服务系列 */
            static PROPERTY_SERIES_NAME: string = "Series";
            /** 获取-服务系列 */
            get series(): number {
                return this.getProperty<number>(Post.PROPERTY_SERIES_NAME);
            }
            /** 设置-服务系列 */
            set series(value: number) {
                this.setProperty(Post.PROPERTY_SERIES_NAME, value);
            }

            /** 映射的属性名称-创建用户 */
            static PROPERTY_CREATEUSERSIGN_NAME: string = "CreateUserSign";
            /** 获取-创建用户 */
            get createUserSign(): number {
                return this.getProperty<number>(Post.PROPERTY_CREATEUSERSIGN_NAME);
            }
            /** 设置-创建用户 */
            set createUserSign(value: number) {
                this.setProperty(Post.PROPERTY_CREATEUSERSIGN_NAME, value);
            }

            /** 映射的属性名称-修改用户 */
            static PROPERTY_UPDATEUSERSIGN_NAME: string = "UpdateUserSign";
            /** 获取-修改用户 */
            get updateUserSign(): number {
                return this.getProperty<number>(Post.PROPERTY_UPDATEUSERSIGN_NAME);
            }
            /** 设置-修改用户 */
            set updateUserSign(value: number) {
                this.setProperty(Post.PROPERTY_UPDATEUSERSIGN_NAME, value);
            }

            /** 映射的属性名称-创建动作标识 */
            static PROPERTY_CREATEACTIONID_NAME: string = "CreateActionId";
            /** 获取-创建动作标识 */
            get createActionId(): string {
                return this.getProperty<string>(Post.PROPERTY_CREATEACTIONID_NAME);
            }
            /** 设置-创建动作标识 */
            set createActionId(value: string) {
                this.setProperty(Post.PROPERTY_CREATEACTIONID_NAME, value);
            }

            /** 映射的属性名称-更新动作标识 */
            static PROPERTY_UPDATEACTIONID_NAME: string = "UpdateActionId";
            /** 获取-更新动作标识 */
            get updateActionId(): string {
                return this.getProperty<string>(Post.PROPERTY_UPDATEACTIONID_NAME);
            }
            /** 设置-更新动作标识 */
            set updateActionId(value: string) {
                this.setProperty(Post.PROPERTY_UPDATEACTIONID_NAME, value);
            }


            /** 映射的属性名称-岗位集合 */
            static PROPERTY_POSTS_NAME: string = "Posts";
            /** 获取-岗位集合 */
            get posts(): Posts {
                return this.getProperty<Posts>(Post.PROPERTY_POSTS_NAME);
            }
            /** 设置-岗位集合 */
            set posts(value: Posts) {
                this.setProperty(Post.PROPERTY_POSTS_NAME, value);
            }

            /** 初始化数据 */
            protected init(): void {
                this.posts = new Posts(this);
                this.belonging = -1; // 默认为顶级岗位
                this.objectCode = ibas.config.applyVariables(Post.BUSINESS_OBJECT_CODE);
            }
        }


        /** 岗位 集合 */
        export class Posts extends ibas.BusinessObjects<Post, Post> implements IPosts {

            /** 创建并添加子项 */
            create(): Post {
                let item: Post = new Post();
                this.add(item);
                return item;
            }
            /** 父项属性改变时 */
            protected onParentPropertyChanged(name: string): void {
                super.onParentPropertyChanged(name);
                if (ibas.strings.equalsIgnoreCase(name, Post.PROPERTY_OBJECTKEY_NAME)) {
                    for (let item of this) {
                        item.belonging = this.parent.objectKey;
                    }
                }
                if (ibas.strings.equalsIgnoreCase(name, Post.PROPERTY_VALIDDATE_NAME)) {
                    for (let item of this) {
                        item.validDate = this.parent.validDate;
                    }
                }
                if (ibas.strings.equalsIgnoreCase(name, Post.PROPERTY_INVALIDDATE_NAME)) {
                    for (let item of this) {
                        item.invalidDate = this.parent.invalidDate;
                    }
                }
            }
            protected onItemPropertyChanged(item: Post, name: string): void {
                super.onItemPropertyChanged(item, name);
            }
            /**
             * 添加项目后
             * @param item 项目
             */
            protected afterAdd(item: Post): void {
                super.afterAdd(item);
                item.belonging = this.parent.objectKey;
                item.validDate = this.parent.validDate;
                item.invalidDate = this.parent.invalidDate;
            }

            /**
             * 移出项目后
             * @param item 项目
             */
            protected afterRemove(item: Post): void {
                super.afterRemove(item);
            }
        }

    }
}