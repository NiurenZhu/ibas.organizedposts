package org.colorcoding.ibas.organizedposts.bo.ownership;

import javax.xml.bind.annotation.*;

import org.colorcoding.ibas.bobas.core.*;
import org.colorcoding.ibas.bobas.mapping.*;
import org.colorcoding.ibas.bobas.bo.*;
import org.colorcoding.ibas.bobas.data.*;
import org.colorcoding.ibas.organizedposts.MyConfiguration;
import org.colorcoding.ibas.organizedposts.data.*;

/**
* 获取-数据权限项
* 
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = OwnershipItem.BUSINESS_OBJECT_NAME, namespace = MyConfiguration.NAMESPACE_BO)
public class OwnershipItem extends BusinessObject<OwnershipItem> implements IOwnershipItem {

    /**
     * 序列化版本标记
     */
    private static final long serialVersionUID = 3793490835144123523L;

    /**
    * 当前类型
    */
    private static final Class<?> MY_CLASS = OwnershipItem.class;

    /**
    * 数据库表
    */
    public static final String DB_TABLE_NAME = "${Company}_OP_WNR1";

    /**
    * 业务对象编码
    */
    public static final String BUSINESS_OBJECT_CODE = "${Company}_OP_OWNERSHIP";

    /**
    * 业务对象名称
    */
    public static final String BUSINESS_OBJECT_NAME = "OwnershipItem";

    /**
    * 属性名称-编号
    */
    private static final String PROPERTY_OBJECTKEY_NAME = "ObjectKey";

    /**
    * 编号 属性
    */
    @DbField(name = "ObjectKey", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = true)
    public static final IPropertyInfo<Integer> PROPERTY_OBJECTKEY = registerProperty(PROPERTY_OBJECTKEY_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-编号
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_OBJECTKEY_NAME)
    public final Integer getObjectKey() {
        return this.getProperty(PROPERTY_OBJECTKEY);
    }

    /**
    * 设置-编号
    * 
    * @param value 值
    */
    public final void setObjectKey(Integer value) {
        this.setProperty(PROPERTY_OBJECTKEY, value);
    }


    /**
    * 属性名称-类型
    */
    private static final String PROPERTY_OBJECTCODE_NAME = "ObjectCode";

    /**
    * 类型 属性
    */
    @DbField(name = "Object", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_OBJECTCODE = registerProperty(PROPERTY_OBJECTCODE_NAME, String.class, MY_CLASS);

    /**
    * 获取-类型
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_OBJECTCODE_NAME)
    public final String getObjectCode() {
        return this.getProperty(PROPERTY_OBJECTCODE);
    }

    /**
    * 设置-类型
    * 
    * @param value 值
    */
    public final void setObjectCode(String value) {
        this.setProperty(PROPERTY_OBJECTCODE, value);
    }


    /**
    * 属性名称-行号
    */
    private static final String PROPERTY_LINEID_NAME = "LineId";

    /**
    * 行号 属性
    */
    @DbField(name = "LineId", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = true)
    public static final IPropertyInfo<Integer> PROPERTY_LINEID = registerProperty(PROPERTY_LINEID_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-行号
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_LINEID_NAME)
    public final Integer getLineId() {
        return this.getProperty(PROPERTY_LINEID);
    }

    /**
    * 设置-行号
    * 
    * @param value 值
    */
    public final void setLineId(Integer value) {
        this.setProperty(PROPERTY_LINEID, value);
    }


    /**
    * 属性名称-实例号（版本）
    */
    private static final String PROPERTY_LOGINST_NAME = "LogInst";

    /**
    * 实例号（版本） 属性
    */
    @DbField(name = "LogInst", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Integer> PROPERTY_LOGINST = registerProperty(PROPERTY_LOGINST_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-实例号（版本）
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_LOGINST_NAME)
    public final Integer getLogInst() {
        return this.getProperty(PROPERTY_LOGINST);
    }

    /**
    * 设置-实例号（版本）
    * 
    * @param value 值
    */
    public final void setLogInst(Integer value) {
        this.setProperty(PROPERTY_LOGINST, value);
    }


    /**
    * 属性名称-数据源
    */
    private static final String PROPERTY_DATASOURCE_NAME = "DataSource";

    /**
    * 数据源 属性
    */
    @DbField(name = "DataSource", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_DATASOURCE = registerProperty(PROPERTY_DATASOURCE_NAME, String.class, MY_CLASS);

    /**
    * 获取-数据源
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_DATASOURCE_NAME)
    public final String getDataSource() {
        return this.getProperty(PROPERTY_DATASOURCE);
    }

    /**
    * 设置-数据源
    * 
    * @param value 值
    */
    public final void setDataSource(String value) {
        this.setProperty(PROPERTY_DATASOURCE, value);
    }


    /**
    * 属性名称-创建日期
    */
    private static final String PROPERTY_CREATEDATE_NAME = "CreateDate";

    /**
    * 创建日期 属性
    */
    @DbField(name = "CreateDate", type = DbFieldType.DATE, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<DateTime> PROPERTY_CREATEDATE = registerProperty(PROPERTY_CREATEDATE_NAME, DateTime.class, MY_CLASS);

    /**
    * 获取-创建日期
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_CREATEDATE_NAME)
    public final DateTime getCreateDate() {
        return this.getProperty(PROPERTY_CREATEDATE);
    }

    /**
    * 设置-创建日期
    * 
    * @param value 值
    */
    public final void setCreateDate(DateTime value) {
        this.setProperty(PROPERTY_CREATEDATE, value);
    }


    /**
    * 属性名称-创建时间
    */
    private static final String PROPERTY_CREATETIME_NAME = "CreateTime";

    /**
    * 创建时间 属性
    */
    @DbField(name = "CreateTime", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Short> PROPERTY_CREATETIME = registerProperty(PROPERTY_CREATETIME_NAME, Short.class, MY_CLASS);

    /**
    * 获取-创建时间
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_CREATETIME_NAME)
    public final Short getCreateTime() {
        return this.getProperty(PROPERTY_CREATETIME);
    }

    /**
    * 设置-创建时间
    * 
    * @param value 值
    */
    public final void setCreateTime(Short value) {
        this.setProperty(PROPERTY_CREATETIME, value);
    }


    /**
    * 属性名称-修改日期
    */
    private static final String PROPERTY_UPDATEDATE_NAME = "UpdateDate";

    /**
    * 修改日期 属性
    */
    @DbField(name = "UpdateDate", type = DbFieldType.DATE, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<DateTime> PROPERTY_UPDATEDATE = registerProperty(PROPERTY_UPDATEDATE_NAME, DateTime.class, MY_CLASS);

    /**
    * 获取-修改日期
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_UPDATEDATE_NAME)
    public final DateTime getUpdateDate() {
        return this.getProperty(PROPERTY_UPDATEDATE);
    }

    /**
    * 设置-修改日期
    * 
    * @param value 值
    */
    public final void setUpdateDate(DateTime value) {
        this.setProperty(PROPERTY_UPDATEDATE, value);
    }


    /**
    * 属性名称-修改时间
    */
    private static final String PROPERTY_UPDATETIME_NAME = "UpdateTime";

    /**
    * 修改时间 属性
    */
    @DbField(name = "UpdateTime", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Short> PROPERTY_UPDATETIME = registerProperty(PROPERTY_UPDATETIME_NAME, Short.class, MY_CLASS);

    /**
    * 获取-修改时间
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_UPDATETIME_NAME)
    public final Short getUpdateTime() {
        return this.getProperty(PROPERTY_UPDATETIME);
    }

    /**
    * 设置-修改时间
    * 
    * @param value 值
    */
    public final void setUpdateTime(Short value) {
        this.setProperty(PROPERTY_UPDATETIME, value);
    }


    /**
    * 属性名称-创建用户
    */
    private static final String PROPERTY_CREATEUSERSIGN_NAME = "CreateUserSign";

    /**
    * 创建用户 属性
    */
    @DbField(name = "Creator", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Integer> PROPERTY_CREATEUSERSIGN = registerProperty(PROPERTY_CREATEUSERSIGN_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-创建用户
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_CREATEUSERSIGN_NAME)
    public final Integer getCreateUserSign() {
        return this.getProperty(PROPERTY_CREATEUSERSIGN);
    }

    /**
    * 设置-创建用户
    * 
    * @param value 值
    */
    public final void setCreateUserSign(Integer value) {
        this.setProperty(PROPERTY_CREATEUSERSIGN, value);
    }


    /**
    * 属性名称-修改用户
    */
    private static final String PROPERTY_UPDATEUSERSIGN_NAME = "UpdateUserSign";

    /**
    * 修改用户 属性
    */
    @DbField(name = "Updator", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Integer> PROPERTY_UPDATEUSERSIGN = registerProperty(PROPERTY_UPDATEUSERSIGN_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-修改用户
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_UPDATEUSERSIGN_NAME)
    public final Integer getUpdateUserSign() {
        return this.getProperty(PROPERTY_UPDATEUSERSIGN);
    }

    /**
    * 设置-修改用户
    * 
    * @param value 值
    */
    public final void setUpdateUserSign(Integer value) {
        this.setProperty(PROPERTY_UPDATEUSERSIGN, value);
    }


    /**
    * 属性名称-创建动作标识
    */
    private static final String PROPERTY_CREATEACTIONID_NAME = "CreateActionId";

    /**
    * 创建动作标识 属性
    */
    @DbField(name = "CreateActId", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_CREATEACTIONID = registerProperty(PROPERTY_CREATEACTIONID_NAME, String.class, MY_CLASS);

    /**
    * 获取-创建动作标识
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_CREATEACTIONID_NAME)
    public final String getCreateActionId() {
        return this.getProperty(PROPERTY_CREATEACTIONID);
    }

    /**
    * 设置-创建动作标识
    * 
    * @param value 值
    */
    public final void setCreateActionId(String value) {
        this.setProperty(PROPERTY_CREATEACTIONID, value);
    }


    /**
    * 属性名称-更新动作标识
    */
    private static final String PROPERTY_UPDATEACTIONID_NAME = "UpdateActionId";

    /**
    * 更新动作标识 属性
    */
    @DbField(name = "UpdateActId", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_UPDATEACTIONID = registerProperty(PROPERTY_UPDATEACTIONID_NAME, String.class, MY_CLASS);

    /**
    * 获取-更新动作标识
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_UPDATEACTIONID_NAME)
    public final String getUpdateActionId() {
        return this.getProperty(PROPERTY_UPDATEACTIONID);
    }

    /**
    * 设置-更新动作标识
    * 
    * @param value 值
    */
    public final void setUpdateActionId(String value) {
        this.setProperty(PROPERTY_UPDATEACTIONID, value);
    }


    /**
    * 属性名称-参考1
    */
    private static final String PROPERTY_REFERENCE1_NAME = "Reference1";

    /**
    * 参考1 属性
    */
    @DbField(name = "Ref1", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_REFERENCE1 = registerProperty(PROPERTY_REFERENCE1_NAME, String.class, MY_CLASS);

    /**
    * 获取-参考1
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_REFERENCE1_NAME)
    public final String getReference1() {
        return this.getProperty(PROPERTY_REFERENCE1);
    }

    /**
    * 设置-参考1
    * 
    * @param value 值
    */
    public final void setReference1(String value) {
        this.setProperty(PROPERTY_REFERENCE1, value);
    }


    /**
    * 属性名称-参考2
    */
    private static final String PROPERTY_REFERENCE2_NAME = "Reference2";

    /**
    * 参考2 属性
    */
    @DbField(name = "Ref2", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_REFERENCE2 = registerProperty(PROPERTY_REFERENCE2_NAME, String.class, MY_CLASS);

    /**
    * 获取-参考2
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_REFERENCE2_NAME)
    public final String getReference2() {
        return this.getProperty(PROPERTY_REFERENCE2);
    }

    /**
    * 设置-参考2
    * 
    * @param value 值
    */
    public final void setReference2(String value) {
        this.setProperty(PROPERTY_REFERENCE2, value);
    }


    /**
    * 属性名称-数据权限项标记
    */
    private static final String PROPERTY_ITEMSIGN_NAME = "ItemSign";

    /**
    * 数据权限项标记 属性
    */
    @DbField(name = "ItemSign", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<emOwnershipSign> PROPERTY_ITEMSIGN = registerProperty(PROPERTY_ITEMSIGN_NAME, emOwnershipSign.class, MY_CLASS);

    /**
    * 获取-数据权限项标记
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_ITEMSIGN_NAME)
    public final emOwnershipSign getItemSign() {
        return this.getProperty(PROPERTY_ITEMSIGN);
    }

    /**
    * 设置-数据权限项标记
    * 
    * @param value 值
    */
    public final void setItemSign(emOwnershipSign value) {
        this.setProperty(PROPERTY_ITEMSIGN, value);
    }


    /**
    * 属性名称-数据权限项描述
    */
    private static final String PROPERTY_ITEMDESCRIPTION_NAME = "ItemDescription";

    /**
    * 数据权限项描述 属性
    */
    @DbField(name = "ItemDesc", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_ITEMDESCRIPTION = registerProperty(PROPERTY_ITEMDESCRIPTION_NAME, String.class, MY_CLASS);

    /**
    * 获取-数据权限项描述
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_ITEMDESCRIPTION_NAME)
    public final String getItemDescription() {
        return this.getProperty(PROPERTY_ITEMDESCRIPTION);
    }

    /**
    * 设置-数据权限项描述
    * 
    * @param value 值
    */
    public final void setItemDescription(String value) {
        this.setProperty(PROPERTY_ITEMDESCRIPTION, value);
    }


    /**
    * 属性名称-数据权限
    */
    private static final String PROPERTY_PERMISSION_NAME = "Permission";

    /**
    * 数据权限 属性
    */
    @DbField(name = "Permission", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<emAuthoriseType> PROPERTY_PERMISSION = registerProperty(PROPERTY_PERMISSION_NAME, emAuthoriseType.class, MY_CLASS);

    /**
    * 获取-数据权限
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_PERMISSION_NAME)
    public final emAuthoriseType getPermission() {
        return this.getProperty(PROPERTY_PERMISSION);
    }

    /**
    * 设置-数据权限
    * 
    * @param value 值
    */
    public final void setPermission(emAuthoriseType value) {
        this.setProperty(PROPERTY_PERMISSION, value);
    }


    /**
    * 属性名称-优先级
    */
    private static final String PROPERTY_PRIORITY_NAME = "Priority";

    /**
    * 优先级 属性
    */
    @DbField(name = "Priority", type = DbFieldType.NUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<Integer> PROPERTY_PRIORITY = registerProperty(PROPERTY_PRIORITY_NAME, Integer.class, MY_CLASS);

    /**
    * 获取-优先级
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_PRIORITY_NAME)
    public final Integer getPriority() {
        return this.getProperty(PROPERTY_PRIORITY);
    }

    /**
    * 设置-优先级
    * 
    * @param value 值
    */
    public final void setPriority(Integer value) {
        this.setProperty(PROPERTY_PRIORITY, value);
    }


    /**
    * 属性名称-参数
    */
    private static final String PROPERTY_PARAMETER_NAME = "Parameter";

    /**
    * 参数 属性
    */
    @DbField(name = "Param", type = DbFieldType.ALPHANUMERIC, table = DB_TABLE_NAME, primaryKey = false)
    public static final IPropertyInfo<String> PROPERTY_PARAMETER = registerProperty(PROPERTY_PARAMETER_NAME, String.class, MY_CLASS);

    /**
    * 获取-参数
    * 
    * @return 值
    */
    @XmlElement(name = PROPERTY_PARAMETER_NAME)
    public final String getParameter() {
        return this.getProperty(PROPERTY_PARAMETER);
    }

    /**
    * 设置-参数
    * 
    * @param value 值
    */
    public final void setParameter(String value) {
        this.setProperty(PROPERTY_PARAMETER, value);
    }




    /**
    * 初始化数据
    */
    @Override
    protected void initialize() {
        super.initialize();// 基类初始化，不可去除
        this.setObjectCode(MyConfiguration.applyVariables(BUSINESS_OBJECT_CODE));
        this.setObjectCode(BUSINESS_OBJECT_CODE);

    }

}
