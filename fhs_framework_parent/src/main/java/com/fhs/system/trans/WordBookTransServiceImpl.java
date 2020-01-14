package com.fhs.system.trans;

import com.fhs.common.constant.Constant;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.result.HttpResult;
import com.fhs.core.trans.*;
import com.fhs.system.api.FeignWordBookApiService;
import com.fhs.system.bean.WordbookVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典翻译服务
 *
 * @author Administrator
 */
@Service
public class WordBookTransServiceImpl implements ITransTypeService, InitializingBean, ApplicationRunner {

    private static final Logger LOGGER = Logger.getLogger(WordBookTransServiceImpl.class);

    /**
     * 用来放字典缓存的map
     */
    private static Map<String, String> wordBookTransMap = new HashMap<>();

    private static Map<String, String> unWordBookTransMap = new HashMap<>();


    private FeignWordBookApiService wordBookService;

    @Override
    public void transOne(SuperBean<?> obj, List<Field> toTransList) {
        Trans tempTrans = null;

        for (Field tempField : toTransList) {
            tempField.setAccessible(true);
            tempTrans = tempField.getAnnotation(Trans.class);
            String bookCode = StringUtil.toString(ReflectUtils.getValue(obj, tempField.getName()));
            String key = tempTrans.key().contains("KEY_") ? StringUtil.toString(ReflectUtils.getValue(obj, tempTrans.key().replace("KEY_", ""))) : tempTrans.key();
            //sex_0/1  男 女
            obj.getTransMap().put(tempField.getName() + "Name", wordBookTransMap.get(key + "_" + bookCode));

        }
    }

    @Override
    public void transMore(List<? extends SuperBean<?>> objList, List<Field> toTransList) {
        for (SuperBean<?> obj : objList) {
            transOne(obj, toTransList);
        }
    }

    /**
     * 反向翻译
     *
     * @param obj 需要翻译的对象
     */
    public void unTransOne(SuperBean<?> obj) {
        if (obj == null) {
            return;
        }
        ClassInfo info = ClassManager.getClassInfoByName(obj.getClass());
        String[] transTypes = info.getTransTypes();
        if (transTypes == null) {
            return;
        }
        List<Field> transFieldList = null;
        for (String type : transTypes) {
            transFieldList = info.getTransField(type);
            if (transFieldList == null || transFieldList.size() == 0) {
                continue;
            }
            Trans tempTrans = null;

            for (Field tempField : transFieldList) {
                tempField.setAccessible(true);
                tempTrans = tempField.getAnnotation(Trans.class);
                String bookCode = StringUtil.toString(ReflectUtils.getValue(obj, tempField.getName()));
                String key = tempTrans.key().contains("KEY_") ? StringUtil.toString(ReflectUtils.getValue(obj, tempTrans.key().replace("KEY_", ""))) : tempTrans.key();
                //sex_0/1  男 女
                obj.getTransMap().put(tempField.getName() + "Name", unWordBookTransMap.get(key + "_" + bookCode));
            }
        }
    }

    /**
     * 翻译多个 字段
     *
     * @param objList 需要翻译的对象集合
     * @param objList 需要翻译的字段集合
     */
    public void unTransMore(List<? extends SuperBean<?>> objList) {
        if (objList == null || objList.size() == 0) {
            return;
        }
        for (SuperBean<?> obj : objList) {
            unTransOne(obj);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //注册自己为一个服务
        TransService.registerTransType(Constant.WORD_BOOK, this);
        TransMessageListener.regTransRefresher(Constant.WORD_BOOK, this::refreshWordBookCache);
    }

    /**
     * 根据消息刷新自己的缓存
     * 如果消息中包含key=xx 则只刷新这个key的缓存 如果不包含key则刷新全部缓存
     *
     * @param message
     */
    public void refreshWordBookCache(Map<String, Object> message) {
        if (wordBookService == null) {
            wordBookService = SpringContextUtil.getBeanByClassForApi(FeignWordBookApiService.class);
        }
        String wordbookGroupcode = null;
        if (message != null && message.containsKey("wordbookGroupCode")) {
            wordbookGroupcode = ConverterUtils.toString(message.get("wordbookGroupCode"));

        }
        HttpResult<List<WordbookVO>> result = wordBookService.getWordBookList(wordbookGroupcode);
        if (result.getCode() == Constant.HPROSE_SUCCESS_CODE) {
            List<WordbookVO> wordbookVOList = result.getData();
            wordbookVOList.forEach(wordbookVO -> {
                wordBookTransMap.put(wordbookVO.getWordbookGroupCode() + "_" + wordbookVO.getWordbookCode(), wordbookVO.getWordbookDesc());
                unWordBookTransMap.put(wordbookVO.getWordbookGroupCode() + "_" + wordbookVO.getWordbookDesc(), wordbookVO.getWordbookCode());
            });
        } else {
            LOGGER.error("调用获取字典错误" + message);
        }
        //调用base的接口
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        refreshWordBookCache(null);
    }
}
