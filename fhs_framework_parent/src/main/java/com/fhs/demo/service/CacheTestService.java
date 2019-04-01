package com.fhs.demo.service;

import java.util.Map;

/**
 * 描述
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.demo.service
 * @ClassName: CacheTestService
 * @Author: JackWang
 * @CreateDate: 2018/11/12 0012 16:40
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/12 0012 16:40
 * @Version: 1.0
 */
public interface CacheTestService {
    String getHello(String name);
    String getHello2(Map paramter);
}
