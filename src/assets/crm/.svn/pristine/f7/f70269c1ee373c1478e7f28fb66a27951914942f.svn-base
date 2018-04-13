package com.hhnz.util;

import com.hhnz.dto.ResponseResult;

public class ResponseMessage {
  private static ResponseResult TOKEN_EXPIRED =
      ResponseResult.builder().code(403).message("token无效，请登陆").build();
  private static ResponseResult LOGIN_ERROR =
      ResponseResult.builder().code(521).message("用户名或密码错误").build();
  private static ResponseResult PASSWORD_ERROR = ResponseResult.builder().code(522).message("密码错误").build();
  private static ResponseResult NEW_PASSWORD_NOT_EQUAL = ResponseResult.builder().code(523).message("新密码不一致").build();
  private static ResponseResult CUSTOMER_INFO_MISS = ResponseResult.builder().code(524).message("客户信息不完整").build();

  private ResponseMessage() {}

  public static ResponseResult tokenExpired() {
    return TOKEN_EXPIRED;
  }

  public static ResponseResult loginError() {
    return LOGIN_ERROR;
  }
  
  public static ResponseResult passwordError(){
    return PASSWORD_ERROR;
  }
  
  public static ResponseResult newPasswordNotEqual(){
    return NEW_PASSWORD_NOT_EQUAL;
  }
  
  public static ResponseResult customerInfoMiss(){
    return CUSTOMER_INFO_MISS;
  }

}
