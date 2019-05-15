package com.fhs.pagex.service.xsimpl;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.BusinessException;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.dto.PagexListSettDTO;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.tag.grid.BaseGridTag;
import com.fhs.pagex.tag.grid.GridTagFactory;
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
 * 处理pagex列表页面的服务
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex
 * @ClassName: PagexListService
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:07
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:07
 * @Version: 1.0
 */
@Component
public class   PagexListService implements IPageXService, InitializingBean {


    private static final Logger LOG = Logger.getLogger(PagexListService.class);




    /**
     *  拼接html
     * @param request request
     * @param response response
     * @param js js配置
     * @param namespace 命名空间
     * @return 列表页HTML
     */
    public String service(HttpServletRequest request, HttpServletResponse response,String js,String namespace) throws NoSuchMethodException, ScriptException {
        StringBuilder htmlBuilder = new StringBuilder();
        PagexListSettDTO listPageSett =  PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace);
        if(!listPageSett.getModelConfig().containsKey("dataGridUrl"))
        {
            listPageSett.getModelConfig().put("dataGridUrl", EConfig.getPathPropertiesValue("basePath") +
                    "/ms/x/" + namespace + "/findPager");
        }
        if(!listPageSett.getModelConfig().get("dataGridUrl").toString().contains("?"))
        {
            listPageSett.getModelConfig().put("dataGridUrl",listPageSett.getModelConfig().get("dataGridUrl") + "?1=1");
        }
        if(!listPageSett.getModelConfig().containsKey("delUrl"))
        {
            listPageSett.getModelConfig().put("delUrl", EConfig.getPathPropertiesValue("basePath") +
                    "/ms/x/" + namespace + "/del");
        }
        StringBuilder formFieldSetts = new StringBuilder("");
        List<Map<String, Object>> fieldSettList = PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace).getFormFieldSett();
        for(Map<String,Object> field : fieldSettList)
        {
            formFieldSetts.append(field.get("type") + ",");
        }

        if(listPageSett.getExcss()!=null){
            formFieldSetts.append(",excss");
        }
        if(listPageSett.getExjs()!=null){
            formFieldSetts.append(",exjss");
        }
        // 普通的过滤条件参数 map包含name和val 2个key其中val为此过滤条件的获取值的代码
        List<Map<String,String>> filterParams = new ArrayList<>();
        List<Map<String,String>> filterParamsForBetween = new ArrayList<>();
        String filtersHtml = createFiltersHtml(request,response,listPageSett,filterParams,filterParamsForBetween);
        try {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("filtersHtml",filtersHtml);
            paramMap.put("modelConfig",listPageSett.getModelConfig());
            paramMap.put("listFields",listPageSett.getListSett());
            paramMap.put("namespace",namespace);
            paramMap.put("formFieldTypes",formFieldSetts.toString());
            paramMap.put("excss",listPageSett.getExcss());
            paramMap.put("exjs",listPageSett.getExjs());
            paramMap.put("buttons",listPageSett.getButtons());
            paramMap.put("disableButtons",listPageSett.getDisableButtons());
            paramMap.put("filterParams",filterParams);
            paramMap.put("filterParamsForBetween",filterParamsForBetween);
            paramMap.put("otherFunctions",listPageSett.getOtherFunctions());
            String resultHtml = BeetlUtil.renderBeelt("/pagex/list_template.html", paramMap);
            PagexDataService.SIGNEL.getListPageHtmlCache().put(namespace,resultHtml);
            return resultHtml;
        } catch (Exception e) {
            LOG.error(this,e);
            throw new BusinessException("页面解析错误");
        }
    }


    /**
     * 拼接ToolsBarHtml <div id="toolsbar"></div>
     * @param pagexListSettDTO 列表页配置
     * @param filterParams 过滤条件获取值的代码配置
     * @param filterParamsForBetween 过滤条件获取值的代码配置-between条件处理
     * @return ToolsBar html
     */
    public String createFiltersHtml(HttpServletRequest request, HttpServletResponse response,
                                     PagexListSettDTO pagexListSettDTO, List<Map<String,String>> filterParams,List<Map<String,String>> filterParamsForBetween)
    {
        String type = null;
        Class gridTagClass = null;
        BaseGridTag gridTag = null;
        StringBuilder filtersBuilder = new StringBuilder();
        Map<String,Object> field = null;
        for(Map<String,Object> tempField : pagexListSettDTO.getFilters())
        {
            field = new HashMap<>();
            field.putAll(tempField);
            type = ConverterUtils.toString(field.get("type"));
            gridTagClass = GridTagFactory.getTag(type);
            if(gridTagClass == null)
            {
                LOG.error("系统不支持的列表type:" + type);
                continue;
            }
            try {

                field.put("name", ColumnNameUtil.underlineToCamel(ConverterUtils.toString(field.get("name"))));
                gridTag = (BaseGridTag)gridTagClass.newInstance();
                gridTag.setTagSett(field,request,response);
                gridTag.initReloadParam(filterParams,filterParamsForBetween);
                filtersBuilder.append(gridTag.getHtmlForToolsBar());
            } catch (InstantiationException e) {
                LOG.error(this,e);
            } catch (IllegalAccessException e) {
                LOG.error(this,e);
            }
        }
        return filtersBuilder.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        HandelPageXService.SIGEL.registerPageXService("list.jsp",this);
    }
}
