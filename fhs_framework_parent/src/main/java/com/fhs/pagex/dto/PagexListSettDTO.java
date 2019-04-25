package com.fhs.pagex.dto;

import com.fhs.common.utils.ConverterUtils;
import com.mybatis.jpa.common.ColumnNameUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.script.ScriptException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * pagex 列表页面DTO
 * 本DTO是一个DOMAIN 里面包含部分业务逻辑
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.dto
 * @ClassName: PagexListSettDTO
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:09
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:09
 * @Version: 1.0
 */
@Data
public class PagexListSettDTO extends  PagexBaseDTO{



    /**
     * 列表页面对象
     */
    private ScriptObjectMirror listPageObject;

    /**
     * 列表页面字段配置
     */
    private List<Map<String, Object>> listSett;

    /**
     * 过滤条件
     */
    private List<Map<String, Object>> filters;

    /**
     * 禁用掉的默认给提供的按钮集合
     * 默认会提供export,add,del,update,view,search 6个按钮
     */
    private List<String> disableButtons;

    /**
     * 自定义的按钮，key为按钮名称 value为按钮点击时候需要调用的方法
     * 其中按钮方法以 R结尾 比如 viewR 会获取列表选中行的对象
     * 不以R结尾则是普通按钮
     */
    private List<Map<String, Object>> buttons;

    /**
     * 系统默认支持的一些参数
     */
    private static Set<String> DEFAULT_KEY = new HashSet<>();

    static
    {
        DEFAULT_KEY.add("showField");
        DEFAULT_KEY.add("formart");
        DEFAULT_KEY.add("title");
        DEFAULT_KEY.add("trans");
        DEFAULT_KEY.add("key");
        DEFAULT_KEY.add("name");
        DEFAULT_KEY.add("isJoin");
        DEFAULT_KEY.add("namespace");
    }

    /**
     * 是的话就把删除，查看，编辑三个按钮放到最后一列上
     */
    private boolean isColumnButton;

    /**
     * 解析js 返回对象
     * @param js js
     * @throws NoSuchMethodException 如果调用某些方法找不到
     * @throws ScriptException 脚本本身有问题
     */
    public PagexListSettDTO(String js) throws NoSuchMethodException, ScriptException {
        super.initScriptEngine(js);
        listPageObject = (ScriptObjectMirror) scriptEngine.get("listPage");
        this.initButtons();
        this.initDisableButton();
        this.initFilter();
        this.initListSett();
        this.initOtherFunction();
        this.initIsColumnButton();
        this.initModelConfig();
    }

    /**
     * 初始化列表字段设置
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initListSett() throws NoSuchMethodException, ScriptException {
        listSett = getListM("listFieldSett",listPageObject);
        String showField = null;
        for(Map<String,Object> field:listSett)
        {
            StringBuilder otherAttr = new StringBuilder();
            for(String key:field.keySet())
            {
                if(!DEFAULT_KEY.contains(key))
                {
                    otherAttr.append( key+ ":'"+field.get(key) + "',");
                }
            }
            field.put("otherAttr",otherAttr.toString());
            field.put("camelName", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(field.get("name"))));
            if(field.containsKey("showField"))
            {
                showField = ConverterUtils.toString(field.get("showField"));
                showField = "transMap." + ColumnNameUtil.underlineToCamel(showField.replace("transMap.",""));
                field.put("showField", showField);
            }

        }
    }


    /**
     * 初始化过滤字段设置
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initFilter() throws NoSuchMethodException, ScriptException {
        this.filters = getListM("filters",listPageObject);
    }

    /**
     * 初始化按钮设置
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initButtons() throws NoSuchMethodException, ScriptException {

        buttons = getListM("buttons",listPageObject);;
        for(Map<String,Object> field:buttons)
        {
            field.put("camelName", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(field.get("name"))));
        }
    }

    /**
     * 初始化哪些默认按钮禁用设置
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initDisableButton() throws NoSuchMethodException, ScriptException {
        this.disableButtons = getListS("disableButtons",listPageObject);
    }

    /**
     * 是否在列中添加操作按钮
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initIsColumnButton() throws NoSuchMethodException, ScriptException {
        this.isColumnButton =  (Boolean)invocable.invokeMethod(listPageObject, "isColumnButton");
    }

    @Override
    Object getOtherFunctionJsObject() {
        return listPageObject;
    }

    /**
     * 将自己变成一个Java PO 的代码
     * @return Java PO代码
     */
    public String formartJavaStr(){

        return "";
    }
}
