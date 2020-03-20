package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SysMenuServerDO;
import com.fhs.basics.service.SysMenuServerService;
import com.fhs.basics.vo.SysMenuServerVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;


/**
 * @author jianbo.qin
 * @version [版本号, 2018/03/23 14:19:21]
 * @Description:服务器配置
 * @versio 1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@Service("sysMenuServerServiceImpl")
@DataSource("base_business")
public class SysMenuServerServiceImpl extends BaseServiceImpl<SysMenuServerVO, SysMenuServerDO> implements SysMenuServerService {

}