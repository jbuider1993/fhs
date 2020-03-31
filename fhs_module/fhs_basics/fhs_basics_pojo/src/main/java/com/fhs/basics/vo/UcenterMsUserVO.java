package com.fhs.basics.vo;

import com.fhs.core.base.pojo.vo.VO;
import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;

/**
 * 用户vo
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TransTypes(types = {TransType.WORD_BOOK,TransType.AUTO_TRANS})
public class UcenterMsUserVO extends UcenterMsUserDO implements VO {

}
