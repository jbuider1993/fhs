package com.fhs.basics.vo;

import com.fhs.basics.dox.SettAlipaySettDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;

/**
 * 支付宝设置vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class SettAlipaySettVO extends SettAlipaySettDO implements VO {

}
