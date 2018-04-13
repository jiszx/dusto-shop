package com.hhnz.crm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TEmployeeExample;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-6-27.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private TEmployeeMapper mapper;
    @Resource
    private CMerchCustBaseMapper custMapper;

    @Override
    public TEmployee findByPK(String username) throws Exception {
        TEmployeeExample ex = new TEmployeeExample();
        ex.createCriteria().andLoginNameEqualTo(username);
        List<TEmployee> emps = this.mapper.selectByExample(ex);
        if(emps !=null && emps.size() == 1){
            return emps.get(0);
        }else{
            return null;
        }
    }

    @Override
    public AjaxDTO findUserByCondation(Map<String, Object> map, AjaxDTO bean) throws Exception {
        Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        TEmployeeExample ex =new TEmployeeExample();
        ex.setPage(page);
        ex.setOrderByClause("id");
        TEmployeeExample.Criteria exc = ex.createCriteria();
        if(StringUtils.hasLength(bean.getSearch())){
            exc.andNameLike(bean.getSearch()+"%");
        }
        if(map.containsKey("org")){
            exc.andOrganizationIdLike((String)map.get("org")+"%");
        }
        if(map.containsKey("merchid")){
          exc.andMerchIdEqualTo((Long) map.get("merchid"));
        }
        if(map.containsKey("role")){
            exc.andRoleIdEqualTo((Long)map.get("role"));
        }
        if(StringUtils.hasLength((String)map.get("userType"))){
          exc.andUserTypeEqualTo((String)map.get("userType"));
        }else{
          exc.andUserTypeLessThan("1");
        }
        if(StringUtils.hasLength((String)map.get("states"))){
          exc.andStatesEqualTo((String)map.get("states"));
        }else{
          exc.andStatesGreaterThanOrEqualTo("0");
        }
        List<TEmployee> list = this.mapper.selectByExample(ex);
        int total = this.mapper.countByExample(ex);
        for(TEmployee emp:list){
          if(emp.getMerchId()!=null && emp.getMerchId()!=0L){
            CMerchCustBase merch = custMapper.selectByPrimaryKey(emp.getMerchId());
            emp.setMerchName(merch == null ? null : merch.getName());
          }
        }
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public Integer addEmployee(TEmployee model) throws Exception {
        return this.mapper.insert(model);
    }

    @Override
    public Integer updateEmployee(TEmployee model) throws Exception {
        TEmployeeExample ex = new TEmployeeExample();
        ex.createCriteria().andIdEqualTo(model.getId());
        return this.mapper.updateByExampleSelective(model,ex);
    }

    @Override
    public Integer delEmployee(Long id) throws Exception {
        TEmployeeExample ex = new TEmployeeExample();
        ex.createCriteria().andIdEqualTo(id);
        TEmployee model = this.mapper.selectByPrimaryKey(id);
        model.setStates("-1");
        model.setLoginName("_"+model.getLoginName());
        return this.mapper.updateByPrimaryKey(model);
    }

    @Override
    public TEmployee findById(Long id) throws Exception {
        return this.mapper.selectByPrimaryKey(id);
    }

	@Override
	public int checkUser(String userName) throws Exception {
		TEmployeeExample ex = new TEmployeeExample();
		ex.createCriteria().andLoginNameEqualTo(userName);
		return this.mapper.countByExample(ex);
	}
	
	@Override
	public int generateUser(CMerchCustBase merch, String loginName) throws Exception{
      String custType = merch.getCustType();
      if(!"2".equals(custType) && !"7".equals(custType) && !"70".equals(custType)){
        return 0;
      }
      if(StringUtils.isEmpty(loginName)){
        loginName=String.valueOf(merch.getId());
      }
      Md5PasswordEncoder encoder = new Md5PasswordEncoder();
      String password = encoder.encodePassword("12345678", null);
      TEmployee user = new TEmployee();
      user.setOrganizationId(merch.getOrganizationId());
      user.setName(merch.getName());
      user.setLoginName(loginName);
      user.setIsSalesman("0");
      user.setCreateTs(new Date());
      user.setStates("1");
      user.setUserType("1");
      user.setMerchId(merch.getId());
      user.setEmail("default");
      user.setPasswd(password);
      user.setRoleId(99901L);
      return addEmployee(user);
    }
}
