package com.sz.common.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.json.LoginSessionObject;

/**
 * User： xin.fang
 * Date： 14-6-9 
 * Time： 下午4:32
 *
 */
public class SessionUtil {


    /**
     * 获取当前系统用户
     * @author vic
     * @return
     */
    public static LoginSessionObject getCurrentUser() {

        LoginSessionObject user = (LoginSessionObject) getSession().getAttribute(AppConstant.LOGIN_SESSION_NAME);
        Assert.notNull(user,"登陆用户数据为空！");
        return user;
    }

    /**
     * 获取当前系统用户名称
     * @author vic
     * @return
     */
    public static String getUserName() {
        LoginSessionObject user = getCurrentUser();
        return user.getUserName();
    }

    /**
     * 获取当前系统用户ID
     * @author vic
     * @return
     */
    public static int getUserId() {
        LoginSessionObject user = getCurrentUser();
        return user.getUserId();
    }

    /**
     * 获取用户当前的权限列表
     * @author vic
     * @return
     */
    public static List<Auth> getCurrentUserAuth(){
        List<Auth> authList = (List<Auth>) getSession().getAttribute(AppConstant.SESSION_AUTH_LIST);
        Assert.notNull(authList,"获取用户当前的权限列表为Null！");
        return authList;
    }

    public static HttpSession getSession(){
        HttpSession session = getRequest().getSession();
        Assert.notNull(session,"获取HttpServletRequest为Null！");
        return session;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try{
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch (Exception e) {
            return request;
        }
        return request;
    }
}
