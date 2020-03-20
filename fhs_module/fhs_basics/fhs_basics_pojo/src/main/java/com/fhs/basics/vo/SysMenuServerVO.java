package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysMenuServerDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 服务器vo
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {BaseTransConstant.WORD_BOOK})
public class SysMenuServerVO extends SysMenuServerDO implements VO {

}