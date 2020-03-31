package com.fhs.basics.service.impl;

import com.fhs.basics.api.rpc.FeignWordBookApiService;
import com.fhs.basics.dox.ServiceWordbookDO;
import com.fhs.basics.mapper.ServiceWordbookMapper;
import com.fhs.basics.service.ServiceWordBookService;
import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

/**
 * 字典表服务服务/带翻译
 *
 * @author wanglei
 * @version [版本号, 2015年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Primary
@Service
@DataSource("base_business")
public class ServiceWordBookServiceImpl extends BaseServiceImpl<ServiceWordbookVO, ServiceWordbookDO> implements ServiceWordBookService, FeignWordBookApiService {
    /**
     * redis获取下拉的key
     */
    private static final String BASE_KEY = "service:wordbook:";

    /**
     * redis翻译的key
     */
    private static final String BASE_TRANS_KEY = "service:wordbook:trans:";

    /**
     * redisService
     */
    @Autowired
    private RedisCacheService<ServiceWordbookVO> redisService;

    @Autowired
    private ServiceWordbookMapper mapper;

    @Override
    public List<ServiceWordbookVO> getWordBookList(String wordbookGroupCode) {
        if (CheckUtils.isNullOrEmpty(wordbookGroupCode)) {
            return new ArrayList<>();
        }
        List<ServiceWordbookVO> list = redisService.getList(BASE_KEY + wordbookGroupCode);
        if (CheckUtils.checkCollectionIsNullOrEmpty(list)) {
            ServiceWordbookVO bean = new ServiceWordbookVO();
            bean.setWordbookGroupCode(wordbookGroupCode);
            this.initWordBookDataCache(bean);
            list = redisService.getList(BASE_KEY + wordbookGroupCode);
        }
        Collections.sort(list, new Comparator<ServiceWordbookVO>() {
            @Override
            public int compare(ServiceWordbookVO book1, ServiceWordbookVO book2) {
                return book1.getOrderNum() - book2.getOrderNum();
            }
        });
        return list;
    }


    @Override
    public Map<String, String> getWordBookMap(String wordbookGroupCode) {
        Map<String, String> result = new HashMap<>();
        List<ServiceWordbookVO> list = redisService.getList(BASE_KEY + wordbookGroupCode);
        for (ServiceWordbookVO temp : list) {
            result.put(temp.getWordbookCode(), temp.getWordbookDesc());
        }
        return result;
    }


    @Override
    public void initWordBookDataCache(ServiceWordbookDO bean) {
        List<ServiceWordbookVO> list = super.select();
        Map<String, Set<ServiceWordbookVO>> groupWordbookSetMap = new HashMap<>();
        Set<ServiceWordbookVO> tempSet = null;
        ServiceWordbookVO tempWordbook = null;
        int listSize = list.size();
        // 按照group类型分组
        for (int i = 0; i < listSize; i++) {
            tempWordbook = list.get(i);
            tempSet = groupWordbookSetMap.get(tempWordbook.getWordbookGroupCode());
            tempSet = tempSet == null ? new HashSet<ServiceWordbookVO>() : tempSet;
            tempSet.add(tempWordbook);
            groupWordbookSetMap.put(tempWordbook.getWordbookGroupCode(), tempSet);
        }
        Set<Entry<String, Set<ServiceWordbookVO>>> entrySet = groupWordbookSetMap.entrySet();
        Iterator<Entry<String, Set<ServiceWordbookVO>>> iterator = entrySet.iterator();
        Entry<String, Set<ServiceWordbookVO>> tempGroupWrodbookEntry = null;

        String tempKey = null;
        String tempTranskey = null;
        // 然后遍历所有的entry添加到缓存中
        while (iterator.hasNext()) {
            tempGroupWrodbookEntry = iterator.next();
            tempKey = BASE_KEY + tempGroupWrodbookEntry.getKey();
            tempTranskey = BASE_TRANS_KEY + tempGroupWrodbookEntry.getKey();
            Set<ServiceWordbookVO> value = tempGroupWrodbookEntry.getValue();
            for (ServiceWordbookVO wordbook : value) {
                if (redisService.exists(tempTranskey + wordbook.getWordbookCode())) {
                    redisService.remove(tempTranskey + wordbook.getWordbookCode());
                }
                redisService.addStr(tempTranskey + wordbook.getWordbookCode(), wordbook.getWordbookDesc());
                redisService.addStr(tempTranskey + wordbook.getWordbookCode() + "_TW", ConverterUtils.toString(wordbook.getWordbookDescTW()));
                redisService.addStr(tempTranskey + wordbook.getWordbookCode() + "_EN", ConverterUtils.toString(wordbook.getWordbookDescEN()));
                if (redisService.exists(tempKey)) {
                    redisService.remove(tempKey);
                }
                redisService.addSet(tempKey, tempGroupWrodbookEntry.getValue());
            }
        }
    }


    @Override
    public List<ServiceWordbookVO> findForListFromMap(Map<String, Object> map) {
        return dos2vos(mapper.findForListFromMap(map));
    }


    @Override
    public List<Map<String, Object>> findMapListFromMap(Map<String, Object> map) {
        return mapper.findMapList(map);
    }

    @Override
    public HttpResult<List<ServiceWordbookVO>> getWordBookListByWordBookGroupCode(String wordBookGroupCode) {
        ServiceWordbookVO param = new ServiceWordbookVO();
        param.setWordbookGroupCode(wordBookGroupCode);
        List<ServiceWordbookVO> wordbookList  = this.selectPage(param, Constant.PAGE_ALL,Constant.PAGE_ALL);
        return HttpResult.success(wordbookList);
    }
}
