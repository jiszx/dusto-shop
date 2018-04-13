package com.hhnz.message.mail.template;

import java.io.IOException;

import org.springframework.mail.MailSendException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hhnz.util.ApplicationContextUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author: chaoyang.ren
 * @date:Jan 12, 2017
 * @time:1:35:23 PM
 * @email:chaoyang.ren@foxmail.com
 */
public class FreemarkerRender<T> implements TemplateRender<T>{
	private FreeMarkerConfigurer freeMarkerConfigurer = ApplicationContextUtil.getBean(FreeMarkerConfigurer.class);
	
    public String renderText(T model, String templatePath){
    	// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
		String htmlText;
		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath); 
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException e) {
			throw new MailSendException("邮件模板读取失败！",e);
		} catch (TemplateException e) {
			throw new MailSendException("邮件模板解析失败！",e);
		}
		return htmlText;
    } 
}
