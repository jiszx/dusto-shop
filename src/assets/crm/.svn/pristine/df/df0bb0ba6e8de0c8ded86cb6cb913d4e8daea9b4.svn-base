package com.hhnz.customer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.model.CMerchCustProductV;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.util.AjaxDTO;

public interface ICustomerService {

	/**
	 * 添加客户
	 * @param custBase
	 * @return
	 * @throws Exception 
	 */
	public CMerchCustBase add(CMerchCustBase custBase);

	/**
	 * 根据员工查询其岗位对应客户
	 * @param empId can not be null!
	 * @return
	 */
	public List<CMerchCustBase> findCustsByEmployee(Long empId);

	AjaxDTO findCustSkuPrice(AjaxDTO bean, CMerchCustProductV cust) throws RuntimeException;

	/**
	 * 根据条件分页查询客户信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月15日  下午5:31:13
	 * @param customerBasedto
	 * @param bean
	 * @return
	 */
	public AjaxDTO findCustBase(CustomerBaseDTO customerBasedto, AjaxDTO bean, List<Long> stations);

	int editSkuPrice(CMerchCustProduct product);

	/**
	 * 根据客户ID查找对应的客户信息
	 * 包含送达方信息、岗位信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月16日  下午2:08:25
	 * @param id
	 * @return
	 */
	public CMerchCustBase findCustBaseById(Long id);
	
	/**
	 * 根据客户ID查找对应的客户基本信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月1日  上午9:38:29
	 * @param id
	 * @return
	 */
	public CMerchCustBase findSimpleCustBaseById(Long id);

	/**
	 * 修改客户信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月17日  上午9:40:27
	 * @param custBase
	 * @param delDists 
	 */
	public void update(CMerchCustBase custBase, String delDists);

	/**
	 * 删除客户信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月17日  下午6:07:11
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 保存客户信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月18日  上午11:57:54
	 * @param custBase
	 */
	public CMerchCustBase save(CMerchCustBase custBase);
	
	/**
	 * 查看是否已存在营业执照号
	 * @author: chaoyang.ren 
	 * @date:2016年8月22日  下午2:04:36
	 * @param taxNo
	 * @param id
	 * @return
	 */
	public boolean isLicenseNoExisted(String taxNo, Long id);

	/**
	 * 设置客户岗位
	 * @author: chaoyang.ren 
	 * @date:2016年8月24日  下午6:04:44
	 * @param custStation
	 */
	public void setPosition(CMerchCustStation custStation);

	/**
     * 根据   模糊条件    分页查询客户信息
	 * @author: tangdonghui
	 * @date:2016年9月2日  下午2:12:13
	 * @param customerBasedto
	 * @param bean
	 * @param arealist
	 * @param nameList
	 * @param custTypeList
	 * @return
	 */
	public AjaxDTO findCustBaseVague(CustomerBaseDTO customerBasedto, AjaxDTO bean, String[] areaList,
			String name, String[] custTypeList,String[] statusList, List<Long> stations);

	int importRetail(String[][] text, TEmployee user, Long pid, UserStations station);

	AjaxDTO findRetails(CustomerBaseDTO customerBasedto, AjaxDTO bean, Long merchid);
  	
    /**
     * 更新客户物料(盐业公司新增物料时，手工更新客户的物料数据)
     * @return
     */
    public Integer upMaterial();

	/**
	 * 根据岗位获取客户
	 * @author: chaoyang.ren 
	 * @date:2016年11月8日  上午10:02:35
	 * @param stationIds
	 * @return
	 */
	public List<CMerchCustBase> findCustBaseByStationId(List<Long> stationIds);

  CMerchCustBaseV findCustWithPosition(Long merchid);

  String generateExcel(List<Long> stations) throws IOException;

   public AjaxDTO selectRetailCustomers(TEmployee user, List<Long> stationids, AjaxDTO bean, String orgid, String custname);

  int changeEnableStatus(Long merchProductId);

  String generateRetailExcel(TEmployee user, List<Long> stationids, AjaxDTO bean, String orgid,
      String custname) throws IOException;

  public String updateStates(Long merchId, String states);

  public String addProductPrice(Long id, BigDecimal hPrice, String priceBdate,
		String priceEdate) throws ParseException;

public AjaxDTO getsaltPrice(AjaxDTO bean);

public Integer editsaltPrice(Long id, BigDecimal price);

	
}
