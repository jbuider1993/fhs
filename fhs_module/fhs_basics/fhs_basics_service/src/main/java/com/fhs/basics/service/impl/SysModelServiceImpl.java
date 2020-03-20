package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SysModelDO;
import com.fhs.basics.service.SysModelService;
import com.fhs.basics.vo.SysModelVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jianbo.qin
 * @since 2018-05-29
 */
@Service
@DataSource("base_business")
public class SysModelServiceImpl extends BaseServiceImpl<SysModelVO, SysModelDO> implements SysModelService {

}
