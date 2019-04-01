package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.UcenterMpSett;
import com.fhs.ucenter.service.UcenterMpSettService;
import org.springframework.stereotype.Service;

/**
 * 公众号配置(UcenterMpSett)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:09:24
 */
@Service("ucenterMpSettService")
@DataSource("base_business")
public class UcenterMpSettServiceImpl extends BaseServiceImpl<UcenterMpSett>   implements UcenterMpSettService {
    
}