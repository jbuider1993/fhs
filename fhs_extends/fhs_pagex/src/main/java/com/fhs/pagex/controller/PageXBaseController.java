package com.fhs.pagex.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.basics.vo.SettMsMenuVO;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.controller.BaseController;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.db.ds.ReadWriteDataSourceDecision;
import com.fhs.core.exception.NotPremissionException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.pagex.service.JoinService;
import com.fhs.pagex.service.ListExtendsHanleService;
import com.fhs.pagex.service.PageXDBService;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.vo.PagexBaseVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pagex处理前段业务逻辑的公共代码
 */
public class PageXBaseController extends BaseController {

    @Autowired
    protected PageXDBService service;

    @Autowired
    protected JoinService joinService;


    @Autowired
    private RedisCacheService<String> redisCacheService;

    @Autowired
    protected ListExtendsHanleService listExtendsHanleService;

    /**
     * namesapce:menu map
     */
    protected Map<String, SettMsMenuVO> namesapceMenuMap = new HashMap<>();

    /**
     * 根据modelconfig中的db配置，来设置本操作连接的数据库
     *
     * @param pagexBaseDTO pagexBaseDTO
     */
    protected void setDB(PagexBaseVO pagexBaseDTO) {
        if (pagexBaseDTO != null) {
            if (pagexBaseDTO.getModelConfig().containsKey("db")) {
                ReadWriteDataSourceDecision.markParam();
                ReadWriteDataSourceDecision.setDataSource(ConverterUtils.toString(pagexBaseDTO.getModelConfig().get("db")));
            }
        }
    }


    /**
     * 获取session中的用户
     *
     * @param request request
     * @return 系统用户
     */
    protected UcenterMsUserVO getSessionUser(HttpServletRequest request) {
        return (UcenterMsUserVO) request.getSession().getAttribute(Constant.SESSION_USER);
    }





    /**
     * 刷新namespace 翻译缓存
     *
     * @param namespace namespace
     */
    protected void refreshPageXTransCache(String namespace) {
        Map<String, String> message = new HashMap<>();
        message.put("transType", "pagex");
        message.put("namespace", namespace);
        redisCacheService.convertAndSend("trans", JsonUtils.map2json(message));
    }

    /**
     * 校验权限和namespace是否存在
     *
     * @param namespace       namespace
     * @param permiessionCode 权限
     */
    protected void checkPermiessAndNamespace(String namespace, String permiessionCode) {
        if (!SecurityUtils.getSubject().isPermitted(namespace + ":" + permiessionCode)) {
            throw new NotPremissionException();

        }
        if (!PagexDataService.SIGNEL.getPagexListSettDTOCache().containsKey(namespace)) {
            throw new ParamException("namespace不存在");
        }
    }

    /**
     * 添加日志
     *
     * @param namespace namespace
     * @param desc      描述
     * @param paramMap  参数
     * @param request   request
     * @param type      操作类型
     */
    protected void addLog(String namespace, String desc, Map<String, Object> paramMap, HttpServletRequest request, int type) {

       /* // 获取菜单name及nameSpace
        if (namesapceMenuMap.isEmpty()) {
            HttpResult<List<SettMsMenuVO>> result = feignSysMenuApiService.findIdAndNameAndNamespaceList();
            List<SettMsMenuVO> sysMenuList = result.getData();
            for (SettMsMenuVO adminMenu : sysMenuList) {
                namesapceMenuMap.put(adminMenu.getNamespace(), adminMenu);
            }
        }
        if(!namesapceMenuMap.containsKey(namespace))
        {
            return;
        }
        UcenterMsUserVO user = getSessionUser(request);*/
        // 创建LogAdminOperatorLog 对象,并给各属性赋值
//        LogAdminOperatorLogVo log = new LogAdminOperatorLogVo();
//        log.setCreateTime(DateUtils.getCurrentDateStr(DateUtils.DATE_FULL_STR_SSS));
//        log.setUrl(request.getRequestURI());
//        log.setOperatDesc(desc + "了" + PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace).getModelConfig().get("title"));
//        log.setOperatorId(user.getUserId());
//        log.setMenuId(namesapceMenuMap.get(namespace).getMenuId());
//        log.setReqParam(JsonUtils.map2json(paramMap));
//        try {
//            log.setNetworkIp(NetworkUtil.getIpAddress(request));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        log.setLogType(type);
//        log.setGroupCode(user.getGroupCode());
//        feignlogAdminOperatorLogApiService.addLogAdminOperatorLog(log);
    }

    /**
     * 查询列表数据并且调用joinservice 填充
     *
     * @param namespace namespace
     * @param paramMap  参数
     * @return 填充好的JSONArray
     */
    protected JSONArray findListDataAndInitJoin(String namespace, Map<String, Object> paramMap) {
        this.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        String resultJson = service.findListPage(paramMap, namespace);
        JSONArray rows = JSONObject.parseArray(resultJson);
        this.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        rows = joinService.initJoinData(rows, namespace);
        listExtendsHanleService.processingData(namespace, rows);
        return rows;
    }
}
