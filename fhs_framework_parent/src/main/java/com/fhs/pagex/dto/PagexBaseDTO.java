package com.fhs.pagex.dto;

import com.fhs.common.utils.ConverterUtils;
import com.mybatis.jpa.common.ColumnNameUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.Data;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pageX基础的DTO 提供对js的一些基础方法的封装
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.dto
 * @ClassName: PagexBaseDTO
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:19
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:19
 * @Version: 1.0
 */
@Data
public abstract class PagexBaseDTO {

    /**
     * 调用对象方法使用
     */
    protected Invocable invocable;
    /**
     * 引擎对象
     */
    protected ScriptEngine scriptEngine;

    /**
     * 自定义的方法字符串
     */
    protected String otherFunctions;

    /**
     * 模型配置
     */
    protected Map<String, Object> modelConfig;

    /**
     * 初始化js引擎
     *
     * @param js
     */
    public void initScriptEngine(String js) {
        ScriptEngineManager sem = new ScriptEngineManager();
        scriptEngine = sem.getEngineByName("js");
        try {
            scriptEngine.eval(js);
            invocable = (Invocable) scriptEngine;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用一个方法拿到一个json数组[{},{}]
     *
     * @param methodKey 方法名字
     * @param obj       对象
     * @return json数组转换成的map
     * @throws NoSuchMethodException 如果方法不存在抛出
     * @throws ScriptException       如果js写的有问题抛出
     */
    protected List<Map<String, Object>> getListM(String methodKey, Object obj) throws NoSuchMethodException, ScriptException {
        ScriptObjectMirror resltArray = (ScriptObjectMirror) invocable.invokeMethod(obj, methodKey);
        List<Map<String, Object>> fieldList = new ArrayList<>();
        Map<String, Object> tempFieldMap = null;
        for (Object i : resltArray.keySet()) {
            ScriptObjectMirror reslt = (ScriptObjectMirror) resltArray.get(i);
            tempFieldMap = new HashMap<>();
            for (Object key : reslt.keySet()) {
                tempFieldMap.put(key.toString(), reslt.get(key));
            }
            fieldList.add(tempFieldMap);
        }
        return fieldList;
    }

    /**
     * 调用一个方法拿到一个map
     *
     * @param methodKey 方法名字
     * @param obj       对象
     * @throws NoSuchMethodException 如果方法不存在抛出
     * @throws ScriptException       如果js写的有问题抛出
     */
    protected Map<String, Object> getMap(String methodKey, Object obj) throws NoSuchMethodException, ScriptException {
        ScriptObjectMirror resltArray = (ScriptObjectMirror) invocable.invokeMethod(obj, methodKey);
        Map<String, Object> result = new HashMap<>();
        for (Object key : resltArray.keySet()) {
            result.put(key.toString(), resltArray.get(key));
        }
        return result;
    }

    /**
     * 执行对象的方法 返回 string 集合 ['','']
     *
     * @param methodKey 方法名字
     * @param obj       对象
     * @return string集合
     * @throws NoSuchMethodException 如果方法不存在抛出
     * @throws ScriptException       如果js写的有问题抛出
     */
    protected List<String> getListS(String methodKey, Object obj) throws NoSuchMethodException, ScriptException {
        ScriptObjectMirror resltArray = (ScriptObjectMirror) invocable.invokeMethod(obj, methodKey);
        List<String> disableButtonList = new ArrayList<>();
        Map<String, Object> tempFieldMap = null;
        for (Object i : resltArray.keySet()) {
            disableButtonList.add(resltArray.get(i).toString());
        }
        return disableButtonList;
    }

    /**
     * 初始化其他的方法
     *
     * @throws NoSuchMethodException fan
     * @throws ScriptException
     */
    public void initOtherFunction() throws NoSuchMethodException, ScriptException {
        StringBuilder resultBuilder = new StringBuilder();
        ScriptObjectMirror tempOtherFunctions = (ScriptObjectMirror) invocable.invokeMethod(getOtherFunctionJsObject(), "otherFunctions");
        for (Object key : tempOtherFunctions.keySet()) {
            resultBuilder.append(key + "=" + tempOtherFunctions.get(key) + "\n");
        }
        otherFunctions = resultBuilder.toString();
    }

    /**
     * 初始化model config
     */
    public void initModelConfig() {
        modelConfig = new HashMap<>();
        ScriptObjectMirror modelConfigObject = (ScriptObjectMirror) scriptEngine.get("modelConfig");
        modelConfig.putAll(modelConfigObject);
        modelConfig.put("pkeyCamel", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(modelConfig.get("pkey"))));
    }

    /**
     * 子类需要给父类一个调用otherfunction的js对象
     *
     * @return
     */
    abstract Object getOtherFunctionJsObject();
}
