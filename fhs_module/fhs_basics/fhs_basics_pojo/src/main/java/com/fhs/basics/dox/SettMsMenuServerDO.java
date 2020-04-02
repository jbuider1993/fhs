package com.fhs.basics.dox;


import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author jianbo.qin
 * @version [版本号, 2018-06-01]
 * @Description:
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@TransTypes(types = {TransType.WORD_BOOK})
@Table(name = "t_sett_ms_menu_server")
public class SettMsMenuServerDO extends BaseDO<SettMsMenuServerDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    @Id
    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
    private String id;
    /**
     * 服务名称
     */
    @Column(name = "server_name", nullable = true, length = 255)
    private String serverName;
    /**
     * 服务链接
     */
    @Column(name = "server_url", nullable = true, length = 500)
    private String serverUrl;

    public SettMsMenuServerDO() {

    }

    public SettMsMenuServerDO(String id, String serverName, String serverUrl) {
        this.id = id;
        this.serverName = serverName;
        this.serverUrl = serverUrl;
    }
}
