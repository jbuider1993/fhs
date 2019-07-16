package com.fhs.system.action;

import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.MapUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.result.HttpResult;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.system.bean.ServiceWordbookGroup;
import com.fhs.system.bean.Wordbook;
import com.fhs.system.service.WordBookService;
import com.fhs.system.service.WordbookAndGroupService;
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
public class WordbookAction extends BaseAction<Wordbook>
{

    Logger LOG = Logger.getLogger(WordbookAction.class);

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
    public void findWordbookForPage(Wordbook wordbook, HttpServletRequest request, HttpServletResponse reponse)
    {

        Map<String, Object> map = super.getPageTurnNum(request);
        int count = wordbookAndGroupService.findWordbookCount(wordbook);
        map.putAll(MapUtils.bean2Map(wordbook));
        List<Wordbook> dataList = wordbookAndGroupService.findWordbookForListFromMap(map);
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
    public HttpResult addWordbook(@Valid Wordbook wordbook, HttpServletRequest request, HttpServletResponse response)
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
    public HttpResult updateWordbook(@Valid  Wordbook wordbook, HttpServletRequest request, HttpServletResponse response)
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
    public HttpResult delWordbook(Wordbook wordbook, HttpServletRequest request, HttpServletResponse response)
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
    public void getWordbookBean(Wordbook wordbook, HttpServletRequest request, HttpServletResponse response)
    {
        Wordbook bean = wordbookAndGroupService.getWordbookBean(wordbook);
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
    public void getWordbookGroupBean(ServiceWordbookGroup wordbookGroup, HttpServletRequest request,
        HttpServletResponse response)
    {
        ServiceWordbookGroup bean = wordbookAndGroupService.getWordbookGroupBean(wordbookGroup);
        super.outWriteJson(JsonUtils.bean2json(bean), response);
    }

    /**
     * 查询字典类型
     *
     * @param request
     * @param reponse
     */
    @RequestMapping("findWordbookGroupForPage")
    public void findWordbookGroupForPage(ServiceWordbookGroup wordbookGroup, HttpServletRequest request,
        HttpServletResponse reponse)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        int count = wordbookAndGroupService.findWordbookGroupCount(wordbookGroup);
        map.putAll(MapUtils.bean2Map(wordbookGroup));
        List<ServiceWordbookGroup> dataList = wordbookAndGroupService.findWordbookGroupForListFromMap(map);
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
    public HttpResult addWordbookGroup(ServiceWordbookGroup wordbookGroup, HttpServletRequest request,
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
    public HttpResult updateWordbookGroup(ServiceWordbookGroup wordbookGroup, HttpServletRequest request,
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
    public HttpResult delWordbookGroup(ServiceWordbookGroup wordbookGroup, HttpServletRequest request,
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
    public HttpResult refreshRedisCache(Wordbook wordbook, HttpServletRequest request, HttpServletResponse response)
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
        List<Wordbook> list = wordBookService.getWordBookList(request.getParameter("wordbookGroupCode"));
        outJsonp(JsonUtils.list2json(list), response, request);
    }
}
