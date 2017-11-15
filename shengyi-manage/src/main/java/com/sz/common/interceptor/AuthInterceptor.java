package com.sz.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sz.common.util.AppConstant;
import com.sz.pojo.manage.auth.Auth;


/**
 * User： xin.fang
 * Date： 14-6-3
 * Time： 下午2:51
 * 权限拦截器(验证用户的访问权限)
 */
public class AuthInterceptor implements HandlerInterceptor {


	private String[] exceptUrls;

	private String [] extensions;

	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}

	public void setExceptUrls(String[] exceptUrls) {
		this.exceptUrls = exceptUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURL().toString();
		//扩展名过滤
		if (StringUtils.isNotBlank(requestUrl)) {
			int dotIndex = requestUrl.lastIndexOf(".");
			if (dotIndex != -1) {
				int paramIndex = requestUrl.indexOf("?");
				String extendName = "";
				if (paramIndex != -1) {
					if (dotIndex <= paramIndex) {
						extendName = requestUrl.substring(dotIndex + 1, paramIndex);
					}
				} else {
					extendName = requestUrl.substring(dotIndex + 1);
				}
				for (String extend : extensions) {
					if (extendName.equalsIgnoreCase(extend)) {
						return true;
					}
				}

			}
		}

		//URL过滤
		String servletPath = request.getServletPath();
		if (StringUtils.isBlank(servletPath)) {
			request.setAttribute("error", "对不起你没有权限访问");
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}

		//不需要拦截的url
		if (null != exceptUrls && exceptUrls.length > 0) {
			for (String url : exceptUrls) {
				if (servletPath.equals(url)) {
					return true;
				}
			}
		}

		Object object = request.getSession().getAttribute(AppConstant.SESSION_AUTH_LIST);
		if (null == object) {
			request.setAttribute("error", "对不起你没有权限访问");
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}

		List<Auth> authList = (List<Auth>) object;
		if(checkAuth(authList, servletPath)){
			return true;
		}

		//查询出不需要验证的权限(即对所有的人开放的权限)
		/*List<Auth> notNeedVeriAuth = authService.queryNotNeedVeriAuth();
        int intIsTrue = AppConstant.INTEGER_IS_TRUE;
        for (Auth auth : notNeedVeriAuth) {
            //判断请求的url是否匹配到权限表中生效的权限url
            String authUrl = auth.getUrl();
            if(authUrl.equals(requestUrl)){
                System.out.println(authUrl);
                if(authUrl.equals(requestUrl)){
                   return true;
                }
            }
        }*/

		//如果请求的url不是所有的人开放的权限，那么在此验证用户是否有权限
       /* String userName = (String)request.getSession().getAttribute(AppConstant.LOGIN_SESSION_NAME);
        List<Auth> needVenriAuth = authService.queryNeedVeriAuth(userName);
        for (Auth auth : needVenriAuth) {
            //如果用户拥有该权限，则转入到下一个过滤器
            if(auth.getUrl().equals(requestUrl)){
                return true;
            }
        }
*/
		//如果两种方式的权限都无法匹配，则直接返回
		//TODO 用户提示
		request.setAttribute("error", "对不起你没有权限访问");
		request.getRequestDispatcher("/login").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//To change body of implemented methods use File | Settings | File Templates.
	}
	
	private boolean checkAuth(List<Auth> authList, String url) {
		if(CollectionUtils.isEmpty(authList)) return false;
		for(Auth auth : authList){
			if(StringUtils.isNotBlank(auth.getUrl()) && auth.getUrl().equalsIgnoreCase(url)){
				return true;
			}
			if(checkAuth(auth.getChildAuth(), url)){
				return true;
			}
		}
		return false;
	}
}
