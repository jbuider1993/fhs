package com.fhs.front.dox;

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
 * (UcenterFrontUserBind)实体类
 *
 * @author jackwong
 * @since 2019-03-11 14:37:18
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("t_ucenter_front_user_bind")
public class UcenterFrontUserBindDO extends BaseDO<UcenterFrontUserBindDO> {
    private static final long serialVersionUID = 920530665191970437L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //用户id
    @NotEmpty
    @NotNull(message = "用户id字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "用户id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("user_id")
    private String userId;
    //openId
    @NotEmpty
    @NotNull(message = "openId字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "openId字段的长度最大为50", groups = {Add.class, Update.class}, max = 50)
    @TableField("auth_openId")
    private String authOpenid;
    //0微信公众号 1 小程序 2 微信APP 3 qq app 4 微博app
    @NotNull(message = "0微信公众号 1 小程序 2 微信APP 3 qq app 4 微博app字段不可为null", groups = {Update.class, Delete.class})
    @TableField("auth_openId_type")
    private Integer authOpenidType;

    //创建时间
    @NotEmpty
    @NotNull(message = "创建时间字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "创建时间字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)


    public UcenterFrontUserBindDO() {
    }

    public UcenterFrontUserBindDO(
            String id,
            String userId,
            String authOpenid,
            Integer authOpenidType) {
        this.id = id;
        this.userId = userId;
        this.authOpenid = authOpenid;
        this.authOpenidType = authOpenidType;
    }


}