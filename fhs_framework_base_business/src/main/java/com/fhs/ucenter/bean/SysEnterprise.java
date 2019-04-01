package com.fhs.ucenter.bean;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.strategy.enume.GeneratedType;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import com.mybatis.jpa.annotation.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description: 企业实体
 * @author  zhangqiang
 * @version [版本号, 2018-08-13]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_ucenter_ms_enterprise")
@TransTypes(types = {Constant.WORD_BOOK, Constant.AREA_INFO})
public class SysEnterprise extends BaseDO<SysEnterprise> {

    private static final long serialVersionUID = 1L;
    
    /**
     * 企业id
     */
    @NotNull(message = "id字段不可为null ", groups = {Update.class, Delete.class})
	@Id
	@GeneratedValue(generator= GeneratedType.UUID)
    private String entId;
    /**
     * 企业名称
     */
	@NotEmpty
	@NotNull(message="企业名称字段不可为null", groups = {Add.class,Update.class, Delete.class})
	@Length(message = "企业名称字段的长度最大为100", groups = {Add.class, Update.class}, max = 100)
    @Column(name = "ent_name")
	@Like
    private String entName;
    /**
     * 企业分类
     */
    @NotNull
    @Max(message = "企业分类字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "企业分类字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "ent_classify")
	@Trans(type = Constant.WORD_BOOK, key = "ent_classify")
    private Integer entClassify;
    /**
     * 省
     */
	@NotEmpty
	@NotNull(message="省字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "省字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
	@Trans(type = Constant.AREA_INFO, key = Constant.AREA_NAME)
    @Column(name = "province_id")
    private String provinceId;
    /**
     * 市
     */
	@NotEmpty
	@NotNull(message="市字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "市字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
	@Trans(type = Constant.AREA_INFO, key = Constant.AREA_NAME)
    @Column(name = "city_id")
    private String cityId;
    /**
     * 区/县
     */
	@NotEmpty
	@NotNull(message="区/县字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "区/县字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
	@Trans(type = Constant.AREA_INFO, key = Constant.AREA_NAME)
    @Column(name = "area_id")
	private String areaId;
    /**
     * 企业地址
     */
	@NotEmpty
	@NotNull(message="企业地址字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "企业地址字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @Column(name = "address")
    private String address;
    /**
     * 经度
     */
	@NotEmpty
	@NotNull(message="经度字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "经度字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @Column(name = "longitude")
    private String longitude;
    /**
     * 纬度
     */
	@NotEmpty
	@NotNull(message="纬度字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "纬度字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @Column(name = "latitude")
    private String latitude;
    /**
     * 法人姓名
     */
	@NotEmpty
	@NotNull(message="法人姓名字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "法人姓名字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @Column(name = "corporation_name")
    private String corporationName;
    /**
     * 法人电话
     */
	@NotEmpty
	@NotNull(message="法人电话字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "法人电话字段的长度最大为16", groups = {Add.class, Update.class}, max = 16)
    @Column(name = "corporation_phone")
    private String corporationPhone;
    /**
     * 法人邮箱
     */
	@NotEmpty
	@NotNull(message="法人邮箱字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "法人邮箱字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @Column(name = "corporation_email")
    private String corporationEmail;
    /**
     * 企业电话
     */
	@NotEmpty
	@NotNull(message="企业电话字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "企业电话字段的长度最大为16", groups = {Add.class, Update.class}, max = 16)
    @Column(name = "ent_phone")
    private String entPhone;
    /**
     * 企业logo地址
     */
	@NotEmpty
	@NotNull(message="企业logo地址字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "企业logo地址字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @Column(name = "ent_logo_src")
    private String entLogoSrc;
    /**
     * 企业详情
     */
	@Length(message = "企业详情字段的长度最大为", groups = {Add.class, Update.class})
    @Column(name = "ent_detail")
    private String entDetail;
    /**
     * 启用标识 0-启用 1-禁用
     */
    @NotNull
    @Max(message = "启用标识 0-启用 1-禁用字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "启用标识 0-启用 1-禁用字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
	@Trans(type = "wordbook", key = "is_disable")
    @Column(name = "is_disable")
    private Integer isDisable;

    /**
     * @desc 房间json
     */
//    @NotEmpty
//    @NotNull(message="房间信息字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "房间信息字段的长度最大为5000", groups = {Add.class, Update.class}, max = 5000)
    @Column(name = "house_json")
    private String houseJson;
}
