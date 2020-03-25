package com.fhs.basics.vo;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.AreaDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 字典分组vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WordbookGroupVO extends WordbookGroupDO implements VO {

}
