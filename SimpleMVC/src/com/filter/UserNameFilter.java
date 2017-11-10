package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserNameFilter implements Filter{
	
	public void destroy() {
		System.out.println("usernamefilter destroy");
	}
	
	/**
	 *  在创建 Filter 对象(Filter 对象在 Servlet 容器加载当前 WEB 应用时即被创建)后, 
	 *	立即被调用, 且只被调用一次. 该方法用于对当前的 Filter 进行初始化操作. Filter 实例是单例的. 
	 *	@param filterConfig : FilterConfig 类似于 ServletConfig,
	 *	可以在 web.xml 文件中配置当前 Filter 的初始化参数. 配置方式也和 Servlet 类似。
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("usernamefilter init");
	}
	
	/**
	 * 真正 Filter 的逻辑代码需要编写在该方法中. 每次拦截都会调用该方法. 
	 * 
	 * FilterChain: Filter 链. 多个 Filter 可以构成一个 Filter 链.
	 * 
	 * doFilter(ServletRequest request, ServletResponse response): 把请求传给 Filter 链的下一个 Filter,
			若当前 Filter 是 Filter 链的最后一个 Filter, 将把请求给到目标 Serlvet(或 JSP),
			多个 Filter 拦截的顺序和 <filter-mapping> 配置的顺序有关, 靠前的先被调用. 
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		String username = arg0.getParameter("username");
		if(username.equals("tom")){
			arg2.doFilter(arg0, arg1);
		}else{
			arg0.setAttribute("message", "username is wrong !!!");
			arg0.getRequestDispatcher("/filter/login.jsp").forward(arg0, arg1);
		}
	}

}
