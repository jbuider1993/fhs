package com.fhs.basics.vo;

import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.basics.dox.UcenterMpSettDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 公众号设置vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
public class UcenterMpSettVO extends UcenterMpSettDO implements VO {

}
