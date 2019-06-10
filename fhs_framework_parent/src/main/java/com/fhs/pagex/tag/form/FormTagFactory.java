package com.fhs.pagex.tag.form;

import com.fhs.core.exception.ParamException;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: FormTagFactory
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 18:44
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 18:44
 * @Version: 1.0
 */
public class FormTagFactory {

    /**
     * key标签名字 value标签对象
     */
    private static Map<String,Class> formTagMap = new HashMap<>();

    /**
     * key标签名字 value标签对象
     */
    private static Map<String,Class<? extends IOne2XTag>> formOne2XTagMap = new HashMap<>();

    /**
     * 根据tag别名获取tag
     * @param tagName tag别名
     * @return tag
     */
    public static Class getTag(String tagName)
    {
        if(!formTagMap.containsKey(tagName))
        {
            throw new ParamException(tagName + "不存在");
        }
        return formTagMap.get(tagName);
    }

    /**
     * 根据tag别名获取tag
     * @param tagName tag别名
     * @return tag
     */
    public static Class<? extends IOne2XTag> getOne2XTag(String tagName)
    {
        if(!formOne2XTagMap.containsKey(tagName))
        {
            throw new ParamException(tagName + "不存在");
        }
        return formOne2XTagMap.get(tagName);
    }

    /**
     * 注册tag
     * @param tagName tag别名
     * @param tag tag
     */
    public static void regTag(String tagName,Class tag)
    {
        if(formTagMap.containsKey(tagName))
        {
            throw new ParamException(tagName + "已存在");
        }
        formTagMap.put(tagName,tag);
    }

    /**
     * 注册tag
     * @param tagName tag别名
     * @param tag tag
     */
    public static void regOne2XTag(String tagName,Class<? extends  IOne2XTag> tag)
    {
        if(formOne2XTagMap.containsKey(tagName))
        {
            throw new ParamException(tagName + "已存在");
        }
        formOne2XTagMap.put(tagName,tag);
    }
}
