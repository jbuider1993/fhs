package com.fhs.core.base.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fhs.common.utils.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有的VO DO都需要继承此类
 * @param <T>
 */
@SuppressWarnings({"serial", "rawtypes"})
public class SuperBean<T extends SuperBean>  extends BaseObject<T>{

    /**
     * 翻译map 给transervice用的
     */
    @TableField(exist=false)
	private Map<String,String> transMap = new HashMap<>();

    /**
     * 数据权限
     */
    @TableField(exist=false)
    private Map<String,String> dataPermissin = new HashMap<>();

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

    @TableField(exist=false)
    private Map<String,Object> userInfo =new HashMap<>();

    /**
     * 将一组过滤条件添加到in中
     * @param field 字段名字
     * @param inParam 参数集合
     */
    public void add2In(String field,List<String> inParam)
    {
        inFilter.put(field,StringUtil.getStrToIn(inParam));
    }

    public Map<String, String> getTransMap() {
        return transMap;
    }

    public void setTransMap(Map<String, String> transMap) {
        this.transMap = transMap;
    }

    public Map<String, String> getDataPermissin() {
        return dataPermissin;
    }

    public void setDataPermissin(Map<String, String> dataPermissin) {
        this.dataPermissin = dataPermissin;
    }

    public Map<String, String> getBetween() {
        return between;
    }

    public void setBetween(Map<String, String> between) {
        this.between = between;
    }

    public Map<String, String> getInFilter() {
        return inFilter;
    }

    public void setInFilter(Map<String, String> inFilter) {
        this.inFilter = inFilter;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }
}
