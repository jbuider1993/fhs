package com.fhs.basics.controller;

import com.fhs.basics.service.ServiceWordBookService;
import com.fhs.basics.service.ServiceWordbookAndGroupService;
import com.fhs.basics.service.ServiceWordbookGroupService;
import com.fhs.basics.vo.ServiceWordbookGroupVO;
import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.controller.BaseController;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典管理Action
 *
 * @author jianbo.qin
 */
@RestController
@RequestMapping("ms/wordbook")
public class ServiceWordbookController extends BaseController {

    private static final Logger LOG = Logger.getLogger(ServiceWordbookController.class);

    @Autowired
    private ServiceWordbookAndGroupService wordbookAndGroupService;

    @Autowired
    private ServiceWordBookService wordBookService;

    @Autowired
    private ServiceWordbookGroupService wordbookGroupService;

    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 查询字典值
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookForPage")
    public Pager<ServiceWordbookVO> findWordbookForPage(ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse reponse) {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        int count = wordBookService.findCountJpa(wordbook);
        List<ServiceWordbookVO> dataList = wordBookService.selectPage(wordbook, pageSizeInfo.getPageStart(), pageSizeInfo.getPageSize());
        return new Pager<ServiceWordbookVO>(count, dataList);
    }

    /**
     * 添加字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:add")
    @RequestMapping("addWordbook")
    public HttpResult<Boolean> addWordbook(@Valid ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        wordbookAndGroupService.addWordbook(wordbook);
        return HttpResult.success(true);

    }

    /**
     * 修改字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:update")
    @RequestMapping("updateWordbook")
    public HttpResult<Boolean> updateWordbook(@Valid ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        wordbookAndGroupService.updateWordbook(wordbook);
        return HttpResult.success(true);
    }

    /**
     * 删除字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:del")
    @RequestMapping("delWordbook")
    public HttpResult<Boolean> delWordbook(ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        wordbookAndGroupService.delWordbook(wordbook);
        return HttpResult.success(true);
    }

    /**
     * 根据id获取单个字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:see")
    @RequestMapping("getWordbookBean")
    public ServiceWordbookVO getWordbookBean(ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        ServiceWordbookVO bean = wordbookAndGroupService.getWordbookBean(wordbook);
        return bean;
    }

    /**
     * 根据id获取单个字典类型
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:see")
    @RequestMapping("getWordbookGroupBean")
    public ServiceWordbookGroupVO getWordbookGroupBean(ServiceWordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                       HttpServletResponse response) {
        return wordbookAndGroupService.getWordbookGroupBean(wordbookGroup);
    }

    /**
     * 查询字典类型
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookGroupForPage")
    public Pager<ServiceWordbookGroupVO> findWordbookGroupForPage(ServiceWordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                                  HttpServletResponse reponse) {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        int count = wordbookGroupService.findCountJpa(wordbookGroup);
        List<ServiceWordbookGroupVO> dataList = wordbookGroupService.selectPage(wordbookGroup, pageSizeInfo.getPageStart(), pageSizeInfo.getPageSize());
        return new Pager<ServiceWordbookGroupVO>(count, dataList);
    }

    /**
     * 添加字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:add")
    @RequestMapping("addWordbookGroup")
    public HttpResult<Boolean> addWordbookGroup(ServiceWordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                HttpServletResponse response) {
        wordbookAndGroupService.addWordbookGroup(wordbookGroup);
        return HttpResult.success(true);
    }

    /**
     * 修改字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:update")
    @RequestMapping("updateWordbookGroup")
    public  HttpResult<Boolean> updateWordbookGroup(ServiceWordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                    HttpServletResponse response) {
        wordbookAndGroupService.updateWordbookGroup(wordbookGroup);
        return HttpResult.success(true);
    }

    /**
     * 删除字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:del")
    @RequestMapping("delWordbookGroup")
    public  HttpResult<Boolean> delWordbookGroup(ServiceWordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                 HttpServletResponse response) {
        wordbookAndGroupService.delWordbookGroup(wordbookGroup);
        return HttpResult.success(true);
    }

    /**
     * 刷新redis缓存
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:refreshRedisCache")
    @RequestMapping("refreshRedisCache")
    @ResponseBody
    public  HttpResult<Boolean> refreshRedisCache(ServiceWordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> message = new HashMap<>();
        message.put("transType", "wordbook");
        message.put("wordbookGroupCode", wordbook.getWordbookGroupCode());
        redisCacheService.convertAndSend("trans", JsonUtils.map2json(message));
        wordbookAndGroupService.refreshRedisCache(wordbook);
        return HttpResult.success(true);
    }

    /**
     * 查询数据
     *
     * @return addok
     */
    @RequestMapping("getData")
    public List<ServiceWordbookVO> getData(String wordbookGroupCode) {
         return wordBookService.getWordBookList(wordbookGroupCode);
    }
}
