package com.hhnz.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author: chaoyang.ren
 * @date:2016年10月22日
 * @time:下午1:03:08
 * @email:chaoyang.ren@foxmail.com
 */
@ControllerAdvice
public class ParameterTrim{
    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(false);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }
}