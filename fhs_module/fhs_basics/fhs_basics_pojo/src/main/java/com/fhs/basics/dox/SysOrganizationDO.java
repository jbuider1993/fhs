package com.fhs.basics.dox;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-04]
 * @Description:机构管理表
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_ucenter_ms_organization")
public class SysOrganizationDO extends BaseDO<SysOrganizationDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */

    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
    @Id
    private String id;
    /**
     * 机构名称
     */
    @NotNull(message = "机构名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "机构名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "name")
    private String name;
    /**
     * 父类编号
     */
    @NotNull(message = "父类编号字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "父类编号字段的长度最大为32", groups = {Add.class, Update.class}, max = 255)
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 同级菜单排行第几
     */
    @NotNull(message = "同级菜单排行第几字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "同级菜单排行第几字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "ranking")
    private String ranking;
    /**
     * 是否启用(0:启用 1:禁用)
     */
    @Max(message = "是否启用(0:启用 1:禁用)字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "是否启用(0:启用 1:禁用)字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = TransType.WORD_BOOK, key = "is_enable")
    @Column(name = "is_enable")
    private Integer isEnable;


    @Column(name = "group_code")
    private String groupCode;

}
