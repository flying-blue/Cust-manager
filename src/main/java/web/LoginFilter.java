package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//排除掉3个不需要检查的路径，即发现是这3个路径，直接让请求继续执行
		String[] paths = new String[]{
			"/toLogin.do","/login.do","/createImg.do"	
		};
		String p = request.getServletPath();
		for (String path : paths) {
			if (p.equals(path)) {
				chain.doFilter(request, response);
				return ;
			}
		}
		
		
		//从session中获取账号
		HttpSession session = request.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		//根据账号判断用户是否登录
		if (adminCode==null) {
			//没登录，重定向到登录页面
			response.sendRedirect("/custManager/toLogin.do");
		}else {
			//已登陆，请求继续执行
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
