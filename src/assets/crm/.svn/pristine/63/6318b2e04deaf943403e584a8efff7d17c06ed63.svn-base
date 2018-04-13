package com.hhnz.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hhnz.rmi.db.model.customer.CustInvWarning;
import com.hhnz.rmi.db.model.customer.CustInvWarningItem;
import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.inventory.CustInventory;

/**
 * @author: chaoyang.ren
 * @date:Apr 17, 2017
 * @time:2:11:29 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
public interface ICustomerInvWarningService {

	/**
	 * 物流商预警列表分页
	 * @author: chaoyang.ren 
	 * @date:Apr 18, 2017  10:40:34 AM
	 * @param searchParams
	 * @param pageable
	 * @return
	 */
	Page<Customer> findByCondition(Map<String, Object> searchParams, Pageable pageable);

	CustInvWarning findByCustomerId(Long id);

	CustInvWarningItem findItemByCustInventory(CustInventory ci);

	CustInvWarningItem saveItem(CustInvWarningItem ciwi);

	void save(List<CustInvWarning> custInvWarningList);
	
}
