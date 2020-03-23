package com.fhs.basics.controller;

import com.fhs.basics.service.WordBookService;
import com.fhs.basics.service.WordbookAndGroupService;
import com.fhs.basics.service.WordbookGroupService;
import com.fhs.basics.vo.WordbookGroupVO;
import com.fhs.basics.vo.WordbookVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.MapUtils;
import com.fhs.core.base.controller.BaseController;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class WordbookAction extends BaseController {

    private static final Logger LOG = Logger.getLogger(WordbookAction.class);

    @Autowired
    private WordbookAndGroupService wordbookAndGroupService;

    @Autowired
    private WordBookService wordBookService;

    @Autowired
    private WordbookGroupService wordbookGroupService;

    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 查询字典值
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookForPage")
    public Pager<WordbookVO> findWordbookForPage(WordbookVO wordbook, HttpServletRequest request, HttpServletResponse reponse) {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        int count = wordBookService.findCountJpa(wordbook);
        List<WordbookVO> dataList = wordBookService.selectPage(wordbook, pageSizeInfo.getPageStart(), pageSizeInfo.getPageSize());
        return new Pager<WordbookVO>(count, dataList);
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
    public HttpResult<Boolean> addWordbook(@Valid WordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
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
    public HttpResult<Boolean> updateWordbook(@Valid WordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
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
    public HttpResult<Boolean> delWordbook(WordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
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
    public WordbookVO getWordbookBean(WordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
        WordbookVO bean = wordbookAndGroupService.getWordbookBean(wordbook);
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
    public WordbookGroupVO getWordbookGroupBean(WordbookGroupVO wordbookGroup, HttpServletRequest request,
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
    public Pager<WordbookGroupVO> findWordbookGroupForPage(WordbookGroupVO wordbookGroup, HttpServletRequest request,
                                                           HttpServletResponse reponse) {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        int count = wordbookGroupService.findCountJpa(wordbookGroup);
        List<WordbookGroupVO> dataList = wordbookGroupService.selectPage(wordbookGroup, pageSizeInfo.getPageStart(), pageSizeInfo.getPageSize());
        return new Pager<WordbookGroupVO>(count, dataList);
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
    public HttpResult<Boolean> addWordbookGroup(WordbookGroupVO wordbookGroup, HttpServletRequest request,
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
    public  HttpResult<Boolean> updateWordbookGroup(WordbookGroupVO wordbookGroup, HttpServletRequest request,
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
    public  HttpResult<Boolean> delWordbookGroup(WordbookGroupVO wordbookGroup, HttpServletRequest request,
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
    public  HttpResult<Boolean> refreshRedisCache(WordbookVO wordbook, HttpServletRequest request, HttpServletResponse response) {
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
    public List<WordbookVO> getData(String wordbookGroupCode) {
         return wordBookService.getWordBookList(wordbookGroupCode);
    }
}
