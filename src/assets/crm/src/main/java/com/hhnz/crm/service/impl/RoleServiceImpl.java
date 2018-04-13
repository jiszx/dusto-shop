package com.hhnz.crm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.model.*;
import com.hhnz.crm.model.TRoleExample.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.mapper.TRoleAuthMapper;
import com.hhnz.crm.mapper.TRoleMapper;
import com.hhnz.crm.service.IRoleService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-6-27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private TRoleMapper mapper;

    @Autowired
    private TEmployeeMapper employeeMapper;

    @Autowired
    private TRoleAuthMapper authMapper;

    @Override
    public AjaxDTO findRole(HashMap<String, Object> stringObjectHashMap, AjaxDTO bean) throws Exception {
        Page page = new Page();
        TRoleExample ex = new TRoleExample();
        if(bean.getLimit() > 0){
            page.setLimit(bean.getLimit());
            page.setOffset(bean.getOffset());
            ex.setPage(page);
        }
        Criteria criteria = ex.createCriteria();
		if (StringUtils.hasLength(bean.getSearch())) {
            criteria.andIdGreaterThan(1L).andRoleNameLike(bean.getSearch() + "%");
        }
        String orgId = (String) stringObjectHashMap.get("orgId");
        if(StringUtils.hasLength(orgId)){
            criteria.andOrgIdEqualTo(orgId);
//        	criteria.andOrgIdLike(orgId + "%");
        }
        ex.createCriteria().andIdGreaterThan(1L);
        ex.setOrderByClause("role_type asc,id desc");
        List<TRole> list = this.mapper.selectByExample(ex);
        for (TRole role:list) {
            int count = this.mapper.countRoleUser(role.getId());
            role.setRoleCount(count);
        }
        int total = this.mapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public Integer addRole(TRole model) throws Exception {
        model.setCreateTs(new Date());
        Integer res = this.mapper.insertSelective(model);
        return res;
    }

    @Override
    public Integer updateRole(TRole model) throws Exception {
        TRoleExample ex = new TRoleExample();
        ex.createCriteria().andIdEqualTo(model.getId());
        return this.mapper.updateByExampleSelective(model, ex);
    }

    @Override
    public Integer delRole(Long id) throws Exception {
        TEmployeeExample ex = new TEmployeeExample();
        ex.createCriteria().andRoleIdEqualTo(id);
        int count = employeeMapper.countByExample(ex);
        if(count > 0){
            return -1;
        }else{
            return this.mapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<Long> findRoleAuths(Long id) throws Exception {
        return this.mapper.selectAuthsOfRole(id);
    }

    @Override
    public Integer grantRole(Long id, List<Integer> auths) throws Exception {
        TRoleAuthExample ex = new TRoleAuthExample();
        ex.createCriteria().andRoleIdEqualTo(id);
        int c = this.authMapper.deleteByExample(ex);
        if (c != -1) {
            int res = 0;
            for (Integer auth : auths) {
                res += this.authMapper.insert(new TRoleAuthKey(id, auth.longValue()));
            }
            return res;
        } else {
            return 0;
        }
    }
}
