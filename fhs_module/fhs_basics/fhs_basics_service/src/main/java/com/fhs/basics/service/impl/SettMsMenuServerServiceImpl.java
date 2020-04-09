package com.fhs.basics.service.impl;

import com.fhs.basics.api.rpc.FeignServerApiService;
import com.fhs.basics.dox.SettMsMenuServerDO;
import com.fhs.basics.service.SettMsMenuServerService;
import com.fhs.basics.vo.SettMsMenuServerVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.annotation.Namespace;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.core.valid.checker.ParamChecker;
import org.springframework.stereotype.Service;


/**
 * @author jianbo.qin
 * @version [版本号, 2018/03/23 14:19:21]
 * @Description:服务器配置
 * @versio 1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@Service("settMsMenuServerService")
@DataSource("base_business")
@Namespace("sett_ms_system")
@AutoTrans(namespace = "sett_ms_system",fields = "serverName",useRedis = true)
public class SettMsMenuServerServiceImpl extends BaseServiceImpl<SettMsMenuServerVO, SettMsMenuServerDO> implements SettMsMenuServerService, FeignServerApiService {

    @Override
    public HttpResult<SettMsMenuServerVO> getMenuServeById(String id) {
        ParamChecker.isNotNullOrEmpty(id,"服务id不可为空");
        SettMsMenuServerVO vo = this.selectById(id);
        ParamChecker.isNotNull(vo,"服务id无效");
        return HttpResult.success(vo);
    }
}