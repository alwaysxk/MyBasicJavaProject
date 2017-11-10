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
	 *  �ڴ��� Filter ����(Filter ������ Servlet �������ص�ǰ WEB Ӧ��ʱ��������)��, 
	 *	����������, ��ֻ������һ��. �÷������ڶԵ�ǰ�� Filter ���г�ʼ������. Filter ʵ���ǵ�����. 
	 *	@param filterConfig : FilterConfig ������ ServletConfig,
	 *	������ web.xml �ļ������õ�ǰ Filter �ĳ�ʼ������. ���÷�ʽҲ�� Servlet ���ơ�
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("usernamefilter init");
	}
	
	/**
	 * ���� Filter ���߼�������Ҫ��д�ڸ÷�����. ÿ�����ض�����ø÷���. 
	 * 
	 * FilterChain: Filter ��. ��� Filter ���Թ���һ�� Filter ��.
	 * 
	 * doFilter(ServletRequest request, ServletResponse response): �����󴫸� Filter ������һ�� Filter,
			����ǰ Filter �� Filter �������һ�� Filter, �����������Ŀ�� Serlvet(�� JSP),
			��� Filter ���ص�˳��� <filter-mapping> ���õ�˳���й�, ��ǰ���ȱ�����. 
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
