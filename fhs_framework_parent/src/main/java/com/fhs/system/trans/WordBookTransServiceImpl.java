package com.fhs.system.trans;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.trans.ITransTypeService;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransService;
import com.fhs.redis.service.RedisCacheService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * 字典翻译服务
 * @author Administrator
 *
 */
@Service
public class WordBookTransServiceImpl implements ITransTypeService,InitializingBean{

    /**
     * redis 的key
     */
    private static final String BASE_TRANS_KEY = "service:wordbook:trans:";

    /**
     * redis 缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;


    @Override
    public void transOne(SuperBean<?> obj, List<Field> toTransList) {


        Trans tempTrans = null;

        for (Field tempField : toTransList)
        {
            tempField.setAccessible(true);
            tempTrans = tempField.getAnnotation(Trans.class);
            String bookCode = StringUtil.toString(ReflectUtils.getValue(obj, tempField.getName()));
            String key = tempTrans.key().contains("KEY_") ? StringUtil.toString(ReflectUtils.getValue(obj, tempTrans.key().replace("KEY_", ""))) : tempTrans.key();
            obj.getTransMap().put(tempField.getName() + "Name", redisCacheService.getStr(BASE_TRANS_KEY + key + bookCode));
            /*
            这块翻译影响性能
            obj.getTransMap().put(tempField.getName() + "NameTW", redisCacheService.getStr(BASE_TRANS_KEY + key + bookCode + "_TW"));
            obj.getTransMap().put(tempField.getName() + "NameEN", redisCacheService.getStr(BASE_TRANS_KEY + key + bookCode+ "_EN"));*/
        }
    }

    @Override
    public void transMore(List<? extends SuperBean<?>> objList, List<Field> toTransList) {

        for(SuperBean<?> obj : objList)
        {
            transOne(obj, toTransList);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //注册自己为一个服务
        TransService.registerTransType(Constant.WORD_BOOK, this);
    }
}
