package com.fhs.ucenter.bean;


import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.strategy.enume.GeneratedType;

import javax.persistence.*;

/**
 * @Description:
 * @author  jianbo.qin
 * @version [版本号, 2018-06-01]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@Entity
@Table(name = "t_ucenter_ms_model")
public class SysModel extends BaseDO<SysModel> {

    private static final long serialVersionUID = 1L;
    
    /**
     * 模块编号
     */
    @Id
    @GeneratedValue(generator = GeneratedType.UUID)
    private String id;
    /**
     * 模块名称
     */
    @Column(name = "model_name", nullable = true, length = 255)
    private String modelName;
    /**
     * 服务编号
     */
    @Column(name = "model_server_id", nullable = true, length = 32)
    private String modelServerId;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelServerId() {
        return modelServerId;
    }

    public void setModelServerId(String modelServerId) {
        this.modelServerId = modelServerId;
    }



    @Override
    public String toString() {
        return "SysModel{" +
        "id=" + id +
        ", modelName=" + modelName +
        ", modelServerId=" + modelServerId +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}";
    }
}
