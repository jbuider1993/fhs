package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysMenuDO;
import com.fhs.basics.dox.SysMenuPermissionDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 菜单权限vo
 */
@Data
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class SysMenuPermissionVO extends SysMenuPermissionDO implements VO {

}
