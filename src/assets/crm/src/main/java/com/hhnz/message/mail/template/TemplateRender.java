package com.hhnz.message.mail.template;

/**
 * @author: chaoyang.ren
 * @date:Jan 12, 2017
 * @time:2:16:21 PM
 * @email:chaoyang.ren@foxmail.com
 */
public interface TemplateRender<T> {
	public String renderText(T model, String templateName);
}
