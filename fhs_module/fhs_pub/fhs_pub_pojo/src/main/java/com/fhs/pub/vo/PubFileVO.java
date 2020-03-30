package com.fhs.pub.vo;

import com.fhs.core.base.pojo.vo.VO;
import com.fhs.pub.dox.PubFileDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PubFileVO extends PubFileDO implements VO {

}
