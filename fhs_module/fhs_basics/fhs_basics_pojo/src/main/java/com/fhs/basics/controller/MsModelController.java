package com.fhs.basics.controller;

import com.fhs.basics.dox.SysModelDO;
import com.fhs.basics.vo.SysModelVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 模块管理action
 * </p>
 *
 * @author jianbo.qin
 * @since 2018-05-29
 */
@Controller
@RequestMapping("ms/sysModel")
public class MsModelController extends ModelSuperController<SysModelVO, SysModelDO>
{
    
}
