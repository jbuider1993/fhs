package com.fhs.core.base.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.fhs.common.constant.Constant;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.trans.Trans;
import com.mybatis.jpa.annotation.Between;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    protected String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @Between
    protected String createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    protected String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @Between
    protected String updateTime;

    /**
     * 将我自己保存到 db中去
     *
     * @return 是否成功
     */
    @SuppressWarnings("unchecked")
    public boolean save() {
        return getBaseService().insert(this) != 0;
    }

    /**
     * 插入之前调用
     */
    public void preInsert(String userId) {
        String dateTime = DateUtils.getCurrentDateStr(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
        this.createTime = dateTime;
        this.updateTime = dateTime;
        this.createUser = userId;
        this.updateUser = userId;
    }

    /**
     * 更新之前调用
     */
    public void preUpdate(String userId) {
        this.updateTime = DateUtils.getCurrentDateStr(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
        this.updateUser = userId;
    }

    /**
     * 根据子类extends 本类的注解获取baseservice
     *
     * @return
     */
    private BaseService getBaseService() {
        BaseService baseService =
                SpringContextUtil.getBeanByClass(BaseService.class, new String[]{this.getClass().getName()});
        if (baseService == null) {
            throw new BusinessException("找不到" + this.getClass().getName() + " 对应的服务类");
        }
        return baseService;
    }

    /**
     * 把自己更新到db中去
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean update() {
        return getBaseService().updateSelectiveById(this) != 0;
    }

    /**
     * 删除我自己
     *
     * @return 是否删除成功
     * @throws BusinessException
     */
    public boolean delete()
            throws BusinessException {
        Field idField = getIdField(true);
        idField.setAccessible(true);
        try {
            Object id = idField.get(this);
            if (id == null) {
                throw new BusinessException(this.toString() + "没有设置id");
            }
            return getBaseService().deleteById(id) != 0;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SneakyThrows
    public Object getId() {
        Field idField = getIdField(false);
        if (idField == null) {
            return null;
        }
        idField.setAccessible(true);
        return idField.get(this);
    }

    /**
     * 根据自己的条件去查询
     *
     * @return 自己实际db中的数据
     */
    @SuppressWarnings("unchecked")
    public T find() {
        return (T) getBaseService().selectBean(this);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findList() {
        return getBaseService().selectPage(this, Constant.PAGE_ALL, Constant.PAGE_ALL);
    }

    /**
     * 获取子类id字段
     *
     * @return 子类id字段
     */
    private Field getIdField(boolean isThrowError) {
        if (ID_FIELD_CACHE_MAP.containsKey(this.getClass())) {
            return ID_FIELD_CACHE_MAP.get(this.getClass());
        }
        List<Field> fieldList = ReflectUtils.getAnnotationField(this.getClass(), javax.persistence.Id.class);
        if (fieldList.size() == 0) {
            if (isThrowError) {
                throw new BusinessException("找不到" + this.getClass() + "的id注解");
            }
            return null;
        }
        ID_FIELD_CACHE_MAP.put(this.getClass(), fieldList.get(0));
        return fieldList.get(0);
    }


    public boolean insert() {
        SqlSession sqlSession = this.sqlSession();

        boolean var2;
        try {
            var2 = SqlHelper.retBool(sqlSession.insert(this.sqlStatement(SqlMethod.INSERT_ONE), this));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var2;
    }

    public boolean insertOrUpdate() {
        return !StringUtils.checkValNull(this.pkVal()) && !Objects.isNull(this.selectById(this.pkVal())) ? this.updateById() : this.insert();
    }

    public boolean deleteById(Serializable id) {
        SqlSession sqlSession = this.sqlSession();

        boolean var3;
        try {
            var3 = SqlHelper.delBool(sqlSession.delete(this.sqlStatement(SqlMethod.DELETE_BY_ID), id));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var3;
    }

    public boolean deleteById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "deleteById primaryKey is null.", new Object[0]);
        return this.deleteById(this.pkVal());
    }

    public boolean delete(Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(1);
        map.put("ew", queryWrapper);
        SqlSession sqlSession = this.sqlSession();

        boolean var4;
        try {
            var4 = SqlHelper.delBool(sqlSession.delete(this.sqlStatement(SqlMethod.DELETE), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }

    public boolean updateById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "updateById primaryKey is null.", new Object[0]);
        Map<String, Object> map = new HashMap(1);
        map.put("et", this);
        SqlSession sqlSession = this.sqlSession();

        boolean var3;
        try {
            var3 = SqlHelper.retBool(sqlSession.update(this.sqlStatement(SqlMethod.UPDATE_BY_ID), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var3;
    }

    public boolean update(Wrapper<T> updateWrapper) {
        Map<String, Object> map = new HashMap(2);
        map.put("et", this);
        map.put("ew", updateWrapper);
        SqlSession sqlSession = this.sqlSession();

        boolean var4;
        try {
            var4 = SqlHelper.retBool(sqlSession.update(this.sqlStatement(SqlMethod.UPDATE), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }

    public List<T> selectAll() {
        SqlSession sqlSession = this.sqlSession();

        List var2;
        try {
            var2 = sqlSession.selectList(this.sqlStatement(SqlMethod.SELECT_LIST));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var2;
    }

    public T selectById(Serializable id) {
        SqlSession sqlSession = this.sqlSession();

        BaseDO var3;
        try {
            var3 = (BaseDO) sqlSession.selectOne(this.sqlStatement(SqlMethod.SELECT_BY_ID), id);
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return (T) var3;
    }

    public T selectById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "selectById primaryKey is null.", new Object[0]);
        return this.selectById(this.pkVal());
    }

    public List<T> selectList(Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(1);
        map.put("ew", queryWrapper);
        SqlSession sqlSession = this.sqlSession();

        List var4;
        try {
            var4 = sqlSession.selectList(this.sqlStatement(SqlMethod.SELECT_LIST), map);
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }

    public T selectOne(Wrapper<T> queryWrapper) {
        return (T) SqlHelper.getObject(this.selectList(queryWrapper));
    }

    public IPage<T> selectPage(IPage<T> page, Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(2);
        map.put("ew", queryWrapper);
        map.put("page", page);
        SqlSession sqlSession = this.sqlSession();

        try {
            page.setRecords(sqlSession.selectList(this.sqlStatement(SqlMethod.SELECT_PAGE), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return page;
    }

    public Integer selectCount(Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(1);
        map.put("ew", queryWrapper);
        SqlSession sqlSession = this.sqlSession();

        Integer var4;
        try {
            var4 = SqlHelper.retCount((Integer) sqlSession.selectOne(this.sqlStatement(SqlMethod.SELECT_COUNT), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }

    public SqlRunner sql() {
        return new SqlRunner(this.getClass());
    }

    protected SqlSession sqlSession() {
        return SqlHelper.sqlSession(this.getClass());
    }

    protected String sqlStatement(SqlMethod sqlMethod) {
        return this.sqlStatement(sqlMethod.getMethod());
    }

    protected String sqlStatement(String sqlMethod) {
        return SqlHelper.table(this.getClass()).getSqlStatement(sqlMethod);
    }

    protected Serializable pkVal() {
        return (Serializable) ReflectionKit.getMethodValue(this, TableInfoHelper.getTableInfo(this.getClass()).getKeyProperty());
    }

    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(this.getClass()));
    }


}
