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
 * 公众号配置(UcenterMpSett)实体类
 *
 * @author jackwong
 * @since 2019-03-11 14:24:39
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("t_sett_mp_sett")
public class SettMpSettDO extends BaseDO<SettMpSettDO> {
    private static final long serialVersionUID = 296286961263761719L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //公众号名称
    @NotEmpty
    @NotNull(message = "公众号名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "公众号名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("name")
    private String name;
    //appid
    @NotEmpty
    @NotNull(message = "appid字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "appid字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("app_id")
    private String appId;
    //密钥
    @NotEmpty
    @NotNull(message = "密钥字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "密钥字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("app_secret")
    private String appSecret;
    //管理员
    @NotEmpty
    @NotNull(message = "管理员字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "管理员字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("contacts")
    private String contacts;
    //第三方编码(做多个公众号的时候使用)
    @Length(message = "第三方编码(做多个公众号的时候使用)字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("extends_code")
    private String extendsCode;
    //token
    @Length(message = "token字段的长度最大为50", groups = {Add.class, Update.class}, max = 50)
    @TableField("token")
    private String token;
    //aeskey
    @Length(message = "aeskey字段的长度最大为50", groups = {Add.class, Update.class}, max = 50)
    @TableField("aes_key")
    private String aesKey;

    public SettMpSettDO() {
    }

    public SettMpSettDO(
            String id,
            String name,
            String appId,
            String appSecret,
            String contacts,
            String extendsCode,
            String token,
            String aesKey) {
        this.id = id;
        this.name = name;
        this.appId = appId;
        this.appSecret = appSecret;
        this.contacts = contacts;
        this.extendsCode = extendsCode;
        this.token = token;
        this.aesKey = aesKey;
    }


}