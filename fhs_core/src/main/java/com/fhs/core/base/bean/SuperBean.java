package com.fhs.core.base.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fhs.common.utils.StringUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有的VO DO都需要继承此类
 * @param <T>
 */
@SuppressWarnings({"serial", "rawtypes"})
@Data
public class SuperBean<T extends SuperBean>  extends BaseObject<T>{

    /**
     * 翻译map 给transervice用的
     */
    @TableField(exist=false)
	private Map<String,String> transMap = new HashMap<>();

    /**
     * 配合mybatis jpa between注解过滤条件使用
     */
    @TableField(exist=false)
	private Map<String,String> between = new HashMap<>();

    /**
     * 配合mybatis jpa in注解使用
     */
    @TableField(exist=false)
    private Map<String,String> inFilter = new HashMap<>();

    /**
     * 将一组过滤条件添加到in中
     * @param field 字段名字
     * @param inParam 参数集合
     */
    public void add2In(String field,List<String> inParam)
    {
        inFilter.put(field,StringUtil.getStrToIn(inParam));
    }
}
