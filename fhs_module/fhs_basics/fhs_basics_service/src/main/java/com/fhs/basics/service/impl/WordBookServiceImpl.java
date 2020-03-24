package com.fhs.basics.service.impl;

import com.fhs.base.api.ucenter.rpc.FeignWordBookApiService;
import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.mapper.WordbookMapper;
import com.fhs.basics.service.WordBookService;
import com.fhs.basics.vo.WordbookVO;
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
public class WordBookServiceImpl extends BaseServiceImpl<WordbookVO, WordbookDO> implements WordBookService, FeignWordBookApiService {
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
    private RedisCacheService<WordbookVO> redisService;

    @Autowired
    private WordbookMapper mapper;

    @Override
    public List<WordbookVO> getWordBookList(String wordbookGroupCode) {
        if (CheckUtils.isNullOrEmpty(wordbookGroupCode)) {
            return new ArrayList<>();
        }
        List<WordbookVO> list = redisService.getList(BASE_KEY + wordbookGroupCode);
        if (CheckUtils.checkCollectionIsNullOrEmpty(list)) {
            WordbookVO bean = new WordbookVO();
            bean.setWordbookGroupCode(wordbookGroupCode);
            this.initWordBookDataCache(bean);
            list = redisService.getList(BASE_KEY + wordbookGroupCode);
        }
        Collections.sort(list, new Comparator<WordbookVO>() {
            @Override
            public int compare(WordbookVO book1, WordbookVO book2) {
                return book1.getOrderNum() - book2.getOrderNum();
            }
        });
        return list;
    }


    @Override
    public Map<String, String> getWordBookMap(String wordbookGroupCode) {
        Map<String, String> result = new HashMap<>();
        List<WordbookVO> list = redisService.getList(BASE_KEY + wordbookGroupCode);
        for (WordbookVO temp : list) {
            result.put(temp.getWordbookCode(), temp.getWordbookDesc());
        }
        return result;
    }


    @Override
    public void initWordBookDataCache(WordbookDO bean) {
        List<WordbookVO> list = super.select();
        Map<String, Set<WordbookVO>> groupWordbookSetMap = new HashMap<>();
        Set<WordbookVO> tempSet = null;
        WordbookVO tempWordbook = null;
        int listSize = list.size();
        // 按照group类型分组
        for (int i = 0; i < listSize; i++) {
            tempWordbook = list.get(i);
            tempSet = groupWordbookSetMap.get(tempWordbook.getWordbookGroupCode());
            tempSet = tempSet == null ? new HashSet<WordbookVO>() : tempSet;
            tempSet.add(tempWordbook);
            groupWordbookSetMap.put(tempWordbook.getWordbookGroupCode(), tempSet);
        }
        Set<Entry<String, Set<WordbookVO>>> entrySet = groupWordbookSetMap.entrySet();
        Iterator<Entry<String, Set<WordbookVO>>> iterator = entrySet.iterator();
        Entry<String, Set<WordbookVO>> tempGroupWrodbookEntry = null;

        String tempKey = null;
        String tempTranskey = null;
        // 然后遍历所有的entry添加到缓存中
        while (iterator.hasNext()) {
            tempGroupWrodbookEntry = iterator.next();
            tempKey = BASE_KEY + tempGroupWrodbookEntry.getKey();
            tempTranskey = BASE_TRANS_KEY + tempGroupWrodbookEntry.getKey();
            Set<WordbookVO> value = tempGroupWrodbookEntry.getValue();
            for (WordbookVO wordbook : value) {
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
    public List<WordbookVO> findForListFromMap(Map<String, Object> map) {
        return dos2vos(mapper.findForListFromMap(map));
    }


    @Override
    public List<Map<String, Object>> findMapListFromMap(Map<String, Object> map) {
        return mapper.findMapList(map);
    }

    @Override
    public HttpResult<List<WordbookVO>> getWordBookListByWordBookGroupCode(String wordBookGroupCode) {
        WordbookVO param = new WordbookVO();
        param.setWordbookGroupCode(wordBookGroupCode);
        List<WordbookVO> wordbookList  = this.selectPage(param, Constant.PAGE_ALL,Constant.PAGE_ALL);
        return HttpResult.success(wordbookList);
    }
}
