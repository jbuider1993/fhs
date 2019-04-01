package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.UcenterAlipaySett;
import com.fhs.ucenter.service.UcenterAlipaySettService;
import org.springframework.stereotype.Service;

/**
 * (UcenterAlipaySett)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */
@Service("ucenterAlipaySettService")
@DataSource("base_business")
public class UcenterAlipaySettServiceImpl extends BaseServiceImpl<UcenterAlipaySett>   implements UcenterAlipaySettService {
    
}