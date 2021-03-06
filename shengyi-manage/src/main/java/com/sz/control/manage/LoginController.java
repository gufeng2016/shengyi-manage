package com.sz.control.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sz.common.util.AppConstant;
import com.sz.common.util.IpAddressUtil;
import com.sz.common.util.MD5Util;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.json.LoginSessionObject;
import com.sz.pojo.manage.log.LoginLog;
import com.sz.pojo.manage.user.User;
import com.sz.service.manage.AuthService;
import com.sz.service.manage.ConfigService;
import com.sz.service.manage.OperateLogService;
import com.sz.service.manage.RoleService;
import com.sz.service.manage.UserService;

/**
 * User: xin.fang
 * Date: 14-2-25
 * Time: 下午3:41
 * 用户登录的controller
 */
@Controller
public class LoginController{

	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService loginService;

	@Autowired
	private OperateLogService ipService;

	@Autowired
	private AuthService authService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ConfigService configService;


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(String name, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login/login");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String name, String password, String captcha) {
		String loginPage = "login/login";
		String result = loginPage;
		try {
			//用户登录
			result = this.userLogin(request, response, name, password, captcha);
			//TODO 插入登录日志
			String ip = IpAddressUtil.getIpAddr(request);
			LoginLog log = new LoginLog();
			log.setIp(ip);
			if (result.equals("web.welcome")) {
				log.setMessage("登录成功");
				log.setState(AppConstant.IP_LONIN_SUCCESS_STATE);
			}else {
				log.setMessage("登录失败");
				log.setState(AppConstant.IP_LONIN_FAIL_STATE);
			}
			loginService.addLoginLog(log);
		} catch (Exception e) {
			logger.error("用户登录出现异常", e);
		}
		return result;
	}

	/**
	 * 用户登录
	 * @param request
	 * @param name
	 * @param password
	 * @param captcha
	 * @return
	 */
	private String userLogin(HttpServletRequest request, HttpServletResponse response, String name, String password, String captcha) {
		String loginPage = "login/login";
		System.out.println(name + ":" + password);
		try {
			String ip = IpAddressUtil.getIpAddr(request);
			//判断ip地址是否允许登录
			if (StringUtils.isBlank(ip) || !ipService.isAuthIp(ip)) {
				request.setAttribute("error", "该ip无权限登录");
				return loginPage;
			}

			//查询用户是否在该ip重复失败登录
			if (loginService.ipIsLock(ip)) {
				request.setAttribute("error", "该ip已被锁定，请联系管理员");
				return loginPage;
			}

			//验证用户名和密码参数是否符合
			if (StringUtils.isBlank(name)) {
				request.setAttribute("error", "请填写用户名");
				return loginPage;
			}
			if (StringUtils.isBlank(password)) {
				request.setAttribute("error", "请填写密码");
				return loginPage;
			}

			//判断验证码输入是否正确
			if (StringUtils.isBlank(captcha)) {
				request.setAttribute("error", "请输入验证码");
				return loginPage;
			} else {
				String code = (String) request.getSession().getAttribute(AppConstant.KAPTCHA_SESSION_KEY);
				if (!code.equalsIgnoreCase(captcha)) {
					request.setAttribute("error", "验证码错误");
					return loginPage;
				}
			}
			
			//查询系统用户
			User user = loginService.queryUserByName(name);
			String md5Password = MD5Util.getMD5String(password).toLowerCase();
			if (null != user && null != user.getPassword() && user.getPassword().equalsIgnoreCase(md5Password)) {
				LoginSessionObject object = new LoginSessionObject();
				object.setUserId(user.getUserId());
				object.setUserName(user.getUserName());
				object.setUserNameCn(user.getUserNameCn());
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(AppConstant.SESSION_MAXINTERVAL);
				session.setAttribute(AppConstant.LOGIN_SESSION_NAME, object);
				//查询用户的权限
				List<Auth> authList = authService.queryAuthByUser(user.getUserId());
				if(CollectionUtils.isEmpty(authList)){
					request.setAttribute("error", "该用户无登录权限");
					return loginPage;
				}
				session.setAttribute(AppConstant.SESSION_AUTH_LIST, authList);
				return  "web.welcome";
			}
		} catch (Exception e) {
			logger.error("用户登录出现异常", e);
		}
		request.setAttribute("error", "用户名或密码错误");
		return loginPage;
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/login");
		try {
			HttpSession session = request.getSession();
			//删除系统权限
			session.removeAttribute(AppConstant.SESSION_AUTH_LIST);

			//删除登录用户信息
			session.removeAttribute(AppConstant.LOGIN_SESSION_NAME);
		} catch (Exception e) {
			logger.error("游戏切换出现异常");
		}
		return view;
	}

}
