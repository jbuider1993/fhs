package com.fhs.core.base.dox;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.SuperBean;
import com.fhs.core.trans.anno.Trans;
import com.mybatis.jpa.annotation.Between;
import lombok.Data;
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
@Data
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
    @Trans(type = Constant.USER_INFO)
    protected String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @Between
    protected Date createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    @Trans(type = Constant.USER_INFO)
    protected String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @Between
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
    }

    /**
     * 更新之前调用
     */
    public void preUpdate(String userId) {
        this.updateTime = new Date();
        this.updateUser = userId;
    }



}
