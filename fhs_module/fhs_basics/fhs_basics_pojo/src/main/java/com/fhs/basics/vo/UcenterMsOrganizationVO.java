package com.fhs.basics.vo;

import com.fhs.basics.dox.UcenterMsOrganizationDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;

/**
 * 组织机构
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK, TransType.AUTO_TRANS})
public class UcenterMsOrganizationVO extends UcenterMsOrganizationDO implements VO {

}
