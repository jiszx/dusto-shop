package com.hhnz.customer.service.impl;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.primitives.Ints;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IDictService;
import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.dto.CustomerBaseExport;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustBaseVMapper;
import com.hhnz.customer.mapper.CMerchCustContractVMapper;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.mapper.CMerchCustProductVMapper;
import com.hhnz.customer.mapper.CMerchCustRetailMapper;
import com.hhnz.customer.mapper.CMerchCustStationMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.customer.model.CMerchCustBaseExample.Criteria;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustBaseVExample;
import com.hhnz.customer.model.CMerchCustBaseVExample.Criterion;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.model.CMerchCustDistributionExample;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.model.CMerchCustProductExample;
import com.hhnz.customer.model.CMerchCustProductV;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.customer.model.CMerchCustStationExample;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.model.TAreaExample;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.Excels;
import com.hhnz.util.Files;
import com.hhnz.util.Params;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	protected static final Log logger = LogFactory.getLog(CustomerServiceImpl.class);
  @Resource
  private CMerchCustBaseMapper customerBaseMapper;
  @Resource
  private CMerchCustProductMapper productMapper;
  @Resource
  private CMerchCustProductVMapper productVMapper;
  @Resource
  private CMerchCustDistributionMapper distributionMapper;
  @Resource
  private CMerchCustBaseVMapper merchCustBaseVMapper;
  @Resource
  private CMerchCustStationMapper merchCustStationMapper;
  @Autowired
  private CrmSalesOrganizationMapper saleOrgMapper;
  @Autowired
  private CMerchCustAccountMapper accountmapper;
  @Autowired
  private CMerchCustBalancesMapper balanceMapper;
  @Autowired
  private CMerchCustContractVMapper contractVMapper;
  @Resource
  private CMerchCustRetailMapper  retailMapper;
  @Resource
  private IDictService dictService;
  @Autowired
  private TAreaMapper areaMapper;
  @Value("${upload.file.path}")
  private String basePath;

	@Override
	public CMerchCustBase add(CMerchCustBase custBase){
		// 插入客户主数据
		customerBaseMapper.insert(custBase);
		Long merchCustId = custBase.getId();
		// 插入送达方信息
		List<CMerchCustDistribution> distributions = custBase.getDistributions();
		if(distributions != null && distributions.size()>0){
			for (CMerchCustDistribution distribution : distributions) {
				distribution.setCreateOid(custBase.getCreateOid());
				distribution.setCreateTs(custBase.getCreateTs());
				distribution.setUpdateOid(custBase.getUpdateOid());
				distribution.setUpdateTs(custBase.getUpdateTs());
				distribution.setOrganizationId(custBase.getOrganizationId());
				distribution.setMerchCustId(merchCustId);
				distributionMapper.insertSelective(distribution);
			}
		}
		//插入客户对应岗位数据
		CMerchCustStation custStation = custBase.getCustStation();
		if(custStation!=null){
		  custStation.setMerchCustId(merchCustId);
	      this.setPosition(custStation);
		}
		return custBase;
	}

	@Override
	public void update(CMerchCustBase custBase, String delDists) {
		// 更改客户主数据
		customerBaseMapper.updateByPrimaryKeySelective(custBase);
		Long merchCustId = custBase.getId();
		// 处理送达方信息
		//删除需要删除的送达方信息
		if(StringUtils.isNotBlank(delDists)){
			String[] delDistArray = delDists.split(",");
			for (String delDist : delDistArray) {
				Long distId = null;
				try {
					distId = Long.valueOf(delDist);
				} catch (NumberFormatException e) {
					continue;
				}
				CMerchCustDistribution custDist = distributionMapper.selectByPrimaryKey(distId);
				if(custDist == null || !Long.valueOf(custDist.getMerchCustId()).equals(merchCustId)){
					continue;
				}
				distributionMapper.deleteByPrimaryKey(distId);
			}
		}
		//新增或修改送达方信息
		List<CMerchCustDistribution> distributions = custBase.getDistributions();
		if(distributions != null && distributions.size()>0){
			for (CMerchCustDistribution distribution : distributions) {
				distribution.setUpdateOid(custBase.getUpdateOid());
				distribution.setUpdateTs(custBase.getUpdateTs());
				if(distribution.getId() != null){
					CMerchCustDistribution existedOne = distributionMapper.selectByPrimaryKey(distribution.getId());
					if(existedOne == null || !existedOne.getMerchCustId().equals(merchCustId)){
						continue;
					}
					distribution.setCreateOid(existedOne.getCreateOid());
					distribution.setCreateTs(existedOne.getCreateTs());
					distribution.setMerchCustId(existedOne.getMerchCustId());
					distribution.setOrganizationId(existedOne.getOrganizationId());
					distributionMapper.updateByPrimaryKeySelective(distribution);
				}else{
					distribution.setCreateOid(custBase.getCreateOid());
					distribution.setCreateTs(custBase.getCreateTs());
					distribution.setMerchCustId(merchCustId);
					distribution.setOrganizationId(custBase.getOrganizationId());
					distributionMapper.insertSelective(distribution);
				}
			}
		}
	}

	@Override
	public List<CMerchCustBase> findCustsByEmployee(Long empId) {
		if (empId == null) {
			return null;
		}
		return customerBaseMapper.findCustsByEmployee(empId);
	}
	
	@Override
	public int importRetail(String[][] text, TEmployee user, Long pid, UserStations station) {
	    int result = 0;
	    if (text == null) {
	      return 0;
	    }
	    int startRow = 0;
	    int startCol = 0;
	    loop: for (int i = 0; i < text.length; i++) {
	      for (int j = 0; j < text[i].length; j++) {
	        if (StringUtils.isNotEmpty(text[i][j])) {
	          startRow = i+1;
	          startCol = j;
	          break loop;
	        }
	      }
	    }
	    
	    CMerchCustBase distributor = null;
	    if(pid!=null){
	      distributor = customerBaseMapper.selectByPrimaryKey(pid);
	    }
	    List<TArea> areas = areaMapper.selectByExample(new TAreaExample());
	    List<CMerchCustBase> custs = new ArrayList<>();
	    
	    for(int i=startRow;i<text.length;i++){
	      CMerchCustBase cust = transfer(text[i], startCol, user, distributor, areas);
	      if(cust==null){
	        return -2;
	      }
	      String validateStr = validateBase(cust);
	      if(validateStr.length()>0){
	        return -1;
	      }
	      custs.add(cust);
	    }
	    for(CMerchCustBase cust:custs){
	      customerBaseMapper.insert(cust);
          
          addAccountAndBalance(cust);
          
          if(station!=null && station.getStationid()!=null){
            CMerchCustStation custStation = new CMerchCustStation();
            custStation.setUpdateOid(user.getId());
            custStation.setUpdateTs(new Date());
            custStation.setCreateOid(user.getId());
            custStation.setCreateTs(new Date());
            custStation.setStates("1");
            custStation.setStationId(station.getStationid());
            custStation.setMerchCustId(cust.getId());
            setPosition(custStation);
          }
          
          result += 1;
	    }

	    return result;
	}
	
	private String validateBase(CMerchCustBase cust){
	  StringBuilder builder = new StringBuilder();
	  if(StringUtils.isEmpty(cust.getName())){
	    builder.append("客户名缺失");
	  }
	  if(StringUtils.isEmpty(cust.getContactName())){
	    builder.append("联系人缺失");
	  }
	  if(StringUtils.isEmpty(cust.getContactTel())){
	    builder.append("联系电话缺失");
	  }
	  if(StringUtils.isEmpty(cust.getTel())){
	    builder.append("财务联系电话缺失");
	  }
	  if(StringUtils.isEmpty(cust.getBusinessLicense())){
	    builder.append("营业执照号缺失");
	  }
	  if(StringUtils.isEmpty(cust.getInvoiceTaxNum())){
	    builder.append("税号缺失");
	  }
	  
	  return builder.toString();
	}
	
	private int addAccountAndBalance(CMerchCustBase merch){
	  if(!"3".equals(merch.getStates())){
	    return 0;
	  }
	  int result = 0;
	  CMerchCustAccountExample ex = new CMerchCustAccountExample();
      ex.createCriteria().andMerchCustIdEqualTo(merch.getId()).andOrganizationIdEqualTo(merch.getOrganizationId());
      List<CMerchCustAccount> accounts = accountmapper.selectByExample(ex);
      if(accounts.isEmpty()){
        CMerchCustAccount  account  = new CMerchCustAccount();
        account.setOrganizationId(merch.getOrganizationId());
        account.setSubsidyAmt(new BigDecimal(Double.valueOf(0)));
        account.setCashAmt(new BigDecimal(Double.valueOf(0)));
        account.setBondAmt(new BigDecimal(Double.valueOf(0)));
        account.setCreditAmt(new BigDecimal(Double.valueOf(0)));
        account.setMerchCustId(merch.getId());
        account.setContractCreditAmt(BigDecimal.ZERO);
        result = accountmapper.insert(account);
      }
      
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
      CMerchCustBalancesExample balanceex = new CMerchCustBalancesExample();
      CMerchCustBalancesExample.Criteria bext = balanceex.createCriteria();
      bext.andMerchCustIdEqualTo(merch.getId());
      bext.andOrganizationIdEqualTo(merch.getOrganizationId());
      bext.andPeriodEqualTo(sf.format(new Date()));
      bext.andAccountTypeEqualTo("1");// 现金账户
      List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceex);
      if(balances.isEmpty()){
        //添加科目余额表
        CMerchCustBalances cb = new CMerchCustBalances();
        cb.setMerchCustId(merch.getId());
        cb.setOrganizationId(merch.getOrganizationId());
        cb.setcAmt(new BigDecimal(Double.valueOf(0)));
        cb.setdAmt(new BigDecimal(Double.valueOf(0)));
        cb.setPtd(new BigDecimal(Double.valueOf(0)));
        cb.setYtd(new BigDecimal(Double.valueOf(0)));
        cb.setAccountType("1");//现金账户
        cb.setPeriod(sf.format(new Date()));
        result = Ints.max(result, balanceMapper.insert(cb));
      }
      return result;
	}
	
	public CMerchCustBase transfer(String[] text, int startCol, TEmployee user, CMerchCustBase distributor, List<TArea> areas){
	  String pid = getValue(text, startCol+14);
	  if(!StringUtils.isEmpty(pid)){
	    CMerchCustBase pcust = customerBaseMapper.selectByPrimaryKey(Long.parseLong(pid));
	    if(pcust==null){
	      return null;
	    }
	    distributor = pcust;
	  }
	  CMerchCustBase cust = new CMerchCustBase();
	  if(StringUtils.isEmpty(text[startCol])){
	    cust.setCreateTs(new Date());
	  }else{
	    cust.setCreateTs(DateUtil.parse(text[startCol], "yyyy/MM/dd"));
	  }
	  cust.setAbbrName(text[startCol+1]);
	  cust.setName(text[startCol+2]);
	  cust.setSapCustomerId(text[startCol+3]);
	  cust.setChannelId(getChannelType(text[startCol+4]));
	  cust.setOpeningType(getWebsite(text[startCol+5]));
	  
	  cust.setAddress(text[startCol+9]);
	  cust.setContactName(text[startCol+10]);
	  cust.setContactTel(text[startCol+11]);
	  cust.setLpName(text[startCol+12]);
	  cust.setTel(text[startCol+13]);
	  cust.setIsInvoice(getInvoiceType(getValue(text, startCol+16)));
	  cust.setInvoiceName(cust.getName());
	  cust.setInvoiceTaxNum(getValue(text, startCol+17));
	  cust.setInvoiceAddress(getValue(text, startCol+18));
	  cust.setInvoiceTel(getValue(text, startCol+19));
	  cust.setInvoiceBankName(getValue(text, startCol+20));
	  cust.setInvoiceAccount(getValue(text, startCol+21));
	  cust.setBusinessLicense(getValue(text, startCol+22));
	  cust.setStates(StringUtils.isBlank(cust.getSapCustomerId()) ? "1" : "3");
	  cust.setCustType("5");
	  cust.setOrganizationId(distributor.getOrganizationId());
	  cust.setIsAttachment("0");
	  cust.setCreateOid(user.getId());
	  cust.setPid(distributor.getId());
	  
	  Region region = getRegion(text[startCol+6], text[startCol+7], text[startCol+8], areas);
	  cust.setProvId(region.getProv());
	  cust.setProvName(region.getProvName());
	  cust.setCityId(region.getCity());
	  cust.setCityName(region.getCityName());
	  cust.setCountyId(region.getCountry());
	  cust.setCountyName(region.getCountryName());
	  return cust;
	}
	
    Region getRegion(String prov, String city, String country, List<TArea> areas) {
      prov = prov == null ? null : prov.trim();
      city = city == null ? null : city.trim();
      country = country == null ? null : country.trim();
      Region region = new Region();
      TArea pr = getArea(prov, "1", null, areas);
      if(pr!=null){
        region.setProv(pr.getId());
        region.setProvName(pr.getName());
      }
      TArea ci = getArea(city, "2", region.getProv(), areas);
      if(ci!=null){
        region.setCity(ci.getId());
        region.setCityName(ci.getName());
      }
      TArea co = getArea(country, "3", region.getCity(), areas);
      if(co!=null){
        region.setCountry(co.getId());
        region.setCountryName(co.getName());
      }
      return region;
    }
    
    private TArea getArea(String name, String level, String pid, List<TArea> areas){
      if(name==null){
        return null;
      }
      for(int i=name.length();i>=2;i--){
        List<TArea> matchArea = getAreaLike(name.substring(0, i), level, pid, areas);
        if(!matchArea.isEmpty()){
          return matchArea.get(0);
        }
      }
      return null;
    }
    
    
    private List<TArea> getAreaLike(String name, String level, String pid, List<TArea> areas){
      List<TArea> result = new ArrayList<>();
      for(TArea area:areas){
        if(area.getAreaLevel().equals(level) && area.getName().startsWith(name)){
          if("1".equals(level) || area.getPid().equals(pid)){
            result.add(area);
          }
        }
      }
      return result;
    }
	
    static class Region {
      private String prov;
      private String provName;
      private String city;
      private String cityName;
      private String country;
      private String countryName;
  
      public String getProv() {
        return prov;
      }
  
      public void setProv(String prov) {
        this.prov = prov;
      }
  
      public String getCity() {
        return city;
      }
  
      public void setCity(String city) {
        this.city = city;
      }
  
      public String getCountry() {
        return country;
      }
  
      public void setCountry(String country) {
        this.country = country;
      }

      public String getProvName() {
        return provName;
      }

      public void setProvName(String provName) {
        this.provName = provName;
      }

      public String getCityName() {
        return cityName;
      }

      public void setCityName(String cityName) {
        this.cityName = cityName;
      }

      public String getCountryName() {
        return countryName;
      }

      public void setCountryName(String countryName) {
        this.countryName = countryName;
      }
    }
	
	private String getValue(String[] text, int index){
	  if(text==null){
	    return "";
	  }
	  if(index>=text.length){
	    return "";
	  }
	  return text[index];
	}
	
	private String getWebsite(String name){
	  if(StringUtils.equalsIgnoreCase(name, "小型网点")){
        return "1";
      }
	  if(StringUtils.equalsIgnoreCase(name, "中型网点")){
        return "2";
      }
	  if(StringUtils.equalsIgnoreCase(name, "大型网点")){
        return "3";
      }
	  return "1";
	}
	
	private String getChannelType(String typeName){
	  if(StringUtils.equalsIgnoreCase(typeName, "餐饮")){
	    return "1";
	  }
	  if(StringUtils.equalsIgnoreCase(typeName, "娱乐")){
        return "2";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "工厂")){
        return "3";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "其他")){
        return "4";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "特渠")){
        return "5";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "传统渠道")){
        return "6";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "现代渠道")){
        return "7";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "农贸市场")){
        return "8";
      }
	  if(StringUtils.equalsIgnoreCase(typeName, "机构食堂")){
        return "9";
      }
	  return "1";
	}
	
	private String getInvoiceType(String name){
	  if(StringUtils.equalsIgnoreCase("不开票", name)){
	    return "1";
	  }
	  if(StringUtils.equalsIgnoreCase("开普票", name)){
	    return "2";
	  }
	  if(StringUtils.equalsIgnoreCase("开专票", name)){
	    return "3";
	  }
	  return "1";
	}

	@Override
	public AjaxDTO findCustSkuPrice(AjaxDTO bean, CMerchCustProductV cust)
			throws RuntimeException {
	    Map<String,Object> params = new HashMap<String, Object>();
	    params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit()+1);
		params.put("brand", cust.getBrand());
	    params.put("category", cust.getCategory());
	    params.put("series", cust.getSeries());
	    params.put("iPackage", cust.getiPackage());
	    params.put("orgId", cust.getOrganizationId());
	    params.put("materialId", cust.getMaterialId());
	    params.put("custname", cust.getCustname());
	    params.put("custType", cust.getCustType());
	    List<CMerchCustProductV> list = productVMapper.selectMerchProduct(params);
	    int total = productVMapper.countMerchProduct(params);
	    bean.setTotal(total);
	    bean.setRows(list);
	    return bean;
	}
	
	public void realAmt(List<CMerchCustProductV> products){
	  if(products==null){
	    return;
	  }
	  for(CMerchCustProductV product:products){
	    product.setBasePrice(product.getBasePrice()==null?BigDecimal.ZERO:BigDecimalASME.divide(product.getBasePrice()));
	    product.sethPrice(product.gethPrice()==null?BigDecimal.ZERO:BigDecimalASME.divide(product.gethPrice()));
	    product.setPrice(product.getPrice()==null?BigDecimal.ZERO:BigDecimalASME.divide(product.getPrice()));
	  }
	}
	
	@Override
	public CMerchCustBaseV findCustWithPosition(Long merchid){
	  CMerchCustBaseVExample example = new CMerchCustBaseVExample();
	  CMerchCustBaseVExample.Criteria c = example.createCriteria();
	  c.andIdEqualTo(merchid);
	  List<CMerchCustBaseV> merchs = merchCustBaseVMapper.selectByExample(example);
	  if(merchs.isEmpty()){
	    return null;
	  }
	  return merchs.get(0);
	}

	@Override
	public AjaxDTO findCustBase(CustomerBaseDTO customerBasedto, AjaxDTO bean, List<Long> stations) {
		Page page = new Page();
		CMerchCustBaseVExample example = new CMerchCustBaseVExample();
		if(bean.getLimit()>0){
		  page.setLimit(bean.getLimit());
		  page.setOffset(bean.getOffset());
		  example.setPage(page);
		}
		example.setOrderByClause("create_ts desc");
		CMerchCustBaseVExample.Criteria c = example.createCriteria();
		if(StringUtils.isNotBlank(customerBasedto.getBizProvId())){
			c.andSalesProvIdEqualTo(customerBasedto.getBizProvId());
		}
		if(StringUtils.isNotBlank(customerBasedto.getCustomerName())){
			c.andNameLike("%"+customerBasedto.getCustomerName()+"%");
		}
		if(StringUtils.isNotBlank(customerBasedto.getCustType())){
		  c.andCustTypeIn(Arrays.asList(customerBasedto.getCustType().split(",")));
		}else{
		  c.getCriteria().add(new Criterion("(CUST_TYPE!='5' or CUST_TYPE is NULL)"));
		}
		if(StringUtils.isNotBlank(customerBasedto.getPosition())){
			c.andPositionIdEqualTo(Long.valueOf(customerBasedto.getPosition()));
		}
		if(customerBasedto.getId() != null){
			c.andIdEqualTo(customerBasedto.getId());
		}
		if(StringUtils.isNotBlank(customerBasedto.getBusinessLicense())){
			c.andBusinessLicenseEqualTo(customerBasedto.getBusinessLicense());
		}
		if(StringUtils.isNotBlank(customerBasedto.getSales())){
			c.andSalesmanLike("%"+customerBasedto.getSales()+"%");
		}
		if(StringUtils.isNotBlank(customerBasedto.getSalesArea())){
			c.andSalesAreaIdEqualTo(customerBasedto.getSalesArea());
		}
		if(StringUtils.isNotBlank(customerBasedto.getSalesOrg())){
//			c.andSalesOrgIdEqualTo(customerBasedto.getSalesOrg());//销售组织可包含查询下属组织
			c.andSalesOrgIdLike(customerBasedto.getSalesOrg()+"%");
		}
		if(StringUtils.isNotBlank(customerBasedto.getStatus())){
			c.andStatesEqualTo(customerBasedto.getStatus());
		}
		if(stations!=null && !stations.isEmpty()){
		  c.andPositionIdIn(stations);
		}
		if(StringUtils.isNotBlank(customerBasedto.getSapCustomerId())){
			c.andSapCustomerIdEqualTo(customerBasedto.getSapCustomerId());
		}
		if(StringUtils.isNotEmpty(customerBasedto.getCode())){
		  c.addCriterion("code like ", "%"+customerBasedto.getCode()+"%", "code");
		}
		if(StringUtils.isNotBlank(customerBasedto.getPid())){
			c.addCriterion("PID =", customerBasedto.getPid(), "pid");
		}else if(customerBasedto.getPids() != null && customerBasedto.getPids().size() > 0){
			c.addCriterion("PID in", customerBasedto.getPids(), "pid");
		}
		if(StringUtils.isNoneEmpty(customerBasedto.getSearch())){
		  String search = customerBasedto.getSearch();
		  if(search.matches("[0-9]*")){
		    c.andSapCustomerIdLike("%"+search+"%");
		  }else{
		    c.andNameLike("%"+search+"%");
		  }
		}
		if("1".equals(customerBasedto.getHasContract())){
		  c.getCriteria().add(new Criterion("EXISTS ( SELECT 1 FROM C_MERCH_CUST_CONTRACT cmcc WHERE  CMCC.merch_cust_id = C_MERCH_CUST_BASE_V.ID AND CMCC.states = '4')"));
		}
		
		List<CMerchCustBaseV> list = merchCustBaseVMapper.selectByExample(example);
		int total = merchCustBaseVMapper.countByExample(example);
		bean.setTotal(total);
	    bean.setRows(list);
		return bean;
	}
  
	@Override
	public List<CMerchCustBase> findCustBaseByStationId(List<Long> stationIds){
		return customerBaseMapper.findCustBaseByStationId(stationIds);
	}
	
	@Override
	public AjaxDTO findCustBaseVague(CustomerBaseDTO customerBasedto, AjaxDTO bean, String[] areaList,
			String name, String[] custTypeList,String[] statusList, List<Long> stations) {
		Page page = new Page();
	    page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
	    int begin = bean.getOffset();
	    int end = bean.getLimit()+bean.getOffset();
		Map<String, Object> params = new HashMap<String,Object>();
	    params.put("customerBasedto", customerBasedto);
	    params.put("begin",begin);
	    params.put("end",end);
	    params.put("name", name);
	    params.put("areaList",areaList);
	    params.put("custTypeList",custTypeList);
	    params.put("statusList", statusList);
	    if(stations!=null && !stations.isEmpty()){
	      params.put("stations", stations);
	    }
	    
		List<CMerchCustBaseV> list = merchCustBaseVMapper.findCustBaseVague(params);
//		int total = merchCustBaseVMapper.countCustBaseVague(params);
//		bean.setTotal(total);
		bean.setTotal(list.size());
	    bean.setRows(list);
		return bean;
		
	}
	
	
  @Override
  public int editSkuPrice(CMerchCustProduct product){
    CMerchCustProduct record = productMapper.selectByPrimaryKey(product.getId());
    if(product.gethPrice()!=null){
      record.sethPrice(BigDecimalASME.multiply(product.gethPrice()));
    }
    if(product.getCifPrice()!=null){
      record.setCifPrice(BigDecimalASME.multiply(product.getCifPrice()));
    }
    return productMapper.updateByPrimaryKey(record);
  }
  
  @Override
  public AjaxDTO findRetails(CustomerBaseDTO customerBasedto, AjaxDTO bean, Long merchid){
    Page page = new Page();
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    CMerchCustBaseExample ex = new CMerchCustBaseExample();
    Criteria param = ex.createCriteria();
    param.andPidEqualTo(merchid);
    if(StringUtils.isNotEmpty(customerBasedto.getStatus())){
      param.andStatesEqualTo(customerBasedto.getStatus());
    }
    ex.setPage(page);
    List<CMerchCustBase> custs = customerBaseMapper.selectByExample(ex);
    int count = customerBaseMapper.countByExample(ex);
    List<Long> custIds = new ArrayList<>();
    for(CMerchCustBase base:custs){
      custIds.add(base.getId());
    }
    
    CMerchCustBaseVExample exa = new CMerchCustBaseVExample();
    exa.or().andIdIn(custIds);
    List<CMerchCustBaseV> result = merchCustBaseVMapper.selectByExample(exa);
    
    bean.setRows(result);
    bean.setTotal(count);
    return bean;
  }

	@Override
	public CMerchCustBase findCustBaseById(Long id) {
		//查找客户基本信息
		CMerchCustBase custBase = customerBaseMapper.selectByPrimaryKey(id);
		if(custBase == null){
			return null;
		}
		//查找客户送达方
		CMerchCustDistributionExample disEx = new CMerchCustDistributionExample();
		disEx.createCriteria().andMerchCustIdEqualTo(custBase.getId());
		List<CMerchCustDistribution> distributions = distributionMapper.selectByExample(disEx);
		custBase.setDistributions(distributions);
		//查找客户岗位
		CMerchCustStationExample ex = new CMerchCustStationExample();
		ex.createCriteria().andMerchCustIdEqualTo(custBase.getId());
		List<CMerchCustStation> custStations = merchCustStationMapper.selectByExample(ex);
		if(custStations != null && custStations.size()>0){
			custBase.setCustStation(custStations.get(0));
		}
		//查询对应的，销售组织名称、销售地区名称 、销售省份名称 
		CrmSalesOrganization saleOrg = saleOrgMapper.selectByPrimaryKey(custBase.getOrganizationId());
		if(saleOrg!=null){
			custBase.setSaleOrgName(saleOrg.getName());
		}
//		TArea saleArea = areaMapper.selectByPrimaryKey();
		
		return custBase;
	}
	
	@Override
	public void delete(Long id) {
		CMerchCustBase custBase = this.findCustBaseById(id);
		//删除送达方信息
		List<CMerchCustDistribution> dists = custBase.getDistributions();
		if(dists != null && dists.size()>0){
			for (CMerchCustDistribution delDist : dists) {
				distributionMapper.deleteByPrimaryKey(delDist.getId());
			}
		}
		//删除客户岗位对应信息
		CMerchCustStationExample ex = new CMerchCustStationExample();
		ex.createCriteria().andMerchCustIdEqualTo(id);
		merchCustStationMapper.deleteByExample(ex);
		//删除客户主信息
		customerBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CMerchCustBase save(CMerchCustBase custBase) {
		if(null != custBase.getId()){
			customerBaseMapper.updateByPrimaryKeySelective(custBase);
		}else{
			customerBaseMapper.insertSelective(custBase);
		}
		return custBase;
	}

	@Override
	public boolean isLicenseNoExisted(String taxNo, Long id) {
		CMerchCustBaseExample ex = new CMerchCustBaseExample();
		ex.createCriteria().andBusinessLicenseEqualTo(taxNo);
		List<CMerchCustBase> custBase = customerBaseMapper.selectByExample(ex);
		if(custBase == null || custBase.size() == 0){
			return false;
		}else if(id != null){
			for (CMerchCustBase cb : custBase) {
				if(id.equals(cb.getId())){
					return false;
				};
			}
			return true;
		}else{
			return true;
		}
	}

	@Override
	public void setPosition(CMerchCustStation custStation) {
		CMerchCustStationExample ex = new CMerchCustStationExample();
		ex.createCriteria().andMerchCustIdEqualTo(custStation.getMerchCustId());
		List<CMerchCustStation> custStations = merchCustStationMapper.selectByExample(ex);
		if(custStations == null || custStations.size() == 0){
			merchCustStationMapper.insertSelective(custStation);
		}else{
			/*
			 *由于处于不同岗位的客户会生成两条客户主数据，因此客户主数据ID与客户岗位数据ID为多对一对应 
			 */
			CMerchCustStation existedOne = custStations.get(0);
			custStation.setCreateOid(existedOne.getCreateOid());
			custStation.setCreateTs(existedOne.getCreateTs());
			custStation.setId(existedOne.getId());
			merchCustStationMapper.updateByPrimaryKeySelective(custStation);
		}
	}

	@Override
	public CMerchCustBase findSimpleCustBaseById(Long id) {
		//查找客户基本信息
		return customerBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer upMaterial() {
		 this.productVMapper.upMaterial();
		 return 1;
	}
	
    @Override
    public String generateExcel(List<Long> stations) throws IOException {
      Map<String, Object> param = new HashMap<>();
      param.put("stations", stations);
      List<CustomerBaseExport> customers = contractVMapper.customerDetail4Export(param);
      if (customers.size() > 10_0000) {
        return "0";
      }
      // 创建文件
      String fileName = UUID.randomUUID().toString() + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      String title[] = {"客户编码", "客户名称", "客户类型", "销售部门", "销售组织", "对应岗位", "对应销售人员", "客户状态", "SAP编码", "联系人",
          "联系电话", "对应盐业公司", "可用保证金", "可用授信", "可用现金", "可用货补", "授信额度", "合同目标", "大区", "业务省", "行政省", "城市",
          "区县", "收货地址", "rdc", "登陆编码"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 写入头数据
        Row header = (Row) sheet1.createRow(0);
        // 循环写入头列数据
        for (int j = 0; j < title.length; j++) {
          Cell cell = header.createCell(j);
          cell.setCellValue(title[j]);
        }
        // 内容
        int rowIndex = 1;
        for (CustomerBaseExport customer : customers) {
          fillRow(sheet1, rowIndex++, customer);
        }
        wb.write(stream);
        Files.deleteFileDelay(file, 3, TimeUnit.MINUTES);
      }finally{
        IOUtils.closeQuietly(stream);
        if(wb instanceof Closeable){
			IOUtils.closeQuietly((Closeable)wb);
		}
      }
      
      return fileName;
    }
    
    @Override
    public String generateRetailExcel(TEmployee user, List<Long> stationids,AjaxDTO bean, String orgid,
        String custname) throws IOException {
      Map<String, Object> params = Params.builder().add("usertype", user.getUserType())
          .add("merchid", user.getMerchId()).add("stationids", stationids).add("orgid", orgid).buid();
       List<CMerchCustBase> retails = retailMapper.selectRetail4Export(params);
      // 创建文件
      String fileName = UUID.randomUUID().toString() + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      Files.makeDirIfAbsent(file);
      
      String title[] = { "客户名称", "客户简称", "省", "市", "县", "采购经理", "联系电话", "财务经理",
          "财务联系电话", "客户渠道", "网点类型", "地址", "所属客户", "开票信息", "开票名称", "税号", "注册地址", "银行账号", "电话", "开户行"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        Excels.fillHeader(title, sheet1);
        // 内容
        List<TDict> channels = dictService.findByName("CUSTOMER_CHANNEL");
        List<TDict> websites = dictService.findByName("CUSTOMER_WEBSITE");
        int rowIndex = 1;
        for (CMerchCustBase retail : retails) {
          fillRow(sheet1, rowIndex++, retail, channels, websites);
        }
        wb.write(stream);
      }finally{
        IOUtils.closeQuietly(stream);
        if(wb instanceof Closeable){
            IOUtils.closeQuietly((Closeable)wb);
        }
      }
      
      return fileName;
    }
    
    private void fillRow(Sheet sheet, int rowIndex, CMerchCustBase retail, List<TDict> channels, List<TDict> websites){
      int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Excels.fillCell(row, cellIndex++, retail.getName());
      Excels.fillCell(row, cellIndex++, retail.getAbbrName());
      Excels.fillCell(row, cellIndex++, retail.getProvName());
      Excels.fillCell(row, cellIndex++, retail.getCityName());
      Excels.fillCell(row, cellIndex++, retail.getCountyName());
      Excels.fillCell(row, cellIndex++, retail.getContactName());
      Excels.fillCell(row, cellIndex++, retail.getContactTel());
      Excels.fillCell(row, cellIndex++, retail.getLpName());
      Excels.fillCell(row, cellIndex++, retail.getTel());
      Excels.fillCell(row, cellIndex++, dictService.code2Str(retail.getChannelId(), channels));
      Excels.fillCell(row, cellIndex++, dictService.code2Str(retail.getOpeningType(), websites));
      Excels.fillCell(row, cellIndex++, retail.getAddress());
      Excels.fillCell(row, cellIndex++, retail.getPname());
      Excels.fillCell(row, cellIndex++, retail.getIsInvoice());
      if(!"不开票".equals(retail.getIsInvoice())){
        Excels.fillCell(row, cellIndex++, retail.getInvoiceName());
        Excels.fillCell(row, cellIndex++, retail.getInvoiceTaxNum());
        Excels.fillCell(row, cellIndex++, retail.getInvoiceAddress());
        Excels.fillCell(row, cellIndex++, retail.getInvoiceAccount());
        Excels.fillCell(row, cellIndex++, retail.getInvoiceTel());
        Excels.fillCell(row, cellIndex++, retail.getInvoiceBankName());
      }
    }

    private void fillRow(Sheet sheet, int rowIndex, CustomerBaseExport customer) {
      int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Excels.fillCell(row, cellIndex++, String.valueOf(customer.getId()));
      Excels.fillCell(row, cellIndex++, customer.getName());
      Excels.fillCell(row, cellIndex++, customer.getCustType());
      Excels.fillCell(row, cellIndex++, customer.getDepartment());
      Excels.fillCell(row, cellIndex++, customer.getOrgname());
      Excels.fillCell(row, cellIndex++, customer.getPositionName());
      Excels.fillCell(row, cellIndex++, customer.getSalesman());
      Excels.fillCell(row, cellIndex++, customer.getStates());
      Excels.fillCell(row, cellIndex++, customer.getSapCustomerId());
      Excels.fillCell(row, cellIndex++, customer.getContactName());
      Excels.fillCell(row, cellIndex++, customer.getContactTel());
      Excels.fillCell(row, cellIndex++, customer.getPname());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getBondAmt()).toString());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getCreditAmt()).toString());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getCashAmt()).toString());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getSubsidyAmt()).toString());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getContractCredit()).toString());
      Excels.fillCell(row, cellIndex++, BigDecimalASME.divide(customer.getYearAmt(), BigDecimal.ONE).toString());
      Excels.fillCell(row, cellIndex++, customer.getReginName());
      Excels.fillCell(row, cellIndex++, customer.getOrgProv());
      Excels.fillCell(row, cellIndex++, customer.getProv());
      Excels.fillCell(row, cellIndex++, customer.getCity());
      Excels.fillCell(row, cellIndex++, customer.getCounty());
      Excels.fillCell(row, cellIndex++, customer.getAddress());
      Excels.fillCell(row, cellIndex++, customer.getRdc());
      Excels.fillCell(row, cellIndex++, customer.getCode());
    }

	@Override
	public AjaxDTO selectRetailCustomers(TEmployee user, List<Long> stationids,AjaxDTO bean, String orgid,
			String custname) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("usertype", user.getUserType());
		params.put("merchid", user.getMerchId());
		params.put("stationids", stationids);
		params.put("orgid", orgid);
		params.put("custname", custname);
		if(bean.getLimit()!=0){
		  params.put("bpage", bean.getOffset());
		  params.put("epage", bean.getLimit()+bean.getOffset());
		}
		AjaxDTO dto = new AjaxDTO();
		List<CustomerBaseDTO> list = this.retailMapper.selectRetailCustomers(params);
		int total = this.retailMapper.countRetailCustomers(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	@Override
	public int changeEnableStatus(Long merchProductId){
	  CMerchCustProduct record = productMapper.selectByPrimaryKey(merchProductId);
	  record.setStates("4".equals(record.getStates())?"6":"4"); // 4为启用 6为停用
	  int flag=  productMapper.updateByPrimaryKey(record);
	  this.productVMapper.updatePriceStates(merchProductId);
	  return flag;
	}

	@Override
	public String updateStates(Long merchId, String states) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("merchId", merchId);
		map.put("states", states);
		this.merchCustBaseVMapper.changeStates(map);
		return (String) map.get("p_result");
	}

	@Override
	public String addProductPrice(Long id, BigDecimal hPrice, String priceBdate,
			String priceEdate) throws ParseException {
		CMerchCustProduct  product = this.productMapper.selectByPrimaryKey(id);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//验证是否存在相同有效期的数据
		CMerchCustProductExample ex = new CMerchCustProductExample();
		CMerchCustProductExample.Criteria ext = ex.createCriteria();
		ext.andMaterialIdEqualTo(product.getMaterialId());
		ext.andMerchCustIdEqualTo(product.getMerchCustId());
		ext.andBDateEqualTo(sf.parse(priceBdate));
		ext.andEDateEqualTo(sf.parse(priceEdate));
		List<CMerchCustProduct> validateProducts = this.productMapper.selectByExample(ex);
		if(validateProducts !=null && validateProducts.size()>0 ){
			return "E1";//存在相同数据
		}
		//如果存在相同的有效日期则直接修改
		if(product.getbDate()!=null && product.geteDate() !=null && sf.format(product.getbDate()).equals(priceBdate) && sf.format(product.geteDate()).equals(priceEdate)){
			product.sethPrice(hPrice);
		    this.productMapper.updateByPrimaryKey(product);
		    return "S";
		}
		product.setId(null);
		product.sethPrice(BigDecimalASME.multiply(hPrice));
		product.setStates("6");
		product.setbDate(sf.parse(priceBdate));
		product.seteDate(sf.parse(priceEdate));
		this.productMapper.insert(product);
		this.productVMapper.updatePriceStates(product.getId());
		return "S";
	}

	@Override
	public AjaxDTO getsaltPrice(AjaxDTO bean) {
		Map<String,Object> params = new HashMap<String, Object>();
	    params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit()+1);
		AjaxDTO dto = new AjaxDTO();
		List<CMerchCustProductV> list = this.productVMapper.selectSaltPrice(params);
		int total = this.productVMapper.countSaltPrice(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public Integer editsaltPrice(Long id, BigDecimal price) {
		CMerchCustBase merch = this.customerBaseMapper.selectByPrimaryKey(id);
		merch.setAdjustPrice(BigDecimalASME.multiply(price));
		return this.customerBaseMapper.updateByPrimaryKeySelective(merch);
	}
}
