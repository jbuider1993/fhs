package com.fhs.core.base.service.impl;

import com.mybatis.jpa.common.AssociationUtil;
import com.mybatis.jpa.meta.NestedMeta;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.core.base.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 *  级联insert 和级联删除 工具类
 * @author xiaoh
 * @version [版本号, 2018/5/11 16:42]
 * @Description:
 * @versio 1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@Component
public class NestedServiceImpl<T> {

    /**
     * 一对多cache
     */
    private Map<Class<?>,List<OnetoXSett>> oneToXFieldCache = new HashMap<>();

    /**
     * 类 主键 缓存
     */
    private Map<Class<?>,Field> pkMap = new HashMap<>();

    /**
     * 插入一个对象，自动处理其子集
     * @param obj 对象
     * @throws IllegalAccessException
     */
    public void insertOnetoX(T obj) throws IllegalAccessException {
        List<OnetoXSett> oneToXFieldList = getSett(obj.getClass());
        if(oneToXFieldList.isEmpty())
        {
            return;
        }
        Field pkField = pkMap.get(obj.getClass());
        pkField.setAccessible(true);
        Object  pKey = pkField.get(obj);
        oneToXFieldList.forEach(onetoXSett->{
            Object fieldValue = null;
            try {
                Field field = onetoXSett.getField();
                field.setAccessible(true);
                fieldValue = field.get(obj);
                if(fieldValue==null){
                    return;
                }
                Field mappedField = onetoXSett.getMappedByField();
                mappedField.setAccessible(true);
                if(onetoXSett.isOnetoOne()){
                    mappedField.set(fieldValue,pKey);
                }else{
                    for (Object xSett : (List) fieldValue) {
                        mappedField.set(xSett,pKey);
                    }
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BaseService baseService = SpringContextUtil.getBeanByClass(BaseService.class,
                    new String[]{onetoXSett.getItemClass().getName()});
            if(onetoXSett.isOnetoOne()){
                baseService.insert(fieldValue);
            }else{
                baseService.batchInsert((List) fieldValue);
            }
        });
    }

    /**
     *  级联删除一个对象
     * @param obj obj
     * @throws IllegalAccessException 如果参数错误则抛出
     */
    public void deleteOneToX(T obj) throws IllegalAccessException {
        List<OnetoXSett> oneToXFieldList = getSett(obj.getClass());
        if(oneToXFieldList.isEmpty())
        {
            return;
        }
        Field field = pkMap.get(obj.getClass());
        field.setAccessible(true);
        Object  pKey = field.get(obj);
        oneToXFieldList.forEach(onetoXSett->{
            Object item = null;
            try {
                item = onetoXSett.getItemClass().newInstance();
                Field mappedByField = onetoXSett.getMappedByField();
                mappedByField.setAccessible(true);
                onetoXSett.getMappedByField().set(item,pKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseService baseService = SpringContextUtil.getBeanByClass(BaseService.class,
                    new String[]{onetoXSett.getItemClass().getName()});
            baseService.deleteBean(item);
        });
       // keng 没有删除主表内容
    }

    /**
     * 根据当前对象构造参数
     * @param clzss
     * @return
     */
    private List<OnetoXSett> getSett(Class<?> clzss)
    {
        if(oneToXFieldCache.containsKey(clzss))
        {
            return oneToXFieldCache.get(clzss);
        }else{
            //获取当前类的主键
            Field pkField =  AssociationUtil.getPkFields(clzss);
            pkMap.put(clzss,pkField);

            List<Field> associationFields = AssociationUtil.getAssociationFields(clzss);
            if (!associationFields.isEmpty()) {
                List<OnetoXSett> list = new ArrayList<>();
                for (Field field : associationFields) {
                    if (field.isAnnotationPresent(OneToOne.class)) {
                        NestedMeta nestedMeta = new NestedMeta();
                        Class<?> javaType = field.getType();
                        OnetoXSett onetoXSett = new OnetoXSett();
                        onetoXSett.setField(field);
                        onetoXSett.setItemClass(javaType);
                        onetoXSett.setOnetoOne(true);
                        onetoXSett.setMappedByField(getMappedByField(javaType,AssociationUtil.getMappedName(field)));
                        list.add(onetoXSett);
                    }
                    if (field.isAnnotationPresent(OneToMany.class)) {
                        Type genericType = field.getGenericType();
                        if (genericType instanceof ParameterizedType) {
                            NestedMeta nestedMeta = new NestedMeta();
                            ParameterizedType pt = (ParameterizedType) genericType;
                            Class<?> actualType = (Class<?>) pt.getActualTypeArguments()[0];
                            OnetoXSett onetoXSett = new OnetoXSett();
                            onetoXSett.setField(field);
                            onetoXSett.setItemClass(actualType);
                            onetoXSett.setOnetoOne(false);
                            onetoXSett.setMappedByField(getMappedByField(actualType,AssociationUtil.getMappedName(field)));
                            list.add(onetoXSett);
                        }
                    }
                }
                oneToXFieldCache.put(clzss,list);
            }
        }
        return oneToXFieldCache.get(clzss);
    }

    /**
     * 根据class和外键字段获取级联对象的属性名
     * @param clazz
     * @param mappedByStr
     * @return
     */
    private Field getMappedByField(Class<?> clazz,String mappedByStr){
        Field[] fields = clazz.getDeclaredFields();
        System.out.println(fields.length);

        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if(column==null){
                continue;
            }
            if(StringUtils.equals(column.name(),mappedByStr)){
                return field;
            }
        }
        return null;
    }

    private static class OnetoXSett
    {
        //主表的
        private Field field;
        private boolean isOnetoOne;
        private Class<?> itemClass;
        // 附表的
        private Field mappedByField;

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public boolean isOnetoOne() {
            return isOnetoOne;
        }

        public void setOnetoOne(boolean onetoOne) {
            isOnetoOne = onetoOne;
        }

        public Class<?> getItemClass() {
            return itemClass;
        }

        public void setItemClass(Class<?> itemClass) {
            this.itemClass = itemClass;
        }

        public Field getMappedByField() {
            return mappedByField;
        }

        public void setMappedByField(Field mappedByField) {
            this.mappedByField = mappedByField;
        }
    }
}
