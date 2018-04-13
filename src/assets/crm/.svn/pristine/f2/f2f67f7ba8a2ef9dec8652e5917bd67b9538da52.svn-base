package com.hhnz.customer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.dto.CustomerInvDTO;
import com.hhnz.customer.dto.CustomerInvResultDTO;
import com.hhnz.customer.mapper.CMerchCustInvMapper;
import com.hhnz.customer.model.CMerchCustInv;
import com.hhnz.customer.service.ICustomerInvService;
import com.hhnz.rmi.db.model.inventory.CustInventory;
import com.hhnz.rmi.db.repository.inventory.CustInventoryRepository;
import com.hhnz.rmi.db.util.DynamicSpecifications;
import com.hhnz.rmi.db.util.SearchFilter;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class CustomerInvServiceImpl implements ICustomerInvService{

	@Resource
    private CMerchCustInvMapper customerInvMapper;
	@Resource
	private CustInventoryRepository custInventoryRepository;
	

	@Override
	public AjaxDTO find(CustomerInvDTO inv, AjaxDTO bean) {
		List<CustomerInvResultDTO> result = customerInvMapper.selectByDTO(inv,bean.getOffset(),bean.getOffset()+bean.getLimit());
		int total = customerInvMapper.countByDTO(inv,bean.getOffset(),bean.getOffset()+bean.getLimit());
		bean.setRows(result);
        bean.setTotal(total);
		return bean;
	}

	@Override
	public int add(CMerchCustInv inv) {
		return customerInvMapper.insertSelective(inv);
	}

	@Override
	public int save(CMerchCustInv inv) {
		return customerInvMapper.updateByPrimaryKeySelective(inv);
	}

	@Override
	public int delete(Long id) {
		return customerInvMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<CustInventory> findByCondition(Map<String, Object> searchParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    	Specification<CustInventory> spec = DynamicSpecifications.bySearchFilter(filters.values(), CustInventory.class);
    	return custInventoryRepository.findAll(spec, pageable);
	}

	@Override
	public CustInventory findById(Long id) {
		return custInventoryRepository.findOne(id);
	}
	
}
