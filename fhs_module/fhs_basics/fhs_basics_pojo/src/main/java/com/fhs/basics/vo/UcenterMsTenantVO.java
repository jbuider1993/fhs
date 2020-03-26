package com.fhs.basics.vo;

import com.fhs.basics.dox.UcenterMpSettDO;
import com.fhs.basics.dox.UcenterMsTenantDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 租户vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class UcenterMsTenantVO extends UcenterMsTenantDO implements VO {

}
