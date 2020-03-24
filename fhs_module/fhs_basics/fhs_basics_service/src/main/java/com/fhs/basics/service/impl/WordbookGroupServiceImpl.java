package com.fhs.basics.service.impl;

import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.basics.mapper.WordbookGroupMapper;
import com.fhs.basics.mapper.WordbookMapper;
import com.fhs.basics.service.WordbookGroupService;
import com.fhs.basics.vo.WordbookGroupVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 字典分组
 *
 * @author nanshouxiao
 * @version [版本号, 2015/12/22 15:13:20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@DataSource("base_business")
public class WordbookGroupServiceImpl extends BaseServiceImpl<WordbookGroupVO, WordbookGroupDO> implements WordbookGroupService
{

    @Autowired
    private WordbookGroupMapper mapper;

    @Autowired
    private WordbookMapper wordbookMapper;

    @Override
    public int add(WordbookGroupDO bean)
    {
        return mapper.insertJpa(bean);
    }

    @Override
    public boolean update(WordbookGroupDO bean)
    {
        return mapper.updateByIdJpa(bean) > 0;
    }

    @Override
    public boolean delete(WordbookGroupDO bean)
    {
        int result = mapper.deleteBean(bean);
        WordbookDO deleteParam = new WordbookDO();
        deleteParam.setWordbookGroupCode(bean.getWordbookGroupCode());
        wordbookMapper.deleteBean(deleteParam);
        return result > 0;

    }

    @Override
    public int findCount(WordbookGroupDO bean)
    {
        return (int)mapper.selectCountJpa(bean);
    }

    @Override
    public List<WordbookGroupVO> findForListFromMap(Map<String, Object> map)
    {
        return dos2vos(mapper.findForListFromMap(map));
    }

    @Override
    public WordbookGroupVO findBean(WordbookGroupDO bean)
    {
        return d2v(mapper.selectBean(bean));
    }
}