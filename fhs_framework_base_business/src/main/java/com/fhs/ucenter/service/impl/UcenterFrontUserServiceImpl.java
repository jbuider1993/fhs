package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.UcenterFrontUser;
import com.fhs.ucenter.service.UcenterFrontUserService;
import org.springframework.stereotype.Service;

/**
 * 前端用户表(UcenterFrontUser)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
@Service("ucenterFrontUserService")
@DataSource("base_business")
public class UcenterFrontUserServiceImpl extends BaseServiceImpl<UcenterFrontUser>   implements UcenterFrontUserService {
    
}