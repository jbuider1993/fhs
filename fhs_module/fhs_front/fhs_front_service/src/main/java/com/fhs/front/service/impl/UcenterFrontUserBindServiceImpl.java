package com.fhs.front.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import com.fhs.front.dox.UcenterFrontUserBindDO;
import com.fhs.front.service.UcenterFrontUserBindService;
import com.fhs.front.vo.UcenterFrontUserBindVO;
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