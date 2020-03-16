package com.fhs.pagex.tag.grid;

import com.fhs.pagex.tag.PagexBaseTag;

import java.util.List;
import java.util.Map;


/**
 * 列表过滤条件标签基础类
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: BaseGridTag
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 12:50
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 12:50
 * @Version: 1.0
 */
public abstract class BaseGridTag extends PagexBaseTag {

    /**
     * 此字段提交表单的时候给后台传的name
     */
    public static final int FORM_NAME = 0;

    /**
     * 获取此过滤条件的value 是什么js
     */
    public static final int VALUE_JS_CODE = 1;

    /**
     * 获取 标签在toolsbar的html
     * @return  标签在toolsbar的html
     */
    public abstract String getHtmlForToolsBar();



    /**
     * 在reload的时候如何获取值，一般的就是$('#idF').val();
     * @param filterParams 过滤条件获取值的代码配置
     * @param filterParamsForBetween 过滤条件获取值的代码配置-between条件处理
     */
    public abstract void initReloadParam(List<Map<String,String>> filterParams, List<Map<String,String>> filterParamsForBetween);

    /**
     * 格式化自动提示
     */
    public String formartPlaceholderHtml(){
        return " placeholder='请填写" + super.tagSett.get("title") + "' prompt='请选择" + super.tagSett.get("title") + "' ";
    }

    /**
     * 格式化id
     * @return
     */
    public String formartIdHtml()
    {
        return  " id='" + super.tagSett.get("name") + "F'";
    }

    @Override
    public void render() {

    }
}
