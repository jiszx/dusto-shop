package com.hhnz.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.dto.AccountReceiveDTO;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchReceiveVerifieMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.mapper.OmInvoiceItemVMapper;
import com.hhnz.account.mapper.OmInvoiceVMapper;
import com.hhnz.account.mapper.ReceiveVerifieMapper;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.model.CMerchReceiveVerifie;
import com.hhnz.account.model.CMerchReceiveVerifieExample;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.model.OmInvoiceItemV;
import com.hhnz.account.model.OmInvoiceItemVExample;
import com.hhnz.account.model.OmInvoiceV;
import com.hhnz.account.model.OmInvoiceVExample;
import com.hhnz.account.service.IAccountReceiveService;
import com.hhnz.jco.business.writeoff.InputDTO;
import com.hhnz.jco.business.writeoff.WriteOffRFC;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.db.Page;

/**
 * 应收发票核销service
 * @author hhnz-skevin
 *
 */
@Service
@Transactional
public class AccountReceiveServiceImpl implements IAccountReceiveService {
	private static Logger logger = Logger.getLogger(AccountReceiveServiceImpl.class);
	@Resource
	private ReceiveVerifieMapper mapper;
	
	@Resource
	private OmInvoiceVMapper  invoicevmapper;
	
	@Resource
	private CMerchUpaccountMapper  upaccountmapper;
	
	@Resource
	private CMerchReceiveVerifieMapper  receviemapper;
	
	@Resource
	private OmInvoiceItemVMapper  invoiceItemMapper;
	
	@Resource
	private WriteOffRFC   writeoffrfc;
	
	@Resource
	private  CMerchCustAccountLogMapper accountlogmapper;
	@Override
	public AjaxDTO verifieList(AjaxDTO bean, OmInvoiceV model) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        OmInvoiceVExample ex =new OmInvoiceVExample();
        OmInvoiceVExample.Criteria ext =ex.createCriteria();
        if(!StringUtils.isEmpty(model.getName())){
        	ext.andNameLike("%"+model.getName()+"%");
        }
        if(!StringUtils.isEmpty(model.getInvoiceNo())){
        	ext.andInvoiceNoEqualTo(model.getInvoiceNo());
        }
        if(!StringUtils.isEmpty(model.getType())){
        	ext.andTypeEqualTo(model.getType());
        }
        ex.setPage(page);
        List<OmInvoiceV> list = this.invoicevmapper.selectByExample(ex);
        int total = this.invoicevmapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO upAccountList(AjaxDTO bean, String sapcustomerid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit() + bean.getOffset());
		params.put("sapcustomerid", sapcustomerid);
		List<AccountReceiveDTO> list = this.mapper.upAccountList(params);
		int total = this.mapper.countUpAccountList(params);
		bean.setRows(list);
		bean.setTotal(total);
 		return bean;
	}

	@Override
	public Map<String, Object> doReceive(Long invoiceId, String accountIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(invoiceId ==null){
			result.put("type", "500");
			result.put("msg", "传入应收发票为空");
			return result;
		}
		if(StringUtils.isBlank(accountIds)){
			result.put("type", "500");
			result.put("msg", "请选择收款记录");
			return result;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		//获取应收发票数据
		OmInvoiceVExample ex =new OmInvoiceVExample();
        OmInvoiceVExample.Criteria ext =ex.createCriteria();
		ext.andIdEqualTo(invoiceId);
        List<OmInvoiceV> list = this.invoicevmapper.selectByExample(ex);
        if(list ==null ||list.size() ==0){
        	result.put("type", "500");
			result.put("msg", "获取应收发票失败");
			return result;
        }
        OmInvoiceV   invoice = list.get(0);//应收发票数据
        BigDecimal  invoiceAmt = BigDecimalASME.multiply(invoice.getTotalPrice().add(invoice.getTotalTax()));//应收发票含税金额
        BigDecimal  unverifieAmt =invoiceAmt.subtract(BigDecimalASME.multiply(invoice.getVerifieAmt()));//未核销金额
        for(String accountid:accountIds.split(",")){
        	CMerchReceiveVerifie  verifie = new CMerchReceiveVerifie();
        	if(StringUtils.isBlank(accountid)){
                continue;
            }
        	if(unverifieAmt.compareTo(BigDecimal.ZERO)==0){
        		break;
        	}
        	//获取资金上账ID
        	CMerchUpaccount  upaccount = this.upaccountmapper.selectByPrimaryKey(Long.parseLong(accountid));
        	//获取资金上账log信息
        	CMerchCustAccountLogExample logex = new CMerchCustAccountLogExample();
        	CMerchCustAccountLogExample.Criteria logext= logex.createCriteria();
        	logext.andOrderIdEqualTo(upaccount.getId());
        	logext.andOrganizationIdEqualTo(upaccount.getOrganizationId());
        	List<CMerchCustAccountLog> logs = this.accountlogmapper.selectByExample(logex);
        	Long logid;
        	if(logs !=null && logs.size()==1){
        		 logid = logs.get(0).getId();
        	}else{
        		continue;
        	}
        	if(upaccount.getUnreceviceAmt().compareTo(unverifieAmt)>=0){
        		//收款资金大于等于应收发票未核销金额
        		verifie.setInvoiceId(invoiceId);
        		verifie.setInvoiceNo(invoice.getInvoiceNo());
        		verifie.setMerchCustId(upaccount.getMerchCusId());
        		verifie.setVerifiedId(logid);
        		verifie.setVerifieAmt(unverifieAmt);
        		verifie.setOrganizationId(upaccount.getOrganizationId());
        		verifie.setType("1");
        		verifie.setVerifieDate(new Date());
        		verifie.setYear(sf.format(new Date()));
        		this.receviemapper.insert(verifie);
        		//修改资金上账对应的核销金额和未核销金额
        		upaccount.setReceviceAmt(upaccount.getReceviceAmt().add(unverifieAmt));
        		upaccount.setUnreceviceAmt(upaccount.getUnreceviceAmt().subtract(unverifieAmt));
        		this.upaccountmapper.updateByPrimaryKeySelective(upaccount);
        		break;
        	}else{
        		//收款金额小于应收发票未核销金额
        		verifie.setInvoiceId(invoiceId);
        		verifie.setInvoiceNo(invoice.getInvoiceNo());
        		verifie.setMerchCustId(upaccount.getMerchCusId());
        		verifie.setVerifiedId(logid);
        		verifie.setVerifieAmt(upaccount.getUnreceviceAmt());
        		verifie.setOrganizationId(upaccount.getOrganizationId());
        		verifie.setType("1");
        		verifie.setVerifieDate(new Date());
        		verifie.setYear(sf.format(new Date()));
        		this.receviemapper.insert(verifie);
        		//减少应收发票未核销金额
        		unverifieAmt =unverifieAmt.subtract(upaccount.getUnreceviceAmt());
        		//更新收款记录数据
        		upaccount.setReceviceAmt(upaccount.getPayAmt());
        		upaccount.setUnreceviceAmt(BigDecimal.ZERO);
        		this.upaccountmapper.updateByPrimaryKeySelective(upaccount);
        	}
        	//推送SAP
        	SendToSap(verifie);
        }
        result.put("type", "200");
		result.put("msg", "核销处理中");
		return result;
	}
	
	@Override
	public AjaxDTO invoiceItemList(AjaxDTO bean, String invoiceNo) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        OmInvoiceItemVExample ex = new OmInvoiceItemVExample();
        OmInvoiceItemVExample.Criteria ext =ex.createCriteria();
        ext.andInvoiceNoEqualTo(invoiceNo);
        ex.setPage(page);
        List<OmInvoiceItemV> list = this.invoiceItemMapper.selectByExample(ex);
        int total = this.invoiceItemMapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
		return bean;
	}

	@Override
	public Map<String, Object> verifieByneinvoice(String invoiceNo,Long invoiceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		//获取负数发票信息
        OmInvoiceV  invoice = this.invoicevmapper.selectByPrimaryKey(invoiceId);
        OmInvoiceVExample ex2 =new OmInvoiceVExample();
        OmInvoiceVExample.Criteria ext2 =ex2.createCriteria();
		ext2.andInvoiceNoEqualTo(invoice.getWriteoffInvoice());
		List<OmInvoiceV> list2 = this.invoicevmapper.selectByExample(ex2);
		if(list2 ==null ||list2.size() ==0){
        	result.put("type", "500");
			result.put("msg", "获取应收发票失败");
			return result;
        } 
		//正数发票
		OmInvoiceV  invoice2 = list2.get(0);
		//负数发票核销
		CMerchReceiveVerifie  verifie = new CMerchReceiveVerifie();
		verifie.setInvoiceId(invoiceId);
		verifie.setInvoiceNo(invoiceNo);
		verifie.setMerchCustId(invoice.getMerchCustId());
		verifie.setVerifiedId(Long.parseLong(invoice2.getInvoiceNo()));
		verifie.setType("2");
		verifie.setVerifieDate(new Date());
		verifie.setYear(sf.format(new Date()));
		verifie.setVerifieAmt(BigDecimalASME.multiply(invoice.getTotalPrice().add(invoice.getTotalTax())));
		this.receviemapper.insert(verifie);
		
		//推送SAP
    	SendToSap(verifie);
    	
		//获取负数发票对应的正数发票已核销信息
		CMerchReceiveVerifieExample  ex = new CMerchReceiveVerifieExample();
		CMerchReceiveVerifieExample.Criteria ext = ex.createCriteria();
		ext.andInvoiceIdEqualTo(invoice2.getId());
		ext.andInvoiceNoEqualTo(invoice2.getInvoiceNo());
		ext.andTypeEqualTo("1");
		List<CMerchReceiveVerifie> receives = this.receviemapper.selectByExample(ex);
		
		if(receives !=null && receives.size() >0){			
			verifieFallBack(invoice,receives);
		}
		CMerchReceiveVerifie  verifie2 = new CMerchReceiveVerifie();
		verifie2.setInvoiceId(invoice2.getId());
		verifie2.setInvoiceNo(invoice2.getInvoiceNo());
		verifie2.setMerchCustId(invoice2.getMerchCustId());
		verifie2.setVerifiedId(Long.parseLong(invoice.getInvoiceNo()));
		verifie2.setType("2");
		verifie2.setVerifieDate(new Date());
		verifie2.setYear(sf.format(new Date()));
		verifie2.setVerifieAmt(BigDecimalASME.multiply(invoice.getTotalPrice().add(invoice.getTotalTax())).abs());
		this.receviemapper.insert(verifie2);
		
		//推送SAP
    	SendToSap(verifie);
    	
		result.put("type", "200");
		result.put("msg", "核销成功");
		return result;
	}
	public void verifieFallBack(OmInvoiceV  invoice,List<CMerchReceiveVerifie> receives){
		//负数发票金额绝对值
		BigDecimal amt = BigDecimalASME.multiply(invoice.getTotalPrice().add(invoice.getTotalTax()).abs());
		for (CMerchReceiveVerifie receive :receives){
			if(receive.getVerifieAmt().compareTo(amt) ==0){
				//核销金额等于负数发票金额，获取应收发票数据
				upAccount(receive,receive.getVerifieAmt(),invoice);
				break;
			}else if(receive.getVerifieAmt().compareTo(amt) <0){
				//核销金额小于负数发票金额
				upAccount(receive,receive.getVerifieAmt(),invoice);
				amt = amt.subtract(receive.getVerifieAmt());
			}else{
				//核销金额大于负数发票金额
				upAccount(receive,amt,invoice);
				break;
			}
		}
	}
	public void upAccount(CMerchReceiveVerifie receive,BigDecimal amt,OmInvoiceV  invoice){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		CMerchUpaccount  upaccount = this.upaccountmapper.selectByPrimaryKey(receive.getVerifiedId());
		upaccount.setReceviceAmt(upaccount.getReceviceAmt().subtract(amt));
		upaccount.setUnreceviceAmt(upaccount.getUnreceviceAmt().add(amt));
		this.upaccountmapper.updateByPrimaryKeySelective(upaccount);
		receive.setId(null);
		receive.setVerifieAmt(amt.multiply(new BigDecimal(-1)));
		receive.setType("3");
		receive.setOrderId(invoice.getId());
		receive.setVerifieDate(new Date());
		receive.setYear(sf.format(new Date()));
		this.receviemapper.insert(receive);
		//推送SAP
    	SendToSap(receive);
	}

	@Override
	public AjaxDTO verifieList(AjaxDTO bean, Long invoiceId, String invoiceNo) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit() + bean.getOffset());
		params.put("invoiceId", invoiceId);
		params.put("invoiceNo", invoiceNo);
		List<CMerchReceiveVerifie> list = this.mapper.getVerifieList(params);
		int total = this.mapper.countVerifieList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Map<String, Object> writeOff(Long invoiceId, String invoiceNo)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		params.put("invoiceId", invoiceId);
		params.put("invoiceNo", invoiceNo);
		List<CMerchReceiveVerifie> list = this.mapper.getReceiveVerifie(params);
		if(list ==null || list.size()==0){
			result.put("type", "500");
			result.put("msg", "没有查询到核销记录");
			return result;
		}
		for(CMerchReceiveVerifie verifie :list){
			CMerchUpaccount  upaccount = this.upaccountmapper.selectByPrimaryKey(verifie.getVerifiedId());
			upaccount.setReceviceAmt(upaccount.getReceviceAmt().subtract(verifie.getVerifieAmt()));
			upaccount.setUnreceviceAmt(upaccount.getUnreceviceAmt().add(verifie.getVerifieAmt()));
			this.upaccountmapper.updateByPrimaryKeySelective(upaccount);
			verifie.setId(null);
			verifie.setVerifieAmt(verifie.getVerifieAmt().multiply(new BigDecimal(-1)));
			verifie.setType("4");
			verifie.setInvoiceId(invoiceId);
			verifie.setInvoiceNo(invoiceNo);
			verifie.setVerifieDate(new Date());
			verifie.setYear(sf.format(new Date()));
			this.receviemapper.insert(verifie);
			//推送SAP
	    	SendToSap(verifie);
		}
		result.put("type", "200");
		result.put("msg", "红冲成功");
		return result;
	}

	@Override
	public String verifieAll(String p_org_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_org_id", p_org_id);
		this.mapper.verifieAll(map);
		return map.get("p_result").toString();
	}
	
	
	/**
	 * 推送SAP
	 * 
	 * @param accountlog资金上账日记记录
	 * @param upaccount
	 *            资金上账信息
	 * @return int
	 */
	public Map<String, Object> SendToSap(CMerchReceiveVerifie verifie){
		Map<String, Object> result = new HashMap<String, Object>();
		InputDTO input = writeoffrfc.constructInputParam(verifie.getId());
		logger.info("提交核销异步处理");
		writeoffrfc.executeInThread(input, AccountReceiveCallback.class.getSimpleName());
		result.put("type", "200");
		result.put("msg", "核销处理中");
		return result;
	}
}
