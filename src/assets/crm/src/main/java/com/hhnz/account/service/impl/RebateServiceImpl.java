package com.hhnz.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhnz.account.mapper.CMerchCustRebateOrderVMapper;
import com.hhnz.account.mapper.CMerchCustRebateVMapper;
import com.hhnz.account.model.CMerchCustRebateOrderV;
import com.hhnz.account.model.CMerchCustRebateOrderVExample;
import com.hhnz.account.model.CMerchCustRebateV;
import com.hhnz.account.model.CMerchCustRebateVExample;
import com.hhnz.account.service.IRebateService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
public class RebateServiceImpl implements IRebateService {
	@Resource
	private CMerchCustRebateVMapper rebatemapper;
	
	@Resource
	private CMerchCustRebateOrderVMapper  rebatedetailMapper;
	@Override
	public AjaxDTO findRebate(List<Long> stationids, AjaxDTO bean) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CMerchCustRebateVExample ex =new CMerchCustRebateVExample();
        CMerchCustRebateVExample.Criteria ext = ex.createCriteria();
        ext.andStationIdIn(stationids);
        ex.setPage(page);
        ex.setOrderByClause("create_ts desc");
        List<CMerchCustRebateV> list = this.rebatemapper.selectByExample(ex);
        int total = this.rebatemapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
	}
	

	@Override
	public CMerchCustRebateV getRebateById(Long id) {
		CMerchCustRebateVExample ex =new CMerchCustRebateVExample();
        CMerchCustRebateVExample.Criteria ext = ex.createCriteria();
        ext.andIdEqualTo(id);
		return this.rebatemapper.selectByExample(ex).get(0);
	}


	@Override
	public AjaxDTO findRebateOrderDetail(Long id, AjaxDTO bean) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CMerchCustRebateOrderVExample ex =new CMerchCustRebateOrderVExample();
        CMerchCustRebateOrderVExample.Criteria ext = ex.createCriteria();
        ext.andRebateIdEqualTo(id);
        ex.setPage(page);
        //ex.setOrderByClause("create_ts desc");
        List<CMerchCustRebateOrderV> list = this.rebatedetailMapper.selectByExample(ex);
        int total = this.rebatedetailMapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
	}

}
