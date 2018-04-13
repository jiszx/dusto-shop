package com.hhnz.message.mail.template.model;

import java.util.Date;

/**
 * @author: chaoyang.ren
 * @date:Jan 13, 2017
 * @time:11:36:48 AM
 * @email:chaoyang.ren@foxmail.com
 */
public class BaseModel {
	/**
	 * default :current user name
	 */
	private String userName;
	private String mailAddress;
	private String subject;
	private Date date = new Date();
	/**
	 * 图片
	 * <img src='cid:imgName'/>
	 */
	private String[] images;
	/**
	 * 附件
	 */
	private String[] files;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	
}
