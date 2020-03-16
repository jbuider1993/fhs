package com.fhs.ucenter.dox;

import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
public class SysMenuPermissionUrlRelaDO extends BaseDO<SysMenuPermissionUrlRelaDO> {

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

    public SysMenuPermissionUrlRelaDO(){
    }

    public SysMenuPermissionUrlRelaDO(Integer permissionId, String url, String oldUrl){
    super();
        this.permissionId = permissionId;
        this.url = url;
        this.oldUrl = oldUrl;
    }

}
