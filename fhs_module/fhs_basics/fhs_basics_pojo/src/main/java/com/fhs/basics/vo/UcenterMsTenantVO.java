package com.fhs.basics.vo;

import com.fhs.basics.dox.UcenterMpSettDO;
import com.fhs.basics.dox.UcenterMsTenantDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 租户vo
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
public class UcenterMsTenantVO extends UcenterMsTenantDO implements VO {

}
