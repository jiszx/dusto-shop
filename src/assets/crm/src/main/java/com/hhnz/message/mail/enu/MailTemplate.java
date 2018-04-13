package com.hhnz.message.mail.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chaoyang.ren
 * @date:Jan 12, 2017
 * @time:11:52:44 AM
 * @email:chaoyang.ren@foxmail.com
 */
public enum MailTemplate {
	SYS_FEEDBACK("1","系统反馈","sys_feedback.ftl",MailContentType.HTML_FREEMARKER),
	LOGISTICS_NOTICE("2","物流通知","logistics_notice.ftl",MailContentType.HTML_FREEMARKER),
	;
	
	private MailTemplate(String code, String subject, String templateName, MailContentType mailContentType){
		this.code = code;
		this.subject = subject;
		this.templateName = templateName;
		this.mailContentType = mailContentType;
	}
	
	private static final Map<String,MailTemplate> CACHE = Collections.unmodifiableMap(new HashMap<String,MailTemplate>(){
		private static final long serialVersionUID = 1L;
		{
			for (MailTemplate rs : MailTemplate.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static MailTemplate toEnum(String code){
		return CACHE.get(code);
	}
	
	private String code;
	private String subject;
	private String templateName;
	private MailContentType mailContentType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public MailContentType getMailContentType() {
		return mailContentType;
	}
	public void setMailContentType(MailContentType mailContentType) {
		this.mailContentType = mailContentType;
	}
	
}
