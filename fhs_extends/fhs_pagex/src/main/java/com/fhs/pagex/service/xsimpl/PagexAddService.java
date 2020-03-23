package com.fhs.pagex.service.xsimpl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.config.EConfig;
import com.fhs.logger.Logger;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.dto.PagexAddDTO;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.tag.form.BaseFormTag;
import com.fhs.pagex.tag.form.FormTagFactory;
import com.fhs.pagex.tag.form.IOne2XTag;
import com.mybatis.jpa.common.ColumnNameUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理添加,修改，详细页面的服务
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex
 * @ClassName: PagexAddService
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:07
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:07
 * @Version: 1.0
 */
@Component
public class PagexAddService  implements IPageXService, InitializingBean {


    private static final Logger LOG = Logger.getLogger(PagexAddService.class);



    /**
     * 拼接html
     *
     * @param request   request
     * @param response  response
     * @param js        js配置
     * @param namespace 命名空间
     * @return 列表页HTML
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response, String js, String namespace) throws NoSuchMethodException, ScriptException {

        // 如果有缓存直接在缓存取
        /*if (PagexDataService.SIGNEL.getAddPageHtmlCache().containsKey(namespace)) {
            return PagexDataService.SIGNEL.getAddPageHtmlCache().get(namespace);
        }*/
        PagexAddDTO pageAddSett = PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace);
        //formHtml
        Map<String,Object> paramMap = new HashMap<>();
        String formHtml = createFormHtml(request, response, pageAddSett,paramMap);
        //save_url
        if(!pageAddSett.getModelConfig().containsKey("saveUrl"))
        {
            pageAddSett.getModelConfig().put("saveUrl", EConfig.getPathPropertiesValue("basePath") +
                    "/ms/x/" + namespace + "/add");
        }
        if(!pageAddSett.getModelConfig().containsKey("updateUrl"))
        {
            pageAddSett.getModelConfig().put("updateUrl", EConfig.getPathPropertiesValue("basePath") +
                    "/ms/x/" + namespace + "/update/");
        }
        if(!pageAddSett.getModelConfig().containsKey("infoUrl"))
        {
            pageAddSett.getModelConfig().put("infoUrl", EConfig.getPathPropertiesValue("basePath") +
                    "/ms/x/" + namespace + "/info/");
        }
        String resultHtml = null;
        try {
            paramMap.put("formHtml",formHtml);
            paramMap.put("modelConfig",pageAddSett.getModelConfig());
            paramMap.put("otherFunctions",pageAddSett.getOtherFunctions());
            if(PagexDataService.SIGNEL.getAddPageExtendsHtmlPathMap().containsKey(namespace)) {
                paramMap.put("extendsHtml", BeetlUtil.renderBeelt(PagexDataService.SIGNEL.getAddPageExtendsHtmlPathMap().get(namespace), new HashMap<>()));
            }
            resultHtml = BeetlUtil.renderBeelt("/pagex/add_update_template.html",paramMap);
            return resultHtml;
        } catch (Exception e) {
            e.printStackTrace();
            return "code:500,message:" +e.getMessage();
        }

    }


    /**
     * 拼接表单内容 <form>开始</form>结束
     * 此处代码是本系统最复杂的一块
     *
     * @param pageAddSett 添加页配置
     * @return 表单内容
     */
    public String createFormHtml(HttpServletRequest request, HttpServletResponse response, PagexAddDTO pageAddSett, Map<String,Object> paramMap) {
        String type = null;
        Class fromTagClass = null;
        BaseFormTag formTag = null;

        StringBuilder formFieldBuilder = new StringBuilder();

        List<String> readyJsListList = new ArrayList<>();
        List<String> loadSuccessList = new ArrayList<>();
        List<String> onSaveList = new ArrayList<>();
        List<String> overallJsList = new ArrayList<>();
        //上一个tag
        BaseFormTag preTag = null;
        // 上一个tag是否包含endDiv
        Boolean preHasEndDiv = true;

        // 获取所有的表单字段
        for (Map<String, Object> field : pageAddSett.getFormFieldSett()) {
            type = ConverterUtils.toString(field.get("type"));
            if (CheckUtils.isNullOrEmpty(type))
            {
                continue;
            }
            if("one2x".equals(type))
            {
                formFieldBuilder.append(handelOne2X( readyJsListList ,loadSuccessList, onSaveList, overallJsList,
                         pageAddSett, field, request,  response,paramMap));
                continue;
            }
            fromTagClass = FormTagFactory.getTag(type);
            if (fromTagClass == null) {
                LOG.error("系统不支持的列表type:" + type);
                continue;
            }
            try {
                formTag = (BaseFormTag) fromTagClass.newInstance();
                Map<String,Object> fieldSettMap = new HashMap<>();
                fieldSettMap.putAll(field);
                fieldSettMap.put("name", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(fieldSettMap.get("name"))));
                formTag.setTagSett(fieldSettMap, request, response);
                // 如果是新起一行
                if (formTag.isNewRow()) {
                    if (preHasEndDiv) {
                        formFieldBuilder.append(formTag.getContentHtml());
                    } else{
                        formFieldBuilder.append("</div>" + formTag.getContentHtml());
                    }
                    preHasEndDiv = true;
                } else { // 如果不是新起一行
                    // 如果上一个为空
                    if (CheckUtils.isNullOrEmpty(preTag)) {
                        formFieldBuilder.append("<div class=\"fitem\">"+formTag.getContentHtml());
                        preHasEndDiv = false;
                    } else { // 如果上一个不为空
                        if (preHasEndDiv) {
                            formFieldBuilder.append("<div class=\"fitem\">"+formTag.getContentHtml());
                            preHasEndDiv = false;
                        } else{
                            formFieldBuilder.append( formTag.getContentHtml()+"</div>");
                            preHasEndDiv = true;
                        }
                    }
                }
                preTag = formTag;

                // 获取readyJsList
                if (!CheckUtils.isNullOrEmpty(formTag.readyJs())){
                    readyJsListList.add(formTag.readyJs());
                }
                // 获取loadSuccessList
                if (!CheckUtils.isNullOrEmpty(formTag.loadSuccessJs())){
                    loadSuccessList.add(formTag.loadSuccessJs());
                }
                // 获取onSaveList
                if (!CheckUtils.isNullOrEmpty(formTag.saveJs())){
                    onSaveList.add(formTag.saveJs());
                }
                // 获取overallJsList
                if (!CheckUtils.isNullOrEmpty(formTag.overallJs())){
                    overallJsList.add(formTag.overallJs());
                }
            } catch (InstantiationException e) {
                LOG.error(this, e);
            } catch (IllegalAccessException e) {
                LOG.error(this, e);
            }
        }
        request.setAttribute("tagReadyJsList",readyJsListList);
        request.setAttribute("tagLoadSuccessList",loadSuccessList);
        request.setAttribute("tagOnSaveList",onSaveList);
        request.setAttribute("tagOverallJsList",overallJsList);
        paramMap.put("tagReadyJsList",readyJsListList);
        paramMap.put("tagLoadSuccessList",loadSuccessList);
        paramMap.put("tagOnSaveList",onSaveList);
        paramMap.put("tagOverallJsList",overallJsList);
        return formFieldBuilder.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        HandelPageXService.SIGEL.registerPageXService("add_update.jsp",this);
    }

    private String handelOne2X(List<String> readyJsListList , List<String> loadSuccessList, List<String> onSaveList, List<String> overallJsList,
                               PagexAddDTO pageAddSett, Map<String, Object> field, HttpServletRequest request, HttpServletResponse response, Map<String,Object> paramMap){
        // 头处理
        // template处理
        // load数据处理
        // 保存数据处理
        // 初始化
        String xnamespace = ConverterUtils.toString(field.get("namespace"));
        readyJsListList.add(xnamespace + "_ready()");
        loadSuccessList.add(xnamespace + "_onload(info)");
        onSaveList.add(xnamespace + "_onsave()");
        //找到需要关联的dto
        PagexAddDTO xAddDTO = PagexDataService.SIGNEL.getPagexAddDTOFromCache(xnamespace);
        String type = null;
        Class<? extends IOne2XTag> fromTagClass = null;
        IOne2XTag tag = null;
        List<Map<String,Object>> fieldList = new ArrayList<>();
        String firstFieldName = null;
        // 获取所有的表单字段
        for (Map<String, Object> tempField : xAddDTO.getFormFieldSett()) {
            type = ConverterUtils.toString(tempField.get("type"));
            //如果type为空或者没有标记支持一对多显示则返回
            if (CheckUtils.isNullOrEmpty(type) || !ConverterUtils.toBoolean(tempField.get("one2x"))) {
                continue;
            }
            if(firstFieldName==null)
            {
                firstFieldName = ColumnNameUtil.underlineToCamel(ConverterUtils.toString(tempField.get("name")));
            }
            fromTagClass = FormTagFactory.getOne2XTag(type);
            try {
                tag = fromTagClass.newInstance();
                tag.makeOne2XModel();
                Map<String,Object> fieldSettMap = new HashMap<>();
                fieldSettMap.putAll(tempField);
                fieldSettMap.put("name", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(fieldSettMap.get("name"))));
                tag.setTagSett(fieldSettMap, request, response);
                fieldSettMap.put("formHtml",tag.getFormHtml().replaceAll("xnamespace",xnamespace).replaceAll("'","\""));
                fieldSettMap.put("setValue",tag.setValueJs());
                fieldSettMap.put("getValue",tag.getValueJs());
                fieldSettMap.put("loadSuccess",tag.one2XDataLoadSuccessJs());
                fieldSettMap.put("save",tag.one2XSaveJs());
                fieldList.add(fieldSettMap);
            } catch (InstantiationException e) {
                LOG.error(this, e);
            } catch (IllegalAccessException e) {
                LOG.error(this, e);
            }
        }
        paramMap.put("tagSett",field);
        paramMap.put("fieldList",fieldList);
        paramMap.put("xnamespace",xnamespace);
        paramMap.put("modelConfig",xAddDTO.getModelConfig());
        paramMap.put("firstFieldName",firstFieldName);
        paramMap.put("sourceModelConfig",pageAddSett.getModelConfig());

        paramMap.put("otherFunctions",pageAddSett.getOtherFunctions());
        return BeetlUtil.renderBeelt("/pagex/one2x_template.html",paramMap);
    }
}
