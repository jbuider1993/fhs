package com.fhs.ucenter.api.vo;

import com.fhs.core.base.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * @Description:前端用户信息vo
 * @author  zhangqiang
 * @version  [版本号, 2018/08/14 11:31:45]
 * @versio  1.0
 * Copyright (c) 2017 All Rights Reserved.
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FrontUserVo extends BaseVo {
	/**
	 * 用户Id
	 */
	private String userId;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 昵称
	 */
	private String realName;

	/**
	 *
	 */
	private String userName;

	/**
	 * 生日
	 */
	private String birthday;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 用户分组
	 */
	private Integer userGroupId;

	/**
	 * 身份证号
	 */
	private String userCard;

	/**
	 * 用户来源  0 PC 1微信
	 */
	private Integer userResource;

	/**
	 * 语种
	 */
	private String language;

	/**
	 * 省
	 */
	private String provinceName;

	/**
	 * 市
	 */
	private String cityName;

	/**
	 * 邮件
	 */
	private String email;

	/**
	 * 区
	 */
	private String areaName;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 等级
	 */
	private Integer grade;

	/**
	 * 积分
	 */
	private Integer point;

	/**
	 * 头像
	 */
	private String imagePath;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改时间
	 */
	private String updateTime;

	/**
	 * 启用标识 0-启用 1-禁用
	 */
	private Integer isDisable;

	/**
	 * openidmap
	 * key0 微信openid 1 支付宝openid
	 */
	private Map<Integer,String> openIdMap;
}
