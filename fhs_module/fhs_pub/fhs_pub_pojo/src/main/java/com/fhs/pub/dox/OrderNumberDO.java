package com.fhs.pub.dox;

import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author jianbo.qin
 * @version [版本号, 2018/05/10 15:09:42]
 * @Description:生成订单号
 * @versio 1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_service_order_log")
public class OrderNumberDO extends BaseDO<OrderNumberDO> {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @NotNull(message = "pubservice的id字段 不可为null ", groups = {Update.class, Delete.class})
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    /**
     *
     */
    @Length(message = "pubservice的type字段的长度最大为20", groups = {Add.class, Update.class}, max = 20, min = 0)
    @Column(name = "type", nullable = true, length = 20)
    private String type;

    /**
     *
     */
    @Length(message = "pubservice的time字段的长度最大为50", groups = {Add.class, Update.class}, max = 50, min = 0)
    @Column(name = "time", nullable = true, length = 50)
    private String time;

    /**
     *
     */
    @Max(message = "{pubservice的number字段大于int最大值}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{pubservice的number字段小于int小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "number", nullable = true, length = 10)
    private Integer number;

}
