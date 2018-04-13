package com.hhnz.organization.service.impl;

import com.hhnz.customer.mapper.CMerchCustBaseVMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustBaseVExample;
import com.hhnz.organization.mapper.CompanyAreaMapper;
import com.hhnz.organization.service.ICompanyAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 杨 on 2016/12/28.
 */
@Service
@Transactional
public class CompanyAreaServiceImpl implements ICompanyAreaService {

    @Autowired
    private CMerchCustBaseVMapper custBaseVMapper;

    @Autowired
    private CompanyAreaMapper mapper;

    @Override
    public List<CMerchCustBaseV> findCompany() throws Exception {

        CMerchCustBaseVExample ex = new CMerchCustBaseVExample();
        ex.createCriteria().andCustTypeEqualTo("1").andStatesEqualTo("3").andSapCustomerIdIsNotNull();//合作盐业公司
        return this.custBaseVMapper.selectByExample(ex);
    }

    @Override
    public List<String> findCompanyArea(String id) throws Exception {

        List<String> aids = this.mapper.findCompanyAreaByiId(id);
        return aids;
    }

    @Override
    public Integer grantArea(String id, List<String> auths) throws Exception {
        int res = this.mapper.deleteByCid(id);
        if(res != -1){
            if(auths !=null && auths.size() > 0){
                for(String aid:auths){
                    res +=this.mapper.insert(id,aid);
                }
            }
        }
        return res;
    }
}
