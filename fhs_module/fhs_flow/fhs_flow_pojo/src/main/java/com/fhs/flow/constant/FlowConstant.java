package com.fhs.flow.constant;

/**
 * 工作流常量
 */
public interface FlowConstant {

    /**
     * 草稿
     */
    int XML_STATUS_DRAFT = 0;

    /**
     * 已发布
     */
    int XML_STATUS_RELEASE =1;

    /**
     * 已禁用
     */
    int XML_STATUS_DISABLE = 2;

    /**
     * 当工作流实例有了变动之后触发
     */
    String LISTENER_INSTANCE = "listener_instance";

    /**
     * 当有了新的变动之后,比如审批完成,撤销申请,拒绝 等等
     */
    String ENVENT_NEWS = "news";

    /**
     * 完成任务
     */
    int INSTANCE_NEWS_TYPE_COMPLATETASK = 1;

    /**
     * 完成整个流程审批
     */
    int INSTANCE_NEWS_TYPE_COMPLATE = 2;

    /**
     * 撤销申请
     */
    int INSTANCE_NEWS_TYPE_REVOKE = 3;


    /**
     *  驳回
     */
    int INSTANCE_NEWS_TYPE_BACK = 4;


    /**
     * 业务状态-审批中
     */
    int BUSINESS_INSTANCE_STATUS_APPROVAL = 0;

    /**
     * 业务状态-驳回到申请
     */
    int BUSINESS_INSTANCE_STATUS_RESUBMIT = 3;

    /**
     * 业务状态-撤销
     */
    int BUSINESS_INSTANCE_STATUS_REVOKE = 2;


    /**
     * 业务状态--结束
     */
    int BUSINESS_INSTANCE_STATUS_END = 1;

    /**
     * 提交申请
     */
    int RESULT_SUBMIT = 6;

    /**
     * 同意
     */
    int RESULT_PASS = 0;

    /**
     * 驳回
     */
    int RESULT_BACK = 1;

    /**
     * 人工终止
     */
    int RESULT_END = 2;

    /**
     * 撤销申请
     */
    int RESULT_REVOKE = 3;

    /**
     * 撤回审批
     */
    int RESULT_WITHDRAW = 4;

}
