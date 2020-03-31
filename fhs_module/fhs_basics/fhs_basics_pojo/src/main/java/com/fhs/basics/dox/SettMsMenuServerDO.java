package com.fhs.basics.dox;


import com.fhs.core.base.dox.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author jianbo.qin
 * @version [版本号, 2018-06-01]
 * @Description:
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_sett_ms_menu_server")
public class SettMsMenuServerDO extends BaseDO<SettMsMenuServerDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    @Id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

}
