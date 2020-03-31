package com.fhs.basics.service.impl;

import com.fhs.basics.dox.ServiceWordbookDO;
import com.fhs.basics.dox.ServiceWordbookGroupDO;
import com.fhs.basics.mapper.ServiceWordbookGroupMapper;
import com.fhs.basics.mapper.ServiceWordbookMapper;
import com.fhs.basics.service.ServiceWordbookGroupService;
import com.fhs.basics.vo.ServiceWordbookGroupVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ServiceWordbookGroupServiceImpl extends BaseServiceImpl<ServiceWordbookGroupVO, ServiceWordbookGroupDO> implements ServiceWordbookGroupService
{

    @Autowired
    private ServiceWordbookGroupMapper mapper;

    @Autowired
    private ServiceWordbookMapper wordbookMapper;

    @Override
    public int add(ServiceWordbookGroupDO bean)
    {
        return mapper.insertJpa(bean);
    }

    @Override
    public boolean update(ServiceWordbookGroupDO bean)
    {
        return mapper.updateByIdJpa(bean) > 0;
    }

    @Override
    public boolean delete(ServiceWordbookGroupDO bean)
    {
        int result = mapper.deleteBean(bean);
        ServiceWordbookDO deleteParam = new ServiceWordbookDO();
        deleteParam.setWordbookGroupCode(bean.getWordbookGroupCode());
        wordbookMapper.deleteBean(deleteParam);
        return result > 0;

    }

    @Override
    public int findCount(ServiceWordbookGroupDO bean)
    {
        return (int)mapper.selectCountJpa(bean);
    }

    @Override
    public List<ServiceWordbookGroupVO> findForListFromMap(Map<String, Object> map)
    {
        return dos2vos(mapper.findForListFromMap(map));
    }

    @Override
    public ServiceWordbookGroupVO findBean(ServiceWordbookGroupDO bean)
    {
        return d2v(mapper.selectBean(bean));
    }
}