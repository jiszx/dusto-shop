package com.hhnz.monitor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhnz.crm.mapper.TDictMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TDictExample;
import com.hhnz.service.IExceptionService;

@Service
public class CrmExceptionServiceImpl implements IExceptionService {
  
  @Resource
  private TDictMapper dictMapper;

  @Override
  public String findExceptionMsg(String errClassName) {
    return "错误信息:" + errClassName;
  }

  @Override
  public String findExceptionMsg(Exception exception) {
    String result = "";
    if (exception.getMessage() != null && exception.getMessage().length() > 0) {
      return exception.getMessage();
    }
    TDictExample example = new TDictExample();
    example.createCriteria().andColNameEqualTo("EXCEPTIONS");
    List<TDict> dict = dictMapper.selectByExample(example);
    boolean isFind = false;
    for (TDict exc : dict) {
      if (exception.getClass().getName().equals(exc.getChooseVal())) {
        result = exc.getShowText();
        isFind = true;
        break;
      }
    }
    if(isFind==false){
      TDict di = new TDict();
      di.setColName("EXCEPTIONS");
      di.setChooseVal(exception.getClass().getName());
      di.setShowText(exception.getClass().getName());
      dictMapper.insert(di);
    }
    return result;
  }

}
