package com.fhs.demo.service.impl;

import com.fhs.core.db.DataSource;
import com.fhs.demo.service.CacheTestService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.demo.service.impl
 * @ClassName: CacheTestServiceImpl
 * @Author: JackWang
 * @CreateDate: 2018/11/12 0012 16:41
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/12 0012 16:41
 * @Version: 1.0
 */
@Service
@DataSource("log")
public class CacheTestServiceImpl implements CacheTestService {
    @Override
    // redis-order#(10*1)
    @Cacheable(unless = "#result == null", value = "redis-order#(10*1)", key = "'order_serviceOrderId_' + #name")
    public String getHello(String name) {
        System.out.println("进来了" + new Date().getMinutes());
        return "你好" + name;
    }

    @Cacheable(unless = "#result == null", value = "redis-order#(60*60*2)", key = "'order_serviceOrderId_' + #paramter")
    @Override
    public String getHello2(Map paramter) {
        System.out.println("进来了1223131313" + new Date().getMinutes());
        return "你好1231313445" + paramter;
    }
}
