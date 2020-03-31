package com.fhs.basics.vo;

import com.fhs.basics.dox.SettMpSettDO;
import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 公众号设置vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class SettMpSettVO extends SettMpSettDO implements VO {

}
