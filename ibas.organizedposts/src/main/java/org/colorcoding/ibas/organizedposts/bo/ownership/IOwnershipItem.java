package org.colorcoding.ibas.organizedposts.bo.ownership;

import org.colorcoding.ibas.bobas.bo.*;
import org.colorcoding.ibas.bobas.data.*;
import org.colorcoding.ibas.organizedposts.data.*;

/**
* 数据权限项 接口
* 
*/
public interface IOwnershipItem extends IBOSimpleLine {

    /**
    * 获取-编号
    * 
    * @return 值
    */
    Integer getObjectKey();

    /**
    * 设置-编号
    * 
    * @param value 值
    */
    void setObjectKey(Integer value);


    /**
    * 获取-类型
    * 
    * @return 值
    */
    String getObjectCode();

    /**
    * 设置-类型
    * 
    * @param value 值
    */
    void setObjectCode(String value);


    /**
    * 获取-行号
    * 
    * @return 值
    */
    Integer getLineId();

    /**
    * 设置-行号
    * 
    * @param value 值
    */
    void setLineId(Integer value);


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
    * 获取-参考1
    * 
    * @return 值
    */
    String getReference1();

    /**
    * 设置-参考1
    * 
    * @param value 值
    */
    void setReference1(String value);


    /**
    * 获取-参考2
    * 
    * @return 值
    */
    String getReference2();

    /**
    * 设置-参考2
    * 
    * @param value 值
    */
    void setReference2(String value);


    /**
    * 获取-数据权限项标记
    * 
    * @return 值
    */
    emOwnershipSign getItemSign();

    /**
    * 设置-数据权限项标记
    * 
    * @param value 值
    */
    void setItemSign(emOwnershipSign value);


    /**
    * 获取-数据权限项描述
    * 
    * @return 值
    */
    String getItemDescription();

    /**
    * 设置-数据权限项描述
    * 
    * @param value 值
    */
    void setItemDescription(String value);


    /**
    * 获取-数据权限
    * 
    * @return 值
    */
    emAuthoriseType getPermission();

    /**
    * 设置-数据权限
    * 
    * @param value 值
    */
    void setPermission(emAuthoriseType value);


    /**
    * 获取-优先级
    * 
    * @return 值
    */
    Integer getPriority();

    /**
    * 设置-优先级
    * 
    * @param value 值
    */
    void setPriority(Integer value);


    /**
    * 获取-参数
    * 
    * @return 值
    */
    String getParameter();

    /**
    * 设置-参数
    * 
    * @param value 值
    */
    void setParameter(String value);



}
