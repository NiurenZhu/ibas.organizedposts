package org.colorcoding.ibas.organizedposts.bo.ownership;

import org.colorcoding.ibas.bobas.bo.*;
import org.colorcoding.ibas.bobas.data.*;
import org.colorcoding.ibas.organizedposts.data.*;

/**
* 数据权限 接口
* 
*/
public interface IOwnership extends IBOSimple {

    /**
    * 获取-用户编码
    * 
    * @return 值
    */
    String getUserCode();

    /**
    * 设置-用户编码
    * 
    * @param value 值
    */
    void setUserCode(String value);


    /**
    * 获取-对象编码
    * 
    * @return 值
    */
    String getBOCode();

    /**
    * 设置-对象编码
    * 
    * @param value 值
    */
    void setBOCode(String value);


    /**
    * 获取-默认数据权限
    * 
    * @return 值
    */
    emAuthoriseType getDefaultPermission();

    /**
    * 设置-默认数据权限
    * 
    * @param value 值
    */
    void setDefaultPermission(emAuthoriseType value);


    /**
    * 获取-激活
    * 
    * @return 值
    */
    emYesNo getActivated();

    /**
    * 设置-激活
    * 
    * @param value 值
    */
    void setActivated(emYesNo value);


    /**
    * 获取-对象编号
    * 
    * @return 值
    */
    Integer getObjectKey();

    /**
    * 设置-对象编号
    * 
    * @param value 值
    */
    void setObjectKey(Integer value);


    /**
    * 获取-对象类型
    * 
    * @return 值
    */
    String getObjectCode();

    /**
    * 设置-对象类型
    * 
    * @param value 值
    */
    void setObjectCode(String value);


    /**
    * 获取-创建日期
    * 
    * @return 值
    */
    DateTime getCreateDate();

    /**
    * 设置-创建日期
    * 
    * @param value 值
    */
    void setCreateDate(DateTime value);


    /**
    * 获取-创建时间
    * 
    * @return 值
    */
    Short getCreateTime();

    /**
    * 设置-创建时间
    * 
    * @param value 值
    */
    void setCreateTime(Short value);


    /**
    * 获取-修改日期
    * 
    * @return 值
    */
    DateTime getUpdateDate();

    /**
    * 设置-修改日期
    * 
    * @param value 值
    */
    void setUpdateDate(DateTime value);


    /**
    * 获取-修改时间
    * 
    * @return 值
    */
    Short getUpdateTime();

    /**
    * 设置-修改时间
    * 
    * @param value 值
    */
    void setUpdateTime(Short value);


    /**
    * 获取-实例号（版本）
    * 
    * @return 值
    */
    Integer getLogInst();

    /**
    * 设置-实例号（版本）
    * 
    * @param value 值
    */
    void setLogInst(Integer value);


    /**
    * 获取-服务系列
    * 
    * @return 值
    */
    Integer getSeries();

    /**
    * 设置-服务系列
    * 
    * @param value 值
    */
    void setSeries(Integer value);


    /**
    * 获取-数据源
    * 
    * @return 值
    */
    String getDataSource();

    /**
    * 设置-数据源
    * 
    * @param value 值
    */
    void setDataSource(String value);


    /**
    * 获取-创建用户
    * 
    * @return 值
    */
    Integer getCreateUserSign();

    /**
    * 设置-创建用户
    * 
    * @param value 值
    */
    void setCreateUserSign(Integer value);


    /**
    * 获取-修改用户
    * 
    * @return 值
    */
    Integer getUpdateUserSign();

    /**
    * 设置-修改用户
    * 
    * @param value 值
    */
    void setUpdateUserSign(Integer value);


    /**
    * 获取-创建动作标识
    * 
    * @return 值
    */
    String getCreateActionId();

    /**
    * 设置-创建动作标识
    * 
    * @param value 值
    */
    void setCreateActionId(String value);


    /**
    * 获取-更新动作标识
    * 
    * @return 值
    */
    String getUpdateActionId();

    /**
    * 设置-更新动作标识
    * 
    * @param value 值
    */
    void setUpdateActionId(String value);



     /**
    * 获取-数据权限项集合
    * 
    * @return 值
    */
    IOwnershipItems getOwnershipItems();

    /**
    * 设置-数据权限项集合
    * 
    * @param value 值
    */
    void setOwnershipItems(IOwnershipItems value);


}
