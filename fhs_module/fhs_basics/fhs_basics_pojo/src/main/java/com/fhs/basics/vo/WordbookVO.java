package com.fhs.basics.vo;

import com.fhs.basics.dox.WordbookDO;
import com.fhs.basics.dox.WordbookGroupDO;
import com.fhs.core.base.pojo.vo.VO;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 字典vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WordbookVO extends WordbookDO implements VO {

}
