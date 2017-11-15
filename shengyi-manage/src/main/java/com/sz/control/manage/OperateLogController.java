package com.sz.control.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sz.pojo.manage.log.OperateLog;
import com.sz.pojo.manage.page.PageInfo;
import com.sz.pojo.manage.page.PaginationResult;
import com.sz.service.manage.OperateLogService;

/**
 * Created by vic on 14-7-2.
 * 日志查询
 */
@Controller
@RequestMapping(value = "/operateLog/")
public class OperateLogController {

    @Autowired
    OperateLogService operateLogService;

    private Logger logger = LoggerFactory.getLogger(OperateLogController.class);

    /**
     * 查询根节点
     * @param request
     * @return
     */
    @RequestMapping(value = "query")
    public ModelAndView queryLog(HttpServletRequest request,@ModelAttribute(value = "log") OperateLog log){
        PageInfo page = PageInfo.getPageInfo(request);
        PaginationResult paginationResult = null;
        try {
            paginationResult = operateLogService.queryAllOperateLog(page,log);
        } catch (Exception e) {
            logger.error(e.toString());
        }

        ModelAndView view = new ModelAndView();
        view.setViewName("log.list");
        view.addObject("paginationResult", paginationResult);
        return view;
    }

    @ModelAttribute(value = "log")
    public OperateLog setLog(OperateLog log){
        return log;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
