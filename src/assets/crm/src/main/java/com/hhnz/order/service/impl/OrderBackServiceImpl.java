package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.order.dto.OrderBackDTO;
import com.hhnz.order.dto.OrderBackSpiltDTO;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderBackMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.service.OrderBackService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.JsonUtil;

/**
 * 
 * @author hhnz-skevin
 *
 */
@Service
@Transactional
public class OrderBackServiceImpl implements OrderBackService {
	private static Logger logger = Logger.getLogger(OrderBackServiceImpl.class);
	@Resource
	private OrderBackMapper backmapper;

	@Resource
	private OmOrderHeadersAllMapper orderHeaderMapper;
	@Resource
	private OmOrderLinesAllMapper orderLineMapper;

	@Resource
	private OmOrderSpiltsMapper spiltMapper;
	@Resource
	private IProcessService processService;

	@Override
	public AjaxDTO selectBackOrderHeaderList() {
		AjaxDTO dto = new AjaxDTO();
		List<OrderBackDTO> OrderBackHeaderlist = this.backmapper.selectBackOrderHeaderList();
		dto.setRows(OrderBackHeaderlist);
		return dto;
	}

	@Override
	public String addBackOrder(Long headerId, String linesData, String backReason, String remark, TEmployee user) {
		OmOrderHeadersAll order = this.orderHeaderMapper.selectByPrimaryKey(headerId);// 原订单头数据
		// 退货订单头表
		OmOrderHeadersAll backOrder = new OmOrderHeadersAll();
		backOrder.setMerchCustId(order.getMerchCustId());
		backOrder.setShipId(order.getShipId());
		backOrder.setCreateTs(new Date());
		backOrder.setCreateOid(user.getId());
		backOrder.setOrganizationId(order.getOrganizationId());
		backOrder.setStates("1");
		backOrder.setStationId(order.getStationId());
		backOrder.setOrderType("3");
		backOrder.setSource("1");
		backOrder.setAttribute13(headerId.toString());// 原订单编号
		backOrder.setAttribute6(backReason);// 退货原因
		backOrder.setRegionId(order.getRegionId());
		backOrder.setProviId(order.getProviId());
		backOrder.setSalesrepId(order.getSalesrepId());
		backOrder.setRdcCode(order.getRdcCode());
		backOrder.setRemark(remark);
		this.orderHeaderMapper.insert(backOrder);

		BigDecimal orderAmt_all = BigDecimal.ZERO;
		BigDecimal amt_all = BigDecimal.ZERO;
		BigDecimal hbAmt_all = BigDecimal.ZERO;
		List<OmOrderLinesAll> backlines = new ArrayList<OmOrderLinesAll>();
		// 获取退货信息
		List<OmOrderLinesAll> lines = new ArrayList<OmOrderLinesAll>();
		lines = JsonUtil.fromJSON(linesData, new TypeToken<ArrayList<OmOrderLinesAll>>() {
		}.getType());
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll line : lines) {
				// 插入退货行数据
				BigDecimal orderAmt = BigDecimal.ZERO;
				BigDecimal amt = BigDecimal.ZERO;
				BigDecimal hbAmt = BigDecimal.ZERO;
				OmOrderLinesAll oldLine = this.orderLineMapper.selectByPrimaryKey(line.getId());
				OmOrderLinesAll backline = new OmOrderLinesAll();
				backline.setHeaderId(backOrder.getId());
				backline.setMaterialId(oldLine.getMaterialId());
				backline.setPrice(oldLine.getPrice());
				backline.setOrderPrice(oldLine.getOrderPrice());
				backline.setNum(line.getNum() != null ? line.getNum() : BigDecimal.ZERO);
				backline.setHbNum(line.getHbNum() != null ? line.getHbNum() : BigDecimal.ZERO);
				hbAmt = oldLine.getPrice().multiply(backline.getHbNum());
				orderAmt = oldLine.getPrice().multiply(backline.getNum());
				amt = hbAmt.add(orderAmt);
				backline.setOrderPrice(oldLine.getOrderPrice());
				backline.setHbAmt(hbAmt);
				backline.setOrderAmt(orderAmt);
				backline.setAmt(amt);
				backline.setDiscountAmt(hbAmt);
				backline.setStates("1");
				backline.setCreateTs(new Date());
				backline.setCreateOid(user.getId());
				backline.setUnit(oldLine.getUnit());
				backline.setAttribute7(line.getId().toString());
				this.orderLineMapper.insert(backline);
				hbAmt_all = hbAmt_all.add(hbAmt);
				orderAmt_all = orderAmt_all.add(amt);
				amt_all = amt_all.add(amt);
				if (line.getNum() != null) {
					backline.setAttribute1("S");// 拆分行创建标准行
				}
				if (line.getHbNum() != null) {
					backline.setAttribute2("S");// 拆分行创建货补行
				}
				backlines.add(backline);
			}
			// 更新头表金额
			backOrder.setAmt(amt_all);
			backOrder.setOrderAmt(orderAmt_all);
			backOrder.setHbAmt(hbAmt_all);
			backOrder.setDiscountAmt(hbAmt_all);
			this.orderHeaderMapper.updateByPrimaryKey(backOrder);
			// 插入拆分表
			try {
				spiltline(backlines, backOrder);
				return backOrder.getId().toString();
			} catch (Exception e) {
				logger.error("添加订单失败：" + e.getMessage());
				return "E";
			}
		} else {
			return "E";
		}
	}

	@Override
	public void spiltline(List<OmOrderLinesAll> lines, OmOrderHeadersAll orderh) {
		if (lines.size() > 0 && lines != null) {
			int i = 0;
			for (OmOrderLinesAll line : lines) {
				if (line.getId() == 0 || line.getId() == null) {
					continue;
				}
				i = i + 10;
				if (line.getAttribute1() != null && line.getAttribute1().equals("S")
						&& line.getNum().compareTo(BigDecimal.ZERO) >= 0) {
					// 现金购买数量不等于0
					Long oldspiltId = getOldSpiltId(line, "1");
					OmOrderSpilts spiltline = new OmOrderSpilts();
					spiltline.setOrderitemSapNo(String.valueOf(i));
					spiltline.setHeaderId(orderh.getId());
					spiltline.setLineId(line.getId());
					spiltline.setMerchCustId(orderh.getMerchCustId());
					spiltline.setCreateTs(new Date());
					spiltline.setCreateOid(orderh.getCreateOid());
					spiltline.setPrice(line.getOrderPrice());
					spiltline.setNum(line.getNum());
					spiltline.setOrganizationId(orderh.getOrganizationId());
					spiltline.setType("1");
					spiltline.setAmt(line.getOrderAmt());
					spiltline.setMaterialId(line.getMaterialId());
					spiltline.setStates("1");
					spiltline.setShipTo(orderh.getShipId());
					spiltline.setCreateOid(orderh.getCreateOid());
					spiltline.setCreateTs(new Date());
					spiltline.setStationId(orderh.getStationId());
					spiltline.setReginId(orderh.getRegionId());
					spiltline.setProvId(orderh.getProviId());
					spiltline.setSalesrepId(orderh.getSalesrepId());
					spiltline.setAttribute5(oldspiltId.toString());
					this.spiltMapper.insert(spiltline);
				}
				if (line.getPolicyLineId() != null && line.getPolicyHeaderId() != null
						&& StringUtils.equals("1", line.getPolicyVerfication())) {
					// 搭赠
					i = i + 10;
					Long oldspiltId = getOldSpiltId(line, "3");
					OmOrderSpilts discountlines = new OmOrderSpilts();
					String materialid = line.getPolicyDiscount();
					discountlines.setHeaderId(orderh.getId());
					discountlines.setOrderitemSapNo(String.valueOf(i));
					discountlines.setLineId(line.getId());
					discountlines.setPrice(new BigDecimal(0));
					// discountlines.setNum(line.getPolicyDiscountIntensity());
					discountlines.setAmt(new BigDecimal(0));
					discountlines.setMerchCustId(orderh.getMerchCustId());
					discountlines.setMaterialId(materialid);
					discountlines.setStates("1");
					discountlines.setOrganizationId(orderh.getOrganizationId());
					discountlines.setNum(new BigDecimal(line.getPolicyDiscountIntensity()));
					discountlines.setType("3");
					discountlines.setShipTo(orderh.getShipId());
					discountlines.setCreateOid(orderh.getCreateOid());
					discountlines.setCreateTs(new Date());
					discountlines.setStationId(orderh.getStationId());
					discountlines.setReginId(orderh.getRegionId());
					discountlines.setProvId(orderh.getProviId());
					discountlines.setSalesrepId(orderh.getSalesrepId());
					discountlines.setAttribute5(oldspiltId.toString());
					this.spiltMapper.insert(discountlines);
				}
				if (line.getAttribute2() != null && line.getAttribute2().equals("S")
						&& line.getHbNum().compareTo(new BigDecimal(0)) >= 0) {
					// 货补
					Long oldspiltId = getOldSpiltId(line, "2");
					i = i + 10;
					OmOrderSpilts discountlines = new OmOrderSpilts();
					discountlines.setHeaderId(orderh.getId());
					discountlines.setOrderitemSapNo(String.valueOf(i));
					discountlines.setLineId(line.getId());
					discountlines.setPrice(new BigDecimal(0));
					discountlines.setAmt(new BigDecimal(0));
					discountlines.setNum(line.getHbNum());
					discountlines.setMerchCustId(orderh.getMerchCustId());
					discountlines.setMaterialId(line.getMaterialId());
					discountlines.setStates("1");
					discountlines.setOrganizationId(orderh.getOrganizationId());
					discountlines.setType("2");
					discountlines.setShipTo(orderh.getShipId());
					discountlines.setCreateOid(orderh.getCreateOid());
					discountlines.setCreateTs(new Date());
					discountlines.setStationId(orderh.getStationId());
					discountlines.setReginId(orderh.getRegionId());
					discountlines.setProvId(orderh.getProviId());
					discountlines.setSalesrepId(orderh.getSalesrepId());
					discountlines.setAttribute5(oldspiltId.toString());
					this.spiltMapper.insert(discountlines);
				}
			}
		}
	}

	private Long getOldSpiltId(OmOrderLinesAll line, String type) {
		// 获取原订单对应的拆分表ID
		OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
		ext.andLineIdEqualTo(Long.valueOf(line.getAttribute7()));
		ext.andMaterialIdEqualTo(line.getMaterialId());
		ext.andTypeEqualTo(type);
		List<OmOrderSpilts> spilts = this.spiltMapper.selectByExample(ex);
		if (spilts != null && spilts.size() == 1) {
			return spilts.get(0).getId();
		} else {
			return 0L;
		}
	}

	@Override
	public AjaxDTO selectBackOrderList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderBackDTO> backOrderList = this.backmapper.selectBackOrderList(params);
		int total = this.backmapper.countBackOrderList(params);
		dto.setRows(backOrderList);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO selectOldOrderLine(Long id) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderLinesDetials> list = this.backmapper.selectOldOrderLines(id);
		dto.setRows(list);
		return dto;
	}

	@Override
	public AjaxDTO selectEditLineData(Long headerid) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderBackSpiltDTO> spilts = this.backmapper.selectEditSpilts(headerid);
		dto.setRows(spilts);
		dto.setTotal(spilts.size());
		return dto;
	}

	@Override
	public AjaxDTO selectAuditLineData(Long id) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderLinesDetials> list = this.backmapper.selectAuditLineData(id);
		dto.setRows(list);
		return dto;
	}

	@Override
	public void editNum(Long spiltId, BigDecimal num) {
		// 更新拆分表数量
		OmOrderSpilts spilt = this.spiltMapper.selectByPrimaryKey(spiltId);
		spilt.setNum(num);
		spilt.setAmt(spilt.getPrice().multiply(num));
		this.spiltMapper.updateByPrimaryKey(spilt);

		// 修改订单行数量
		OmOrderLinesAll line = this.orderLineMapper.selectByPrimaryKey(spilt.getLineId());
		if ("1".equals(spilt.getType())) {
			// 标准行
			line.setNum(num);
			line.setOrderAmt(line.getOrderPrice().multiply(num));
			line.setAmt(line.getHbAmt().add(line.getOrderPrice().multiply(num)));
		} else if ("2".equals(spilt.getType())) {
			// 货补
			line.setHbNum(num);
			line.setHbAmt(line.getOrderPrice().multiply(num));
			line.setDiscountAmt(line.getOrderPrice().multiply(num));
			line.setAmt(line.getOrderAmt().add(line.getOrderPrice().multiply(num)));
		}
		this.orderLineMapper.updateByPrimaryKey(line);
		OmOrderHeadersAll header = this.orderHeaderMapper.selectByPrimaryKey(spilt.getHeaderId());
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(spilt.getHeaderId());
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);
		BigDecimal orderamt = BigDecimal.ZERO;
		BigDecimal hbamt = BigDecimal.ZERO;
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll l : lines) {
				orderamt = orderamt.add(l.getOrderAmt());
				hbamt = hbamt.add(l.getHbAmt());
			}
		}
		// 更新订单头金额
		header.setAmt(orderamt.add(hbamt));
		header.setOrderAmt(orderamt);
		header.setHbAmt(hbamt);
		header.setDiscountAmt(hbamt);
		this.orderHeaderMapper.updateByPrimaryKeySelective(header);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void startProcess(TEmployee user, Long id, String states) throws Exception {

		// 删除数量为0的拆分表数据
		OmOrderSpiltsExample spiltsEx = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria spiltsExt = spiltsEx.createCriteria();
		spiltsExt.andHeaderIdEqualTo(id);
		List<OmOrderSpilts> spilts = this.spiltMapper.selectByExample(spiltsEx);
		if (spilts != null && spilts.size() > 0) {
			for (OmOrderSpilts spilt : spilts) {
				if (spilt.getNum().compareTo(BigDecimal.ZERO) == 0) {
					this.spiltMapper.deleteByPrimaryKey(spilt.getId());
				}
			}
		}
		// 删除数量为0的行数据
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(id);
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll line : lines) {
				if (line.getNum().compareTo(BigDecimal.ZERO) == 0 && line.getHbNum().compareTo(BigDecimal.ZERO) == 0) {
					this.orderLineMapper.deleteByPrimaryKey(line.getId());
				}
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		String processName = "orderBack";
		param.put("key", id);
		param.put("name", "退货订单编号:" + id);
		param.put("startUser", user.getLoginName());
		param.put("groupType", "admin");
		param.put("level", "1");
		param.put("SKIP", 0);
		param.put("viewPage", "orderBack/orderBackAuditHtml.html?id=" + id);
		String processId = processService.startProcess(param, processName, user.getLoginName());
		OmOrderHeadersAll header = this.orderHeaderMapper.selectByPrimaryKey(id);
		header.setAttribute1(processId);

		// 修改状态
		header.setStates("2");
		this.orderHeaderMapper.updateByPrimaryKey(header);
	}

	@Override
	public String editAllocatePrice(Long id, BigDecimal price) {
		// 更新行表金额
		OmOrderLinesAll line = this.orderLineMapper.selectByPrimaryKey(id);
		line.setPrice(price);
		line.setOrderPrice(price);
		line.setOrderAmt(line.getNum().multiply(price));
		line.setHbAmt(line.getHbNum().multiply(price));
		line.setAmt(line.getHbAmt().add(line.getOrderAmt()));
		this.orderLineMapper.updateByPrimaryKey(line);
		// 更新拆分行金额
		OmOrderSpiltsExample spiltEx = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria spiltExt = spiltEx.createCriteria();
		spiltExt.andHeaderIdEqualTo(line.getHeaderId());
		spiltExt.andLineIdEqualTo(line.getId());
		List<OmOrderSpilts> spilts = this.spiltMapper.selectByExample(spiltEx);
		if (spilts != null && spilts.size() > 0) {
			for (OmOrderSpilts spilt : spilts) {
				spilt.setPrice(spilt.getType().equals("1") ? price : BigDecimal.ZERO);
				spilt.setAmt(spilt.getType().equals("1") ? spilt.getNum().multiply(price) : BigDecimal.ZERO);
				this.spiltMapper.updateByPrimaryKeySelective(spilt);
			}
		}
		// 更新订单头金额
		OmOrderHeadersAll header = this.orderHeaderMapper.selectByPrimaryKey(line.getHeaderId());
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(line.getHeaderId());
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);
		BigDecimal orderamt = BigDecimal.ZERO;
		BigDecimal hbamt = BigDecimal.ZERO;
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll l : lines) {
				orderamt = orderamt.add(l.getOrderAmt());
				hbamt = hbamt.add(l.getHbAmt());
			}
		}
		header.setAmt(orderamt.add(hbamt));
		header.setOrderAmt(orderamt);
		header.setHbAmt(hbamt);
		header.setDiscountAmt(hbamt);
		this.orderHeaderMapper.updateByPrimaryKeySelective(header);
		return "S";
	}

	@Override
	public String validateBackOrder(Long id) {
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(id);
		List<OmOrderLinesAll> orderLines = this.orderLineMapper.selectByExample(lineEx);
		if (orderLines != null && orderLines.size() > 0) {
			for (OmOrderLinesAll line : orderLines) {
				OmOrderLinesAll oldLine = this.orderLineMapper.selectByPrimaryKey(Long.parseLong(line.getAttribute7()));
				if (oldLine.getNum() != null && oldLine.getNum()
						.subtract(oldLine.getReturnNum() != null ? oldLine.getReturnNum() : BigDecimal.ZERO)
						.compareTo(line.getNum()) < 0) {
					// 原订单可退数量不足
					return "原订单可退数量不足";
				}
				if (oldLine.getHbNum() != null && oldLine.getHbNum()
						.subtract(oldLine.getRetrunHbNum() != null ? oldLine.getRetrunHbNum() : BigDecimal.ZERO)
						.compareTo(line.getHbNum()) < 0) {
					return "原订单可退货补数量不足";
				}
			}
		}
		return "S";
	}

	@Override
	public void updateOldOrderReturnNum(Long id) {
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(id);
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll line : lines) {
				OmOrderLinesAll oldLine = this.orderLineMapper.selectByPrimaryKey(Long.parseLong(line.getAttribute7()));
				oldLine.setReturnNum(
						oldLine.getReturnNum() != null ? oldLine.getReturnNum() : BigDecimal.ZERO.add(line.getNum()));
				oldLine.setRetrunHbNum(oldLine.getRetrunHbNum() != null ? oldLine.getRetrunHbNum()
						: BigDecimal.ZERO.add(line.getHbNum()));
				this.orderLineMapper.updateByPrimaryKeySelective(oldLine);
			}
		}

	}

	/**
	 * 
	 * @param header
	 *            合作仓储服务商退货订单
	 * @return
	 */
	@Override
	public void addStorageOrder(Long id) {
		// Map<String,Object> result = new HashMap<String, Object>();
		OmOrderHeadersAll header = this.orderHeaderMapper.selectByPrimaryKey(id);// 退货订单
		OmOrderHeadersAll oldOrder = this.orderHeaderMapper.selectByPrimaryKey(Long.parseLong(header.getAttribute13()));// 原合作仓储服务商调拨单
		OmOrderHeadersAll oldSaltOrder = this.orderHeaderMapper
				.selectByPrimaryKey(Long.parseLong(oldOrder.getAttribute13()));// 原合作仓储服务商调拨单，对应的合作盐业公司订单
		if (oldSaltOrder == null) {
			return;
		}
		// 新增合作合作盐业公司的退货头数据
		OmOrderHeadersAll newBackSaltOrder = new OmOrderHeadersAll();
		newBackSaltOrder.setShipId(oldSaltOrder.getShipId());
		newBackSaltOrder.setMerchCustId(oldSaltOrder.getMerchCustId());
		newBackSaltOrder.setCreateTs(new Date());
		newBackSaltOrder.setCreateOid(header.getCreateOid());
		newBackSaltOrder.setOrganizationId(oldSaltOrder.getOrganizationId());
		newBackSaltOrder.setStates("2");
		newBackSaltOrder.setStationId(oldSaltOrder.getStationId());
		newBackSaltOrder.setOrderType("3");
		newBackSaltOrder.setSource("1");
		newBackSaltOrder.setAttribute13(oldSaltOrder.getId().toString());// 原订单编号
		newBackSaltOrder.setRegionId(oldSaltOrder.getRegionId());
		newBackSaltOrder.setProviId(oldSaltOrder.getProviId());
		newBackSaltOrder.setSalesrepId(oldSaltOrder.getSalesrepId());
		newBackSaltOrder.setAttribute6(header.getAttribute6());// 退货原因
		newBackSaltOrder.setAttribute2(header.getId().toString());// 对应的合作仓储服务商退货订单
		newBackSaltOrder.setRdcCode(oldSaltOrder.getRdcCode());
		this.orderHeaderMapper.insert(newBackSaltOrder);

		BigDecimal orderAmt_all = BigDecimal.ZERO;
		BigDecimal amt_all = BigDecimal.ZERO;
		BigDecimal hbAmt_all = BigDecimal.ZERO;
		List<OmOrderLinesAll> storgageBacklines = new ArrayList<OmOrderLinesAll>();

		// 获取退货行数据
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(header.getId());
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);

		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal amt = BigDecimal.ZERO;
		BigDecimal hbAmt = BigDecimal.ZERO;

		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll l : lines) {
				// 新增合作盐业公司对应的退货行
				OmOrderLinesAll backSaltline = new OmOrderLinesAll();
				backSaltline.setHeaderId(newBackSaltOrder.getId());
				backSaltline.setMaterialId(l.getMaterialId());
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("headerId", Long.parseLong(newBackSaltOrder.getAttribute13()));
				params.put("materialId", l.getMaterialId());
				BigDecimal price = this.backmapper.selectStoragePrice(params);
				backSaltline.setPrice(price);
				backSaltline.setOrderPrice(price);
				backSaltline.setNum(l.getNum() != null ? l.getNum() : BigDecimal.ZERO);
				backSaltline.setHbNum(l.getHbNum() != null ? l.getHbNum() : BigDecimal.ZERO);
				hbAmt = price.multiply(backSaltline.getHbNum());
				orderAmt = price.multiply(backSaltline.getNum());
				amt = hbAmt.add(orderAmt);
				backSaltline.setOrderPrice(price);
				backSaltline.setHbAmt(hbAmt);
				backSaltline.setOrderAmt(orderAmt);
				backSaltline.setAmt(amt);
				backSaltline.setDiscountAmt(hbAmt);
				backSaltline.setStates("2");
				backSaltline.setCreateTs(new Date());
				backSaltline.setCreateOid(header.getCreateOid());
				backSaltline.setUnit(l.getUnit());
				backSaltline.setAttribute7(l.getId().toString());// 原
				this.orderLineMapper.insert(backSaltline);
				if (l.getNum() != null && l.getNum().compareTo(BigDecimal.ZERO) >0) {
					backSaltline.setAttribute1("S");// 拆分行创建标准行
				}
				if (l.getHbNum() != null && l.getHbNum().compareTo(BigDecimal.ZERO) >0) {
					backSaltline.setAttribute2("S");// 拆分行创建货补行
				}
				hbAmt_all = hbAmt_all.add(hbAmt);
				orderAmt_all = orderAmt_all.add(amt);
				amt_all = amt_all.add(amt);
				storgageBacklines.add(backSaltline);
			}
		}
		// 更新头表金额
		newBackSaltOrder.setAmt(amt_all);
		newBackSaltOrder.setOrderAmt(orderAmt_all);
		newBackSaltOrder.setHbAmt(hbAmt_all);
		newBackSaltOrder.setDiscountAmt(hbAmt_all);
		this.orderHeaderMapper.updateByPrimaryKey(newBackSaltOrder);
		// 插入拆分表
		this.spiltline(storgageBacklines, newBackSaltOrder);
		header.setAttribute2(newBackSaltOrder.getId().toString());
		this.orderHeaderMapper.updateByPrimaryKeySelective(header);

		// 删除数量为0的拆分表数据
		OmOrderSpiltsExample spiltsEx = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria spiltsExt = spiltsEx.createCriteria();
		spiltsExt.andHeaderIdEqualTo(newBackSaltOrder.getId());
		List<OmOrderSpilts> spilts = this.spiltMapper.selectByExample(spiltsEx);
		if (spilts != null && spilts.size() > 0) {
			for (OmOrderSpilts spilt : spilts) {
				if (spilt.getNum().compareTo(BigDecimal.ZERO) == 0) {
					this.spiltMapper.deleteByPrimaryKey(spilt.getId());
				}
			}
		}
		// 删除数量为0的行数据
		OmOrderLinesAllExample saltlineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria saltlineExt = lineEx.createCriteria();
		saltlineExt.andHeaderIdEqualTo(newBackSaltOrder.getId());
		List<OmOrderLinesAll> saltlines = this.orderLineMapper.selectByExample(saltlineEx);
		if (saltlines != null && saltlines.size() > 0) {
			for (OmOrderLinesAll line : saltlines) {
				if (line.getNum().compareTo(BigDecimal.ZERO) == 0 && line.getHbNum().compareTo(BigDecimal.ZERO) == 0) {
					this.orderLineMapper.deleteByPrimaryKey(line.getId());
				}
			}
		}
	}
}
