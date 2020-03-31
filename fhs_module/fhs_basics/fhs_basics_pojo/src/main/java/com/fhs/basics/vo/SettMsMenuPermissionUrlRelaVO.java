package com.fhs.basics.vo;

import com.fhs.basics.dox.SettMsMenuPermissionUrlRelaDO;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单和URL对应关系
 */
@Data
@NoArgsConstructor
@TransTypes(types = {TransType.WORD_BOOK})
public class SettMsMenuPermissionUrlRelaVO extends SettMsMenuPermissionUrlRelaDO implements VO {

}
