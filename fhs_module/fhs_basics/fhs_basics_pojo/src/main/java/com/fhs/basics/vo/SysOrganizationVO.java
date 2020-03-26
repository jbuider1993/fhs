package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysModelDO;
import com.fhs.basics.dox.SysOrganizationDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 组织机构
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK, TransType.AUTO_TRANS})
public class SysOrganizationVO extends SysOrganizationDO implements VO {

}
