package com.hhnz.account.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.mapper.CMerchBalancesVMapper;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.GlPeriodsMapper;
import com.hhnz.account.model.CMerchBalancesV;
import com.hhnz.account.model.CMerchBalancesVExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.model.GlPeriods;
import com.hhnz.account.model.GlPeriodsExample;
import com.hhnz.account.service.ICustPeriodService;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.db.Page;

@Service
public class CustPeriodServiceImpl implements ICustPeriodService {
	@Resource
	private CMerchBalancesVMapper balanceMapper;
	@Resource
	private CMerchCustBalancesMapper custBalanceMapper;
	@Resource
	private CMerchCustAccountLogMapper accountLogMapper;
    
	@Resource
	private GlPeriodsMapper  glmapper;
	
	@Resource
	private AccountUtilMapper  utilmapper;
	@SuppressWarnings("unchecked")
	@Override
	public AjaxDTO findCustPeriod(AjaxDTO bean,Map<String, Object> params) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CMerchBalancesVExample ex =new CMerchBalancesVExample();
        CMerchBalancesVExample.Criteria ext = ex.createCriteria();
        if(!StringUtils.isEmpty((String)params.get("custname")) && !params.get("custname").equals("null")){
        	ext.andCustnameLike((String)params.get("custname")+"%");
        }
        if(!StringUtils.isEmpty((String)params.get("accountType")) && !params.get("accountType").equals("null")){
        	ext.andAccountTypeEqualTo((String)params.get("accountType"));
        }
        if(!StringUtils.isEmpty((String)params.get("period")) && !params.get("period").equals("null") && !"0".equals(params.get("period"))){
        	ext.andPeriodEqualTo((String)params.get("period"));
        }
        if(!StringUtils.isEmpty((String)params.get("type"))){
        	ext.andOrganizationIdLike((String)params.get("orgid")+"%");
        }
        
        if(StringUtils.equals("1", (String)params.get("usertype"))){
        	ext.andMerchPidEqualTo((Long)params.get("merchid"));
        }else{
        	List<Long> stationids=(List<Long>)params.get("stationids");
        	if(stationids !=null && stationids.size() >0){        	
            	ext.andStationIdIn((List<Long>)params.get("stationids"));
            }
        	//获取零售商对应的数据
        }
        if(!StringUtils.isEmpty((String) params.get("custType"))){
        	ext.andCustTypeEqualTo((String) params.get("custType"));
        }
        if(!StringUtils.isEmpty((String) params.get("sorgid"))){
        	ext.andOrganizationIdEqualTo((String) params.get("sorgid"));
        }
        ex.setPage(page);
        ex.setOrderByClause("period desc");
        List<CMerchBalancesV> list = this.balanceMapper.selectByExample(ex);
        int total = this.balanceMapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
	}
	
	@Override
	public AjaxDTO findPeriodDetail(Long custid, String orgid, String period, int limit, int offset,String accountType){
		AjaxDTO dto = new AjaxDTO();
		Page page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        CMerchCustAccountLogExample ex =new CMerchCustAccountLogExample();
        CMerchCustAccountLogExample.Criteria ext = ex.createCriteria();
        if(custid!=null){
        	ext.andMerchCustIdEqualTo(custid);
        }
        if(StringUtils.isNotEmpty(orgid)){
        	ext.andOrganizationIdEqualTo(orgid);
        }
        ext.andAccountTypeEqualTo(accountType);
        if(period!=null && !StringUtils.equals("null", period)){
        	ext.andPeriodEqualTo(period);
        }
        ext.andStatesEqualTo("S");
        ext.andAttribute1IsNull();
        ex.setPage(page);
        ex.setOrderByClause("create_ts desc");
        List<CMerchCustAccountLog> list = this.accountLogMapper.selectByExample(ex);
        if(list!=null && list.size()>0){
        	for(int i=0;i<list.size();i++){
        		list.get(i).setdAmt(BigDecimalASME.divide(list.get(i).getdAmt()));
        		list.get(i).setcAmt(BigDecimalASME.divide(list.get(i).getcAmt()));
        	}
        }
        int total = this.accountLogMapper.countByExample(ex);
        dto.setRows(list);
        dto.setTotal(total);
        return dto;
	}
	
	@Override
	public CMerchBalancesV getBalance(Long id) {
		// TODO Auto-generated method stub
		CMerchBalancesVExample ex =new CMerchBalancesVExample();
        CMerchBalancesVExample.Criteria ext = ex.createCriteria();
        ext.andIdEqualTo(new BigDecimal(id));
		return this.balanceMapper.selectByExample(ex).get(0);
	}
	
	@Override
	public CMerchCustBalances getMarginBalance(Long merchCustId, String orgId, String period) {
		CMerchCustBalancesExample ex =new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria ext = ex.createCriteria();
		ext.andMerchCustIdEqualTo(merchCustId);
		ext.andPeriodEqualTo(period);
		ext.andOrganizationIdEqualTo(orgId);
		ext.andAccountTypeEqualTo("4");//保证金
		List<CMerchCustBalances> balances = this.custBalanceMapper.selectByExample(ex);
		if(balances != null && !balances.isEmpty()){
			return balances.get(0);
		}
		return null;
	}

	@Override
	public AjaxDTO periodData() {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		GlPeriodsExample ex = new GlPeriodsExample();
		List<GlPeriods> list  = this.glmapper.selectByExample(ex);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public String getMaxPeriod() {
		return this.utilmapper.getMaxPeriod();
	}

	@Override
	public String upBalance(String period) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_period", period);
		this.utilmapper.upBalance(map);
		return map.get("p_result").toString();
	}

}
