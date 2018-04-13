package com.hhnz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Params {
  private Params() {}

  public static ParamMapBuilder builder() {
    return new ParamMapBuilder();
  }

  public static class ParamMapBuilder {
    private Map<String, Object> param = new HashMap<>();

    public ParamMapBuilder add(String key, Object value) {
      if (value instanceof String) {
        String strValue = (String) value;
        if (StringUtils.isNotEmpty(strValue)) {
          param.put(key, strValue);
        }
      }else if(value instanceof List){
        List<?> listParam = (List<?>)value;
        if(!listParam.isEmpty()){
          param.put(key, value);
        }
      }else {
        if (value != null) {
          param.put(key, value);
        }
      }
      return this;
    }

    public Map<String, Object> buid() {
      return param;
    }
  }
}
