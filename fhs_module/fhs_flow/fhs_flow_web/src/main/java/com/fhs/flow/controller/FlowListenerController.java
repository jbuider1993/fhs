package com.fhs.flow.controller;

import com.fhs.flow.dox.FlowListenerDO;
import com.fhs.flow.vo.FlowListenerVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监听器(FlowListener)表控制层
 *
 * @author jackwong
 * @since 2019-11-11 14:28:44
 */
@RestController
@RequestMapping("/ms/flowListener")
public class FlowListenerController extends ModelSuperController<FlowListenerVO, FlowListenerDO> {
   

}