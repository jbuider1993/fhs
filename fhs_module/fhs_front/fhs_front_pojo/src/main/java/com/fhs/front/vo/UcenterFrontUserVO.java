package com.fhs.front.vo;

import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import com.fhs.front.dox.UcenterFrontUserDO;
import lombok.*;

import java.util.Map;

/**
 * 前端用户vo
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class UcenterFrontUserVO extends UcenterFrontUserDO implements VO {

    /**
     * openidmap
     * key0 微信openid 1 支付宝openid
     */
    private Map<Integer,String> openIdMap;
}
