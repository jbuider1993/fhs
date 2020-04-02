package com.fhs.core.base.dox;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.pojo.SuperBean;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.mybatis.jpa.annotation.Between;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 所有的do请都继承此do
 *
 * @Filename: BaseDO.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@SuppressWarnings("rawtypes")
public abstract class BaseDO<T extends BaseDO> extends SuperBean<T> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 子类id字段缓存
     */
    @TableField(exist = false)
    private static final Map<Class<?>, Field> ID_FIELD_CACHE_MAP = new HashMap<>();

    /**
     * 创建人
     */
    @TableField("create_user")
    @Trans(type = TransType.AUTO_TRANS, key = "sysUser#createUser")
    protected String createUser;

    /**
     * 创建时间
     */
    @Between
    @TableField("create_time")
    @JSONField(format = DateUtils.DATETIME_PATTERN)
    protected Date createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    @Trans(type = TransType.AUTO_TRANS, key = "sysUser#updateUser")
    protected String updateUser;

    /**
     * 更新时间
     */
    @Between
    @TableField("update_time")
    @JSONField(format = DateUtils.DATETIME_PATTERN)
    protected Date updateTime;

    @TableField("is_delete")
    protected Integer isDelete;


    /**
     * 插入之前调用
     */
    public void preInsert(String userId) {
        Date now = new Date();
        this.createTime = now;
        this.updateTime = now;
        this.createUser = userId;
        this.updateUser = userId;
        this.isDelete = Constant.INT_FALSE;
    }

    /**
     * 更新之前调用
     */
    public void preUpdate(String userId) {
        this.updateTime = new Date();
        this.updateUser = userId;
    }


    /**
     * 获取主键
     *
     * @return 主键
     */
    public Object getPkey() {
        Field idField = getIdField(true);
        try {
            return idField.get(this);
        } catch (IllegalAccessException e) {
            return null;
        }
    }


    /**
     * 获取子类id字段
     *
     * @return 子类id字段
     */
    public Field getIdField(boolean isThrowError) {
        if (ID_FIELD_CACHE_MAP.containsKey(this.getClass())) {
            return ID_FIELD_CACHE_MAP.get(this.getClass());
        }
        List<Field> fieldList = ReflectUtils.getAnnotationField(this.getClass(), javax.persistence.Id.class);
        if (fieldList.size() == 0) {
            fieldList = ReflectUtils.getAnnotationField(this.getClass(), TableId.class);
            if (fieldList.size() == 0) {
                if (isThrowError) {
                    throw new RuntimeException("找不到" + this.getClass() + "的id注解");
                }
            }
            return fieldList.get(0);
        }
        fieldList.get(0).setAccessible(true);
        ID_FIELD_CACHE_MAP.put(this.getClass(), fieldList.get(0));
        return fieldList.get(0);
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
