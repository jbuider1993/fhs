package com.fhs.basics.service.impl;

import com.fhs.basics.dox.UcenterAlipaySettDO;
import com.fhs.basics.service.UcenterAlipaySettService;
import com.fhs.basics.vo.UcenterAlipaySettVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * (UcenterAlipaySett)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */
@Service("ucenterAlipaySettService")
@DataSource("base_business")
public class UcenterAlipaySettServiceImpl extends BaseServiceImpl<UcenterAlipaySettVO, UcenterAlipaySettDO> implements UcenterAlipaySettService {

}