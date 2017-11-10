package com.tag;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ReadFileTag extends SimpleTagSupport{
	
	private String src;
	public void setSrc(String src){
		this.src = src;
	}
	
	private PageContext pageContext = null;
	@Override
	public void doTag() throws JspException, IOException {
		pageContext = (PageContext)getJspContext();
		pageContext.getOut().print(pageContext.getServletContext());
		pageContext.getOut().print("<br>");
		InputStream in = pageContext.getServletContext().getResourceAsStream(src);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String c = null;
		int b = 0;
		while((c = br.readLine())!=null){
			
			
			pageContext.getOut().println(c);
			pageContext.getOut().println("<br>");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
