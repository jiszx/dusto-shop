package com.hhnz.jco.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.jco.business.RfcCaller;
import com.hhnz.jco.business.base.BaseInput;
import com.hhnz.jco.enu.RfcExeStatus;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.mapper.TRfcExecuteHistoryMapper;
import com.hhnz.jco.mapper.TRfcExecuteMapper;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.model.TRfcExecuteExample;
import com.hhnz.jco.model.TRfcExecuteExample.Criteria;
import com.hhnz.jco.model.TRfcExecuteHistory;
import com.hhnz.jco.model.TRfcExecuteHistoryExample;
import com.hhnz.jco.service.IRFCExecuteService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;
import com.hhnz.util.db.Page;

/**
 * @author: chaoyang.ren
 * @date:2016年11月10日
 * @time:上午11:11:01
 * @email:chaoyang.ren@foxmail.com
 */
@Service("rfcExecuteService")
@Transactional
public class RFCExecuteServiceImpl implements IRFCExecuteService{
	@Resource
	private TRfcExecuteHistoryMapper rfcExecuteHistoryMapper;
	@Resource
	private TRfcExecuteMapper rfcExecuteMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(TRfcExecute rfcExecute) {
		TRfcExecute re = rfcExecuteMapper.selectByPrimaryKey(rfcExecute.getSerialNo());
		if(re != null){
			rfcExecuteMapper.updateByPrimaryKeySelective(rfcExecute);
		}else{
			rfcExecuteMapper.insertSelective(rfcExecute);
		}
	}
	
	@Override
	public void addToHistory(TRfcExecute rfcExecute) {
		TRfcExecuteHistory reh = new TRfcExecuteHistory();
		if(RfcExeStatus.SUCCESS.name().equals(rfcExecute.getStatus())){
			rfcExecute.setStatus(RfcExeStatus.FINISHED.name());
		}else if(RfcExeStatus.FAILURE.name().equals(rfcExecute.getStatus())){
			rfcExecute.setStatus(RfcExeStatus.NEVER.name());
		}
		BeanUtils.copyProperties(rfcExecute, reh);
		reh.setCreateDate(new Date());
		rfcExecuteHistoryMapper.insertSelective(reh);
		rfcExecuteMapper.deleteByPrimaryKey(rfcExecute.getSerialNo());
	}
	
	/**
	 * 刷新rfc请求参数并将旧参数加入历史记录
	 */
	@Override
	public void refreshParam(TRfcExecute rfcExecute){
		String params = rfcExecute.getParametersWithLength();
		RfcExeType executeType = RfcExeType.toEnum(rfcExecute.getExecuteType());
		String beanName = executeType.getBeanName();
		RfcCaller caller = ApplicationContextUtil.getBean(beanName);
		Class<?> c = caller.getClass();
		String callerName = c.getName();
		if(callerName.contains("$$EnhancerBySpringCGLIB$$")){
			callerName = c.getSuperclass().getName();
		}
		int beginIndex = callerName.lastIndexOf(".");
		String prefix = callerName.substring(0, beginIndex);
		String inputDtoClass = prefix.concat(".InputDTO");
		BaseInput bi = null;
		try {
			bi = (BaseInput) JsonUtil.fromJSON(params, Class.forName(inputDtoClass));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Serializable crmRelatedId = bi.getCrmRelatedId();
		String newParam = caller.constructParam(crmRelatedId);
		if(params.equals(newParam)){
			return;
		}
		TRfcExecuteHistory reh = new TRfcExecuteHistory();
		BeanUtils.copyProperties(rfcExecute, reh);
		rfcExecute.setParametersWithLength(newParam);
		rfcExecute.setStatus(RfcExeStatus.REFRESHED.name());
		rfcExecute.setResult(StringUtils.EMPTY);
		rfcExecute.setFailureTimes(0);
		rfcExecuteMapper.updateByPrimaryKeySelective(rfcExecute);
		reh.setStatus(RfcExeStatus.REFRESHED.name());
		reh.setCreateDate(new Date());
		TRfcExecuteHistoryExample ex = new TRfcExecuteHistoryExample();
	    TRfcExecuteHistoryExample.Criteria param = ex.createCriteria();
	    param.andSerialNoLike(reh.getSerialNo()+"%");
	    int count = rfcExecuteHistoryMapper.countByExample(ex);
		reh.setSerialNo(reh.getSerialNo()+"_"+count);
		reh.setParametersWithLength(params);
		rfcExecuteHistoryMapper.insertSelective(reh);
	}

	@Override
	public AjaxDTO searchCurrentExecutions(AjaxDTO bean, TRfcExecute re) {
		Page page = new Page();
	    page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
	    TRfcExecuteExample ex = new TRfcExecuteExample();
	    Criteria param = ex.createCriteria();
	    if(StringUtils.isNotBlank(re.getExecuteType())){
	    	param.andExecuteTypeEqualTo(re.getExecuteType());
	    }
	    if(StringUtils.isNotBlank(re.getStatus())){
	    	param.andStatusEqualTo(re.getStatus());
	    }
	    ex.setPage(page);
	    ex.setOrderByClause("create_date desc");
	    List<TRfcExecute> res = rfcExecuteMapper.selectByExample(ex);
	    int count = rfcExecuteMapper.countByExample(ex);
	    bean.setRows(res);
	    bean.setTotal(count);
	    return bean;
	}
	
	@Override
	public List<TRfcExecute> searchCurrentExecutions(TRfcExecute re) {
		TRfcExecuteExample ex = new TRfcExecuteExample();
		Criteria param = ex.createCriteria();
		if(re.getFailureTimes() != null){
			param.andFailureTimesLessThanOrEqualTo(re.getFailureTimes());
		}
		if(StringUtils.isNotBlank(re.getExecuteType())){
			param.andExecuteTypeEqualTo(re.getExecuteType());
		}
		if(StringUtils.isNotBlank(re.getStatus())){
			param.andStatusEqualTo(re.getStatus());
		}
		ex.setOrderByClause("create_date desc");
		List<TRfcExecute> res = rfcExecuteMapper.selectByExample(ex);
		return res;
	}

	@Override
	public AjaxDTO searchHistoryExecutions(AjaxDTO bean, TRfcExecuteHistory reh) {
		Page page = new Page();
	    page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
	    TRfcExecuteHistoryExample ex = new TRfcExecuteHistoryExample();
	    TRfcExecuteHistoryExample.Criteria param = ex.createCriteria();
	    if(StringUtils.isNotBlank(reh.getExecuteType())){
	    	param.andExecuteTypeEqualTo(reh.getExecuteType());
	    }
	    if(StringUtils.isNotBlank(reh.getStatus())){
	    	param.andStatusEqualTo(reh.getStatus());
	    }
	    ex.setPage(page);
	    ex.setOrderByClause("create_date desc");
	    List<TRfcExecuteHistory> res = rfcExecuteHistoryMapper.selectByExample(ex);
	    int count = rfcExecuteHistoryMapper.countByExample(ex);
	    bean.setRows(res);
	    bean.setTotal(count);
	    return bean;
	}

	@Override
	public TRfcExecute findBySerialNo(String serialNo) {
		return rfcExecuteMapper.selectByPrimaryKey(serialNo);
	}
	
}
