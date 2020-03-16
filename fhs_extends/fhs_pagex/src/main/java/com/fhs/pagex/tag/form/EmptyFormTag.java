package com.fhs.pagex.tag.form;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: EmptyFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/4 0004 19:44
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/4 0004 19:44
 * @Version: 1.0
 */
public abstract class EmptyFormTag extends BaseFormTag{


    @Override
    public String readyJs() {
        //当document.ready 的时候执行这里的js代码，比如文件服务的initFileupalod那些代码，比如UE的初始化代码等等，比如百度地图的一些代码
        return "";
    }

    @Override
    public String loadSuccessJs() {
        // 当加载成功的时候需要写的代码，比如图片回显，百度地图默认点标记
        return "";
    }

    @Override
    public String saveJs() {
        //当点击保存的时候需要执行的js，比如图片的value获取，图片的必填校验，UE的必填校验都写到这里
        //写必填校验的时候记得用EalertE 记得验证不通过 使用return来组织程序继续往下面执行。
        return "";
    }

    @Override
    public String overallJs() {
        //这块js给你当做全局变量，比如你储存一个全局变量，就可以把代码写到这里
        return "";
    }


    public String getFormHtml() {
        return getContentHtml();
    }


    public String one2XDataLoadSuccessJs() {
        return null;
    }


    public String one2XSaveJs() {
        return null;
    }


    public String one2XOverallJs() {
        return null;
    }


    public String setValueJs() {
        return "val(";
    }


    public String getValueJs() {
        return "val()";
    }

}
