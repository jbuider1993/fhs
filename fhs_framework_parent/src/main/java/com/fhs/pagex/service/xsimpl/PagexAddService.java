package com.fhs.pagex.service.xsimpl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.dto.PagexAddDTO;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.tag.form.BaseFormTag;
import com.fhs.pagex.tag.form.FormTagFactory;
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
     * 向html传递的Map
     */

    private Map<String,Object> paramMap;

    /**
     * 拼接html
     *
     * @param request   request
     * @param response  response
     * @param js        js配置
     * @param namespace 命名空间
     * @return 列表页HTML
     */
    public String service(HttpServletRequest request, HttpServletResponse response, String js, String namespace) throws NoSuchMethodException, ScriptException {

        // 如果有缓存直接在缓存取
        /*if (PagexDataService.SIGNEL.getAddPageHtmlCache().containsKey(namespace)) {
            return PagexDataService.SIGNEL.getAddPageHtmlCache().get(namespace);
        }*/
        PagexAddDTO pageAddSett = PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace);
        //formHtml
        paramMap = new HashMap<>();
        String formHtml = createFormHtml(request, response, pageAddSett);
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
    public String createFormHtml(HttpServletRequest request, HttpServletResponse response, PagexAddDTO pageAddSett) {
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
}
