package com.hhnz.message.mail;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.message.mail.enu.MailTemplate;
import com.hhnz.message.mail.template.FreemarkerRender;
import com.hhnz.message.mail.template.TemplateRender;
import com.hhnz.message.mail.template.model.BaseModel;
import com.hhnz.util.ApplicationContextUtil;

/**
 * @author: chaoyang.ren
 * @date:Jan 11, 2017
 * @time:5:11:03 PM
 * @email:chaoyang.ren@foxmail.com
 */
@Component
public class MailSender {
	private static final Log LOG = LogFactory.getLog(MailSender.class);
	@Value("${mail.default.from}")
	private String from;
	@Autowired  
    private JavaMailSender javaMailSender;
	
	/**
	 * 发送简单文本邮件
	 * @author: chaoyang.ren 
	 * @date:Jan 13, 2017  12:38:02 PM
	 * @param text
	 * @param subject
	 * @param to
	 * @param recipients
	 */
	public void send(String text, String subject, String to, String... recipients){
		SimpleMailMessage simpleMailMessage = toSimpleMailMessage(text, subject, to, recipients);
		javaMailSender.send(simpleMailMessage);
	}
	
	/**
	 * 发送模板邮件
	 * @author: chaoyang.ren 
	 * @date:Jan 13, 2017  12:38:17 PM
	 * @param model
	 * @param mailTemplate
	 * @param to
	 * @param recipients
	 */
	public void send(BaseModel model, MailTemplate mailTemplate, String to, String... recipients){
		model.setMailAddress(to);
		model.setSubject(mailTemplate.getSubject());
		if(StringUtils.isBlank(model.getUserName())){
			TEmployee currentUser = ApplicationContextUtil.getCurrentUser();
			model.setUserName(currentUser != null? currentUser.getLoginName():"");
		}
		SimpleMailMessage simpleMailMessage = toSimpleMailMessage(model, mailTemplate, to, recipients);
		javaMailSender.send(toMimeMessage(simpleMailMessage,model.getImages(),model.getFiles()));
	}

	/**
	 * 处理模板邮件参数
	 * @author: chaoyang.ren 
	 * @date:Jan 13, 2017  12:38:32 PM
	 * @param simpleMailMessage
	 * @param images
	 * @param files
	 * @return
	 */
	public MimeMessagePreparator toMimeMessage(final SimpleMailMessage simpleMailMessage, final String[] images, final String[] files) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(simpleMailMessage.getTo());
				message.setCc(simpleMailMessage.getCc());
				message.setSubject(simpleMailMessage.getSubject());
				message.setText(simpleMailMessage.getText(), true);
				message.setFrom(simpleMailMessage.getFrom());
				// 内嵌文件
				message.addInline("user-24.png", new ClassPathResource("/template/imgs/user-24.png"));
				if(images != null){
					for (String img : images) {
						String path;
						if(img.contains("/") || img.contains("\\")){
							path = img;
						}else{
							path = "/template/imgs/"+img;
						}
						ClassPathResource resource = new ClassPathResource(path);
						message.addInline(img,resource);
					}
				}
				// 附件
				if(files != null){
					for (String fileName : files) {
						InputStream input = null;
						if(fileName.contains("/") || fileName.contains("\\")){
						  input = new FileInputStream(fileName);
						}else{
						  input = new ClassPathResource("/template/files/"+fileName).getInputStream();
						}
                        try {
                          message.addAttachment(MimeUtility.encodeWord("附件." +Files.getFileExtension(fileName)),
                              new ByteArrayResource(IOUtils.toByteArray(input)));
                        }finally {
                          IOUtils.closeQuietly(input);
                        }
					}
				}
			}
		};
		return preparator;
	}
	
	/**
	 * 处理模板邮件基本参数
	 * @author: chaoyang.ren 
	 * @date:Jan 13, 2017  12:38:44 PM
	 * @param model
	 * @param mailTemplate
	 * @param to
	 * @param recipients
	 * @return
	 */
	public SimpleMailMessage toSimpleMailMessage(BaseModel model, MailTemplate mailTemplate, String to, String... recipients){
		TemplateRender<BaseModel> templateRender = new FreemarkerRender<BaseModel>();
        String text = templateRender.renderText(model, mailTemplate.getTemplateName());
        LOG.info(text);
        SimpleMailMessage message = new SimpleMailMessage();  
        message.setSubject(mailTemplate.getSubject());
        message.setFrom(from);
        message.setTo(to);
        message.setCc(recipients);
        message.setText(text);
		return message;
	}
	
	/**
	 * 处理简单邮件基本参数
	 * @author: chaoyang.ren 
	 * @date:Jan 13, 2017  12:39:00 PM
	 * @param text
	 * @param subject
	 * @param to
	 * @param recipients
	 * @return
	 */
	public SimpleMailMessage toSimpleMailMessage(String text, String subject, String to, String... recipients){
		SimpleMailMessage message = new SimpleMailMessage();  
		message.setSubject(subject);
		message.setFrom(from);  
		message.setTo(to);
		message.setCc(recipients);
		message.setText(text);
		return message;
	}
	
}
