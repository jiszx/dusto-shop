package com.hhnz.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by 杨 on 2017/1/10.
 */
@Aspect
public class PageAroundHandler {

    @Around( "execution(* com.hhnz.*.mapper.*Provider.*(..))")
    public void processText(ProceedingJoinPoint PJ) throws Throwable
    {
        System.out.println("执行目标方法之前，模拟开始事务。");
        PJ.proceed() ;
        System.out.println("执行目标方法之后，模拟结束事务。");
    }
}
