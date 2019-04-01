package com.fhs.pagex.service.xsimpl;

import com.fhs.common.utils.Logger;
import com.fhs.config.BeetlConf;
import com.fhs.pagex.dto.PageXTreeDTO;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.PagexDataService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理pagexTree页面的服务
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
public class PagexTreeService  implements IPageXService, InitializingBean {

    private static final Logger LOG = Logger.getLogger(PagexTreeService.class);



    /**
     *  拼接html
     * @param request request
     * @param response response
     * @param js js配置
     * @param namespace 命名空间
     * @return Tree页HTML
     */
    public String service(HttpServletRequest request, HttpServletResponse response,String js,String namespace) throws NoSuchMethodException, ScriptException {
        PageXTreeDTO treeDTO = PagexDataService.SIGNEL.getPageXTreeDTOFromCache(namespace);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("modelConfig",treeDTO.getModelConfig());
        paramMap.put("keySett",treeDTO.getKeySettMap());
        paramMap.put("listUrlSettMap",treeDTO.getListUrlSettMap());
        return BeetlConf.beetlConf.renderBeelt("/pagex/tree_template.html",paramMap);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        HandelPageXService.SIGEL.registerPageXService("tree.jsp",this);
    }

}
