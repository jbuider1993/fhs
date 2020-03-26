package com.fhs.basics.vo;

import com.fhs.basics.dox.SysOrganizationDO;
import com.fhs.basics.dox.UcenterAlipaySettDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 支付宝设置vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class UcenterAlipaySettVO extends UcenterAlipaySettDO implements VO {

}
