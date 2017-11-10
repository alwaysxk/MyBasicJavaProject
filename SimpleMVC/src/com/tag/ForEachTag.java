package com.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport{
	private Collection<?> items;
	private String var;
	
	public void setItems(Collection<?> items) {
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	private PageContext pageContext = null;
	
	public void doTag() throws JspException, IOException {
		JspFragment content = getJspBody();
		pageContext = (PageContext)getJspContext();
		//pageContext.setAttribute("jjjj", "jjjj");
		if(items != null){
			for(Object o : items){
				//getJspContext().setAttribute(var, o);
				pageContext.getRequest().setAttribute(var, o);
				content.invoke(null);
			}
		}
	}
}
