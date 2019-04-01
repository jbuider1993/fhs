package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.SysModel;
import com.fhs.ucenter.service.SysModelService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jianbo.qin
 * @since 2018-05-29
 */
@Service
@DataSource("base_business")
public class SysModelServiceImpl extends BaseServiceImpl<SysModel> implements SysModelService
{
    
}
