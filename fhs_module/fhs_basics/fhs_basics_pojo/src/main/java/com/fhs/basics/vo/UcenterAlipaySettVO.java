package com.fhs.basics.vo;

import com.fhs.basics.dox.SysOrganizationDO;
import com.fhs.basics.dox.UcenterAlipaySettDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 支付宝设置vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
public class UcenterAlipaySettVO extends UcenterAlipaySettDO implements VO {

}
