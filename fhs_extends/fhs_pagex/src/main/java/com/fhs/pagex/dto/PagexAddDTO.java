package com.fhs.pagex.dto;

import com.fhs.common.utils.ConverterUtils;
import com.mybatis.jpa.common.ColumnNameUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.Data;

import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/**
 * pagex 添加页面DTO
 * 本DTO包含解析数据的业务逻辑
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.dto
 * @ClassName: PagexAddDTO
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:33
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:33
 * @Version: 1.0
 */
@Data
public class PagexAddDTO extends PagexBaseDTO {

    /**
     * 添加页面JS对象
     */
    private ScriptObjectMirror addPageObject;

    /**
     * 列表页面字段配置
     */
    private List<Map<String, Object>> formFieldSett;


    /**
     * 解析js 返回对象
     * @param js js
     * @throws NoSuchMethodException 如果调用某些方法找不到
     * @throws ScriptException 脚本本身有问题
     */
    public PagexAddDTO(String js) throws NoSuchMethodException, ScriptException {
        super.initScriptEngine(js);
        addPageObject = (ScriptObjectMirror) scriptEngine.get("add");
        this.initFormFieldSett();
        this.initOtherFunction();
        this.initModelConfig();
    }

    /**
     * 初始化列表字段设置
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initFormFieldSett() throws NoSuchMethodException, ScriptException {
        formFieldSett = getListM("formFields",addPageObject);
        for(Map<String,Object> field:formFieldSett)
        {
            field.put("camelName", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(field.get("name"))));
        }
    }



    @Override
    Object getOtherFunctionJsObject() {
        return addPageObject;
    }


}
