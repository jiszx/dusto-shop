package com.hhnz.organization.service.impl;

import com.hhnz.organization.mapper.CompanyOrgMapper;
import com.hhnz.organization.service.ICompanySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 杨 on 2016/12/27.
 */
@Service
@Transactional
public class CompanySetServiceImpl implements ICompanySetService {

    @Autowired
    private CompanyOrgMapper mapper;

    @Override
    public List<String> findCompanyOrg(String id) throws Exception {
        List<String> res = this.mapper.selectByCompanyID(id);
        return res;
    }

    @Override
    public Integer grantOrg(String id, List<String> auths) throws Exception {
        int res = this.mapper.deleteByCid(id);
        if(res != -1){
            for(String aid:auths){
                res +=this.mapper.insert(id,aid);
            }
        }
        return res;
    }

    @Override
    public String findCompanyCode(String id) throws Exception {
        return this.mapper.selectBySalesOrg(id);
    }
}
