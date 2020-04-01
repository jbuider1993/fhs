package com.fhs.basics.service.impl;

import com.fhs.basics.dox.ServiceWordbookDO;
import com.fhs.basics.dox.ServiceWordbookGroupDO;
import com.fhs.basics.service.ServiceWordBookService;
import com.fhs.basics.service.ServiceWordbookAndGroupService;
import com.fhs.basics.service.ServiceWordbookGroupService;
import com.fhs.basics.vo.ServiceWordbookGroupVO;
import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ServiceWordbookAndGroupServiceImpl implements ServiceWordbookAndGroupService
{

    @Autowired
    private ServiceWordBookService wordBookService;

    @Autowired
    private ServiceWordbookGroupService serviceWordbookGroupService;

    @Override
    public boolean addWordbook(ServiceWordbookDO wordbook)
    {
        wordBookService.insertSelective(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean updateWordbook(ServiceWordbookDO wordbook)
    {
        wordBookService.updateSelectiveById(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean delWordbook(ServiceWordbookDO wordbook)
    {
        wordBookService.deleteById(wordbook.getWordbookId());
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public int findWordbookCount(ServiceWordbookDO wordbook)
    {
        return wordBookService.findCount(wordbook);
    }

    @Override
    public List<ServiceWordbookVO> findWordbookForListFromMap(Map<String, Object> map)
    {
        return wordBookService.findForListFromMap(map);
    }

    @Override
    public boolean addWordbookGroup(ServiceWordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.add(wordbookGroup);
        return true;
    }

    @Override
    public boolean updateWordbookGroup(ServiceWordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.update(wordbookGroup);
        return true;
    }

    @Override
    public boolean delWordbookGroup(ServiceWordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.delete(wordbookGroup);
        return true;
    }

    @Override
    public int findWordbookGroupCount(ServiceWordbookGroupDO wordbookGroup)
    {
        return serviceWordbookGroupService.findCount(wordbookGroup);
    }

    @Override
    public List<ServiceWordbookGroupVO> findWordbookGroupForListFromMap(Map<String, Object> map)
    {
        return serviceWordbookGroupService.findForListFromMap(map);
    }

    @Override
    public ServiceWordbookVO getWordbookBean(ServiceWordbookDO wordbook)
    {
        return wordBookService.selectById(wordbook.getWordbookId());
    }

    @Override
    public ServiceWordbookGroupVO getWordbookGroupBean(ServiceWordbookGroupDO wordbookGroup)
    {
        return serviceWordbookGroupService.findBean(wordbookGroup);
    }

    @Override
    public boolean refreshRedisCache(ServiceWordbookDO wordbook)
    {
        wordBookService.initWordBookDataCache(wordbook);
        return true;
    }

}