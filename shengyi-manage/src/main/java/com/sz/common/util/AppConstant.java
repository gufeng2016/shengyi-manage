package com.sz.common.util;

import com.sz.common.listener.SystemConfigUtil;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午3:02
 * 程序应用中的常量
 */
public class AppConstant {

    /** 存于request中的结果属性 */
    public static final String REQUEST_RESULT_ATTRIBUTE_NAME = "result";

    /** 代表从数据库中取出的tinyint为1的数据 */
    public static final int INTEGER_IS_TRUE = 1;

    /** 代表从数据库中取出的tinyint为0的数据 */
    public static final int INTEGER_IS_FALSE = 0;

    /** 存入session 中的验证码的名称 */
    public static final String KAPTCHA_SESSION_KEY = "kapthca";

    /** 存入SESSION的权限list名称 */
    public static final String SESSION_AUTH_LIST = "authList";

    /** 管理用户登录信息存入session的属性名称 */
    public static final String LOGIN_SESSION_NAME = "user";
    
    /** 代理商以及其所有子代理存入session的属性名称*/
    public static final String AGENT_AND_SUBS_LISTS = "agentList";

    /** 返回时失败的状态值 */
    public static final String RESULT_FAILURE = "failure";

    /** 返回时成功的状态值 */
    public static final String RESULT_SUCCESS = "success";

    /** 返回时异常的状态值 */
    public static final String RESULT_ERROR = "error";

    /** 列表页显示的条数 */
    public static final Integer SHOW_PAGE_LIST_SIZE = 20;

    /** json信息返回的配置文件地址 */
    public static final String JSON_BACK_MESSAGE_FILE_PATH = "WEB-INF/classes/messages.properties";

    /** 程序的配置文件地址 */
    public static final String APP_CONFIG_FILE_PATH = "WEB-INF/classes/AppConfig.properties";

    /** 用户的初始密码 */
    public static final String INIT_USER_PASSWORD = MD5Util.getMD5String(SystemConfigUtil.getProperty("user.default.password"));

    /** 开放ip权限 */
    public static final String ALLOW_ALL_IP = "*";

    /** 顶级角色的父角色ID */
    public static final int TOP_ROLE_PARENT_ID = 1;

    /** 根角色的父角色ID */
    public static final int ROOT_ROLE_PARENT_ID = -1;

    /** 顶级权限的父权限ID */
    public static final int TOP_AUTH_PARENT_ID = 1;

    /** 根权限的父权限ID */
    public static final int ROOT_AUTH_PARENT_ID = -1;

    /** 用户密码的有效期为3个月 */
    public static final int PASSWORD_EFFECTIVE_TIME = 3;

    /** 用户初始密码的有效期为7天 */
    public static final int INIT_PASSWORD_EFFECTIVE_TIME = 7;

    /** 图片服务器域名 */
    public static final String IMAGE_HOST = SystemConfigUtil.getProperty("image.host");

    /** 一天对应的毫秒数 */
    public static final int DAY_TRANSFORM_MILLISECOND = 24 * 60 * 60 * 1000;

    /** 用户在半小时之内登录失败的最大次数 */
    public static final int USER_LOGIN_FAIL_COUNT = 5;

    /** 用户被锁定 */
    public static final int IP_LONIN_LOCK = 1;

    /** 用户未被锁定 */
    public static final int IP_LONIN_UNLOCK = 0;

    /** ip登录失败的状态 */
    public static final int IP_LONIN_FAIL_STATE = 0;

    /** ip登录成功的状态 */
    public static final int IP_LONIN_SUCCESS_STATE = 1;

    /** IP锁定时间 */
    public static final int IP_LONIN_LOCK_TIME = 30;
	/** SESSION超时时间 */
	public static final int SESSION_MAXINTERVAL = 1800;

	/** 本地测试的标识 */
	public static final String LOCAL_TEST = SystemConfigUtil.getProperty("test.local.sign");

	/**  数据字典中imageHost的类型 */
	public static final String IMAGE_HOST_DICTIONARY = "imageHost";
	
	/**手机端标识*/
	public static final int MOBILEGAMES = 1;
	
	/**web端标识*/
	public static final int WEBGAMES = 0;
	
	/**顶级代理的父代理id*/
	public static final int ROOT_PARENT_AGENT_ID = 0;
	
	/**代理商默认角色id key*/
	public static final String AGENT_DEFAULT_ROLE_ID = "agent.default.role.id";
	
	/**玩家默认角色id key*/
	public static final String PLAYER_DEFAULT_ROLE_ID = "player.default.role.id";
	
}
