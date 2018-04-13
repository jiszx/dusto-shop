package com.hhnz.order.validator.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.order.validator.OrderValidator;
/**
 *销售订单验证
 * @author dell
 *
 */
@Component
public class OrderValidatorImpl implements OrderValidator {
	private final String VALID = "validated";
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private OrderUtilService service;
	
	@Resource
	private OmOrderHeadersAllMapper  headermapper;
	@Resource
    private CMerchCustBaseMapper custbaseMapper;
	
	@Override
	public String validateOrderAdd(OmOrderHeadersAll orderh,List<OmOrderLinesAll> orderlines) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.validateMerchId(orderh));
		sb.append(this.validateShipId(orderh));
		if(orderlines ==null || orderlines.size()==0){
			sb.append("行数据为空");
		}else{
			for(OmOrderLinesAll line:orderlines){
				if(line.getId()!=0){					
					sb.append(this.validateLinePrice(line));
					sb.append(this.validateLineNum(line,orderh));
					sb.append(this.validateLineSKU(line));
				}
			}
		}
		if (sb.length() < 2) {
			return VALID;
		} else {
			return sb.toString();
		}

	}
	
	/**
	 * 验证客户编码是否正确
	 * @param orderh
	 * @return
	 */
	public String validateMerchId(OmOrderHeadersAll orderh){
		if(orderh.getMerchCustId()==null ||StringUtils.isEmpty(orderh.getMerchCustId())){
			return "客户不能为空";
		}
		CMerchCustBase custBase = this.customerService.findCustBaseById(orderh.getMerchCustId());
		if(custBase ==null){
			return "此客户不存在，请选择正确客户";
		}
		return "";
	}
	
	/**
	 * 验证送达方是否为空
	 * @param orderh
	 * @return
	 */
	public String validateShipId(OmOrderHeadersAll orderh){
		if(orderh.getShipId()==null ||StringUtils.isEmpty(orderh.getShipId())){
			return "客户送达方不能为空";
		}
		return "";
	}
	
	public String validateLinePrice(OmOrderLinesAll line){
		if(line.getPrice() ==null ||StringUtils.isEmpty(line.getPrice())){
			return "销售订单价格为空";
		}
		return "";
	}
	public String validateLineNum(OmOrderLinesAll line,OmOrderHeadersAll order){
		if((line.getNum()==null||StringUtils.isEmpty(line.getNum())||line.getNum().toString().equals("0"))&&(line.getHbNum()==null || StringUtils.isEmpty(line.getHbNum())||line.getHbNum().toString().equals("0"))){
			return "购买数量为空";
		}
		CMerchCustBase custBase = custbaseMapper.selectByPrimaryKey(order.getMerchCustId());
		if("6".equals(order.getOrderType())){ // 零售订单
		  return "";
		}
		BigDecimal  num = line.getHbNum()==null ? new BigDecimal(0) : line.getHbNum().add(line.getNum());
		int i = validateDepo(line, order, num, custBase);
		if(i !=1){
			return line.getMaterialId()+"可售库存不足，请修改后再提交";
		}
		return "";
	}

    private int validateDepo(OmOrderLinesAll line, OmOrderHeadersAll order, BigDecimal num,
        CMerchCustBase merch) {
      int i = 0;
      if("3".equals(merch.getCustType())){ // ka
        Objects.requireNonNull(merch.getPid());
        i = this.service.validateNum(line.getMaterialId(), order.getOrganizationId(),
            merch.getPid(), num);
      }else if ("8".equals(order.getOrderType()) || "0".equals(order.getOrderType())) {
        // 特殊调拨单,标准订单通过订单获取RDC
        i = this.service.validateNumByRdc(line.getMaterialId(), order.getOrganizationId(),
            order.getMerchCustId(), order.getRdcCode(), num);
      } else {
        i = this.service.validateNum(line.getMaterialId(), order.getOrganizationId(),
            order.getMerchCustId(), num);
      }
      return i;
    }
    
	public String validateLineSKU(OmOrderLinesAll line){
		if(line.getMaterialId() ==null ||StringUtils.isEmpty(line.getMaterialId())){
			return "物料不能为空";
		}
		return "";
	}

	@Override
	public String validateLine(OmOrderLinesAll line) {
		// TODO Auto-generated method stub
		OmOrderHeadersAll header = this.headermapper.selectByPrimaryKey(line.getHeaderId());
		StringBuffer sb = new StringBuffer();
		sb.append(this.validateLinePrice(line));
		sb.append(this.validateLineNum(line,header));
		sb.append(this.validateLineSKU(line));
		if (sb.length() < 2) {
			return VALID;
		} else {
			return sb.toString();
		}
	}
}
