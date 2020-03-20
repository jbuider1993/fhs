package com.fhs.basics.service.impl;

import com.fhs.basics.dox.UcenterFrontUserDO;
import com.fhs.basics.service.UcenterFrontUserService;
import com.fhs.basics.vo.UcenterFrontUserVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * 前端用户表(UcenterFrontUser)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:14:58
 */
@Service("ucenterFrontUserService")
@DataSource("base_business")
public class UcenterFrontUserServiceImpl extends BaseServiceImpl<UcenterFrontUserVO, UcenterFrontUserDO> implements UcenterFrontUserService {

}