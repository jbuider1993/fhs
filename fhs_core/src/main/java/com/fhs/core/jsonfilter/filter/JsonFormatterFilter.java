package com.fhs.core.jsonfilter.filter;

import com.alibaba.fastjson.serializer.BeforeFilter;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.jsonfilter.bean.VoConverterObject;
import com.fhs.core.result.HttpResult;

import java.util.Collection;

/**
 * json格式化 实现
 * by wanglei
 */
public class JsonFormatterFilter extends BeforeFilter {

    //本来的key
    private static final Integer KEY = 0;

    //as后的key
    private static final Integer AS_KEY = 1;

    //转换器配置
    private VoConverterObject converterObject;

    public JsonFormatterFilter(VoConverterObject converterObject) {
        this.converterObject = converterObject;
    }

    @Override
    public void writeBefore(Object o) {

        if (o instanceof HttpResult) {
            return;
        }
        if (o instanceof Collection) {
            Collection<?> objs = (Collection<?>) o;
            for (Object obj : objs) {
                writeObject(o);
            }
            return;
        }
        writeObject(o);
    }

    /**
     * 写一个obejct
     *
     * @param obj obj
     */
    private void writeObject(Object obj) {
        if (obj == null) {
            return;
        }
        boolean isSuperBean = obj instanceof SuperBean;
        SuperBean superBean = null;
        if (isSuperBean) {
            superBean = (SuperBean) obj;
        }
        String[] settings = converterObject.getSettings();
        String[] kAsKey = null;
        for (String setting : settings) {
            kAsKey = setting.split(":");
            if (kAsKey[KEY].contains("transMap.") && isSuperBean) {
                super.writeKeyValue(kAsKey[AS_KEY], superBean.getTransMap().get(kAsKey[KEY].replace("transMap.", "")));
            } else {
                super.writeKeyValue(kAsKey[AS_KEY], ReflectUtils.getValue(obj, kAsKey[KEY]));
            }
        }
    }


}
