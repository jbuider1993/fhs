package com.fhs.basics.service;

import com.fhs.basics.dox.ServiceWordbookDO;
import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 字典服务类
 *
 * @author wanglei
 * @version [版本号, 2015年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ServiceWordBookService extends BaseService<ServiceWordbookVO, ServiceWordbookDO> {

    /**
     * 获取字典分组下面的所有字典项
     *
     * @param wordbookGroupCode 字典分组code
     * @return 字典集合
     */
    List<ServiceWordbookVO> getWordBookList(String wordbookGroupCode);

    /**
     * 获取字典分组下面的所有字典项
     *
     * @param wordbookGroupCode 字典分组code
     * @return 字典集合
     */
    Map<String, String> getWordBookMap(String wordbookGroupCode);

    /**
     * 更新缓存数据
     *
     * @param bean 主要取wordbookgroupcode 用来过滤需要的数据
     */
    void initWordBookDataCache(ServiceWordbookDO bean);


    /**
     * 查询数据 参数为map
     *
     * @param map map
     * @return 查询出来的数据集合
     */
    @Override
    List<ServiceWordbookVO> findForListFromMap(Map<String, Object> map);

    /**
     * 获取字典的map
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapListFromMap(Map<String, Object> map);
}
