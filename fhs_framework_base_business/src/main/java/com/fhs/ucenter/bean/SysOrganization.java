package com.fhs.ucenter.bean;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.strategy.enume.GeneratedType;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:机构管理表
 * @author  qixiaobo
 * @version [版本号, 2018-09-04]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */

@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_ucenter_ms_organization")
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
@Entity
public class SysOrganization extends BaseDO<SysOrganization> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */

    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
    @Id
    @GeneratedValue(generator= GeneratedType.UUID)
    private String id;
    /**
     * 机构名称
     */
    @NotEmpty
    @NotNull(message="机构名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "机构名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "name")
    private String name;
    /**
     * 父类编号
     */
    @NotEmpty
    @NotNull(message="父类编号字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "父类编号字段的长度最大为32", groups = {Add.class, Update.class}, max = 255)
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 同级菜单排行第几
     */
    @NotEmpty
    @NotNull(message="同级菜单排行第几字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "同级菜单排行第几字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "ranking")
    private String ranking;
    /**
     * 是否启用(0:启用 1:禁用)
     */
    @Max(message = "是否启用(0:启用 1:禁用)字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "是否启用(0:启用 1:禁用)字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = Constant.WORD_BOOK, key = "is_disable")
    @Column(name = "is_disable")
    private Integer isDisable;
    /**
     * 小区id
     */
    @NotEmpty
    @NotNull(message="小区id字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "小区id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "project_id")
    private String projectId;

}
