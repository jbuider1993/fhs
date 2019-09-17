package com.fhs.pagex.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.ExcelExportTools;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.api.annotation.AutowareYLM;
import com.fhs.core.exception.NotPremissionException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.log.LogDesc;
import com.fhs.core.result.HttpResult;
import com.fhs.pagex.dto.PageXTreeDTO;
import com.fhs.pagex.dto.TreeDTO;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.system.api.FeignlogAdminOperatorLogApiService;
import com.fhs.system.bean.LogAdminOperatorLogVo;
import com.fhs.ucenter.api.service.FeignSysMenuApiService;
import com.fhs.ucenter.api.vo.SysMenuVo;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.mybatis.jpa.context.DataPermissonContext;
import com.mybatis.jpa.context.MultiTenancyContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * pagex的控制器
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex
 * @ClassName: PageXPubAction
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 19:44
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 19:44
 * @Version: 1.0
 */
@AutowareYLM
@RestController
@RequestMapping("/ms/x/")
public class PageXMsPubAction extends PageXBaseAction{

    /**
     * 日志记录服务
     */
    @AutowareYLM
    private FeignlogAdminOperatorLogApiService feignlogAdminOperatorLogApiService;

    /**
     * 系统菜单服务
     */
    @AutowareYLM
    private FeignSysMenuApiService feignSysMenuApiService;


    /**
     * namesapce:menu map
     */
    private Map<String, SysMenuVo> namesapceMenuMap = new HashMap<>();

    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 添加-后台只做重复校验，不做参数格式校验
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/add")
    public HttpResult<Boolean> add(@PathVariable("namespace")String namespace, HttpServletRequest request, HttpServletResponse response)
    {

        checkPermiessAndNamespace( namespace,"add");
        EMap<String,Object> paramMap = super.getParameterMap(request);
        SysUserVo user = getSessionUser( request);
        paramMap.put("createUser",user.getUserId());
        paramMap.put("groupCode",user.getGroupCode());
        paramMap.put("updateUser",user.getUserId());
        paramMap.put("pkey",StringUtil.getUUID());
        super.setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
        addLog( namespace, "添加", paramMap, request, LogDesc.ADD);
        service.insert(paramMap,namespace);
        refreshPageXTransCache(namespace);
        return HttpResult.success(true);

    }

    /**
     * 刷新namespace 翻译缓存
     * @param namespace namespace
     */
    private void refreshPageXTransCache(String namespace)
    {
        Map<String,String> message = new HashMap<>();
        message.put("transType","pagex");
        message.put("namespace",namespace);
        redisCacheService.convertAndSend("trans", JsonUtils.map2json(message));
    }

    /**
     * 添加日志
     * @param namespace  namespace
     * @param desc 描述
     * @param paramMap 参数
     * @param request request
     * @param type 操作类型
     */
    private void addLog(String namespace,String desc,Map<String,Object> paramMap,HttpServletRequest request,int type)
    {

        // 获取菜单name及nameSpace
        if (namesapceMenuMap.isEmpty()) {
            HttpResult<List<SysMenuVo>> result = feignSysMenuApiService.findIdAndNameAndNamespaceList();
            List<SysMenuVo> sysMenuList = result.getData();
            for (SysMenuVo adminMenu : sysMenuList) {
                namesapceMenuMap.put(adminMenu.getNamespace(), adminMenu);
            }
        }
        if(!namesapceMenuMap.containsKey(namespace))
        {
            return;
        }
        SysUserVo user = getSessionUser(request);
        // 创建LogAdminOperatorLog 对象,并给各属性赋值
        LogAdminOperatorLogVo log = new LogAdminOperatorLogVo();
        log.setCreateTime(DateUtils.getCurrentDateStr(DateUtils.DATE_FULL_STR_SSS));
        log.setUrl(request.getRequestURI());
        log.setOperatDesc(desc + "了" + PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace).getModelConfig().get("title"));
        log.setOperatorId(user.getUserId());
        log.setMenuId(namesapceMenuMap.get(namespace).getMenuId());
        log.setReqParam(JsonUtils.map2json(paramMap));
        try {
            log.setNetworkIp(NetworkUtil.getIpAddress(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.setLogType(type);
        log.setGroupCode(user.getGroupCode());
        feignlogAdminOperatorLogApiService.addLogAdminOperatorLog(log);
    }

    /**
     * 获取数据详情
     * @param namespace namespace
     * @param id id
     */
    @RequestMapping("{namespace}/info/{id}")
    public JSONObject info(@PathVariable("namespace")String namespace, @PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response)
    {
           checkPermiessAndNamespace( namespace,"see");
           Map<String,Object> paramMap = new HashMap<>();
           paramMap.put("id",id);
           paramMap.put("groupCode", MultiTenancyContext.getProviderId());
           super.setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
           addLog( namespace, "查看", paramMap, request, LogDesc.SEE);
           return JSONObject.parseObject(service.findBean(paramMap,namespace));
    }

    /**
     * 更新
     * @param namespace namespace
     * @param id id
     */
    @RequestMapping("{namespace}/update/{id}")
    public HttpResult<Boolean> update(@PathVariable("namespace")String namespace, @PathVariable("id")String id,HttpServletRequest request, HttpServletResponse response)
    {
        checkPermiessAndNamespace( namespace,"update");
        EMap<String,Object> paramMap = super.getParameterMap(request);
        paramMap.put("id",id);
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        paramMap.put("updateUser",getSessionUser( request).getUserId());
        addLog( namespace, "更新", paramMap, request, LogDesc.UPDATE);
        super.setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
        int i = service.update(paramMap,namespace);
        refreshPageXTransCache(namespace);
        return HttpResult.success(i!=0);
    }



    /**
     * 删除
     * @param namespace namespace
     * @param id id
     */
    @RequestMapping("{namespace}/del/{id}")
    public HttpResult<Boolean> del(@PathVariable("namespace")String namespace, @PathVariable("id")String id,HttpServletRequest request, HttpServletResponse response)
    {
        if(id.contains("jackToken"))
        {
            id = id.substring(0,id.indexOf("&amp;"));
        }
        checkPermiessAndNamespace( namespace,"del");
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        addLog( namespace, "删除", paramMap, request, LogDesc.DEL);
        int i = service.del(id,namespace);
        refreshPageXTransCache(namespace);
        return HttpResult.success(i!=0);
    }

    /**
     *  获取数据带分页
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/findPager")
    public Map<String,Object> findPager(@PathVariable("namespace")String namespace,  HttpServletRequest request, HttpServletResponse response)
    {
        checkPermiessAndNamespace( namespace,"see");
        Map<String,Object> paramMap = super.getPageTurnNum(request);
        paramMap.put("dataPermissin", DataPermissonContext.getDataPermissonMap());
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        request.getSession().setAttribute(this.getClass() + "preLoadParam",paramMap);
        Map<String,Object> resultMap = new HashMap<>();
        JSONArray rows = super.findListDataAndInitJoin(namespace,paramMap);
        resultMap.put("rows",rows);
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        resultMap.put("total",service.findPageCount(paramMap,namespace));
        return resultMap;
    }

    /**
     *  获取ztree数据
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/ztreeData")
    public JSONArray ztreeData(@PathVariable("namespace")String namespace, HttpServletRequest request, HttpServletResponse response)
    {
        checkPermiessAndNamespace( namespace,"see");
        PageXTreeDTO treeDTO = PagexDataService.SIGNEL.getPageXTreeDTOFromCache(namespace);
        if(treeDTO==null)
        {
            throw new ParamException("namespace:" + namespace + "不支持tree");
        }
        String fidField = ConverterUtils.toString(treeDTO.getKeySettMap().get("fidkey"));
        String pkey =  ConverterUtils.toString(treeDTO.getModelConfig().get("pkey"));
        JSONArray result = new JSONArray();

        //祖宗的id
        String fkeyVal = request.getParameter("pid");
        // 所有的都查询出来
        JSONArray jsonArray = getAllListPage(namespace);
        if(CheckUtils.isNullOrEmpty(fkeyVal))
        {
            return jsonArray;
        }
        //所有的 fkeyVal 的后辈都在这个集合中
        Set<String> posteritySet = new HashSet<>();

        JSONObject temp = null;

        //下面这段程序的名字叫做 他是不是我祖宗
        for(int i=0;i<jsonArray.size();i++)
        {
            temp = jsonArray.getJSONObject(i);
            //如果传进来的fkeyVal 是我的爸爸，就把我添加到返回的集合去  || 我爸爸是人家后辈，我肯定也是人家后辈
            if(temp.getString(fidField).equals(fkeyVal) || posteritySet.contains(temp.getString(fidField)))
            {
                result.add(temp);
                posteritySet.add(temp.getString(pkey));//他都是我爸爸了，我肯定是他的后辈
            }
        }
        return result;
    }

    /**
     * 将导出的列配置信息缓存到session中
     * @param fieldSett 导出配置
     * @param request request
     * @return 成功
     */
    @RequestMapping("{namespace}/setExportField")
    @ResponseBody
    public HttpResult setExportField(@PathVariable("namespace")String namespace,@RequestBody String fieldSett, HttpServletRequest request){
        return ExcelExportTools.setExportField(fieldSett,request);
    }

    /**
     *  公共导出excel 03  by jackwang
     * @param request  request
     * @param response response
     */
    @RequestMapping("{namespace}/pubExportExcel")
    @LogDesc(value = "导出数据", type = LogDesc.SEE)
    public void exportExcel(@PathVariable("namespace")String namespace,HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> paramMap = (Map<String,Object>)request.getSession().getAttribute(this.getClass() + "preLoadParam");
        if(paramMap==null)
        {
            paramMap = new HashMap<>();
        }
        //去掉分页
        paramMap.remove("start");
        paramMap.remove("end");
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        paramMap.put("start", Constant.PAGE_ALL);
        paramMap.put("dataPermissin", DataPermissonContext.getDataPermissonMap());
        addLog( namespace, "导出了", paramMap, request, LogDesc.OTHER);
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        String resultJson = service.findListPage(paramMap,namespace);
        List<JSONObject> dataList = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(resultJson);
        jsonArray = joinService.initJoinData(jsonArray,namespace);
        listExtendsHanleService.processingData(namespace,jsonArray);
        for(int i = 0;i<jsonArray.size();i++)
        {
            dataList.add(jsonArray.getJSONObject(i));
        }
        ExcelExportTools.exportExcel(dataList, request,  response);
    }

    /**
     *  获取数据不带分页
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/findListData")
    public JSONArray findListData(@PathVariable("namespace")String namespace, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> paramMap =super.getParameterMap(request);
        paramMap.put("start", Constant.PAGE_ALL);
        paramMap.put("dataPermissin", DataPermissonContext.getDataPermissonMap());
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        String resultJson = service.findListPage(paramMap,namespace);
        return JSONArray.parseArray(resultJson);
    }

    /**
     * 获取session中的用户
     * @param request request
     * @return 系统用户
     */
    private SysUserVo getSessionUser(HttpServletRequest request){
        return (SysUserVo)request.getSession().getAttribute(Constant.SESSION_USER);
    }

    /**
     * 校验权限和namespace是否存在
     * @param namespace namespace
     * @param permiessionCode 权限
     */
    private void checkPermiessAndNamespace(String namespace,String permiessionCode)
    {
        if(!SecurityUtils.getSubject().isPermitted(namespace + ":"+permiessionCode))
        {
            throw new NotPremissionException();

        }
        if(!PagexDataService.SIGNEL.getPagexListSettDTOCache().containsKey(namespace))
        {
            throw new ParamException("namespace不存在");
        }
    }

	/**
     *  获取ztree数据
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/getTree")
    public List<TreeDTO> getTree(@PathVariable("namespace")String namespace, HttpServletRequest request){
        checkPermiessAndNamespace( namespace,"see");
        JSONArray jsonArray = getAllListPage(namespace);

        List<TreeDTO> result = new ArrayList<>();
        Map<String, TreeDTO> map = new HashMap();
        PageXTreeDTO treeDTO = PagexDataService.SIGNEL.getPageXTreeDTOFromCache(namespace);
        if(treeDTO==null)
        {
            throw new ParamException("namespace:" + namespace + "不支持tree");
        }
        //父id
        String fidField = ConverterUtils.toString(treeDTO.getKeySettMap().get("fidkey"));
        //本身id
        String pkey =  ConverterUtils.toString(treeDTO.getModelConfig().get("pkey"));
        //显示那个字段
        String namekey = ConverterUtils.toString(treeDTO.getKeySettMap().get("namekey"));

        for(int i=0;i<jsonArray.size();i++)
        {
            TreeDTO tree = new TreeDTO();
            tree.setId(jsonArray.getJSONObject(i).getString(pkey));
            tree.setParentId(jsonArray.getJSONObject(i).getString(fidField));
            tree.setText(jsonArray.getJSONObject(i).getString(namekey));
            map.put(tree.getId(),tree);
            //找爸爸
            if(map.containsKey(tree.getParentId())){
                //和爸爸待一起
                map.get(tree.getParentId()).getChildren().add(tree);
            }else{
                result.add(tree);
            }
        }
        return result;
    }

    /**
     * 获取树形数据
     * @param namespace
     * @return
     */
    private JSONArray getAllListPage(String namespace){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("start",-1);
        paramMap.put("end",-1);
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        paramMap.put("sortTzwName","create_time ASC");
        super.setDB(PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace));
        paramMap.put("dataPermissin", DataPermissonContext.getDataPermissonMap());
        // 所有的都查询出来
        return JSON.parseArray(service.findListPage(paramMap,namespace));
    }
}
