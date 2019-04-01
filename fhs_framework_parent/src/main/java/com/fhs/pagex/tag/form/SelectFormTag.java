package com.fhs.pagex.tag.form;

import com.fhs.common.utils.ConverterUtils;
import org.springframework.stereotype.Component;

/**
 * 下拉标签 easyui combobox
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: SelectFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/4 0004 19:32
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/4 0004 19:32
 * @Version: 1.0
 */
@Component
public class SelectFormTag extends  BaseEasyuiCombo {
    static
    {
        FormTagFactory.regTag("select",SelectFormTag.class);
    }

    @Override
    public String getEasyuiClass() {
        return "easyui-combobox";
    }
    @Override
    public String overallJs() {
        //这块js给你当做全局变量，比如你储存一个全局变量，就可以把代码写到这里
        return "function  setSelectHiddenValue_" + name+"(){" +
                "$('#"+name+"').val($('#"+name+"_select').combobox('getValues')+'');" +
                "return true;}";
    }

    @Override
    public String loadSuccessJs() {
        // 如果不是多选 return "";
        if(ConverterUtils.toBoolean(tagSett.get("multiple"))){
            return  "if(info."+name+" && info."+name+" !=''){" +
                    " $('#"+name+"_select').combobox('setValues',info."+name+".split(','))}";
        }
        return "";
    }

    @Override
    public String saveJs() {
        //当点击保存的时候需要执行的js，比如图片的value获取，图片的必填校验，UE的必填校验都写到这里
        //写必填校验的时候记得用EalertE 记得验证不通过 使用return来组织程序继续往下面执行。
        if(ConverterUtils.toBoolean(tagSett.get("multiple"))){
            return "setSelectHiddenValue_" + name+"()";
        }
        return "";
    }

    @Override
    protected String[] getHandelKeys() {
        return new String[0];
    }
}
