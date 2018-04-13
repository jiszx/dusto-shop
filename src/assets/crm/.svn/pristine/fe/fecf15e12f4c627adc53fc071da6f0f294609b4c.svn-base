package com.hhnz.jco;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.customer.CustomerRFC;
import com.hhnz.jco.business.customer.InputDTO;
import com.hhnz.jco.business.customer.InputDTO.Customer;
import com.hhnz.jco.business.customer.InputDTO.UnloadingPort;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRFCTest {
	private static final Log LOG = LogFactory.getLog(CustomerRFCTest.class);
	@Autowired
	private CustomerRFC customerRFC;
	@Autowired
	private ICustomerService customerService; 
	@Autowired
	private IorganizationService organizationService;
	
	@Test
	public void testExecute(){
		Long id = 61l;
		//处理SAP传入参数
		InputDTO input = customerRFC.constructInputParam(id);
		//把传入参数信息放入缓存等待执行
		customerRFC.storeInCache(new RfcRedoDto(JsonUtil.toJSON(input), RfcExeType.CUSTOMER));
	}
	
	@Ignore
	@Test
	public void testExecuteWithParam(){
		String[] codes = {  
							"1100"/*,
							"1320",
							"1340",
							"1350",
							"1410",
							"1420"*/

		};
		String[] os = {
						"00"/*,
						"40"*/
					  };
		int i = 17;
		for (String code : codes) {
			for (String o : os) {
				InputDTO input = new InputDTO();
				
				//		CMerchCustBase custBase = customerService.findCustBaseById(58l);
				CMerchCustBase custBase = new CMerchCustBase();
				i++;
				custBase.setId(Long.valueOf(i));
				custBase.setName("测试经销商"+i);
				custBase.setAbbrName("csjxs");
				custBase.setOrganizationId("T0101");
				List<CMerchCustDistribution> dists = new ArrayList<CMerchCustDistribution>();
				CMerchCustDistribution port1 = new CMerchCustDistribution();
				port1.setSite("到站"+i);
				CMerchCustDistribution port2 = new CMerchCustDistribution();
				port2.setSite("到站"+i+i);
				dists.add(port1);
				dists.add(port2);
				custBase.setDistributions(dists);
				//		custBase.setInvoiceTaxNum("45465444444234");
				//		custBase.setBusinessLicense("1212344534534");
				
				Customer c = input.new Customer();
				c.setId(custBase.getId().toString());
				c.setName(custBase.getName());
				c.setAbbrName(custBase.getAbbrName());
				c.setAddedTaxNo(custBase.getInvoiceTaxNum());
//				c.setTaxNo5(custBase.getInvoiceTaxNum());
				CrmSalesOrganization org = organizationService.findById(custBase.getOrganizationId());
//				String sapId = null;
				if(org != null){
//					sapId = org.getSapId();
					CrmSalesOrganization g = organizationService.findById(org.getPid());
					if(g != null){
						if(g.getName().indexOf("盐") != -1){
							//盐
							c.setProductGroup("00");
						}
						else if(g.getName().indexOf("调") != -1){
							//调味品
							c.setProductGroup("40");
						}
						else{
							//其他
							c.setProductGroup("90");
						}
					}
				}
				//渠道如果没有默认分销渠道"10"
				c.setChannel(StringUtils.isBlank(custBase.getChannelId())?"10":custBase.getChannelId());
				c.setProductGroup(o);
				c.setSalesOrg(code);
				c.setFax("");
				c.setTel(custBase.getTel());
				
				//TODO company
				//公司
				c.setCompanyCode(code);
				//销售区域
				//c.setSalesAreaCode("Y0002");
				//销售部门
				//c.setSalesDepartCode("Y300");
				//TODO 工厂
				c.setFactory("");
				//TODO 库存
				c.setVwLocation("");
				/*
				 * 账户组
				 * 售达方Z001,送达方Z002
				 * 售达方可以作为送达方使用，但当客户地址与送达方地址不一致时，需调用两次接口分别传输售达方与送达方
				 */
				c.setAccountGroup("Z001");
				input.setCustomer(c);
				
				//no banks currently
				/*List<CustomerBank> banks = new ArrayList<InputDTO.CustomerBank>();
				CustomerBank b1 = input.new CustomerBank();
				b1.setAccountName("michael");
				b1.setAccountNo("622123222324344");
				b1.setBankNo("zggsyh");
				CustomerBank b2 = input.new CustomerBank();
				b2.setAccountName("michael2");
				b2.setAccountNo("620123222324342");
				b2.setBankNo("zgjsyh");
				banks.add(b1);
				banks.add(b2);
				input.setBanks(banks);*/
				
				//到站信息
				List<UnloadingPort> ports = new ArrayList<InputDTO.UnloadingPort>();
				for (CMerchCustDistribution dis : custBase.getDistributions()) {
					UnloadingPort port = input.new UnloadingPort();
					port.setName(dis.getSite());
					ports.add(port);
				}
				input.setPorts(ports);
				BaseResultDTO r = customerRFC.execute(input);
				if(r.getResult().getTYPE().equals(RFCConstants.ERROR_FLAG)){
					LOG.info("=============="+code+"========="+o);
				}
				LOG.info(r.getResult().getMESSAGE());
				LOG.info(r.getData());
			}
		}
	}
}
