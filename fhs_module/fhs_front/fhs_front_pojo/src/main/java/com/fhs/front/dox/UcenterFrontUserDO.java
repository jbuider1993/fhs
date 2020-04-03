package com.fhs.front.dox;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 前端用户表(UcenterFrontUser)实体类
 *
 * @author jackwong
 * @since 2019-03-25 13:58:21
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@TableName("t_ucenter_front_user")
public class UcenterFrontUserDO extends BaseDO<UcenterFrontUserDO> {
    private static final long serialVersionUID = 545604903343287075L;
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    @Length(message = "${column.comment}字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("user_name")
    private String userName;

    //昵称
    @Length(message = "昵称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("nick_name")
    private String nickName;

    //生日
    @Length(message = "生日字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("birthday")
    private String birthday;

    //手机号
    @Length(message = "手机号字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("mobile")
    private String mobile;

    //性别 1-男 0-女 2-未填写
    @Length(message = "性别 1-男 0-女 2-未填写字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("sex")
    private String sex;

    //用户密码
    @Length(message = "用户密码字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("passwd")
    private String passwd;

    //身份证号
    @Length(message = "身份证号字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("user_card")
    private String userCard;

    //用户来源 0 PC 1微信
    @Length(message = "用户来源 0 PC 1微信字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("user_resource")
    private String userResource;

    //语种
    @Length(message = "语种字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("language")
    private String language;

    //省
    @Length(message = "省字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("province_id")
    private String provinceId;

    //市
    @Length(message = "市字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("city_id")
    private String cityId;

    //区
    @Length(message = "区字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("area_id")
    private String areaId;

    //地址
    @Length(message = "地址字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @TableField("address")
    private String address;

    //头像
    @Length(message = "头像字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @TableField("image_path")
    private String imagePath;


    //启用标识 0-启用 1-禁用
    @Length(message = "启用标识 0-启用 1-禁用字段的长度最大为1", groups = {Add.class, Update.class}, max = 1)
    @TableField("is_enable")
    private int isEnable;

    //真实姓名
    @TableField("real_name")
    private String realName;


    public UcenterFrontUserDO() {
    }


}