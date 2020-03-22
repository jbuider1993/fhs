package com.fhs.basics.controller;

import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.basics.vo.UcenterFrontUserBindVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (UcenterFrontUserBind)表控制层
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */
@RestController
@RequestMapping("/ms/ucenterFrontUserBind")
public class UcenterFrontUserBindController extends ModelSuperController<UcenterFrontUserBindVO, UcenterFrontUserBindDO> {
   

}