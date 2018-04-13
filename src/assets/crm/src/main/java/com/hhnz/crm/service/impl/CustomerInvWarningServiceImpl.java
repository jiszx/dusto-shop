package com.hhnz.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.service.ICustomerInvWarningService;
import com.hhnz.rmi.db.model.customer.CustInvWarning;
import com.hhnz.rmi.db.model.customer.CustInvWarningItem;
import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.inventory.CustInventory;
import com.hhnz.rmi.db.repository.customer.CustInvWarningItemRepository;
import com.hhnz.rmi.db.repository.customer.CustInvWarningRepository;
import com.hhnz.rmi.db.repository.customer.CustomerRepository;
import com.hhnz.rmi.db.util.DynamicSpecifications;
import com.hhnz.rmi.db.util.SearchFilter;

/**
 * @author: chaoyang.ren
 * @date:Mar 17, 2017
 * @time:11:05:06 AM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service
@Transactional
public class CustomerInvWarningServiceImpl implements ICustomerInvWarningService {

	@Resource
	private CustomerRepository customerRepository;
	
	@Resource
	private CustInvWarningRepository custInvWarningRepository;
	
	@Resource
	private CustInvWarningItemRepository custInvWarningItemRepository;

	@Override
	public Page<Customer> findByCondition(Map<String, Object> searchParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    	Specification<Customer> spec = DynamicSpecifications.bySearchFilter(filters.values(), Customer.class);
    	return customerRepository.findAll(spec, pageable);
	}

	@Override
	public CustInvWarning findByCustomerId(Long custId) {
		return custInvWarningRepository.findByCustomerId(custId);
	}

	@Override
	public CustInvWarningItem findItemByCustInventory(CustInventory ci) {
		return custInvWarningItemRepository.findByCustInventory(ci);
	}

	@Override
	public CustInvWarningItem saveItem(CustInvWarningItem ciwi) {
		custInvWarningRepository.save(ciwi.getCustInvWarning());
		return custInvWarningItemRepository.save(ciwi);
	}

	@Override
	public void save(List<CustInvWarning> custInvWarningList) {
		custInvWarningRepository.save(custInvWarningList);
	}
	
	
}
