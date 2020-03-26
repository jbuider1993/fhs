package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysRoleDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 角色vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK, TransType.AUTO_TRANS})
public class SysRoleVO extends SysRoleDO implements VO {

}
