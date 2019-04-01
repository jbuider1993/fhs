package com.fhs.pagex.tag;

import com.fhs.common.utils.ConverterUtils;
import org.beetl.core.Tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  列表页和表单页面字段共同的base类
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag
 * @ClassName: PagexBaseTag
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 12:50
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 12:50
 * @Version: 1.0
 */
public abstract class PagexBaseTag extends Tag {

    /**
     * easyui 需要特殊处理的属性
     */
    protected static final Set<String> DATA_OPTION_KEY = new HashSet<>();

    /**
     * 所有的插件都支持的key
     */
    protected static final Set<String> DEFAULT_HANDEL_KEYS = new HashSet<>();



    static
    {
        DEFAULT_HANDEL_KEYS.add("title");
        DEFAULT_HANDEL_KEYS.add("name");
        DEFAULT_HANDEL_KEYS.add("dataType");
        DEFAULT_HANDEL_KEYS.add("max");
        DEFAULT_HANDEL_KEYS.add("min");
        DEFAULT_HANDEL_KEYS.add("required");
        DEFAULT_HANDEL_KEYS.add("class");
        DEFAULT_HANDEL_KEYS.add("type");
        DEFAULT_HANDEL_KEYS.add("id");
        DEFAULT_HANDEL_KEYS.add("camelName");

    }


    static
    {
        DATA_OPTION_KEY.add("groupFormatter");
        DATA_OPTION_KEY.add("filter");
        DATA_OPTION_KEY.add("formatter");
        DATA_OPTION_KEY.add("loader");
        DATA_OPTION_KEY.add("loadFilter");
        DATA_OPTION_KEY.add("onBeforeLoad");
        DATA_OPTION_KEY.add("onLoadSuccess");
        DATA_OPTION_KEY.add("onLoadError");
        DATA_OPTION_KEY.add("onSelect");
        DATA_OPTION_KEY.add("onUnselect");
        DATA_OPTION_KEY.add("onShowPanel");
        DATA_OPTION_KEY.add("onHidePanel");
        DATA_OPTION_KEY.add("onChange");
        DATA_OPTION_KEY.add("columns");
        DATA_OPTION_KEY.add("showAll");
    }

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * 标签配置信息
     */
    protected Map<String,Object> tagSett;

    /**
     * 设置tag的配置信息
     * @param tagSett 标签配置
     * @return
     */
    public  void setTagSett(Map<String,Object> tagSett, HttpServletRequest request, HttpServletResponse response)
    {
        this.request=request;
        this.response=response;
        this.tagSett=tagSett;
    }


    /**
     * 如果是easyui的话 自动识别哪些key 放到dataoption中
     */
    protected  String formartEasyuiDataOptions()
    {
        StringBuilder options = new StringBuilder(" data-options=\"");
        for (String optionName : DATA_OPTION_KEY)
        {
            if(tagSett.containsKey(optionName))
            {
                options.append(optionName+":" + ConverterUtils.toString(tagSett.get(optionName)).replace("\"","'") +  ",");
            }
        }
        options.append("\" ");
        return options.toString();
    }

    /**
     * 获取子类不自己处理的属性拼接位html element attr
     *
     */
    protected String getOtherAttrValHtml(){
        String [] handelKeys = getHandelKeys();
        Map<String,Object> tempTagSett = new HashMap<>();
        tempTagSett.putAll(tagSett);
        for(String key : handelKeys)
        {
            tempTagSett.remove(key);
        }
        //easyui dataoptions里面的也去掉
        for(String key : DATA_OPTION_KEY)
        {
            tempTagSett.remove(key);
        }
        //大家都支持的attr 也去掉
        for(String key : DEFAULT_HANDEL_KEYS)
        {
            tempTagSett.remove(key);
        }
        Set<String> keys = tempTagSett.keySet();
        StringBuilder resultBuilder = new StringBuilder();
        for(String key:keys)
        {
            resultBuilder.append(" " + key +"='" +tempTagSett.get(key) + "'" );
        }
        return resultBuilder.toString();
    }

    /**
     * 一些控件默认带class属性，如果程序员又在使用控件的时候又使用到了
     * @param clazz
     * @return
     */
    protected  String formartClass(String clazz)
    {
        return   " class='" + clazz + " " + ConverterUtils.toString(tagSett.get("class")) + "' ";
    }

    /**
     * 页面配置中有多个key，哪些key是程序自己处理的，就给base设置一下
     * 系统已经内置了一部分见 defaultHandelKeys
     */
    protected abstract  String[] getHandelKeys();


}
