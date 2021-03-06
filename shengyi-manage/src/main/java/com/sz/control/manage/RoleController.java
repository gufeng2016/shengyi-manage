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
import com.sz.pojo.manage.role.Role;
import com.sz.service.manage.AuthService;
import com.sz.service.manage.RoleService;
import com.sz.service.manage.UserRoleAuthSiteService;

/**
 * User: xin.fang
 * Date: 14-3-12
 * Time: 上午10:48
 * 角色管理的控制器
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	private Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleAuthSiteService service;

	@Autowired
	private AuthService authService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		Role role = null;
		try {
			List<Role> roleList = roleService.queryRootAndTopRole();
			role = new Role();
			view.addObject("roleList", roleList);
			view.addObject("role", role);
		} catch (Exception e) {
			logger.error("跳转增加角色页面出现异常", e);
		}

		view.setViewName("role.add");
		view.addObject("role", role);
		return view;
	}

    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("role") Role role, BindingResult result, HttpServletRequest request,
					  RedirectAttributes redirectAttributes) {
		String forward = "role.add";
		try {
			List<Role> roleList = roleService.queryRootAndTopRole();
			request.setAttribute("roleList", roleList);
			if (result.hasErrors()) {
				return forward;
			}
			boolean addResult = roleService.addRole(role);
			if (addResult) {
				logger.info("增加角色成功");
				request.setAttribute("result", AppConstant.RESULT_SUCCESS);
				redirectAttributes.addAttribute("id", role.getParentId());
				forward = "redirect:query";
			} else {
				logger.error("增加角色失败");
				request.setAttribute("result", AppConstant.RESULT_FAILURE);
			}
		} catch (Exception e) {
			logger.error("增加角色出现异常", e);
			request.setAttribute("result", AppConstant.RESULT_ERROR);
		}
		return forward;
	}

	
    @RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(String id, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			List<Role> roleList = roleService.queryRootAndTopRole();
			view.addObject("roleList", roleList);

			Role role = roleService.queryRoleById(Integer.parseInt(id));

			if (!(role.getRoleId().equals("0") && StringUtils.isNotBlank(role.getRoleName()))) {
				logger.info("根据ID查询角色信息成功");
				view.setViewName("role.update");
				view.addObject("role", role);
			} else {
				logger.error("根据ID查询角色信息失败");
				view.setViewName("role.list");
				view.addObject("result", AppConstant.RESULT_FAILURE);
			}
		} catch (Exception e) {
			logger.error("根据ID查询角色信息出现异常", e);
			request.setAttribute("result", AppConstant.RESULT_ERROR);
		}
		return view;
	}

    @RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") Role role, BindingResult result, HttpServletRequest request,
						 RedirectAttributes redirectAttributes) {
		String forward = "role.update";
		try {
			//设置list
			List<Role> roleList = roleService.queryRootAndTopRole();
			request.setAttribute("roleList", roleList);

			if (result.hasErrors()) {
				return forward;
			}
			boolean updateResult = roleService.updateRole(role);
			if (updateResult) {
				logger.info("修改角色成功");
				redirectAttributes.addAttribute("id", role.getParentId());
				forward = "redirect:query";
			} else {
				logger.error("修改角色失败");
			}
		} catch (Exception e) {
			logger.error("修改角色出现异常", e);
			request.setAttribute("result", AppConstant.RESULT_ERROR);
		}
		return forward;
	}

    @ResponseBody
	@RequestMapping(value = "/delete")
	public JsonBack delete(@RequestParam("ids") String ids) {
		JsonBack back = new JsonBack();
		try {
			boolean result = false;
			StringBuilder failureResult = new StringBuilder();

			if (StringUtils.isNotBlank(ids)) {
				String[] roleIds = ids.split(",");
				for (String str : roleIds) {
					if (StringUtils.isNotBlank(str) && StringUtils.isNumeric(str)) {
						int roleId = Integer.parseInt(str);
						result = roleService.delRole(roleId);
						if (result) {
							logger.info("删除角色id为" + roleId + "的角色成功");
						} else {
							logger.error("删除角色id为" + roleId + "的角色失败");
							failureResult.append(roleId).append(",");
						}
					}
				}
			}
			String resultIds = failureResult.toString();
			if (StringUtils.isBlank(resultIds)) {
				back.setCode(100);
				back.setMessage(SystemConfigUtil.getProperty("100"));
			} else {
				logger.error("删除角色失败");
				back.setCode(121);
				back.setMessage(SystemConfigUtil.getProperty("121"));
				back.setObj(resultIds);
			}
		} catch (Exception e) {
			logger.error("删除角色出现异常", e);
			back.exceptionJsonBack();
		}
		return back;
	}

    @RequestMapping(value = "/query")
	public ModelAndView query(Integer id, HttpServletRequest request) {
		PageInfo page = PageInfo.getPageInfo(request);
		PaginationResult<Role> paginationResult = null;
		try {
			if (null == id || id.intValue() == 0) {
				id = AppConstant.TOP_ROLE_PARENT_ID;
			}
			paginationResult = roleService.queryChildRole(page, id);
			if (null == paginationResult) {
				request.setAttribute(AppConstant.REQUEST_RESULT_ATTRIBUTE_NAME, AppConstant.RESULT_ERROR);
				logger.error("获取角色列表失败");
			} else {
				logger.info("获取角色列表成功");
			}
		} catch (Exception e) {
			logger.error("获取角色列表出现异常", e);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("role.list");
		view.addObject("paginationResult", paginationResult);
		return view;
	}

	/**
	 * 查找角色的权限信息
	 * @return
	 */
	@RequestMapping(value = "/auth/allocation", method = RequestMethod.GET)
	public ModelAndView roleAllocationAuth(@RequestParam("id") Integer id) {
		ModelAndView view = new ModelAndView("web.main");
		try {
			//TODO 查询id是否有权限对应
			//查询角色已分配的权限
			Role role = roleService.queryRoleAllInfoByRoleId(id);
			//查询父角色具有的权限
			List<Auth> authList = service.queryParentRoleAuthAndChildAuth(id);
			System.out.println(authList.size());
			view.setViewName("role.auth.allocation");
			view.addObject("role", role);
			view.addObject("authList", authList);
		} catch (Exception e) {
			logger.error("查询角色权限信息出现异常", e);
		}
		return view;
	}

	/**
	 * 分配角色权限
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/auth/allocation", method = RequestMethod.POST)
	public String roleAllocationAuth(Role role, HttpServletRequest request) {
		String forward = "role.auth.allocation";
		try {

			int roleId = role.getRoleId();
			//查询父角色具有的权限
			Role queryRole = roleService.queryRoleAllInfoByRoleId(roleId);
			//查询父角色具有的权限
			List<Auth> authList = service.queryParentRoleAuthAndChildAuth(roleId);
			request.setAttribute("role", role);
			request.setAttribute("authList", authList);

			//分配权限
			boolean allocationResult = service.allocationAuth(role);
			if (allocationResult) {
				logger.info("为角色分配权限成功");
				forward = "redirect:/role/query";
			} else {
				logger.error("为角色分配权限失败");
			}
		} catch (Exception e) {
			logger.error("分配角色权限信息出现异常", e);
		}
		return forward;
	}

}
