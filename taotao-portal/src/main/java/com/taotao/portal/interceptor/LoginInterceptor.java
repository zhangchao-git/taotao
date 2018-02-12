package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在handle执行之前处理
		// 1. 判断用户是否登录
		// 2. 从cookie中获取token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 3. 根据token获取用户信息
		TbUser user = userService.getUserByToken(token);
		if (null == user) {
			// 4. 如果获取不到用户信息,跳转到登录页面,把用户请求url作为参数传递给登录页面,返回false
			response.sendRedirect(
					userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURL());
			return false;
		}
		// 5. 可以获取到用户信息,直接登录,返货true;
		// 返回值决定handler是否执行. true执行. false不执行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在handle执行之后处理,modelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 在handle执行之后处理,modelAndView之后,响应用户之后

	}

}
