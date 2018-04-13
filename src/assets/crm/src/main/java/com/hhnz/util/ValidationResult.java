package com.hhnz.util;

import java.util.Map;

/**
 * 校验结果模型
 * @author: chaoyang.ren
 * @date:2016年9月14日
 * @time:上午11:01:07
 * @email:chaoyang.ren@foxmail.com
 */
public class ValidationResult {
    //校验结果是否有错
    private boolean hasErrors;
    
    //校验错误信息
    private Map<String,String> errorMsg;

    public boolean isHasErrors() {
      return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
      this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
      return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
      this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
      return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
          + errorMsg + "]";
    }
}
