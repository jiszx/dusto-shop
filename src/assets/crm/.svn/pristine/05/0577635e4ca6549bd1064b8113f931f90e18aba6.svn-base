package com.hhnz.customerInv.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.hhnz.customer.enu.CustomerType;
import com.hhnz.rmi.db.model.order.enu.OrderStatus;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.util.DateUtil;
import com.hhnz.util.io.excel.util.excel.ExcelField;

public class CustomerInvAllocationDTO {
	@ExcelField(order = 5, header = "送达方名称")
	private String custname;
	private Long merchCustId;
	@ExcelField(order = 8, header = "调拨单编号")
	private Long id;
	private String saporderid;
	@ExcelField(order = 7, header = "SAP客户编码")
	private String sapCustomerId;
	@ExcelField(order = 11, header = "创建人")
	private String creater;
	private Date createTs;
	@ExcelField(order = 12, header = "金额")
	private BigDecimal amt;
	private String states;
	@ExcelField(order = 0, header = "销售组织")
	private String orgname;
	private String organizationId;
	@ExcelField(order = 1, header = "大区")
	private String reginname;
	@ExcelField(order = 2, header = "业务省")
	private String provname;
	private String reginId;
	private String provId;
	@ExcelField(order = 4, header = "发货RDC")
	private String RdcName;
	@ExcelField(order = 18, header = "默认RDC")
	private String defaultRdc;
	private String custType;
	@ExcelField(order = 15, header = "已调拨数量")
	private BigDecimal allocationNum;
	@ExcelField(order = 16, header = "已调拨金额")
	private BigDecimal allocationAmt;
	@ExcelField(order = 9, header = "CRM订单编号")
	private Long  orderId;
	
	// 导出需要的字段
	private BigDecimal num;
	private String materialId;
	private String sku;
	private BigDecimal allocationWeight;
	private String agentType;
	private String sapError;
	private String rfcSerialNo;
	@ExcelField(order = 3, header = "行政省")
	private String prov; // 行政省
	private BigDecimal price;
	@ExcelField(order = 13, header = "重量")
	private BigDecimal weight;
	private String sendTime;
	
	@ExcelField(order = 14, header = "吨均价")
	private BigDecimal averagePrice;
	@ExcelField(order = 17, header = "状态")
	private String statesDesc;
	@ExcelField(order = 6, header = "客户类型")
	private String custTypeDesc;
	@ExcelField(order = 10, header = "创建时间")
	private String createTsDesc;
	@ExcelField(order = 18, header = "地址")
	private String address;
	private String deliveryType;
	private BigDecimal freight;
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSaporderid() {
		return saporderid;
	}
	public void setSaporderid(String saporderid) {
		this.saporderid = saporderid;
	}
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getReginname() {
		return reginname;
	}
	public void setReginname(String reginname) {
		this.reginname = reginname;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getReginId() {
		return reginId;
	}
	public void setReginId(String reginId) {
		this.reginId = reginId;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getRdcName() {
		return RdcName;
	}
	public void setRdcName(String rdcName) {
		RdcName = rdcName;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public BigDecimal getAllocationNum() {
		return allocationNum;
	}
	public void setAllocationNum(BigDecimal allocationNum) {
		this.allocationNum = allocationNum;
	}
	public BigDecimal getAllocationAmt() {
		return allocationAmt;
	}
	public void setAllocationAmt(BigDecimal allocationAmt) {
		this.allocationAmt = allocationAmt;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
    public BigDecimal getNum() {
      return num;
    }
    public void setNum(BigDecimal num) {
      this.num = num;
    }
    public String getMaterialId() {
      return materialId;
    }
    public void setMaterialId(String materialId) {
      this.materialId = materialId;
    }
    public String getSku() {
      return sku;
    }
    public void setSku(String sku) {
      this.sku = sku;
    }
    public BigDecimal getAllocationWeight() {
      return allocationWeight;
    }
    public void setAllocationWeight(BigDecimal allocationWeight) {
      this.allocationWeight = allocationWeight;
    }
    public String getAgentType() {
      return agentType;
    }
    public void setAgentType(String agentType) {
      this.agentType = agentType;
    }

	public String getSapError() {
		return sapError;
	}
	public void setSapError(String sapError) {
		this.sapError = sapError;
	}
	public String getRfcSerialNo() {
		return rfcSerialNo;
	}
	public void setRfcSerialNo(String rfcSerialNo) {
		this.rfcSerialNo = rfcSerialNo;
	}
    public String getProv() {
      return prov;
    }
    public void setProv(String prov) {
      this.prov = prov;
    }
    public BigDecimal getPrice() {
      return price;
    }
    public void setPrice(BigDecimal price) {
      this.price = price;
    }
    public BigDecimal getWeight() {
      return weight;
    }
    public void setWeight(BigDecimal weight) {
      this.weight = weight;
    }
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getDefaultRdc() {
		return defaultRdc;
	}
	public void setDefaultRdc(String defaultRdc) {
		this.defaultRdc = defaultRdc;
	}
	
	public BigDecimal getAveragePrice() {
		if (amt == null || amt == null || weight.compareTo(BigDecimal.ZERO) <= 0) {
			return null;
		}
		return BigDecimalUtil.divide(amt, weight);
	}
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getStatesDesc() {
		if(states == null || OrderStatus.toEnum(states) == null){
			return null;
		}
		return OrderStatus.toEnum(states).getDesc();
	}
	public void setStatesDesc(String statesDesc) {
		this.statesDesc = statesDesc;
	}
	public String getCustTypeDesc() {
		if(custType == null || CustomerType.toEnum(custType) == null){
			return null;
		}
		return CustomerType.toEnum(custType).getDesc();
	}
	public void setCustTypeDesc(String custTypeDesc) {
		this.custTypeDesc = custTypeDesc;
	}
	public String getCreateTsDesc() {
		if(createTs == null){
			return null;
		}
		return DateUtil.format(createTs, DateUtil.DATE_TIME_FORMAT_STR_PLAIN);
	}

	public void setCreateTsDesc(String createTsDesc) {
		this.createTsDesc = createTsDesc;
	}
    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
}
