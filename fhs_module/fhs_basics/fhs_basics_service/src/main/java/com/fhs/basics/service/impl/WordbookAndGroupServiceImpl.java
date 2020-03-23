package com.fhs.basics.service.impl;

import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.basics.service.WordBookService;
import com.fhs.basics.service.WordbookAndGroupService;
import com.fhs.basics.service.WordbookGroupService;
import com.fhs.basics.vo.WordbookGroupVO;
import com.fhs.basics.vo.WordbookVO;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WordBookService wordBookService;

    @Autowired
    private WordbookGroupService serviceWordbookGroupService;

    @Override
    public boolean addWordbook(WordbookDO wordbook)
    {
        wordBookService.insertJpa(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean updateWordbook(WordbookDO wordbook)
    {
        wordBookService.updateById(wordbook);
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public boolean delWordbook(WordbookDO wordbook)
    {
        wordBookService.deleteById(wordbook.getWordbookId());
        this.refreshRedisCache(wordbook);
        return true;
    }

    @Override
    public int findWordbookCount(WordbookDO wordbook)
    {
        return wordBookService.findCount(wordbook);
    }

    @Override
    public List<WordbookVO> findWordbookForListFromMap(Map<String, Object> map)
    {
        return wordBookService.findForListFromMap(map);
    }

    @Override
    public boolean addWordbookGroup(WordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.add(wordbookGroup);
        return true;
    }

    @Override
    public boolean updateWordbookGroup(WordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.update(wordbookGroup);
        return true;
    }

    @Override
    public boolean delWordbookGroup(WordbookGroupDO wordbookGroup)
    {
        serviceWordbookGroupService.delete(wordbookGroup);
        return true;
    }

    @Override
    public int findWordbookGroupCount(WordbookGroupDO wordbookGroup)
    {
        return serviceWordbookGroupService.findCount(wordbookGroup);
    }

    @Override
    public List<WordbookGroupVO> findWordbookGroupForListFromMap(Map<String, Object> map)
    {
        return serviceWordbookGroupService.findForListFromMap(map);
    }

    @Override
    public WordbookVO getWordbookBean(WordbookDO wordbook)
    {
        return wordBookService.selectById(wordbook.getWordbookId());
    }

    @Override
    public WordbookGroupVO getWordbookGroupBean(WordbookGroupDO wordbookGroup)
    {
        return serviceWordbookGroupService.findBean(wordbookGroup);
    }

    @Override
    public boolean refreshRedisCache(WordbookDO wordbook)
    {
        wordBookService.initWordBookDataCache(wordbook);
        return true;
    }

}