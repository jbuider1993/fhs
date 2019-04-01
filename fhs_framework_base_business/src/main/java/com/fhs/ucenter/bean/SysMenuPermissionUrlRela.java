package com.fhs.ucenter.bean;

import javax.persistence.*;

import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fhs.core.trans.TransTypes;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @Description: 菜单权限和URL关联
* @author  qixiaobo
* @version [版本号, 2018-09-30]
* @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
*/
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@Table(name = "t_ucenter_ms_menu_permission_url_rela")
@TransTypes(types = {"wordbook"})
@Entity
public class SysMenuPermissionUrlRela extends SuperBean<SysMenuPermissionUrlRela> {

private static final long serialVersionUID = 1L;

    /**
    * 权限id
    */

    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
    @Id
    private Integer permissionId;
    /**
    * 关联url
    */
    @NotEmpty
    @NotNull(message="关联url字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "关联url字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @Column(name = "url")
    private String url;

    @Transient
    private String oldUrl;

    public SysMenuPermissionUrlRela(){
    }

    public SysMenuPermissionUrlRela(Integer permissionId, String url, String oldUrl){
    super();
        this.permissionId = permissionId;
        this.url = url;
        this.oldUrl = oldUrl;
    }

}
