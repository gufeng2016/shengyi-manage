package com.sz.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * User: wuhaifei
 * Date: 13-8-23
 * Time: 下午09:23
 * 程序全局过滤器	 1.解决中文乱码  2.过滤XSS跨站脚本
 */
public class GlobalFilter implements Filter {

	/**待输出的编码格式*/
	private String destEncode = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {

		// 1. GET 乱码解决 ; 2. XSS跨站脚本攻击规避处理
		HttpServletRequest req = new CustomHttpServletRequest(
				(HttpServletRequest) request, destEncode);
		chain.doFilter(req, response);

		/* -- 
		 * 当前授权的方式 不是很好，暂时去除，待采用更好的方式
		 * 原因：不是每个请求都会携带http头中的referer属性,可能导致合理的请求被拒
		 */
		// 只接受授权的请求
		/*boolean allow = DomainAuthorization.authorization(req);
		if(allow) {
			chain.doFilter(req, response);
		}else {
			PrintWriter out = response.getWriter();
			out.write("unauthorized");
			out.flush();
			out.close();
		}*/
	}

	@Override
	public void destroy() {

	}
}
