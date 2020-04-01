package com.fhs.core.base.autodel.service;

import com.fhs.common.spring.ScannerUtils;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.exception.ParamException;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import com.fhs.core.base.autodel.anno.AutoDel;
import com.fhs.core.base.autodel.anno.AutoDelSett;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 自动软删除附表
 * @Author: Wanglei
 * @Date: Created in 10:14 2019/10/15
 */
@Service
public class AutoDelService implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoDelService.class);

    /**
     * service包的路径，多个用逗号分隔
     */
    @Value("${fhs.autodel.service-packege}")
    private String servicePackege;


    /**
     * 用于根据一个被更新的类(表)找到 关联的子类(子表)
     * 然后又可找到相关字段
     */
    private Map<String, Map<Class<? extends BaseServiceImpl>, AutoDelSett>> cacheMap = new HashedMap();


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Set<Class<?>> serviceClazzSet = ScannerUtils.scan(AutoDel.class, servicePackege.split(","));
        // 遍历所有class，获取所有用@AutoValid注释的字段
        if (serviceClazzSet != null) {
            Map<Class<? extends BaseServiceImpl>, AutoDelSett> itemTBLSett = null;
            for (Class<?> serviceClazz : serviceClazzSet) {
                // 获取该类
                Object baseService = SpringContextUtil.getBeanByClass(serviceClazz);
                if (!(baseService instanceof BaseServiceImpl)) {
                    LOGGER.warn("AutoValid 只能用到BaseServiceImpl上,不能用到:" + baseService.getClass());
                    continue;
                }
                AutoDel autoDel = serviceClazz.getAnnotation(AutoDel.class);
                for (AutoDelSett autoDelMainService : autoDel.mainServiceSetts()) {
                    itemTBLSett = cacheMap.containsKey(autoDelMainService.namespace()) ?
                            cacheMap.get(autoDelMainService.namespace()) : new HashedMap();
                    itemTBLSett.put((Class<? extends BaseServiceImpl>) serviceClazz, autoDelMainService);
                    cacheMap.put(autoDelMainService.namespace(), itemTBLSett);
                }
            }
        }
    }

    /**
     * 删除子表数据
     *
     * @param namespace 命名空间
     * @param pkey
     */
    public void deleteItemTBL(String namespace, Object pkey) {
        if(CheckUtils.isNullOrEmpty(namespace)){
            return;
        }
        Map<Class<? extends BaseServiceImpl>, AutoDelSett> itemTBLSett = cacheMap.get(namespace);
        if (itemTBLSett == null) {
            return;
        }
        Set<Class<? extends BaseServiceImpl>> classSet = itemTBLSett.keySet();
        classSet.forEach(cl -> {
            LOGGER.debug("auto del,main namespace:" + namespace + ",pkey:" + pkey);
            SpringContextUtil.getBeanByClass(cl).deleteForMainTblPkey(itemTBLSett.get(cl).field(), pkey);
        });
    }

    /**
     * 删除主表检查子表是否有数据
     *
     * @param namespace  命名空间
     * @param pkey
     */
    public void deleteCheck(String namespace, Object pkey) {
        if(CheckUtils.isNullOrEmpty(namespace)){
            return;
        }
        Map<Class<? extends BaseServiceImpl>, AutoDelSett> itemTBLSett = cacheMap.get(namespace);
        if (itemTBLSett == null) {
            return;
        }
        Set<Class<? extends BaseServiceImpl>> classSet = itemTBLSett.keySet();
        classSet.forEach(cl -> {
            AutoDelSett sett = itemTBLSett.get(cl);
            if (sett.isChecker()) {
                int count = SpringContextUtil.getBeanByClass(cl).findCountForMainTblPkey(sett.field(), pkey);
                if (count > 0) {
                    throw new ParamException("有关联" + sett.desc() + "未删除");
                }
            }
        });
    }

}
