package com.fhs.basics.controller;

import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.basics.service.WordBookService;
import com.fhs.basics.service.WordbookAndGroupService;
import com.fhs.basics.vo.WordbookGroupVO;
import com.fhs.basics.vo.WordbookVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.MapUtils;
import com.fhs.core.base.controller.BaseController;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 *
 */
@Controller
@RequestMapping("ms/wordbook")
public class WordbookController extends BaseController
{

    Logger LOG = Logger.getLogger(WordbookController.class);

    @Autowired
    private WordbookAndGroupService wordbookAndGroupService;

    @Autowired
    private WordBookService wordBookService;

    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 查询字典值
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookForPage")
    public void findWordbookForPage(WordbookDO wordbook, HttpServletRequest request, HttpServletResponse reponse)
    {

        Map<String, Object> map = super.getPageTurnNum(request);
        int count = wordbookAndGroupService.findWordbookCount(wordbook);
        map.putAll(MapUtils.bean2Map(wordbook));
        List<WordbookVO> dataList = wordbookAndGroupService.findWordbookForListFromMap(map);
        super.writeJsonForPager(dataList, count, reponse);
    }

    /**
     *
     * 添加字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:add")
    @RequestMapping("addWordbook")
    @ResponseBody
    public HttpResult addWordbook(@Valid WordbookDO wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.addWordbook(wordbook);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();

    }

    /**
     *
     * 修改字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:update")
    @RequestMapping("updateWordbook")
    @ResponseBody
    public HttpResult updateWordbook(@Valid  WordbookDO wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.updateWordbook(wordbook);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     *
     * 删除字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:del")
    @RequestMapping("delWordbook")
    @ResponseBody
    public HttpResult delWordbook(WordbookDO wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.delWordbook(wordbook);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     *
     * 根据id获取单个字典
     *
     * @param wordbook
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:see")
    @RequestMapping("getWordbookBean")
    public void getWordbookBean(WordbookDO wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        WordbookDO bean = wordbookAndGroupService.getWordbookBean(wordbook);
        super.outWriteJson(JsonUtils.bean2json(bean), response);
    }

    /**
     *
     * 根据id获取单个字典类型
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:see")
    @RequestMapping("getWordbookGroupBean")
    public void getWordbookGroupBean(WordbookGroupDO wordbookGroup, HttpServletRequest request,
                                     HttpServletResponse response)
    {
        WordbookGroupDO bean = wordbookAndGroupService.getWordbookGroupBean(wordbookGroup);
        super.outWriteJson(JsonUtils.bean2json(bean), response);
    }

    /**
     * 查询字典类型
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookGroupForPage")
    public void findWordbookGroupForPage(WordbookGroupDO wordbookGroup, HttpServletRequest request,
        HttpServletResponse reponse)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        int count = wordbookAndGroupService.findWordbookGroupCount(wordbookGroup);
        map.putAll(MapUtils.bean2Map(wordbookGroup));
        List<WordbookGroupVO> dataList = wordbookAndGroupService.findWordbookGroupForListFromMap(map);
        super.writeJsonForPager(dataList, count, reponse);
    }

    /**
     *
     * 添加字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:add")
    @RequestMapping("addWordbookGroup")
    @ResponseBody
    public HttpResult addWordbookGroup(WordbookGroupDO wordbookGroup, HttpServletRequest request,
                                       HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.addWordbookGroup(wordbookGroup);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     *
     * 修改字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:update")
    @RequestMapping("updateWordbookGroup")
    @ResponseBody
    public HttpResult updateWordbookGroup(WordbookGroupDO wordbookGroup, HttpServletRequest request,
                                          HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.updateWordbookGroup(wordbookGroup);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     *
     * 删除字典类型
     *
     * @param wordbookGroup
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:del")
    @RequestMapping("delWordbookGroup")
    @ResponseBody
    public HttpResult delWordbookGroup(WordbookGroupDO wordbookGroup, HttpServletRequest request,
                                       HttpServletResponse response)
    {
        try
        {
            wordbookAndGroupService.delWordbookGroup(wordbookGroup);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     *
     * 刷新redis缓存
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("wordbook:refreshRedisCache")
    @RequestMapping("refreshRedisCache")
    @ResponseBody
    public HttpResult refreshRedisCache(WordbookDO wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            Map<String,String> message = new HashMap<>();
            message.put("transType","wordbook");
            message.put("wordbookGroupCode",wordbook.getWordbookGroupCode());
            redisCacheService.convertAndSend("trans", JsonUtils.map2json(message));
            wordbookAndGroupService.refreshRedisCache(wordbook);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

   /**
     * 查询数据
     *
     * @return addok
     */
    @RequestMapping("getData")
    public void getData(HttpServletRequest request, HttpServletResponse response)
    {
        List<WordbookVO> list = wordBookService.getWordBookList(request.getParameter("wordbookGroupCode"));
        outJsonp(JsonUtils.list2json(list), response, request);
    }
}
