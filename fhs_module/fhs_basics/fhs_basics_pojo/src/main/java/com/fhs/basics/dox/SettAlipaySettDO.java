package com.fhs.basics.dox;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * (UcenterAlipaySett)实体类
 *
 * @author jackwong
 * @since 2019-03-19 16:10:29
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("t_sett_alipay_sett")
public class SettAlipaySettDO extends BaseDO<SettAlipaySettDO> {
    private static final long serialVersionUID = -22527353735624120L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //服务号名称
    @NotEmpty
    @NotNull(message = "服务号名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "服务号名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("name")
    private String name;

    //appid
    @NotEmpty
    @NotNull(message = "appid字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "appid字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("app_id")
    private String appId;

    //应用公钥
    @NotEmpty
    @NotNull(message = "应用公钥字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "应用公钥字段的长度最大为1000", groups = {Add.class, Update.class}, max = 1000)
    @TableField("app_key")
    private String appKey;

    //支付宝公钥
    @NotEmpty
    @NotNull(message = "支付宝公钥字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "支付宝公钥字段的长度最大为1000", groups = {Add.class, Update.class}, max = 1000)
    @TableField("alipay_key")
    private String alipayKey;

    //扩展编码
    @NotEmpty
    @NotNull(message = "扩展编码字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "扩展编码字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("extends_code")
    private String extendsCode;

    /**
     * 应用私钥
     */
    @TableField("app_private_key")
    private String appPrivateKey;


    public SettAlipaySettDO() {
    }

    public SettAlipaySettDO(
            String id,
            String name,
            String appId,
            String appKey,
            String alipayKey,
            String extendsCode,
            String appPrivateKey
    ) {
        this.id = id;
        this.name = name;
        this.appId = appId;
        this.appKey = appKey;
        this.alipayKey = alipayKey;
        this.extendsCode = extendsCode;
        this.appPrivateKey = appPrivateKey;
    }


}