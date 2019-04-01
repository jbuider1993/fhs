package com.fhs.system.service.impl;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.system.bean.Wordbook;
import com.fhs.system.dao.WordbookDAO;
import com.fhs.system.service.WordBookService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
@DataSource("base_business")
public class WordBookServiceImpl extends BaseServiceImpl<Wordbook> implements WordBookService
{
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
    private RedisCacheService<Wordbook> redisService;

    @Autowired
    private WordbookDAO dao;

   @Override
    public List<Wordbook> getWordBookList(String wordbookGroupCode)
    {
    	if(CheckUtils.isNullOrEmpty(wordbookGroupCode))
    	{
    		return new ArrayList<>();
    	}
        List<Wordbook> list = redisService.getList(BASE_KEY + wordbookGroupCode);
        if (CheckUtils.checkCollectionIsNullOrEmpty(list))
        {
            Wordbook bean = new Wordbook();
            bean.setWordbookGroupCode(wordbookGroupCode);
            this.initWordBookDataCache(bean);
            list = redisService.getList(BASE_KEY + wordbookGroupCode);
        }
        Collections.sort(list, new Comparator<Wordbook>(){
            @Override
            public int compare(Wordbook book1,Wordbook book2) {
                return  book1.getOrderNum() - book2.getOrderNum();
            }
        });
        return list;
    }



    @Override
	public Map<String, String> getWordBookMap(String wordbookGroupCode) {
    	Map<String,String> result = new HashMap<>();
    	List<Wordbook> list = redisService.getList(BASE_KEY + wordbookGroupCode);
    	for(Wordbook temp : list)
    	{
    		result.put(temp.getWordbookCode(), temp.getWordbookDesc());
    	}
		return result;
	}





    @Override
    public void initWordBookDataCache(Wordbook bean)
    {
        List<Wordbook> list = dao.selectPageJpa(bean,-1,-1);
        Map<String, Set<Wordbook>> groupWordbookSetMap = new HashMap<>();
        Set<Wordbook> tempSet = null;
        Wordbook tempWordbook = null;
        int listSize = list.size();
        // 按照group类型分组
        for (int i = 0; i < listSize; i++)
        {
            tempWordbook = list.get(i);
            tempSet = groupWordbookSetMap.get(tempWordbook.getWordbookGroupCode());
            tempSet = tempSet == null ? new HashSet<Wordbook>() : tempSet;
            tempSet.add(tempWordbook);
            groupWordbookSetMap.put(tempWordbook.getWordbookGroupCode(), tempSet);
        }
        Set<Entry<String, Set<Wordbook>>> entrySet = groupWordbookSetMap.entrySet();
        Iterator<Entry<String, Set<Wordbook>>> iterator = entrySet.iterator();
        Entry<String, Set<Wordbook>> tempGroupWrodbookEntry = null;

        String tempKey = null;
        String tempTranskey = null;
        // 然后遍历所有的entry添加到缓存中
        while (iterator.hasNext())
        {
            tempGroupWrodbookEntry = iterator.next();
            tempKey = BASE_KEY + tempGroupWrodbookEntry.getKey();
            tempTranskey = BASE_TRANS_KEY +tempGroupWrodbookEntry.getKey();
            Set<Wordbook> value = tempGroupWrodbookEntry.getValue();
            for (Wordbook wordbook : value)
            {
                if(redisService.exists(tempTranskey + wordbook.getWordbookCode()))
                {
                    redisService.remove(tempTranskey + wordbook.getWordbookCode());
                }
                redisService.addStr(tempTranskey + wordbook.getWordbookCode(), wordbook.getWordbookDesc());
                redisService.addStr(tempTranskey + wordbook.getWordbookCode() + "_TW", ConverterUtils.toString(wordbook.getWordbookDescTW()));
                redisService.addStr(tempTranskey + wordbook.getWordbookCode() + "_EN", ConverterUtils.toString(wordbook.getWordbookDescEN()));
                if(redisService.exists(tempKey)){
                    redisService.remove(tempKey);
                }
                redisService.addSet(tempKey, tempGroupWrodbookEntry.getValue());
            }
        }
    }



    @Override
    public List<Wordbook> findForList(Wordbook bean)
    {
        List<Wordbook> list = dao.findForList(bean);
        return list;
    }

    @Override
    public int add(Wordbook bean)
    {
        return dao.add(bean);
    }

    @Override
    public boolean update(Wordbook bean)
    {
        return dao.update(bean) > Constant.ZREO;
    }

    @Override
    public boolean delete(Wordbook bean)
    {
        return dao.delete(bean) > Constant.ZREO;
    }

    @Override
    public int findCount(Wordbook bean)
    {
        return dao.findCount(bean);
    }

    @Override
    public List<Wordbook> findForListFromMap(Map<String, Object> map)
    {
        return dao.findForListFromMap(map);
    }

    @Override
    public Wordbook findBean(Wordbook bean)
    {
        return dao.findBean(bean);
    }

    @Override
    public List<Map<String, Object>> findMapListFromMap(Map<String, Object> map)
    {
        return dao.findMapList(map);
    }

}
