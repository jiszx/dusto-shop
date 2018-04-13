package com.hhnz.invoiceSecurity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.OmInvoiceVMapper;
import com.hhnz.account.model.OmInvoiceV;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.invoiceSecurity.dto.AisionInvoicesDTO;
import com.hhnz.invoiceSecurity.dto.BillingInvoiceDTO;
import com.hhnz.invoiceSecurity.dto.HeadersDTO;
import com.hhnz.invoiceSecurity.dto.InvoiceSecurityListDTO;
import com.hhnz.invoiceSecurity.dto.ItemsDTO;
import com.hhnz.invoiceSecurity.mapper.CrmTaxInvoiceMapper;
import com.hhnz.invoiceSecurity.mapper.InvoiceSecurityMapper;
import com.hhnz.invoiceSecurity.model.CrmTaxInvoice;
import com.hhnz.invoiceSecurity.service.InvoiceSecurityService;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class InvoiceSecurityServiceImpl implements InvoiceSecurityService {
	private static Logger logger = Logger.getLogger(InvoiceSecurityServiceImpl.class);
	
	@Resource
	private  InvoiceSecurityMapper  mapper;
	
	@Resource
	private OmInvoiceVMapper invoicemapper;
	
	@Resource
	private CMerchCustBaseMapper merchmapper;
	
	@Resource
	private CrmSalesOrganizationMapper orgmapper;
	
	@Resource
	private CrmTaxInvoiceMapper callbackmapper;
	
	/**
	 * 获取开票数据
	 */
	@Override
	public AjaxDTO getinvoiceList(Map<String,Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<InvoiceSecurityListDTO> list = new ArrayList<InvoiceSecurityListDTO>();
		int total =0;
		if("1".equals((String) params.get("sourcesType"))){
			//通过销售订单获取
			list = this.mapper.getInvoiceListByOrder(params);
			total = this.mapper.countInvoiceByOrder(params);
		}else{
			//通过应收发票获取数据
			list = this.mapper.getInvoiceListByAr(params);
			total = this.mapper.countInvoiceByAr(params);			
		}
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public List<BillingInvoiceDTO> getBillInvoicesItem(String ids,String sourcesType,String IsMerge) {
		if(StringUtils.isBlank(ids)){
			return null;
		}
		logger.info("获取金税发票信息开始");
		List<BillingInvoiceDTO>  list = new ArrayList<BillingInvoiceDTO>();
		List<String> id = new ArrayList<String>();
		for(String invoiceId:ids.split(",")){
			id.add(invoiceId);
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", id);
		params.put("IsMerge", IsMerge);
		if("1".equals(sourcesType)){
			//通过订单获取数据
			List<HeadersDTO> headers = this.mapper.getInvoiceHeaderByOrder(params);
			if("1".equals(IsMerge)){
				if(headers !=null && headers.size()>0){
					for(HeadersDTO header :headers){
						BillingInvoiceDTO  model = new BillingInvoiceDTO();
						model.setHeader(header);
						//获取行数据
						List<ItemsDTO>  items = new ArrayList<ItemsDTO>();
						items = this.mapper.getOrdersItemsByOrder(Long.parseLong(header.getSourcesId()));
						model.setItems(items);
						list.add(model);
					}
				}
			}else{
				//合并开票
				BillingInvoiceDTO  model = new BillingInvoiceDTO();
				model.setHeader(headers.size()==1?headers.get(0):new HeadersDTO());
				//获取行数据
				List<ItemsDTO>  items = new ArrayList<ItemsDTO>();
				items = this.mapper.getOrdersItemsByOrderAll(params);
				model.setItems(items);
				list.add(model);
			}
			
		}else{	
			//通过应收发票获取数据
			List<HeadersDTO> headers = this.mapper.getInvoiceHeaderByAr(params);
			if("1".equals(IsMerge)){
				//不合并开票
				if(headers !=null && headers.size()>0){
					for(HeadersDTO header :headers){
						BillingInvoiceDTO  model = new BillingInvoiceDTO();
						model.setHeader(header);
						//获取行数据
						List<ItemsDTO>  items = new ArrayList<ItemsDTO>();
						items = this.mapper.getOrdersItemsByAr(Long.parseLong(header.getSourcesId()));
						model.setItems(items);
						
						list.add(model);
					}
				}
			}else{
				//合并开票
				BillingInvoiceDTO  model = new BillingInvoiceDTO();
				model.setHeader(headers.size()==1?headers.get(0):new HeadersDTO());
				//获取行数据
				List<ItemsDTO>  items = new ArrayList<ItemsDTO>();
				items = this.mapper.getOrdersItemsByArAll(params);
				model.setItems(items);
				list.add(model);
			}		
		}
		return list;
	}



	@Override
	public int callBackInvoiceNo(CrmTaxInvoice invoices, String ids) {
		if("1".equals(invoices.getIsIsmerge())){
			this.callbackmapper.insert(invoices);
		}else{
			//合并开票
			for(String sourcesId:ids.split(",")){
				//OmInvoiceV invoice = this.invoicemapper.selectByPrimaryKey(Long.parseLong(invoiceId));
				invoices.setSourcesId(Long.parseLong(sourcesId));
				//通过应收发票获取销售订单号
				if("1".equals(invoices.getSourcesType())){
					invoices.setSourcesNo(sourcesId);
				}else{
					OmInvoiceV ar = this.invoicemapper.selectByPrimaryKey(Long.parseLong(sourcesId));
					invoices.setSourcesNo(ar.getInvoiceNo());
				}
				this.callbackmapper.insert(invoices);
			}
			
		}
		return 1;
	}

	@Override
	public AjaxDTO selectAisionInvociesList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		if("1".equals((String) params.get("sourcesType"))){
			//订单获取数据
			List<AisionInvoicesDTO> list = this.mapper.selectAisionInvoicesListByOrder(params);
			int total = this.mapper.countAisionInvoicesListByOrder(params);
			dto.setRows(list);
			dto.setTotal(total);
		}else{
			
		}
		return null;
	}

}
