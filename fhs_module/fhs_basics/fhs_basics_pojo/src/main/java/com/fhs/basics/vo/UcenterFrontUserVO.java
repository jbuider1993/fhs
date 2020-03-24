package com.fhs.basics.vo;

import com.fhs.basics.dox.UcenterFrontUserBindDO;
import com.fhs.basics.dox.UcenterFrontUserDO;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 前端用户vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
public class UcenterFrontUserVO extends UcenterFrontUserDO implements VO {

    /**
     * openidmap
     * key0 微信openid 1 支付宝openid
     */
    private Map<Integer,String> openIdMap;
}
