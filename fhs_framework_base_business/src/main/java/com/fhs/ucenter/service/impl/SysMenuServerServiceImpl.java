package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.SysMenuServer;
import com.fhs.ucenter.service.SysMenuServerService;
import org.springframework.stereotype.Service;


/**
 * @Description:服务器配置
 * @author  jianbo.qin
 * @version  [版本号, 2018/03/23 14:19:21]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 * */
@Service("sysMenuServerServiceImpl")
@DataSource("base_business")
public class SysMenuServerServiceImpl extends BaseServiceImpl<SysMenuServer> implements SysMenuServerService
{

}