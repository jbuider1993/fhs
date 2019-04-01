package com.fhs.constant;

/**
 * 公共常量
 *
 * @Filename: BusConstant.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *               陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public interface BusConstant
{
    /**
     * 状态0
     */
    String ZERO = "0";

    /**
     * 成功
     */
    String LINK_SUCCESS = "0";

    /**
     * 用户在Link存在
     */
    String USER_EXIST = "1";

    /**
     * 用户在Link不存在
     */
    String USER_NOT_EXIST = "0";

    /**
     * 注册验证码
     */
    String VCODE_REGISTER = "1";

    /**
     * 登录验证码
     */
    String VCODE_LOGIN = "2";

    /**
     * LinkShareVip
     */
    String IS_LINK_VIP = "0";

    /**
     * 非LinkShareVip
     */
    String IS_NOT_LINK_VIP = "1";

    /**
     * 接入token
     */
    String ACCESS_TOKEN = "accessToken";

    /**
     * 是否是vip
     */
    String IS_VIP = "isVip";

    /**
     * 服务信息
     */
    String CSERVICE_INFO = "cservice";

    /**
     * 服务翻译服务的key
     */
    String CSERVICE_NAME = "cservice_name";

    /**
     * 用户事件类型
     */
    interface UserEventType
    {
        /**
         * 打电话
         */
        String PHONE = "1";

        /**
         * 导航
         */
        String NAVI = "2";

        /**
         * 收藏
         */
        String FAVORITE = "3";

        /**
         * 查看
         */
        String CHECK = "4";
    }

    /**
     * 用户事件模块类型
     */
    interface UserEventModelType
    {
        /**
         * 分类服务模块
         */
        String CSERVICE_ENTERPRISE = "1";

        /**
         * 商铺模块
         */
        String MERCHANT = "2";

        String MERCHANTNAME = "merchant";
    }

    interface TemplateMsgType{
        String ACTIVITY = "1";
    }

    interface SignType{
        //未签到
        Integer NO = 0;
        //已签到
        Integer YES = 1;
    }

}
