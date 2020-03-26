package com.fhs.core.base.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fhs.common.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支持vo 即可进行翻译
 */
public interface VO {
    /**
     * 获取翻译map
     * @return  翻译map
     */
    Map<String,String> getTransMap();

    /**
     * 获取主键
     * @return  主键
     */
    default Object getPkey(){
        Field idField = getIdField(true);
        try {
            return idField.get(this);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 子类id字段缓存
     */
    @TableField(exist = false)
    static final Map<Class<?>, Field> ID_FIELD_CACHE_MAP = new HashMap<>();

    /**
     * 获取子类id字段
     *
     * @return 子类id字段
     */
    default Field getIdField(boolean isThrowError) {
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

    /**
     * 是否软删除
     * @return 0 为软删除 1 已经软删除
     */
    Integer getIsDelete();
}
