package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.UcenterFrontUserBind;
import com.fhs.ucenter.service.UcenterFrontUserBindService;
import org.springframework.stereotype.Service;

/**
 * (UcenterFrontUserBind)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
@Service("ucenterFrontUserBindService")
@DataSource("base_business")
public class UcenterFrontUserBindServiceImpl extends BaseServiceImpl<UcenterFrontUserBind>   implements UcenterFrontUserBindService {
    
}