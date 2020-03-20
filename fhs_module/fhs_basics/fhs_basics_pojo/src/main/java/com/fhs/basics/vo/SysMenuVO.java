package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysMenuDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 系统菜单vo
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {BaseTransConstant.WORD_BOOK})
public class SysMenuVO extends SysMenuDO implements VO {

}
