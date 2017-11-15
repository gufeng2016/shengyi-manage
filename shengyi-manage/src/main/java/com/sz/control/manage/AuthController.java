package com.sz.control.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sz.common.listener.SystemConfigUtil;
import com.sz.common.util.AppConstant;
import com.sz.pojo.manage.auth.Auth;
import com.sz.pojo.manage.json.JsonBack;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.service.manage.AuthService;

/**
 * User: xin.fang
 * Date: 14-3-6
 * Time: 下午6:03
 * 权限管理的controller( RequestMethod.GET表示是跳转到表单页面， RequestMethod.POST执行表单提交)
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	private Logger logger = Logger.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;


	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.setViewName("auth.add");
		try {
			List<Auth> authList = authService.queryRootAndTopAuth();
			Auth auth = new Auth();
			view.addObject("authList", authList);
			view.addObject("auth", auth);
		} catch (Exception e) {
			logger.error("转入增加权限页面时，查询权限列表异常", e);
		}
		return view;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, @Valid @ModelAttribute("auth") Auth auth, BindingResult result,
					  RedirectAttributes redirectAttrs) {
		String forward = "auth.add";

		try {
			List<Auth> authList = authService.queryRootAndTopAuth();
			request.setAttribute("authList", authList);
			if (result.hasErrors()) {
				return forward;
			}

			boolean addResult = authService.addAuth(auth);
			if (addResult) {
				logger.info("增加权限成功");
				redirectAttrs.addAttribute("id", auth.getParentAuthId());
				forward = "redirect:query";
			} else {
				logger.error("增加权限失败");
			}
		} catch (Exception e) {
			logger.error("增加权限出现异常", e);
		}
		return forward;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(value = "id") Integer id) {
		String viewName = "auth.update";
		Auth auth = null;
		ModelAndView view = new ModelAndView();
		try {
			if (id == null || id.intValue() == 0) {
				viewName = "auth.update";
			} else {
				auth = authService.queryAuthById(id.intValue());
				List<Auth> authList = authService.queryRootAndTopAuth();
				view.addObject("authList", authList);
				if (null != auth && auth.getAuthId() != 0) {
					logger.info("根据权限ID查询权限成功");
					viewName = "auth.update";
				} else {
					logger.error("根据权限ID查询权限失败");
				}
			}
		} catch (Exception e) {
			logger.error("根据ID获取信息出现异常", e);
		}

		view.setViewName(viewName);
		view.addObject("auth", auth);
		return view;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("auth") Auth auth, BindingResult result, HttpServletRequest request,
						 RedirectAttributes redirectAttrs) {
		String forward = "auth.update";

		try {
			List<Auth> authList = authService.queryRootAndTopAuth();
			request.setAttribute("authList", authList);
			if (result.hasErrors()) {
				return forward;
			}
			boolean updateResult = authService.updateAuth(auth);
			if (updateResult) {
				logger.info("更新权限成功");
				redirectAttrs.addAttribute("id", auth.getParentAuthId());
				forward = "redirect:query";
			} else {
				logger.error("更新权限失败");
				//TODO 更新权限失败后的操作
			}
		} catch (Exception e) {
			logger.error("更新权限出现异常", e);
		}
		return forward;
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonBack delete(@RequestParam("id") String id) {
		JsonBack jsonBack = new JsonBack();
		boolean deleteResult = false;
		try {
			StringBuilder successBuilder = new StringBuilder();
			StringBuilder failureBuilder = new StringBuilder();

			String[] ids = id.split(",");
			for (String str : ids) {
				if (StringUtils.isNotBlank(str) && StringUtils.isNumeric(str)) {
					int authId = Integer.parseInt(str);
					deleteResult = authService.delAuth(authId);
					if (deleteResult) {
						logger.info("删除权限id为:" + authId + "成功");
						successBuilder.append(authId).append(",");
					} else {
						logger.error("删除权限id为:" + authId + "失败");
						failureBuilder.append(authId).append(",");
					}
				}
			}

			String failureBack = failureBuilder.toString();
			if (deleteResult && StringUtils.isBlank(failureBack)) {
				jsonBack.setCode(100);
				jsonBack.setMessage(SystemConfigUtil.getProperty("100"));
				jsonBack.setMessage(successBuilder.toString());
				logger.info("删除权限信息成功");
			} else {
				jsonBack.setCode(108);
				jsonBack.setMessage(SystemConfigUtil.getProperty("108"));
				jsonBack.setObj(failureBack);
				logger.error("删除权限信息失败");
			}
		} catch (Exception e) {
			logger.error("删除权限信息出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@RequestMapping(value = "/query")
	public ModelAndView query(@RequestParam(value = "id", required = false) String id, HttpServletRequest request,
								  String authName, String authId, String url) {
		ModelAndView view = new ModelAndView("auth.list");
		try {
			int authIdInt = 0;
			if (StringUtils.isNotBlank(authId) && StringUtils.isNumeric(authId)) {
				authIdInt = Integer.parseInt(authId);
			}
			PageInfo info = PageInfo.getPageInfo(request);

			//如果不是指定查询方式查询权限
			if (StringUtils.isBlank(authName) && StringUtils.isBlank(url) && authIdInt == 0) {
				int idInt = 0;
				if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
					idInt = AppConstant.TOP_AUTH_PARENT_ID;
				} else {
					idInt = Integer.parseInt(id);
				}
				PaginationResult<Auth> paginationResult = authService.queryAuthList(idInt, info);
				view.addObject("paginationResult", paginationResult);
				view.addObject("id", id);
			} else {
				authName = new String(authName.getBytes("ISO-8859-1"), "UTF-8");
				PaginationResult<Auth> paginationResult = authService.queryAuthList(authIdInt, info, authName, url);
				view.addObject("paginationResult", paginationResult);
			}
			view.addObject("authName", authName);
			view.addObject("authId", authId);
			view.addObject("url", url);

			logger.info("分页查询权限列表成功");
		} catch (Exception e) {
			logger.error("分页查询权限列表出现异常", e);
		}

		return view;
	}

}
