package com.fhs.flow.pagex.service.xsimpl;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.BusinessException;
import com.fhs.logger.Logger;
import com.fhs.pagex.common.BeetlUtil;
import com.fhs.pagex.service.HandelPageXService;
import com.fhs.pagex.service.IPageXService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.service.xsimpl.PagexListService;
import com.fhs.pagex.tag.grid.BaseGridTag;
import com.fhs.pagex.tag.grid.GridTagFactory;
import com.fhs.pagex.vo.PagexListSettVO;
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
 *
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
public class PagexFlowGridService extends PagexListService {


    private static final Logger LOG = Logger.getLogger(PagexFlowGridService.class);


    @Override
    public String getListTemplate() {
        return "/pagex/flow_grid_template.html";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        HandelPageXService.SIGEL.registerPageXService("flow_grid.jsp", this);
    }
}
