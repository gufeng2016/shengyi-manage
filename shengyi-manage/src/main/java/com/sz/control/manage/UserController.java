package com.sz.control.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.common.listener.SystemConfigUtil;
import com.sz.common.util.AppConstant;
import com.sz.common.util.MD5Util;
import com.sz.pojo.manage.json.JsonBack;
import com.sz.pojo.manage.json.LoginSessionObject;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.pojo.manage.role.Role;
import com.sz.pojo.manage.user.User;
import com.sz.service.manage.RoleService;
import com.sz.service.manage.UserService;

/**
 * User: xin.fang
 * Date: 14-3-10
 * Time: 下午2:28
 * 管理用户的controller(GET方法代表跳转到对应的页面，POST方法代表表单提交)[单独做ip权限验证，只允许公司的ip地址进行操作]
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("user.add");
			User user = new User();
			List<Role> roleList = roleService.queryTopRole();
			view.addObject("roleList", roleList);
			view.addObject("user", user);
			return view;
		} catch (Exception e) {
			logger.error("转向增加用户页面时出现异常", e);
		}
		return view;
	}

	@RequestMapping(value = "/queryUserByName")
	@ResponseBody
	public JsonBack queryUserByName(String name) {
		JsonBack jsonBack = new JsonBack();
		try {
			User user = userService.queryUserByName(name);
			if (user.getUserId() != 0) {
				jsonBack.setCode(120);
				jsonBack.setMessage(SystemConfigUtil.getProperty("120"));
				logger.error("用户已存在");
			} else {
				jsonBack.setCode(121);
				jsonBack.setMessage(SystemConfigUtil.getProperty("121"));
				logger.error("用户名可用");
			}
		} catch (Exception e) {
			logger.error("根据用户名查询用户出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		String forward = "user.add";
		try {
			List<Role> roleList = roleService.queryTopRole();
			request.setAttribute("roleList", roleList);
			if (result.hasErrors()) {
				logger.debug("增加用户表单数据有误");
				return forward;
			}

			User queryUser = userService.queryUserByName(user.getUserName());
			if (queryUser != null && queryUser.getUserId() != 0) {
				logger.error("用户已存在");
				//result.rejectValue("userName", "用户已存在");
				request.setAttribute(AppConstant.RESULT_ERROR, "用户已存在");
				return forward;
			}
			boolean addResult = userService.addUser(user);
			if (addResult) {
				forward = "redirect:query";
				logger.info("增加用户成功");
			} else {
				logger.error("增加用户失败");
				//result.rejectValue("result", "增加用户失败");
				request.setAttribute(AppConstant.RESULT_ERROR, AppConstant.RESULT_FAILURE);
			}
		} catch (Exception e) {
			logger.error("增加用户出现异常", e);
			//result.rejectValue("result", "增加用户出现异常");
			request.setAttribute(AppConstant.RESULT_ERROR, AppConstant.RESULT_ERROR);
		}
		return forward;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(Integer id) {
		User user = null;
		List<Role> roleList = null;
		ModelAndView view = new ModelAndView();
		try {
			user = userService.queryUserById(id);
			roleList = roleService.queryTopRole();
			if (!user.getUserId().equals("0") && StringUtils.isNotBlank(user.getUserName())) {
				logger.info("根据id查询用户信息成功");
				view.setViewName("user.update");
				view.addObject("roleList", roleList);
				view.addObject("user", user);
			} else {
				logger.error("根据id查询用户信息失败");
			}
		} catch (Exception e) {
			logger.error("根据用户Id查询用户信息出现异常", e);
			view.setViewName("500");
		}
		return view;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		String forward = "user.update";
		try {
			List<Role> roleList = roleService.queryTopRole();
			request.setAttribute("roleList", roleList);
			if (result.hasErrors()) {
				return forward;
			}
			//更新用户信息，不能更新用户密码
			boolean updateResult = userService.updateUser(user);
			if (updateResult) {
				forward = "redirect:query";
				logger.info("更新用户信息成功");
			} else {
				result.rejectValue("result", "更新用户失败");
				logger.error("更新用户信息失败");
			}
		} catch (Exception e) {
			logger.error("更新用户信息出现异常", e);
			result.rejectValue("result", "更新用户出现异常");
		}
		return forward;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonBack delete(String id) {
		JsonBack jsonBack = new JsonBack();
		boolean deleteResult = false;
		try {
			StringBuilder successBuilder = new StringBuilder();
			StringBuilder failureBuilder = new StringBuilder();

			String[] ids = id.split(",");
			for (String str : ids) {
				if (StringUtils.isNotBlank(str) && StringUtils.isNumeric(str)) {
					int userId = Integer.parseInt(str);
					deleteResult = userService.delUserById(userId);
					if (deleteResult) {
						logger.info("删除用户id为:" + userId + "成功");
						successBuilder.append(userId).append(",");
					} else {
						logger.error("删除用户id为:" + userId + "失败");
						failureBuilder.append(userId).append(",");
					}
				}
			}

			String failureBack = failureBuilder.toString();
			if (deleteResult && StringUtils.isBlank(failureBack)) {
				jsonBack.setCode(107);
				jsonBack.setMessage(SystemConfigUtil.getProperty("107"));
				jsonBack.setMessage(successBuilder.toString());
				logger.info("删除用户信息成功");
			} else {
				jsonBack.setCode(108);
				jsonBack.setMessage(SystemConfigUtil.getProperty("108"));
				jsonBack.setObj(failureBack);
				logger.error("删除用户信息失败");
			}
		} catch (Exception e) {
			logger.error("删除用户信息出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@RequestMapping(value = "/query")
	public ModelAndView query(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			PageInfo pageInfo = PageInfo.getPageInfo(request);
			PaginationResult<User> paginationResult = userService.queryUsers(pageInfo);
			view.setViewName("user.list");
			view.addObject("paginationResult", paginationResult);
			logger.info("查询用户信息成功");
		} catch (Exception e) {
			logger.error("查询用户信息出现异常", e);
		}
		return view;
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public ModelAndView updatePassword() {
		ModelAndView view = new ModelAndView("user.password");
		return view;
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseBody
	public JsonBack updatePassword(HttpSession session, String oldPassord, String newPassword, String newPasswordRep) {
		JsonBack jsonBack = new JsonBack();
		//验证参数
		if (StringUtils.isBlank(oldPassord)) {
			jsonBack.setCode(110);
			jsonBack.setMessage(SystemConfigUtil.getProperty("110"));
			return jsonBack;
		}
		if (StringUtils.isBlank(newPassword)) {
			jsonBack.setCode(111);
			jsonBack.setMessage(SystemConfigUtil.getProperty("111"));
			return jsonBack;
		}
		if (StringUtils.isBlank(newPasswordRep)) {
			jsonBack.setCode(112);
			jsonBack.setMessage(SystemConfigUtil.getProperty("112"));
			return jsonBack;
		}
		if (!newPassword.equals(newPasswordRep)) {
			jsonBack.setCode(113);
			jsonBack.setMessage(SystemConfigUtil.getProperty("113"));
			return jsonBack;
		}
		try {
			//获取存入session的登录信息，修改session中用户名的密码
			LoginSessionObject obj = (LoginSessionObject) session.getAttribute(AppConstant.LOGIN_SESSION_NAME);
			String password = null;
			User user = userService.queryUserById(obj.getUserId());
			if(user != null){
				password = user.getPassword();
			}
			if(password == null){
				jsonBack.setCode(123);
				jsonBack.setMessage(SystemConfigUtil.getProperty("123"));
				return jsonBack;
			}
			String md5Password = MD5Util.getMD5String(oldPassord).toLowerCase();
			//验证原始秘密输入是否正确
			if (!password.equalsIgnoreCase(md5Password)) {
				jsonBack.setCode(114);
				jsonBack.setMessage(SystemConfigUtil.getProperty("114"));
				return jsonBack;
			}

			//验证输入的密码是否和前次一样
			String md5NewPassword = MD5Util.getMD5String(newPassword).toLowerCase();
			if (md5NewPassword.equalsIgnoreCase(password)) {
				jsonBack.setCode(117);
				jsonBack.setMessage(SystemConfigUtil.getProperty("117"));
				return jsonBack;
			}
			//修改用户密码
			boolean updateResult = userService.updateUserPassword(md5NewPassword, password, obj.getUserId());
			if (updateResult) {
				jsonBack.setCode(115);
				jsonBack.setMessage(SystemConfigUtil.getProperty("100"));
				logger.info("用户修改密码成功");
			} else {
				jsonBack.setCode(116);
				jsonBack.setMessage(SystemConfigUtil.getProperty("116"));
				logger.error("用户修改密码失败");
			}
		} catch (Exception e) {
			logger.error("修改密码出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@ResponseBody
	@RequestMapping(value = "/reset")
	public JsonBack resetPassword(Integer userId) {
		JsonBack jsonBack = new JsonBack();
		try {
		   /* if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
                jsonBack.setCode(118);
                jsonBack.setMessage(SystemConfig.getProperty("118"));
                return jsonBack;
            }
            int userId = Integer.parseInt(id);*/
			boolean result = userService.resetPassword(userId);
			if (result) {
				jsonBack.setCode(100);
				jsonBack.setMessage(SystemConfigUtil.getProperty("100"));
				logger.info("重置密码成功");
			} else {
				jsonBack.setCode(119);
				jsonBack.setMessage(SystemConfigUtil.getProperty("119"));
				logger.error("重置密码失败");
			}
		} catch (Exception e) {
			logger.error("重置用户密码出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@RequestMapping(value = "/query/role")
	public ModelAndView queryUserRole(Integer userId) {

		return null;
	}

}
