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
public enum MailContentType {
	NORMAL_TEXT("1","基础文本"),
	/**
	 * FREEMARKER模板格式
	 */
	HTML_VELOCITY("2","FREEMARKER模板格式"),
	/**
	 * velocity模板格式
	 * not accomplished
	 */
	HTML_FREEMARKER("3","VELOCITY模板格式")
	;
	
	private MailContentType(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<String,MailContentType> CACHE = Collections.unmodifiableMap(new HashMap<String,MailContentType>(){
		private static final long serialVersionUID = 1L;
		{
			for (MailContentType rs : MailContentType.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static MailContentType toEnum(String code){
		return CACHE.get(code);
	}
	
	private String code;
	private String desc;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
