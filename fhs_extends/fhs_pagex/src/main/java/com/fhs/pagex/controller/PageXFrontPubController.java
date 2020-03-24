package com.fhs.pagex.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.core.result.PubResult;
import com.fhs.pagex.dto.PageXFrontDTO;
import com.fhs.pagex.service.PagexDataService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * pagex自动生成前端接口  by jackwong
 */
@RestController
@RequestMapping("/front/x/")
public class PageXFrontPubController extends PageXBaseController implements InitializingBean {

    /**
     * 哪些方法该交给谁处理
     */
    private Map<String, FunctionHandle> functionHandleMap = new HashMap<>();


    /**
     * 处理前段请求，先组织参数，然后调用方法 在处理方法返回值
     *
     * @param namespace    namespace
     * @param functionName 方法名字
     */
    @RequestMapping("{namespace}/{functionName}")
    public HttpResult<Object> handleFunction(@PathVariable("namespace") String namespace, @PathVariable("functionName") String functionName,
                                             HttpServletRequest request, HttpServletResponse response) {
        // 校验参数 start
        if (namespace == null || functionName == null) {
            throw new ParamException("参数不完整，namespace或者functionName为null");
        }
        PageXFrontDTO frontDTO = PagexDataService.SIGNEL.getPageXFrontDTOFromCache(namespace);
        if (!frontDTO.getFrontApiMap().containsKey(functionName)) {
            return PubResult.NO_FIND.asResult();
        }
        // 校验参数 end


        PageXFrontDTO.FrontApi api = frontDTO.getFrontApiMap().get(functionName);
        FunctionHandle handle = functionHandleMap.get(api.getType());

        // 处理参数start
        Map<String, Object> parameterMap = new HashMap<>();
        //后续可自定义匹配类型 如 like > <
        for (String where : api.getWheres()) {
            parameterMap.put(where, request.getParameter(where));
        }
        parameterMap.put(Constant.PAGE, request.getParameter(Constant.PAGE));
        parameterMap.put(Constant.ROWS, request.getParameter(Constant.ROWS));
        //处理参数end

        FunctionResult functionResult = handle.handel(namespace, parameterMap);

        //处理返回结果start

        //返回值是int不用处理结果
        if (functionResult.getType() == FunctionHandle.TYPE_INT) {
            return handelHttpReulst(api,functionResult.getResultInt(),request,response);
        }
        //如果返回值是单个对象
        if (functionResult.getType() == FunctionHandle.TYPE_ONE) {
            if (api.isHasEXF()) {
                return handelHttpReulst(api,handleExFields(functionResult.getResultObject(), api.getExFs()),request,response);
            }
            if (api.isHasINF()) {
                return handelHttpReulst(api,handleInFields(functionResult.getResultObject(), api.getInFs()),request,response);
            }
            //以上2个都没有直接返回
            return  handelHttpReulst(api,functionResult.getResultObject(),request,response);
        }
        //如果返回值是单个对象
        if (functionResult.getType() == FunctionHandle.TYPE_MANY) {
            //不指定返回也不指定include 就直接把查询到的结果返回回去
            if (api.isHasEXF() || api.isHasINF()) {
                return handelHttpReulst(api,functionResult.getResultArray(),request,response);
            }
            JSONArray resultArray = new JSONArray();
            for (int i = 0; i < functionResult.getResultArray().size(); i++) {
                if (api.isHasEXF()) {
                    resultArray.add(handleExFields(functionResult.getResultArray().getJSONObject(i), api.getExFs()));
                } else {
                    resultArray.add(handleInFields(functionResult.getResultArray().getJSONObject(i), api.getInFs()));
                }
            }
            return handelHttpReulst(api,resultArray,request,response);
        }
        //处理返回结果end
        return handelHttpReulst(api,"js配置错误",request,response);
    }

    /**
     * 对于返回的数据进行包装
     * 在jsonp或者客户端指定不要httpresult的时候不用httpresult返回
     * @param api  api对象
     * @param resultData 给前段的数据
     * @param request request
     * @param response response
     * @return 只有前端不传notHttpResult并且接口不支持jsonp的时候给前端返回Httpresult
     */
    public HttpResult<Object> handelHttpReulst(PageXFrontDTO.FrontApi api,Object resultData,HttpServletRequest request,HttpServletResponse response){
        if(api.isJSONP())
        {
            if(request.getParameter("jsonpCallback")!=null)
            {
                if(request.getParameter("notHttpResult")!=null)
                {
                    super.outJsonp(JsonUtils.object2json(resultData));
                    return null;
                }
                super.outJsonp(HttpResult.success(resultData).asJson());
                return null;
            }
        }
        if(request.getParameter("notHttpResult")!=null)
        {
            super.outWriteJson(JsonUtils.object2json(resultData));
            return null;
        }
        return HttpResult.success(resultData);
    }


    /**
     * 只保留需要的字段
     *
     * @param source 原来的对象
     * @param inFs   保留的字段
     * @return 结果
     */
    private JSONObject handleInFields(JSONObject source, String[] inFs) {
        JSONObject result = new JSONObject();
        for (String field : inFs) {
            result.put(field, source.get(field));
        }
        return result;
    }

    /**
     * 排除指定的字段
     *
     * @param source 原来的对象
     * @param exFs   保留的字段
     * @return 结果
     */
    private JSONObject handleExFields(JSONObject source, String[] exFs) {
        for (String field : exFs) {
            source.remove(field);
        }
        return source;
    }

    /**
     * 查询单个对象
     *
     * @param namespace namespace
     * @param paramMap  参数map
     * @return
     */
    private FunctionResult handelOne(String namespace, Map<String, Object> paramMap) {
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        String result = service.findBean(paramMap, namespace);
        return FunctionResult.builder().type(FunctionHandle.TYPE_ONE).resultObject(JSON.parseObject(result)).build();
    }

    /**
     * 查询所有的数据
     *
     * @param namespace namespace
     * @param paramMap  参数map
     * @return
     */
    private FunctionResult handelList(String namespace, Map<String, Object> paramMap) {
        if (paramMap.containsKey(Constant.PAGE)) {
            paramMap.put(Constant.START, Constant.PAGE_ALL);
        } else {
            int page = ConverterUtils.toInt(paramMap.get(Constant.PAGE));
            int rows = ConverterUtils.toInt(paramMap.get(Constant.ROWS));
            PageSizeInfo pageSizeInfo = getPageSizeInfo(page, rows);
            paramMap.put(Constant.START, pageSizeInfo.getPageStart());
            paramMap.put(Constant.END, pageSizeInfo.getPageSize());
        }

        return FunctionResult.builder().type(FunctionHandle.TYPE_MANY).resultArray(super.findListDataAndInitJoin(namespace,paramMap)).build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        functionHandleMap.put("one", this::handelOne);
        functionHandleMap.put("list", this::handelList);
    }
}

@FunctionalInterface
interface FunctionHandle {
    int TYPE_ONE = 0;
    int TYPE_MANY = 1;
    int TYPE_INT = 2;

    FunctionResult handel(String namespace, Map<String, Object> paramMap);
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class FunctionResult {

    /**
     * 类型
     */
    int type;

    /**
     * 单条记录
     */
    JSONObject resultObject;

    /**
     * 多条记录
     */
    JSONArray resultArray;

    /**
     * 如果返回count或者受影响行数
     */
    int resultInt;
}
