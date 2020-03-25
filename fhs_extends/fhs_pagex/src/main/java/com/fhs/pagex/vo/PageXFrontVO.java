package com.fhs.pagex.vo;


import com.fhs.common.utils.ConverterUtils;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pagex 前端配置 DTO
 */
@Data
public class PageXFrontVO extends PagexBaseVO {

    /**
     * 前段对象
     */
    private ScriptObjectMirror frontObject;

    /**
     * 前段接口配置
     */
    private Map<String,FrontApi> frontApiMap = new HashMap();

    /**
     * 解析js 返回对象
     * @param js js
     * @throws NoSuchMethodException 如果调用某些方法找不到
     * @throws ScriptException 脚本本身有问题
     */
    public PageXFrontVO(String js) throws NoSuchMethodException, ScriptException {
        super.initScriptEngine(js);
        frontObject = (ScriptObjectMirror) scriptEngine.get("front");
        this.initModelConfig();
        this.initFrontApi();
    }

    /**
     * 初始化前段接口
     * @throws NoSuchMethodException
     * @throws ScriptException
     */
    public void initFrontApi() throws NoSuchMethodException, ScriptException {
        List<Map<String,Object>> apiSetts = super.getListM("apis",frontObject);
        FrontApi api = null;
        for(Map<String,Object> apiSett:apiSetts)
        {
            api =  FrontApi.builder().name(ConverterUtils.toString(apiSett.get("name")))
                    .type(ConverterUtils.toString(apiSett.get("type"))).
                            isJSONP(ConverterUtils.toBoolean(apiSett.get("jsonp"))).build();
            if(apiSett.get("inF")!=null)
            {
                api.setHasINF(true);
                api.setInFs(ConverterUtils.toString(apiSett.get("inF")).split(","));
            }
            if(apiSett.get("outF")!=null)
            {
                api.setHasEXF(true);
                api.setExFs(ConverterUtils.toString(apiSett.get("exF")).split(","));
            }
            api.setWheres(ConverterUtils.toString(apiSett.get("where")).split(","));
            frontApiMap.put(ConverterUtils.toString(apiSett.get("name")),api);
        }
    }

    @Override
    Object getOtherFunctionJsObject() {
        // 不需要，所以返回null
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FrontApi{
        /**
         * 接口名字
         */
        private String name;

        /**
         * 是否是jsonp接口
         */
        private boolean isJSONP;

        /**
         * 是否有include 字段
         */
        private boolean isHasINF;

        /**
         * 是否有排除字段
         */
        private boolean isHasEXF;

        /**
         * 支持哪些where条件
         */
        private String[] wheres;

        /**
         * include 字段
         */
        private String[] inFs;
        /**
         * exclude 字段
         */
        private String[] exFs;

        /**
         * 类型
         */
        private String type;
    }
}
