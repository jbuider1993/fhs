package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.AreaDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 区域
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AreaVO extends AreaDO implements VO {

}
