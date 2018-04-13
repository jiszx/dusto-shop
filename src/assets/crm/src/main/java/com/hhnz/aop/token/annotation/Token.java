package com.hhnz.aop.token.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止数据重复提交注解
 * <p>用法：</p>
 * <blockquote>
 * <pre>
 * a.在保存方法体上添加注解<b><i>{@code @Token}</i></b>
 * </pre>
 * <pre>
 * b.在表单页面添加标签<b><i>{@code <token:token/>}</i></b>
 * 并确保该jsp页面已引入标签
 * <b><i>{@code <%@ taglib prefix="token" uri="/WEB-INF/token.tld"%>}</i></b>
 * </pre>
 * </blockquote>
 * @author: chaoyang.ren
 * @date:2016年10月18日
 * @time:下午5:24:26
 * @email:chaoyang.ren@foxmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
}
