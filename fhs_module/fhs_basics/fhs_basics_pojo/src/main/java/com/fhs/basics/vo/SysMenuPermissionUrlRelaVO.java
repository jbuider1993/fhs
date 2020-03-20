package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysMenuPermissionDO;
import com.fhs.basics.dox.SysMenuPermissionUrlRelaDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 菜单和URL对应关系
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {BaseTransConstant.WORD_BOOK})
public class SysMenuPermissionUrlRelaVO extends SysMenuPermissionUrlRelaDO implements VO {

}
