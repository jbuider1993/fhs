package com.fhs.system.trans;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.trans.ITransTypeService;
import com.fhs.core.trans.TransService;
import com.fhs.redis.service.RedisCacheService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Description: 后管机构翻译服务
 * @author  zhangqiang
 * @version [版本号, 2018-09-05]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
public class SysOrganizationTransServiceImpl implements ITransTypeService,InitializingBean {

    /**
     * redis 缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 将自身注册为一个服务
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        TransService.registerTransType(Constant.SYS_ORGANIZATION_INFO, this);
    }

    /**
     * 翻译单条数据
     * @param obj 需要翻译的对象
     * @param toTransList 需要翻译的字段
     */
    @Override
    public void transOne(SuperBean<?> obj, List<Field> toTransList) {

        for (Field tempField : toTransList)
        {
            tempField.setAccessible(true);
            String entId = null;
            try
            {
                entId = StringUtil.toString(tempField.get(obj));
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
            obj.getTransMap().put(tempField.getName() + "OrganizationName", redisCacheService.getStr(Constant.SYS_ORGANIZATION_NAME + entId));

        }
    }

    /**
     * 翻译多条数据
     * @param objList 需要翻译的对象集合
     * @param toTransList 需要翻译的字段集合
     */
    @Override
    public void transMore(List<? extends SuperBean<?>> objList, List<Field> toTransList) {

        for(SuperBean<?> obj : objList)
        {
            transOne(obj, toTransList);
        }
    }

}
