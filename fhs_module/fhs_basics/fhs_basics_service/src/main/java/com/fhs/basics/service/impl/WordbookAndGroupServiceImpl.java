package com.fhs.basics.service.impl;

import com.fhs.core.db.DataSource;
import com.fhs.system.bean.ServiceWordbookGroup;
import com.fhs.system.bean.Wordbook;
import com.fhs.system.service.ServiceWordbookGroupService;
import com.fhs.system.service.WordBookService;
import com.fhs.system.service.WordbookAndGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典及字典类型
 *
 * @author nanshouxiao
 * @version [版本号, 2015/12/22 14:20:46]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@DataSource("base_business")
public class WordbookAndGroupServiceImpl  implements WordbookAndGroupService
{

    @Resource(name = "wordBookServiceImpl")
    private WordBookService wordBookService;

    @Resource(name = "serviceWordbookGroupServiceImpl")
    private ServiceWordbookGroupService serviceWordbookGroupService;

    @Override
    public boolean addWordbook(Wordbook wordbook)
    {
        wordBookService.insertJpa(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean updateWordbook(Wordbook wordbook)
    {
        wordBookService.updateById(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean delWordbook(Wordbook wordbook)
    {
        wordBookService.deleteById(wordbook.getWordbookId());
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public int findWordbookCount(Wordbook wordbook)
    {
        return wordBookService.findCount(wordbook);
    }

    @Override
    public List<Wordbook> findWordbookForListFromMap(Map<String, Object> map)
    {
        return wordBookService.findForListFromMap(map);
    }

    @Override
    public boolean addWordbookGroup(ServiceWordbookGroup wordbookGroup)
    {
        serviceWordbookGroupService.add(wordbookGroup);
        return true;
    }

    @Override
    public boolean updateWordbookGroup(ServiceWordbookGroup wordbookGroup)
    {
        serviceWordbookGroupService.update(wordbookGroup);
        return true;
    }

    @Override
    public boolean delWordbookGroup(ServiceWordbookGroup wordbookGroup)
    {
        serviceWordbookGroupService.delete(wordbookGroup);
        return true;
    }

    @Override
    public int findWordbookGroupCount(ServiceWordbookGroup wordbookGroup)
    {
        return serviceWordbookGroupService.findCount(wordbookGroup);
    }

    @Override
    public List<ServiceWordbookGroup> findWordbookGroupForListFromMap(Map<String, Object> map)
    {
        return serviceWordbookGroupService.findForListFromMap(map);
    }

    @Override
    public Wordbook getWordbookBean(Wordbook wordbook)
    {
        return wordBookService.selectById(wordbook.getWordbookId());
    }

    @Override
    public ServiceWordbookGroup getWordbookGroupBean(ServiceWordbookGroup wordbookGroup)
    {
        return serviceWordbookGroupService.findBean(wordbookGroup);
    }

    @Override
    public boolean refreshRedisCache(Wordbook wordbook)
    {
        wordBookService.initWordBookDataCache(wordbook);
        return true;
    }

}