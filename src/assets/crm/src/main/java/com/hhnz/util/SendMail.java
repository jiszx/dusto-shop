package com.hhnz.util;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hhnz.message.mail.MailSender;
import com.hhnz.message.mail.enu.MailTemplate;
import com.hhnz.message.mail.template.model.DeliveryEmailModel;
import com.hhnz.message.mail.template.model.FeedbackModel;
import com.hhnz.order.dto.DeliveryOrderDTO;

@Service
public class SendMail {
  @Value("${mail.admin}")
  private String sendto;
  @Value("${order.delivery.print.url}")
  private String printUrl;
  @Autowired
  private MailSender mailSender;
  
  public boolean sendFeedback(String message) throws EmailException{
	  FeedbackModel fm = new FeedbackModel();
	  fm.setContent(message);
	  mailSender.send(fm, MailTemplate.SYS_FEEDBACK, sendto);
	  return true;
  }
  
  public boolean noticeDelivery(String logisticsName, String[] files, DeliveryOrderDTO dod, String sendto, String... recipients){
    DeliveryEmailModel model = new DeliveryEmailModel();
    model.setPrintUrl(printUrl+"?t="+dod.getToken());
    model.setUserName(logisticsName);
    model.setOrder(dod);
    model.setContent(dod.getOrder().getId().toString());
    model.setFiles(files);
    mailSender.send(model, MailTemplate.LOGISTICS_NOTICE, sendto, recipients);
    return true;
  }
}
