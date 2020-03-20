package com.fhs.basics.service.impl;

import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.basics.service.UcenterFrontUserBindService;
import com.fhs.basics.vo.UcenterFrontUserBindVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * (UcenterFrontUserBind)表服务实现类
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
@Service("ucenterFrontUserBindService")
@DataSource("base_business")
public class UcenterFrontUserBindServiceImpl extends BaseServiceImpl<UcenterFrontUserBindVO, UcenterFrontUserBindDO> implements UcenterFrontUserBindService {

}