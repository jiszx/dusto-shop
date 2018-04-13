package com.hhnz.customer.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hhnz.customer.dto.CustomerInvDTO;
import com.hhnz.customer.model.CMerchCustInv;
import com.hhnz.rmi.db.model.inventory.CustInventory;
import com.hhnz.util.AjaxDTO;

public interface ICustomerInvService {

	/**
	 * 查询库存上报信息
	 * @param inv
	 * @param bean
	 * @return
	 */
	public AjaxDTO find(CustomerInvDTO inv, AjaxDTO bean);

	/**
	 * 库存上报信息添加
	 * @param inv
	 * @return
	 */
	public int add(CMerchCustInv inv);

	/**
	 * 库存上报信息修改
	 * @param inv
	 * @return
	 */
	public int save(CMerchCustInv inv);

	/**
	 * 库存上报信息删除
	 * @param id
	 * @return
	 */
	public int delete(Long id);

	public Page<CustInventory> findByCondition(Map<String, Object> searchParams, Pageable pageable);

	public CustInventory findById(Long id);

	
}
