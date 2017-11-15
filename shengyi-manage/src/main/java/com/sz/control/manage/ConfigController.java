package com.sz.control.manage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.sz.common.listener.SystemConfigUtil;
import com.sz.common.util.AppConstant;
import com.sz.pojo.app.SystemConfig;
import com.sz.pojo.manage.json.JsonBack;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.service.app.SystemConfigService;

/**
 * 配置信息 Controller
 * @author jiangfan.zhou
 * date Jun 4, 2014
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SystemConfigService systemConfigService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("config.add");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(SystemConfig config, BindingResult result, HttpServletRequest request) {
	    JsonBack json = new JsonBack();
	    json.setCode(0);
	    json.setMessage("关键码重复");
		try {
			if(!systemConfigService.checkKey(config.getKey())) {
				systemConfigService.add(config);
			    json.setCode(1);
                json.setMessage("成功");
                logger.info("增加配置成功");
			}
		} catch (Exception e) {
			logger.error("增加配置出现异常", e);
			json.setMessage("错误，请联系管理员");
		}
		return json;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(required = false) int id, HttpServletRequest request) {
		try {
		    SystemConfig entity = systemConfigService.queryById(id);
		    if(entity != null) {
		        request.setAttribute("entity", entity);
		    } else {
		        return "config.add";
		    }
		} catch (Exception e) {
			logger.error("根据ID查询配置信息出现异常", e);
			request.setAttribute("result", AppConstant.RESULT_ERROR);
		}
		return "config.update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@Valid @ModelAttribute("config") SystemConfig form, BindingResult result, HttpServletRequest request) {
	    JsonBack json = new JsonBack();
        json.setCode(0);
        json.setMessage("修改失败");
        
        try {
        	SystemConfig entity = systemConfigService.queryById(form.getId());
            if(entity == null) {
                json.setMessage("不存在配置信息");
                return json;
            }
            SystemConfig keyConfig = systemConfigService.queryByKey(entity.getKey());
			if (keyConfig != null && keyConfig.getId().intValue() != form.getId().intValue()) {
				json.setMessage("关键码重复");
				return json;
			}

			systemConfigService.update(form);
            json.setCode(1);
            json.setMessage("成功");
            logger.info("修改配置成功");
        } catch (Exception e) {
            logger.error("增加配置出现异常", e);
            json.setMessage("错误，请联系管理员");
        }
        return json;
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonBack delete(@RequestParam("id") Integer id) {
		JsonBack jsonBack = new JsonBack();
		try {
			if (systemConfigService.delete(id)) {
				jsonBack.setCode(100);
				jsonBack.setMessage(SystemConfigUtil.getProperty("100"));
				logger.info("删除配置成功");
			} else {
				jsonBack.setCode(108);
				jsonBack.setMessage(SystemConfigUtil.getProperty("108"));
				logger.error("删除配置失败");
			}
		} catch (Exception e) {
			logger.error("删除配置出现异常", e);
			jsonBack.exceptionJsonBack();
		}
		return jsonBack;
	}

	@RequestMapping(value = "/query")
	public ModelAndView query(String key, HttpServletRequest request) {
	    ModelAndView view = new ModelAndView("config.list");
		try {
			PageInfo pageInfo = PageInfo.getPageInfo(request);
			PaginationResult<SystemConfig> paginationResult = systemConfigService.queryByPage(key, pageInfo);
			view.addObject("paginationResult", paginationResult);
		    view.addObject("key", key);
		} catch (Exception e) {
			logger.error("获取配置列表出现异常", e);
		}
		return view;
	}
	
}
