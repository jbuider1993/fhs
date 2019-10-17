package com.fhs.common.constant;

/**
 * <公共常量接口>
 *
 * @author wanglei
 * @version [版本号, 2013年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface Constant
{
    /**
     * 禁用
     */
    Integer DISABLE = 1;
    /**
     * 启用
     */
    Integer ENABLED = 0;
    /**
     * 验证码生成几位参数
     */
    int VERIFYING_CODE_NUM =5;


    /**
     * 0
     */
    int ZREO = 0;

    /**
     * 是
     */
    int INT_TRUE = 1;

    /**
     *  否
     */
    int  INT_FALSE = 0;
    /**
     * 是
     */
    String STR_YES = "1";
    /**
     *  否
     */
    String STR_NO = "0";

    /**
     * 默认session类型
     */
    int DEFAULT_SESSION_TYPE = -1;

    /**
     * HTTP请求调用失败
     */
    int HTTP_ERROR = 300;

    /**
     * session中的用户
     */
    String SESSION_USER = "sessionUser";

    /**
     * 用户数据权限
     */
    String SESSION_USER_DATA_PERMISSION = "sessionUserDataPermission";

    /**
     * page 第几页
     */
    String PAGE = "page";

    /**
     * 每页多少条，主要给我的物业手机app用
     */
    String PAGE_SIZE = "pageSize";

    /**
     * page 每页多少条数据或者数据
     */
    String ROWS = "rows";

    /**
     * 总数
     */
    String TOTAL = "total";

    /**
     * datagrid底部
     */
    String FOOTER = "footer";

    /**
     * 大
     */
    int BIGGER = 1;

    /**
     * 小
     */
    int SMALL =-1;

    /**
     * 等于
     */
    int EQUAL = 0;

    /**
     * 已经失效/过期的界面
     */
    String OLD = "old";

    /**
     * 文件ids
     */
    String FILE_IDS = "fileIds";

    /**
     * 比例
     */
    String RATIO = "ratio";


    /**
     * start
     */
    String START = "start";

    /**
     * end
     */
    String END = "end";



    /**
     * 接口调用成功代码
     */
    int HPROSE_SUCCESS_CODE = 200;

    /**
     *接口调用失败代码
     */
    int HPROSE_DEFEAT_CODE = 300;

    /**
     * 302
     */
    int HPROSE_302 = 302;

    /**
     * sessionId/ssoid过期
     */
    int SESSION_ID_TIMEOUT = 403;

    /**
     * 服务器错误
     */
    int SERVER_EXCEPTION = 500;

    /**
     * 检查中
     */
    String CHECK_ING = "2";

    /**
     * 检查验证成功
     */
    String CHECK_SUCCESS = "3";

    /**
     * true
     */
    Boolean BTRUE = true;

    /**
     * 重新加载
     */
    String RELOAD = "reload";

    /**
     * false
     */
    Boolean BFALSE = false;

    /**
     *是
     */
    String STR_TRUE = "true";


    /**
     * 文件名字
     */
    String FILENAME = "fileName";

    /**
     * 状态
     */
    String STATUS = "status";


    /**
     * 编辑
     */
    String EDIT = "edit";

    /**
     * 更新
     */
    String UPDATE = "update";

    /**
     * 最大的int值
     */
    int MAX_INT = 2147483647;

    /**
     * 查看 预览
     */
    String VIEW = "view";



    /**
     * 斜杠
     */
    String SLASH = "/";

    /**
     * EMPTY 空字符串
     */
    String EMPTY = "";

    /**
     * 中划线
     */
    String CENTER_LINE = "-";



    /**
     * 1
     */
    int ONE = 1;

    /**
     * 0
     */
    int ZERO = 0;

    /**
     * 5
     */
    int FIVE = 5;

    /**
     * ID
     */
    String ID = "id";

    /**
     * 创建日期
     */
    String CREATE_DATE = "create_date";

    /**
     * excel后缀
     */
    String XLSX = ".xlsx";

    /**
     * 是
     */
    int I_YES = 1;

    void test();


    /**
     * 分号
     */
    String SEMICOLON = ";";

    /**
     * 冒号
     */
    String COLON = ":";

    /**
     * list
     */
    String LIST = "list";


    /**
     * 验证码过期
     */
    int CODE_OVERDUE = 201;



    /**
     * 年
     */
    String YEAR = "year";

    /**
     * 月
     */
    String MONTH = "month";

    /**
     * 日
     */
    String DAY = "day";

    /**
     * 时
     */
    String HOURS = "hours";

    /**
     * 分
     */
    String MINUTES = "minutes";

    /**
     * 秒
     */
    String SECONDS = "seconds";

    /**
     * 毫秒
     */
    String MILLISECOND = "millisecond";


    /**
     * 日期格式化字符串年月日格式
     */
    String DATE_FORMAT_Y_M_D = "yyyy-MM-dd";

    /**
     * 日期格式化字符串时分格式
     */
    String DATE_FORMAT_H_M = "HH:mm";

    /**
     * 日期格式化字符串年月日时分格式
     */
    String DATE_FORMAT_Y_M_D_H_M = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式化字符串年月日时分秒格式
     */
    String DATE_FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化字符串时分秒格式
     */
    String DATE_FORMAT_H_M_S = "HH:mm:ss";

    /**
     * 等待执行
     */
    int TO_BE_RUN = 0;

    /**
     * 获取数据成功
     */
    int GET_DATA_SUCCESS = 1;

    /**
     * 获取数据成功
     */
    int GET_DATA_FAIL = 2;

    /**
     *用于查询所有数据
     */
    int PAGE_ALL = -1;

    /**
     *用户 名称 redis key
     */
    String USER_NAME_REDIS_KEY = "sysUser:user_username:";

    /**
     *用户 部门名称 redis key
     */
    String USER_ORG_NAME_REDIS_KEY = "ucenter:user_orgname:";

    /**
     *字典
     */
    String WORD_BOOK  = "wordbook";

    /**
     *用户信息
     */
    String USER_INFO = "sysUser";

    /**
     *用户名
     */
    String USER_NAME = "sysUser:userName";

    /**
     *前台用户名
     */
    String FRONT_USER_INFO = "ucenter:frontUser";

    /**
     *前台用户昵称
     */
    String FRONT_USER_NICK_NAME = "ucenter:frontUser:nickName";

    /**
     *前台用户名
     */
    String FRONT_USER_NAME = "ucenter:frontUser:userName";

    /**
     * 前台用户头像
     */
    String FRONT_USER_IMAGE = "ucenter:frontUser:imagePath";

    /**
     *部门
     */
    String USER_ORG_NAME = "orgName";

    /**
     *部门信息
     */
    String ORG_INFO = "orgInfo";

    /**
     *应用信息
     */
    String APP_INFO = "appInfo";

    /**
     * 分类信息
     */
    String CLASSIFY_INFO = "classifyInfo";

    /**
     * 分类名称
     */
    String CLASSIFY_NAME = "classify:classifyName";

    /**
     * 区域信息
     */
    String AREA_INFO = "areaInfo";

    /**
     * 区域字典
     */
    String AREA_NAME = "area:areaName";

    /**
     * 前端用户认证类型 0-企业管理员
     */
    String FRONT_USER_AUTHEN_TYPE_ENTER_ADMIN = "0";

    /**
     * 前端用户认证类型 1-商户
     */
    String FRONT_USER_AUTHEN_TYPE_MERCHANT = "1";

    /**
     * 前端用户认证类型 2-业主/租户
     */
    String FRONT_USER_AUTHEN_TYPE_OWER = "2";

    /**
     * 前端用户认证类型 3-职工
     */
    String FRONT_USER_AUTHEN_TYPE_STAFF = "3";

    /**
     * 前端用户审核状态 0-待审核
     */
    Integer FRONT_USER_AUTHEN_STATUS_WAIT = 0;

    /**
     * 前端用户审核状态 1-已通过
     */
    Integer FRONT_USER_AUTHEN_STATUS_PASS = 1;

    /**
     * 前端用户审核状态 2-未通过
     */
    Integer FRONT_USER_AUTHEN_STATUS_NO_PASS = 2;

    /**
     * 机构信息
     */
    String SYS_ORGANIZATION_INFO = "sysOrganizationInfo";

    /**
     * 机构名称
     */
    String SYS_ORGANIZATION_NAME = "sysOrganizationInfo:organizationName";

    /**
     *
     */
    Integer SCHEDULE_THREAD_NUM = 10;

    //前端用户与openid
    String FRONT_USER_OPENID = "ucenter:frontUser:openId";
}
