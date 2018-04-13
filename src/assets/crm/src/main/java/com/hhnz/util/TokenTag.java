package com.hhnz.util;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 防止重复提交标签
 * @author: chaoyang.ren
 * @date:2016年10月19日
 * @time:上午9:20:57
 * @email:chaoyang.ren@foxmail.com
 */
public class TokenTag extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException{
		JspWriter jsp = getJspContext().getOut();
		String _token = UUID.randomUUID().toString();
		ApplicationContextUtil.getRequest().getSession().setAttribute("_token", _token);
		jsp.write("<input type=\"hidden\" name=\"_token\" value=\""+_token+"\">");
	}
}
