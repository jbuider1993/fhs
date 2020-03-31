package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SettMsModelDO;
import com.fhs.basics.service.SettMsModelService;
import com.fhs.basics.vo.SettMsModelVO;
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
public class SettMsModelServiceImpl extends BaseServiceImpl<SettMsModelVO, SettMsModelDO> implements SettMsModelService {

}
