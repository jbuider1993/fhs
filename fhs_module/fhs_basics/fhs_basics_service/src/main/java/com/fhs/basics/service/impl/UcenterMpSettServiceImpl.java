package com.fhs.basics.service.impl;

import com.fhs.basics.dox.UcenterMpSettDO;
import com.fhs.basics.service.UcenterMpSettService;
import com.fhs.basics.vo.UcenterMpSettVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * 公众号配置(UcenterMpSett)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:09:24
 */
@Service("ucenterMpSettService")
@DataSource("base_business")
public class UcenterMpSettServiceImpl extends BaseServiceImpl<UcenterMpSettVO, UcenterMpSettDO> implements UcenterMpSettService {

}