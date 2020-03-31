package com.fhs.basics.vo;

import com.fhs.basics.dox.SettMsSystemDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.*;

/**
 * 子系统vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class SettMsSystemVO extends SettMsSystemDO implements VO {

}
