/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import {
    emYesNo,
    emDocumentStatus,
    emBOStatus,
    emApprovalStatus,
    emAuthoriseType,
    BusinessObject,
    BusinessObjects,
    BOMasterData,
    BOMasterDataLine,
    BODocument,
    BODocumentLine,
    BOSimple,
    BOSimpleLine,
} from "ibas/index";
import {
    IOwnership,
    IOwnershipItems,
    IOwnershipItem,
    BO_CODE_OWNERSHIP,
    emOwnershipSign,
} from "../../api/index";

/** 数据权限 */
export class Ownership extends BOSimple<Ownership> implements IOwnership {

    /** 业务对象编码 */
    static BUSINESS_OBJECT_CODE: string = BO_CODE_OWNERSHIP;
    /** 构造函数 */
    constructor() {
        super();
    }
    /** 映射的属性名称-用户编码 */
    static PROPERTY_USERCODE_NAME: string = "UserCode";
    /** 获取-用户编码 */
    get userCode(): string {
        return this.getProperty<string>(Ownership.PROPERTY_USERCODE_NAME);
    }
    /** 设置-用户编码 */
    set userCode(value: string) {
        this.setProperty(Ownership.PROPERTY_USERCODE_NAME, value);
    }

    /** 映射的属性名称-对象编码 */
    static PROPERTY_BOCODE_NAME: string = "BOCode";
    /** 获取-对象编码 */
    get boCode(): string {
        return this.getProperty<string>(Ownership.PROPERTY_BOCODE_NAME);
    }
    /** 设置-对象编码 */
    set boCode(value: string) {
        this.setProperty(Ownership.PROPERTY_BOCODE_NAME, value);
    }

    /** 映射的属性名称-默认数据权限 */
    static PROPERTY_DEFAULTPERMISSION_NAME: string = "DefaultPermission";
    /** 获取-默认数据权限 */
    get defaultPermission(): emAuthoriseType {
        return this.getProperty<emAuthoriseType>(Ownership.PROPERTY_DEFAULTPERMISSION_NAME);
    }
    /** 设置-默认数据权限 */
    set defaultPermission(value: emAuthoriseType) {
        this.setProperty(Ownership.PROPERTY_DEFAULTPERMISSION_NAME, value);
    }

    /** 映射的属性名称-激活 */
    static PROPERTY_ACTIVATED_NAME: string = "Activated";
    /** 获取-激活 */
    get activated(): emYesNo {
        return this.getProperty<emYesNo>(Ownership.PROPERTY_ACTIVATED_NAME);
    }
    /** 设置-激活 */
    set activated(value: emYesNo) {
        this.setProperty(Ownership.PROPERTY_ACTIVATED_NAME, value);
    }

    /** 映射的属性名称-对象编号 */
    static PROPERTY_OBJECTKEY_NAME: string = "ObjectKey";
    /** 获取-对象编号 */
    get objectKey(): number {
        return this.getProperty<number>(Ownership.PROPERTY_OBJECTKEY_NAME);
    }
    /** 设置-对象编号 */
    set objectKey(value: number) {
        this.setProperty(Ownership.PROPERTY_OBJECTKEY_NAME, value);
    }

    /** 映射的属性名称-对象类型 */
    static PROPERTY_OBJECTCODE_NAME: string = "ObjectCode";
    /** 获取-对象类型 */
    get objectCode(): string {
        return this.getProperty<string>(Ownership.PROPERTY_OBJECTCODE_NAME);
    }
    /** 设置-对象类型 */
    set objectCode(value: string) {
        this.setProperty(Ownership.PROPERTY_OBJECTCODE_NAME, value);
    }

    /** 映射的属性名称-创建日期 */
    static PROPERTY_CREATEDATE_NAME: string = "CreateDate";
    /** 获取-创建日期 */
    get createDate(): Date {
        return this.getProperty<Date>(Ownership.PROPERTY_CREATEDATE_NAME);
    }
    /** 设置-创建日期 */
    set createDate(value: Date) {
        this.setProperty(Ownership.PROPERTY_CREATEDATE_NAME, value);
    }

    /** 映射的属性名称-创建时间 */
    static PROPERTY_CREATETIME_NAME: string = "CreateTime";
    /** 获取-创建时间 */
    get createTime(): number {
        return this.getProperty<number>(Ownership.PROPERTY_CREATETIME_NAME);
    }
    /** 设置-创建时间 */
    set createTime(value: number) {
        this.setProperty(Ownership.PROPERTY_CREATETIME_NAME, value);
    }

    /** 映射的属性名称-修改日期 */
    static PROPERTY_UPDATEDATE_NAME: string = "UpdateDate";
    /** 获取-修改日期 */
    get updateDate(): Date {
        return this.getProperty<Date>(Ownership.PROPERTY_UPDATEDATE_NAME);
    }
    /** 设置-修改日期 */
    set updateDate(value: Date) {
        this.setProperty(Ownership.PROPERTY_UPDATEDATE_NAME, value);
    }

    /** 映射的属性名称-修改时间 */
    static PROPERTY_UPDATETIME_NAME: string = "UpdateTime";
    /** 获取-修改时间 */
    get updateTime(): number {
        return this.getProperty<number>(Ownership.PROPERTY_UPDATETIME_NAME);
    }
    /** 设置-修改时间 */
    set updateTime(value: number) {
        this.setProperty(Ownership.PROPERTY_UPDATETIME_NAME, value);
    }

    /** 映射的属性名称-实例号（版本） */
    static PROPERTY_LOGINST_NAME: string = "LogInst";
    /** 获取-实例号（版本） */
    get logInst(): number {
        return this.getProperty<number>(Ownership.PROPERTY_LOGINST_NAME);
    }
    /** 设置-实例号（版本） */
    set logInst(value: number) {
        this.setProperty(Ownership.PROPERTY_LOGINST_NAME, value);
    }

    /** 映射的属性名称-服务系列 */
    static PROPERTY_SERIES_NAME: string = "Series";
    /** 获取-服务系列 */
    get series(): number {
        return this.getProperty<number>(Ownership.PROPERTY_SERIES_NAME);
    }
    /** 设置-服务系列 */
    set series(value: number) {
        this.setProperty(Ownership.PROPERTY_SERIES_NAME, value);
    }

    /** 映射的属性名称-数据源 */
    static PROPERTY_DATASOURCE_NAME: string = "DataSource";
    /** 获取-数据源 */
    get dataSource(): string {
        return this.getProperty<string>(Ownership.PROPERTY_DATASOURCE_NAME);
    }
    /** 设置-数据源 */
    set dataSource(value: string) {
        this.setProperty(Ownership.PROPERTY_DATASOURCE_NAME, value);
    }

    /** 映射的属性名称-创建用户 */
    static PROPERTY_CREATEUSERSIGN_NAME: string = "CreateUserSign";
    /** 获取-创建用户 */
    get createUserSign(): number {
        return this.getProperty<number>(Ownership.PROPERTY_CREATEUSERSIGN_NAME);
    }
    /** 设置-创建用户 */
    set createUserSign(value: number) {
        this.setProperty(Ownership.PROPERTY_CREATEUSERSIGN_NAME, value);
    }

    /** 映射的属性名称-修改用户 */
    static PROPERTY_UPDATEUSERSIGN_NAME: string = "UpdateUserSign";
    /** 获取-修改用户 */
    get updateUserSign(): number {
        return this.getProperty<number>(Ownership.PROPERTY_UPDATEUSERSIGN_NAME);
    }
    /** 设置-修改用户 */
    set updateUserSign(value: number) {
        this.setProperty(Ownership.PROPERTY_UPDATEUSERSIGN_NAME, value);
    }

    /** 映射的属性名称-创建动作标识 */
    static PROPERTY_CREATEACTIONID_NAME: string = "CreateActionId";
    /** 获取-创建动作标识 */
    get createActionId(): string {
        return this.getProperty<string>(Ownership.PROPERTY_CREATEACTIONID_NAME);
    }
    /** 设置-创建动作标识 */
    set createActionId(value: string) {
        this.setProperty(Ownership.PROPERTY_CREATEACTIONID_NAME, value);
    }

    /** 映射的属性名称-更新动作标识 */
    static PROPERTY_UPDATEACTIONID_NAME: string = "UpdateActionId";
    /** 获取-更新动作标识 */
    get updateActionId(): string {
        return this.getProperty<string>(Ownership.PROPERTY_UPDATEACTIONID_NAME);
    }
    /** 设置-更新动作标识 */
    set updateActionId(value: string) {
        this.setProperty(Ownership.PROPERTY_UPDATEACTIONID_NAME, value);
    }


    /** 映射的属性名称-数据权限项集合 */
    static PROPERTY_OWNERSHIPITEMS_NAME: string = "OwnershipItems";
    /** 获取-数据权限项集合 */
    get ownershipItems(): OwnershipItems {
        return this.getProperty<OwnershipItems>(Ownership.PROPERTY_OWNERSHIPITEMS_NAME);
    }
    /** 设置-数据权限项集合 */
    set ownershipItems(value: OwnershipItems) {
        this.setProperty(Ownership.PROPERTY_OWNERSHIPITEMS_NAME, value);
    }


    /** 初始化数据 */
    protected init(): void {
        this.ownershipItems = new OwnershipItems(this);
        this.objectCode = Ownership.BUSINESS_OBJECT_CODE;
        this.activated = emYesNo.YES;
    }
}

/** 数据权限项 集合 */
export class OwnershipItems extends BusinessObjects<OwnershipItem, Ownership> implements IOwnershipItems {

    /** 创建并添加子项 */
    create(): OwnershipItem {
        let item: OwnershipItem = new OwnershipItem();
        this.add(item);
        return item;
    }
}

/** 数据权限项 */
export class OwnershipItem extends BOSimpleLine<OwnershipItem> implements IOwnershipItem {

    /** 构造函数 */
    constructor() {
        super();
    }
    /** 映射的属性名称-编号 */
    static PROPERTY_OBJECTKEY_NAME: string = "ObjectKey";
    /** 获取-编号 */
    get objectKey(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_OBJECTKEY_NAME);
    }
    /** 设置-编号 */
    set objectKey(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_OBJECTKEY_NAME, value);
    }

    /** 映射的属性名称-类型 */
    static PROPERTY_OBJECTCODE_NAME: string = "ObjectCode";
    /** 获取-类型 */
    get objectCode(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_OBJECTCODE_NAME);
    }
    /** 设置-类型 */
    set objectCode(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_OBJECTCODE_NAME, value);
    }

    /** 映射的属性名称-行号 */
    static PROPERTY_LINEID_NAME: string = "LineId";
    /** 获取-行号 */
    get lineId(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_LINEID_NAME);
    }
    /** 设置-行号 */
    set lineId(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_LINEID_NAME, value);
    }

    /** 映射的属性名称-实例号（版本） */
    static PROPERTY_LOGINST_NAME: string = "LogInst";
    /** 获取-实例号（版本） */
    get logInst(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_LOGINST_NAME);
    }
    /** 设置-实例号（版本） */
    set logInst(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_LOGINST_NAME, value);
    }

    /** 映射的属性名称-数据源 */
    static PROPERTY_DATASOURCE_NAME: string = "DataSource";
    /** 获取-数据源 */
    get dataSource(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_DATASOURCE_NAME);
    }
    /** 设置-数据源 */
    set dataSource(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_DATASOURCE_NAME, value);
    }

    /** 映射的属性名称-创建日期 */
    static PROPERTY_CREATEDATE_NAME: string = "CreateDate";
    /** 获取-创建日期 */
    get createDate(): Date {
        return this.getProperty<Date>(OwnershipItem.PROPERTY_CREATEDATE_NAME);
    }
    /** 设置-创建日期 */
    set createDate(value: Date) {
        this.setProperty(OwnershipItem.PROPERTY_CREATEDATE_NAME, value);
    }

    /** 映射的属性名称-创建时间 */
    static PROPERTY_CREATETIME_NAME: string = "CreateTime";
    /** 获取-创建时间 */
    get createTime(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_CREATETIME_NAME);
    }
    /** 设置-创建时间 */
    set createTime(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_CREATETIME_NAME, value);
    }

    /** 映射的属性名称-修改日期 */
    static PROPERTY_UPDATEDATE_NAME: string = "UpdateDate";
    /** 获取-修改日期 */
    get updateDate(): Date {
        return this.getProperty<Date>(OwnershipItem.PROPERTY_UPDATEDATE_NAME);
    }
    /** 设置-修改日期 */
    set updateDate(value: Date) {
        this.setProperty(OwnershipItem.PROPERTY_UPDATEDATE_NAME, value);
    }

    /** 映射的属性名称-修改时间 */
    static PROPERTY_UPDATETIME_NAME: string = "UpdateTime";
    /** 获取-修改时间 */
    get updateTime(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_UPDATETIME_NAME);
    }
    /** 设置-修改时间 */
    set updateTime(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_UPDATETIME_NAME, value);
    }

    /** 映射的属性名称-创建用户 */
    static PROPERTY_CREATEUSERSIGN_NAME: string = "CreateUserSign";
    /** 获取-创建用户 */
    get createUserSign(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_CREATEUSERSIGN_NAME);
    }
    /** 设置-创建用户 */
    set createUserSign(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_CREATEUSERSIGN_NAME, value);
    }

    /** 映射的属性名称-修改用户 */
    static PROPERTY_UPDATEUSERSIGN_NAME: string = "UpdateUserSign";
    /** 获取-修改用户 */
    get updateUserSign(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_UPDATEUSERSIGN_NAME);
    }
    /** 设置-修改用户 */
    set updateUserSign(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_UPDATEUSERSIGN_NAME, value);
    }

    /** 映射的属性名称-创建动作标识 */
    static PROPERTY_CREATEACTIONID_NAME: string = "CreateActionId";
    /** 获取-创建动作标识 */
    get createActionId(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_CREATEACTIONID_NAME);
    }
    /** 设置-创建动作标识 */
    set createActionId(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_CREATEACTIONID_NAME, value);
    }

    /** 映射的属性名称-更新动作标识 */
    static PROPERTY_UPDATEACTIONID_NAME: string = "UpdateActionId";
    /** 获取-更新动作标识 */
    get updateActionId(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_UPDATEACTIONID_NAME);
    }
    /** 设置-更新动作标识 */
    set updateActionId(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_UPDATEACTIONID_NAME, value);
    }

    /** 映射的属性名称-参考1 */
    static PROPERTY_REFERENCE1_NAME: string = "Reference1";
    /** 获取-参考1 */
    get reference1(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_REFERENCE1_NAME);
    }
    /** 设置-参考1 */
    set reference1(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_REFERENCE1_NAME, value);
    }

    /** 映射的属性名称-参考2 */
    static PROPERTY_REFERENCE2_NAME: string = "Reference2";
    /** 获取-参考2 */
    get reference2(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_REFERENCE2_NAME);
    }
    /** 设置-参考2 */
    set reference2(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_REFERENCE2_NAME, value);
    }

    /** 映射的属性名称-数据权限项标记 */
    static PROPERTY_ITEMSIGN_NAME: string = "ItemSign";
    /** 获取-数据权限项标记 */
    get itemSign(): emOwnershipSign {
        return this.getProperty<emOwnershipSign>(OwnershipItem.PROPERTY_ITEMSIGN_NAME);
    }
    /** 设置-数据权限项标记 */
    set itemSign(value: emOwnershipSign) {
        this.setProperty(OwnershipItem.PROPERTY_ITEMSIGN_NAME, value);
    }

    /** 映射的属性名称-数据权限项描述 */
    static PROPERTY_ITEMDESCRIPTION_NAME: string = "ItemDescription";
    /** 获取-数据权限项描述 */
    get itemDescription(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_ITEMDESCRIPTION_NAME);
    }
    /** 设置-数据权限项描述 */
    set itemDescription(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_ITEMDESCRIPTION_NAME, value);
    }

    /** 映射的属性名称-数据权限 */
    static PROPERTY_PERMISSION_NAME: string = "Permission";
    /** 获取-数据权限 */
    get permission(): emAuthoriseType {
        return this.getProperty<emAuthoriseType>(OwnershipItem.PROPERTY_PERMISSION_NAME);
    }
    /** 设置-数据权限 */
    set permission(value: emAuthoriseType) {
        this.setProperty(OwnershipItem.PROPERTY_PERMISSION_NAME, value);
    }

    /** 映射的属性名称-优先级 */
    static PROPERTY_PRIORITY_NAME: string = "Priority";
    /** 获取-优先级 */
    get priority(): number {
        return this.getProperty<number>(OwnershipItem.PROPERTY_PRIORITY_NAME);
    }
    /** 设置-优先级 */
    set priority(value: number) {
        this.setProperty(OwnershipItem.PROPERTY_PRIORITY_NAME, value);
    }

    /** 映射的属性名称-参数 */
    static PROPERTY_PARAMETER_NAME: string = "Parameter";
    /** 获取-参数 */
    get parameter(): string {
        return this.getProperty<string>(OwnershipItem.PROPERTY_PARAMETER_NAME);
    }
    /** 设置-参数 */
    set parameter(value: string) {
        this.setProperty(OwnershipItem.PROPERTY_PARAMETER_NAME, value);
    }



    /** 初始化数据 */
    protected init(): void {
        //
    }
}

