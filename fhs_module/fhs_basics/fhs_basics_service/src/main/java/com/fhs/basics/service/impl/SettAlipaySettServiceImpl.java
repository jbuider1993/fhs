package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SettAlipaySettDO;
import com.fhs.basics.service.SettAlipaySettService;
import com.fhs.basics.vo.SettAlipaySettVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * (UcenterAlipaySett)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */
@Service("settAlipaySettService")
@DataSource("base_business")
public class SettAlipaySettServiceImpl extends BaseServiceImpl<SettAlipaySettVO, SettAlipaySettDO> implements SettAlipaySettService {

}