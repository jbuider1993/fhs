package com.fhs.basics.dox;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import com.mybatis.jpa.annotation.Like;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-26]
 * @Description:
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "t_ucenter_ms_system")
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
@Entity
public class SysSystemDO extends BaseDO<SysSystemDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
    @Id
    private String id;
    /**
     * 子系统名称
     */
    @NotEmpty
    @NotNull(message = "子系统名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "子系统名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "name")
    @Like
    private String name;
    /**
     * 排序
     */
    @Max(message = "排序字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "排序字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "sort")
    private Integer sort;
    /**
     * 子系统logo
     */
    @NotEmpty
    @NotNull(message = "子系统logo字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "子系统logo字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "logo")
    private String logo;
    /**
     * 是否禁用 0:启用 1:禁用
     */
    @Max(message = "是否禁用 0:启用 1:禁用字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "是否禁用 0:启用 1:禁用字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Trans(type = Constant.WORD_BOOK, key = "is_disable")
    @Column(name = "is_disable")
    private Integer isDisable;
    /**
     * 0 全新 1 集成现有
     */
    @Max(message = "0 全新 1 集成现有字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "0 全新 1 集成现有字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "type")
    @Trans(type = Constant.WORD_BOOK, key = "system_type")
    private Integer type;
    /**
     * 如果是集成第三方，则写第三方url
     */
    @NotEmpty
    @NotNull(message = "如果是集成第三方，则写第三方url字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "如果是集成第三方，则写第三方url字段的长度最大为255", groups = {Add.class, Update.class}, max = 255)
    @Column(name = "url")
    private String url;

    /**
     * 子系统首页url
     */
    @NotEmpty
    @NotNull(message = "子系统首页url字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "子系统首页url字段的长度最大为255", groups = {Add.class, Update.class}, max = 255)
    @Column(name = "index_url")
    private String indexUrl;

    public SysSystemDO() {
    }

    public SysSystemDO(String id, String name, Integer sort, String logo, Integer isDisable, Integer type, String url, String indexUrl) {
        super();
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.logo = logo;
        this.isDisable = isDisable;
        this.type = type;
        this.url = url;
        this.indexUrl = indexUrl;
    }

}
