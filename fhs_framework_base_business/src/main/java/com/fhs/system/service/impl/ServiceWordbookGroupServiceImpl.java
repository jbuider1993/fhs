package com.fhs.system.service.impl;

import com.fhs.core.db.DataSource;
import com.fhs.system.bean.ServiceWordbookGroup;
import com.fhs.system.dao.ServiceWordbookGroupDAO;
import com.fhs.system.dao.WordbookDAO;
import com.fhs.system.service.ServiceWordbookGroupService;
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
public class ServiceWordbookGroupServiceImpl implements ServiceWordbookGroupService
{

    @Autowired
    private ServiceWordbookGroupDAO dao;

    @Autowired
    private WordbookDAO wordbookDao;

    @Override
    public int add(ServiceWordbookGroup bean)
    {
        return dao.add(bean);
    }

    @Override
    public boolean update(ServiceWordbookGroup bean)
    {
        return dao.update(bean) > 0;
    }

    @Override
    @Transactional
    public boolean delete(ServiceWordbookGroup bean)
    {
        int result = dao.delete(bean);
        wordbookDao.batchDelete(bean.getWordbookGroupCode());
        return result > 0;

    }

    @Override
    public int findCount(ServiceWordbookGroup bean)
    {
        return dao.findCount(bean);
    }

    @Override
    public List<ServiceWordbookGroup> findForListFromMap(Map<String, Object> map)
    {
        return dao.findForListFromMap(map);
    }

    @Override
    public ServiceWordbookGroup findBean(ServiceWordbookGroup bean)
    {
        return dao.findBean(bean);
    }
}