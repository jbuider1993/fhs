package com.fhs.workflow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.utils.CheckUtils;
import com.fhs.workflow.bean.WorkFlowListener;
import com.fhs.workflow.service.WorkFlowListenerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程监听器
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/24 11:06:17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("workFlowListener")
public class WorkFlowListenerAction extends ModelSuperAction<WorkFlowListener>
{
	@Resource(name = "workFlowListenerServiceImpl")
	private WorkFlowListenerService workFlowListenerService;
	
	 /**
     * 获取菜单树结构json字符串对象 菜单管理左侧树形结构
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getWorkFlowListenerList")
    @RequiresPermissions("workFlowListener:see")
    public void getOrgStrutureTreesData(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> paramMap = super.getPageTurnNum(request);
        List<WorkFlowListener> dataList = null;
        int dataCount =  0;
        if(CheckUtils.isNullOrEmpty(paramMap.get("ids")))
        {
            dataList = new ArrayList();
        }
        else
        {
           dataList = workFlowListenerService.findForListFromMap(paramMap);
           dataCount = workFlowListenerService.findCountFromMap(paramMap);
        }
       
        super.writeJsonForPager(dataList, dataCount, response);
    } 
}