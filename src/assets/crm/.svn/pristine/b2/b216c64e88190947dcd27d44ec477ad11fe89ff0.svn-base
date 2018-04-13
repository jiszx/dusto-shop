package com.hhnz.crm.controller;

import javax.annotation.Resource;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.util.SendMail;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

  @Resource
  private SendMail sendMail;

  @ResponseBody
  @RequestMapping(value = "/feedback", method = RequestMethod.POST)
  public int feedback(String message) throws EmailException {
    return sendMail.sendFeedback(message) ? 1 : 0;
  }
}
