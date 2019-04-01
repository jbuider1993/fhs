package com.fhs.ucenter.action;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.ucenter.bean.SysModel;
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
public class MsModelAction extends ModelSuperAction<SysModel>
{
    
}
