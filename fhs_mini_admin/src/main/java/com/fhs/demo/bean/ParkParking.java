package com.fhs.demo.bean;


import com.baomidou.mybatisplus.annotation.*;
import com.fhs.core.group.*;
import com.fhs.core.base.bean.BaseDO;


import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 停车场表(ParkParking)实体类
 *
 * @author sb生成的代码
 * @since 2020-01-19 20:21:05
 */

@Data
@Builder
@TableName("t_park_parking")
public class ParkParking extends BaseDO<ParkParking> {
    private static final long serialVersionUID = -84712852744901545L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //停车场名称
    @NotEmpty
    @NotNull(message = "停车场名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "停车场名称字段的长度最大为100", groups = {Add.class, Update.class}, max = 100)
    @TableField("park_name")
    private String parkName;

    //区域编码
    @Length(message = "区域编码字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("area_count")
    private String areaCount;

    //本停车场停车位数
    @NotEmpty
    @NotNull(message = "本停车场停车位数字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "本停车场停车位数字段的长度最大为3", groups = {Add.class, Update.class}, max = 3)
    @TableField("space_count")
    private String spaceCount;

    //放大率
    @Length(message = "放大率字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("zoom_rate")
    private String zoomRate;

    //停车场经度  停车场中心的
    @Length(message = "停车场经度  停车场中心的字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("longitude")
    private String longitude;

    //停车场纬度  停车场中心的
    @Length(message = "停车场纬度  停车场中心的字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("latitude")
    private String latitude;

    //车场的具体位置，哪条街，哪个路
    @Length(message = "车场的具体位置，哪条街，哪个路字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("address")
    private String address;

    //是否有效 1 有效 0无效
    @Length(message = "是否有效 1 有效 0无效字段的长度最大为1", groups = {Add.class, Update.class}, max = 1)
    @TableField("is_disable")
    private String isDisable;

    //运营模式
    @Length(message = "运营模式字段的长度最大为1", groups = {Add.class, Update.class}, max = 1)
    @TableField("operation_way")
    private String operationWay;

    //运营单位
    @Length(message = "运营单位字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("business_unit")
    private String businessUnit;

    //停车场类型
    @Length(message = "停车场类型字段的长度最大为1", groups = {Add.class, Update.class}, max = 1)
    @TableField("park_type")
    private String parkType;

    //使用年限
    @Length(message = "使用年限字段的长度最大为10", groups = {Add.class, Update.class}, max = 10)
    @TableField("service_life")
    private String serviceLife;

    //产权单位
    @Length(message = "产权单位字段的长度最大为64", groups = {Add.class, Update.class}, max = 64)
    @TableField("right_unit")
    private String rightUnit;

    //产权负责人
    @Length(message = "产权负责人字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("right_charge")
    private String rightCharge;

    //产权负责人电话
    @Length(message = "产权负责人电话字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("right_phone")
    private String rightPhone;

    //运营负责人
    @Length(message = "运营负责人字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("business_charge")
    private String businessCharge;

    //运营负责人电话
    @Length(message = "运营负责人电话字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("business_phone")
    private String businessPhone;

    //描述
    @Length(message = "描述字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("description")
    private String description;

    //停车场网络状态，1正常 ，0 断网
    @Length(message = "停车场网络状态，1正常 ，0 断网字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("network_state")
    private String networkState;

    //停车场网络地址
    @Length(message = "停车场网络地址字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("url")
    private String url;

    //停车场图片
    @Length(message = "停车场图片字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("img")
    private String img;

    //预留预约车位数
    @Length(message = "预留预约车位数字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("reserved_lot_num")
    private String reservedLotNum;

    //是否仅月租 0：否 1：是
    @Length(message = "是否仅月租 0：否 1：是字段的长度最大为1", groups = {Add.class, Update.class}, max = 1)
    @TableField("only_vip")
    private String onlyVip;

    //所属停车场
    @Length(message = "所属停车场字段的长度最大为11", groups = {Add.class, Update.class}, max = 11)
    @TableField("dept_id")
    private String deptId;

    //父停车场编号
    @Length(message = "父停车场编号字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("parent_park_id")
    private String parentParkId;

    //省份
    @Length(message = "省份字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("provinceid")
    private String provinceid;

    //市
    @Length(message = "市字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("cityid")
    private String cityid;

    //区县
    @Length(message = "区县字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("areaid")
    private String areaid;

    //密钥
    @Length(message = "密钥字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("secret")
    private String secret;

    //黑名单提示
    @Length(message = "黑名单提示字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("black_list_remark")
    private String blackListRemark;

    //免费时长:分钟
    @TableField("free_time")
    private Integer freeTime;

    //提前付费后出场限制时间
    @TableField("exit_time")
    private Integer exitTime;

    //是否下发 0 未下发 1已下发
    @TableField("is_sync")
    private Integer isSync;

    //一天最多收费多少钱
    @TableField("one_day_max_fee")
    private Object oneDayMaxFee;


    public ParkParking() {
    }

    public ParkParking(
            String id,
            String parkName,
            String areaCount,
            String spaceCount,
            String zoomRate,
            String longitude,
            String latitude,
            String address,
            String isDisable,
            String operationWay,
            String businessUnit,
            String parkType,
            String serviceLife,
            String rightUnit,
            String rightCharge,
            String rightPhone,
            String businessCharge,
            String businessPhone,
            String description,
            String networkState,
            String url,
            String img,
            String reservedLotNum,
            String onlyVip,
            String deptId,
            String parentParkId,
            String provinceid,
            String cityid,
            String areaid,
            String secret,
            String blackListRemark,
            Integer freeTime,
            Integer exitTime,
            Integer isSync,
            Object oneDayMaxFee) {
        this.id = id;
        this.parkName = parkName;
        this.areaCount = areaCount;
        this.spaceCount = spaceCount;
        this.zoomRate = zoomRate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.isDisable = isDisable;
        this.operationWay = operationWay;
        this.businessUnit = businessUnit;
        this.parkType = parkType;
        this.serviceLife = serviceLife;
        this.rightUnit = rightUnit;
        this.rightCharge = rightCharge;
        this.rightPhone = rightPhone;
        this.businessCharge = businessCharge;
        this.businessPhone = businessPhone;
        this.description = description;
        this.networkState = networkState;
        this.url = url;
        this.img = img;
        this.reservedLotNum = reservedLotNum;
        this.onlyVip = onlyVip;
        this.deptId = deptId;
        this.parentParkId = parentParkId;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.areaid = areaid;
        this.secret = secret;
        this.blackListRemark = blackListRemark;
        this.freeTime = freeTime;
        this.exitTime = exitTime;
        this.isSync = isSync;
        this.oneDayMaxFee = oneDayMaxFee;
    }


}