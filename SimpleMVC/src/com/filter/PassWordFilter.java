package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PassWordFilter implements Filter{

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		String passwd = arg0.getParameter("password");
		
		if(passwd.equals("1234")){
			arg2.doFilter(arg0, arg1);
		}else{
			arg0.setAttribute("message", "password is wrong!!!");
			arg0.getRequestDispatcher("/filter/login.jsp").forward(arg0, arg1);
		}
	}
	
}
