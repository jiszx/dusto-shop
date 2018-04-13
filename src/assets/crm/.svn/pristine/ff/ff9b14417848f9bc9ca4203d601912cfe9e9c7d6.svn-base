package com.hhnz.customer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.customer.enu.CustomerType;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

public class CMerchCustBase implements Cloneable{
	
	/**
	 * 客户编号生成,共计10位
	 * <br/>
	 * 规则：
	 * <pre>
	 * 1位字母代号{@link CustomerType#getPrefix()}
	 * 4位城市代码{@link CMerchCustBase#cityId}
	 * 5位序列号
	 * </pre>
	 * @author: chaoyang.ren 
	 * @date:Feb 9, 2017  11:38:47 AM
	 * @return
	 */
	public String genCode(){
		Assert.notNull(custType);
		String prefix = CustomerType.toEnum(custType).getPrefix();
		String cityCode;
		if(StringUtils.isEmpty(cityId)){
			cityCode = "0000";
		}else{
			if(cityId.length()>4){
				throw new IllegalArgumentException("生成客户编码时，城市id长度超过4位！");
			}else{
				cityCode = StringUtils.leftPad(cityId, 4, "0");
			}
		}
		CMerchCustBaseMapper custBaseMapper = ApplicationContextUtil.getBean(CMerchCustBaseMapper.class);
		String codeSeq = custBaseMapper.findCodeSeq().toString();
		if(codeSeq.length()<=5){
			codeSeq = StringUtils.leftPad(codeSeq, 5, "0");
		}else{
			codeSeq = codeSeq.substring(codeSeq.length()-5, codeSeq.length());
		}
		return prefix+cityCode+codeSeq;
	}
    
    private Long id;

    @NotBlank(message="客户名称不能为空!")
    @Length(max=60,message="客户名称不能超过{max}个字符!")
    private String name;
    
    private String code;

    @Length(max=10,message="客户简称不能超过{max}个字符!")//change max length from 16 to 10 at 2016年10月17日
    private String abbrName;

    @NotBlank(message="省份不能为空!")
    private String provId;

    @NotBlank(message="城市不能为空!")
    private String cityId;

    @NotBlank(message="区县不能为空!")
    private String countyId;

    @Length(max=100,message="详细地址不能超过{max}个字符!")
    private String address;

    @Pattern(regexp="[a-zA-Z0-9]*",message="法人身份证号必须是数字或字母")
    @Length(max=30,message="法人身份证号不能超过{max}个字符!")
    private String lpNo;

    @Length(max=10,message="法人名称不能超过{max}个字符!")
    private String lpName;

    @Pattern(regexp="[a-zA-Z0-9]*",message="邮政编码必须是数字或字母")
    @Length(max=10,message="邮政编码不能超过{max}个字符!")
    private String zipCode;

    @Length(max=100,message="注册地址不能超过{max}个字符!")
    private String regAddr;

    @Pattern(regexp="[a-zA-Z0-9]*",message="客户电话必须是数字或字母")
    @Length(max=50,message="客户电话不能超过{max}个字符!")
    private String tel;

    
    private Date createTs;

    
    private Long createOid;

    
    private Date updateTs;

    
    private Long updateOid;

    
    private String states;

    
    private String sapCustomerId;

    
    private String channelId;

    
    private String custType;

    
    private String isAttachment;

    @Pattern(regexp="[a-zA-Z0-9]*",message="营业执照号必须是数字或字母")
    @Length(max=50,message="营业执照号不能超过{max}个字符!")
    private String businessLicense;

    @NotBlank(message="客户联系人不能为空!")
    @Length(max=10,message="客户联系人不能超过{max}个字符!")
    private String contactName;

    @Pattern(regexp="[a-zA-Z0-9]*",message="业务电话必须是数字或字母")
    @Length(max=50,message="业务电话不能超过{max}个字符!")
    private String contactTel;

    
    private String organizationId;

    
    private Long pid;

    @Length(max=60,message="开票名称不能超过{max}个字符!")
    private String invoiceName;

    @Pattern(regexp="[a-zA-Z0-9]*",message="税号必须是数字或字母")
    @Length(max=30,message="税号不能超过{max}个字符!")
    private String invoiceTaxNum;

    @Length(max=100,message="开票地址信息不能超过{max}个字符!")
    private String invoiceAddress;

    @Pattern(regexp="[a-zA-Z0-9]*",message="开票账户必须是数字或字母")
    @Length(max=30,message="开票账户不能超过{max}个字符!")
    private String invoiceAccount;

    @Pattern(regexp="[a-zA-Z0-9]*",message="开票电话信息必须是数字或字母")
    @Length(max=30,message="开票电话信息不能超过{max}个字符!")
    private String invoiceTel;

    @Length(max=100,message="开票常用打款银行名称不能超过{max}个字符!")
    private String invoiceBankName;

    @Length(max=10,message="开票常用账户名称不能超过{max}个字符!")
    private String invoiceAccountName;

    private String openingType;

    @Length(max=100,message="替代/升级原因不能超过{max}个字符!")
    private String openingReason;

    @Length(max=30,message="替代/升级经销商名称不能超过{max}个字符!")
    private String openingMerchant;

    
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date openingCloseTs;

    @Length(max=10,message="计划拓展KA渠道不能超过{max}个字符!")
    private String expandKaPlace;

    @Length(max=10,message="计划拓展BC渠道不能超过{max}个字符!")
    private String expandBcPlace;

    @Length(max=10,message="计划拓展流通（农贸/批发）渠道不能超过{max}个字符!")
    private String expandCirculatePlace;

    @Length(max=10,message="计划拓展工厂不能超过{max}个字符!")
    private String expandFactoryPlace;

    @Length(max=10,message="计划拓展学校渠道不能超过{max}个字符!")
    private String expandSchoolPlace;

    @Length(max=10,message="计划拓展零售网点数不能超过{max}个字符!")
    private String expandRetailPlace;

    @Length(max=10,message="计划拓展区域人口数不能超过{max}个字符!")
    private String expandAreaPeoples;

    @Length(max=10,message="计划拓展区域市场体量不能超过{max}个字符!")
    private String expandAreaVolume;

    @Length(max=10,message="计划生意体量不能超过{max}个字符!")
    private String expandBusinesVolume;

    @Length(max=10,message="每月运营金额不能超过{max}个字符!")
    private String expandSpentMamt;

    @Length(max=10,message="首单金额不能超过{max}个字符!")
    private String expandSpentFamt;

    @Length(max=10,message="年销售额 不能超过{max}个字符!")
    private String contextSalesYear;

    @Length(max=10,message="生意占比不能超过{max}个字符!")
    private String contextBusinessRatio;

    @Length(max=10,message="年度总投资额不能超过{max}个字符!")
    private String contextInvestment;

    @Length(max=10,message="每月营运资金不能超过{max}个字符!")
    private String contextOperateCapital;

    @Length(max=10,message="ＫＡ数量不能超过{max}个字符!")
    private String contextKaNum;

    @Length(max=10,message="ＢＣ数量不能超过{max}个字符!")
    private String contextBcNum;

    @Length(max=10,message="批发商数量不能超过{max}个字符!")
    private String contextWholesalersNum;

    @Length(max=10,message="零售网点数量不能超过{max}个字符!")
    private String contextRetailNum;

    @Length(max=10,message="农贸网点不能超过{max}个字符!")
    private String contextFarmersNum;

    @Length(max=10,message="餐饮网点不能超过{max}个字符!")
    private String contextRestaurantNum;

    @Length(max=10,message="其他网点不能超过{max}个字符!")
    private String contextOthersNum;

    @Length(max=10,message="销售人员数量不能超过{max}个字符!")
    private String contextSalesmanNum;

    @Length(max=10,message="后勤人员数量不能超过{max}个字符!")
    private String contextLogisticsNum;

    @Length(max=15,message="卡车数量不能超过{max}个字符!")
    private String contextTruckNum;

    @Length(max=15,message="货车数量不能超过{max}个字符!")
    private String contextLorryNum;

    @Length(max=60,message="仓库面积不能超过{max}个字符!")
    private String contextDepot;

    private String provName;

    private String cityName;

    private String countyName;

    @Length(max=60,message="计划拓展区域不能超过{max}个字符!")
    private String planArea;

    @Length(max=60,message="计划拓展品牌不能超过{max}个字符!")
    private String planBrand;

    @Length(max=60,message="计划拓展品类不能超过{max}个字符!")
    private String planCategory;

    private String processId;
    
    /**
     * 父级配送商信息
     * 
     */
    private CMerchCustBase parent;
    
    /**
     * cash_amt客户的现金余额
     */
    private BigDecimal cashAmt;
    
    /**
     * 配送商名称
     */
    private String pname;
    
    /**
     * 送达方信息
     */
    @Valid
    private List<CMerchCustDistribution> distributions;
    
    /**
     * 附件信息
     */
    @Valid
    private List<TAttachment> attachments;
    
    /**
     * 客户对应岗位
     */
    private CMerchCustStation custStation;
    
    /**
     * 客户合同
     */
    private ContractDetail contract;
    
    /**
     *销售组织名称 
     */
    private  String saleOrgName;
    
    /**
     *销售地区名称 
     */
    private String saleAreaName;
    
    /**
     *销售省份名称 
     */
    private String saleProvinceName;
    
    /**
     * 是否开票
     */
    private String isInvoice;
    
    /**
     * 最小起订量
     * 单位吨
     */
    private BigDecimal minOrder;
    
    private String rdcName;
    
    private String email;
    
    private Long payer;
    
    /**
     * 合作盐业公司调拨调整价
     */
    private BigDecimal adjustPrice;
    public BigDecimal getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(BigDecimal minOrder) {
		this.minOrder = minOrder;
	}

	public String getJsonDistributions(){
        if(distributions != null && distributions.size()>0){
            return JsonUtil.toJSON(distributions);
        }
        return null;
    }
    
    public String getIsInvoice() {
      return isInvoice;
    }


    public void setIsInvoice(String isInvoice) {
      this.isInvoice = isInvoice;
    }


    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getAbbrName() {
        return abbrName;
    }

    
    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    
    public String getProvId() {
        return provId;
    }

    
    public void setProvId(String provId) {
        this.provId = provId;
    }

    
    public String getCityId() {
        return cityId;
    }

    
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    
    public String getCountyId() {
        return countyId;
    }

    
    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getLpNo() {
        return lpNo;
    }

    
    public void setLpNo(String lpNo) {
        this.lpNo = lpNo;
    }

    
    public String getLpName() {
        return lpName;
    }

    
    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    
    public String getZipCode() {
        return zipCode;
    }

    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
    public String getRegAddr() {
        return regAddr;
    }

    
    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    
    public String getTel() {
        return tel;
    }

    
    public void setTel(String tel) {
        this.tel = tel;
    }

    
    public Date getCreateTs() {
        return createTs;
    }

    
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    
    public Long getCreateOid() {
        return createOid;
    }

    
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    
    public Date getUpdateTs() {
        return updateTs;
    }

    
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    
    public Long getUpdateOid() {
        return updateOid;
    }

    
    public void setUpdateOid(Long updateOid) {
        this.updateOid = updateOid;
    }

    
    public String getStates() {
        return states;
    }

    
    public void setStates(String states) {
        this.states = states;
    }

    
    public String getSapCustomerId() {
        return sapCustomerId;
    }

    
    public void setSapCustomerId(String sapCustomerId) {
        this.sapCustomerId = sapCustomerId;
    }

    
    public String getChannelId() {
        return channelId;
    }

    
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    
    public String getCustType() {
        return custType;
    }

    
    public void setCustType(String custType) {
        this.custType = custType;
    }

    
    public String getIsAttachment() {
        return isAttachment;
    }

    
    public void setIsAttachment(String isAttachment) {
        this.isAttachment = isAttachment;
    }

    
    public String getBusinessLicense() {
        return businessLicense;
    }

    
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    
    public String getContactName() {
        return contactName;
    }

    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    
    public String getContactTel() {
        return contactTel;
    }

    
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    
    public String getOrganizationId() {
        return organizationId;
    }

    
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    
    public Long getPid() {
        return pid;
    }

    
    public void setPid(Long pid) {
        this.pid = pid;
    }

    
    public String getInvoiceName() {
        return invoiceName;
    }

    
    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    
    public String getInvoiceTaxNum() {
        return invoiceTaxNum;
    }

    
    public void setInvoiceTaxNum(String invoiceTaxNum) {
        this.invoiceTaxNum = invoiceTaxNum;
    }

    
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    
    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    
    public String getInvoiceAccount() {
        return invoiceAccount;
    }

    
    public void setInvoiceAccount(String invoiceAccount) {
        this.invoiceAccount = invoiceAccount;
    }

    
    public String getInvoiceTel() {
        return invoiceTel;
    }

    
    public void setInvoiceTel(String invoiceTel) {
        this.invoiceTel = invoiceTel;
    }

    
    public String getInvoiceBankName() {
        return invoiceBankName;
    }

    
    public void setInvoiceBankName(String invoiceBankName) {
        this.invoiceBankName = invoiceBankName;
    }

    
    public String getInvoiceAccountName() {
        return invoiceAccountName;
    }

    
    public void setInvoiceAccountName(String invoiceAccountName) {
        this.invoiceAccountName = invoiceAccountName;
    }

    
    public String getOpeningType() {
        return openingType;
    }

    
    public void setOpeningType(String openingType) {
        this.openingType = openingType;
    }

    
    public String getOpeningReason() {
        return openingReason;
    }

    
    public void setOpeningReason(String openingReason) {
        this.openingReason = openingReason;
    }

    
    public String getOpeningMerchant() {
        return openingMerchant;
    }

    
    public void setOpeningMerchant(String openingMerchant) {
        this.openingMerchant = openingMerchant;
    }

    
    public Date getOpeningCloseTs() {
        return openingCloseTs;
    }

    
    public void setOpeningCloseTs(Date openingCloseTs) {
        this.openingCloseTs = openingCloseTs;
    }

    
    public String getExpandKaPlace() {
        return expandKaPlace;
    }

    
    public void setExpandKaPlace(String expandKaPlace) {
        this.expandKaPlace = expandKaPlace;
    }

    
    public String getExpandBcPlace() {
        return expandBcPlace;
    }

    
    public void setExpandBcPlace(String expandBcPlace) {
        this.expandBcPlace = expandBcPlace;
    }

    
    public String getExpandCirculatePlace() {
        return expandCirculatePlace;
    }

    
    public void setExpandCirculatePlace(String expandCirculatePlace) {
        this.expandCirculatePlace = expandCirculatePlace;
    }

    
    public String getExpandFactoryPlace() {
        return expandFactoryPlace;
    }

    
    public void setExpandFactoryPlace(String expandFactoryPlace) {
        this.expandFactoryPlace = expandFactoryPlace;
    }

    
    public String getExpandSchoolPlace() {
        return expandSchoolPlace;
    }

    
    public void setExpandSchoolPlace(String expandSchoolPlace) {
        this.expandSchoolPlace = expandSchoolPlace;
    }

    
    public String getExpandRetailPlace() {
        return expandRetailPlace;
    }

    
    public void setExpandRetailPlace(String expandRetailPlace) {
        this.expandRetailPlace = expandRetailPlace;
    }

    
    public String getExpandAreaPeoples() {
        return expandAreaPeoples;
    }

    
    public void setExpandAreaPeoples(String expandAreaPeoples) {
        this.expandAreaPeoples = expandAreaPeoples;
    }

    
    public String getExpandAreaVolume() {
        return expandAreaVolume;
    }

    
    public void setExpandAreaVolume(String expandAreaVolume) {
        this.expandAreaVolume = expandAreaVolume;
    }

    
    public String getExpandBusinesVolume() {
        return expandBusinesVolume;
    }

    
    public void setExpandBusinesVolume(String expandBusinesVolume) {
        this.expandBusinesVolume = expandBusinesVolume;
    }

    
    public String getExpandSpentMamt() {
        return expandSpentMamt;
    }

    
    public void setExpandSpentMamt(String expandSpentMamt) {
        this.expandSpentMamt = expandSpentMamt;
    }

    
    public String getExpandSpentFamt() {
        return expandSpentFamt;
    }

    
    public void setExpandSpentFamt(String expandSpentFamt) {
        this.expandSpentFamt = expandSpentFamt;
    }

    
    public String getContextSalesYear() {
        return contextSalesYear;
    }

    
    public void setContextSalesYear(String contextSalesYear) {
        this.contextSalesYear = contextSalesYear;
    }

    
    public String getContextBusinessRatio() {
        return contextBusinessRatio;
    }

    
    public void setContextBusinessRatio(String contextBusinessRatio) {
        this.contextBusinessRatio = contextBusinessRatio;
    }

    
    public String getContextInvestment() {
        return contextInvestment;
    }

    
    public void setContextInvestment(String contextInvestment) {
        this.contextInvestment = contextInvestment;
    }

    
    public String getContextOperateCapital() {
        return contextOperateCapital;
    }

    
    public void setContextOperateCapital(String contextOperateCapital) {
        this.contextOperateCapital = contextOperateCapital;
    }

    
    public String getContextKaNum() {
        return contextKaNum;
    }

    
    public void setContextKaNum(String contextKaNum) {
        this.contextKaNum = contextKaNum;
    }

    
    public String getContextBcNum() {
        return contextBcNum;
    }

    
    public void setContextBcNum(String contextBcNum) {
        this.contextBcNum = contextBcNum;
    }

    
    public String getContextWholesalersNum() {
        return contextWholesalersNum;
    }

    
    public void setContextWholesalersNum(String contextWholesalersNum) {
        this.contextWholesalersNum = contextWholesalersNum;
    }

    
    public String getContextRetailNum() {
        return contextRetailNum;
    }

    
    public void setContextRetailNum(String contextRetailNum) {
        this.contextRetailNum = contextRetailNum;
    }

    
    public String getContextFarmersNum() {
        return contextFarmersNum;
    }

    
    public void setContextFarmersNum(String contextFarmersNum) {
        this.contextFarmersNum = contextFarmersNum;
    }

    
    public String getContextRestaurantNum() {
        return contextRestaurantNum;
    }

    
    public void setContextRestaurantNum(String contextRestaurantNum) {
        this.contextRestaurantNum = contextRestaurantNum;
    }

    
    public String getContextOthersNum() {
        return contextOthersNum;
    }

    
    public void setContextOthersNum(String contextOthersNum) {
        this.contextOthersNum = contextOthersNum;
    }

    
    public String getContextSalesmanNum() {
        return contextSalesmanNum;
    }

    
    public void setContextSalesmanNum(String contextSalesmanNum) {
        this.contextSalesmanNum = contextSalesmanNum;
    }

    
    public String getContextLogisticsNum() {
        return contextLogisticsNum;
    }

    
    public void setContextLogisticsNum(String contextLogisticsNum) {
        this.contextLogisticsNum = contextLogisticsNum;
    }

    
    public String getContextTruckNum() {
        return contextTruckNum;
    }

    
    public void setContextTruckNum(String contextTruckNum) {
        this.contextTruckNum = contextTruckNum;
    }

    
    public String getContextLorryNum() {
        return contextLorryNum;
    }

    
    public void setContextLorryNum(String contextLorryNum) {
        this.contextLorryNum = contextLorryNum;
    }

    
    public String getContextDepot() {
        return contextDepot;
    }

    
    public void setContextDepot(String contextDepot) {
        this.contextDepot = contextDepot;
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

    
    public String getCountyName() {
        return countyName;
    }

    
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    
    public String getPlanArea() {
        return planArea;
    }

    
    public void setPlanArea(String planArea) {
        this.planArea = planArea;
    }

    
    public String getPlanBrand() {
        return planBrand;
    }

    
    public void setPlanBrand(String planBrand) {
        this.planBrand = planBrand;
    }

    
    public String getPlanCategory() {
        return planCategory;
    }

    
    public void setPlanCategory(String planCategory) {
        this.planCategory = planCategory;
    }

    
    public String getProcessId() {
        return processId;
    }

    
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<CMerchCustDistribution> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<CMerchCustDistribution> distributions) {
        this.distributions = distributions;
    }

    public List<TAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<TAttachment> attachments) {
        this.attachments = attachments;
    }

    public CMerchCustStation getCustStation() {
        return custStation;
    }

    public void setCustStation(CMerchCustStation custStation) {
        this.custStation = custStation;
	}

	public ContractDetail getContract() {
		return contract;
	}

	public void setContract(ContractDetail contract) {
		this.contract = contract;
	}

	public String getSaleOrgName() {
		return saleOrgName;
	}

	public void setSaleOrgName(String saleOrgName) {
		this.saleOrgName = saleOrgName;
	}

	public String getSaleAreaName() {
		return saleAreaName;
	}

	public void setSaleAreaName(String saleAreaName) {
		this.saleAreaName = saleAreaName;
	}

	public String getSaleProvinceName() {
		return saleProvinceName;
	}

	public void setSaleProvinceName(String saleProvinceName) {
		this.saleProvinceName = saleProvinceName;
	}

	public CMerchCustBase getParent() {
		return parent;
	}

	public void setParent(CMerchCustBase parent) {
		this.parent = parent;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getRdcName() {
		return rdcName;
	}

	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public String getEmail() {
      return email;
    }
  
    public void setEmail(String email) {
      this.email = email;
    }
  
    public Long getPayer() {
      return payer;
    }
  
    public void setPayer(Long payer) {
      this.payer = payer;
    }

	public BigDecimal getAdjustPrice() {
		return adjustPrice;
	}

	public void setAdjustPrice(BigDecimal adjustPrice) {
		this.adjustPrice = adjustPrice;
	}
    
}