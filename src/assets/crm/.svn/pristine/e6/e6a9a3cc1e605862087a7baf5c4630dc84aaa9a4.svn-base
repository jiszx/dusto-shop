package com.hhnz.crm.service.impl;

import com.hhnz.crm.mapper.TAuthorityMapper;
import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TAuthorityExample;
import com.hhnz.crm.model.TAuthorityExt;
import com.hhnz.crm.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang on 2016-6-26.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private TAuthorityMapper mapper;

    @Override
    public List<TAuthority> findAll() throws Exception {
        TAuthorityExample ee = new TAuthorityExample();
        ee.setOrderByClause("res_type asc,orders asc");
        return this.mapper.selectByExample(ee);
    }

    @Override
    public List<TAuthorityExt> findRoleAuth() throws Exception {
        return this.mapper.selectRoleAuth();
    }

    @Override
    public List<TAuthority> findPubAuth() throws Exception {
        TAuthorityExample ex = new TAuthorityExample();
        ex.createCriteria().andXtypeEqualTo("1");
        return this.mapper.selectByExample(ex);
    }

	@Override
	public int update(TAuthority bean) throws Exception {
		return this.mapper.updateByPrimaryKeySelective(bean);
	}

    @Override
    public int add(TAuthority bean) throws Exception {
        return this.mapper.insert(bean);
    }

    @Override
    public int delete(Long resId) throws Exception {
        return this.mapper.deleteByPrimaryKey(resId);
    }

    @Override
    public int updateAll(List<TAuthority> auths) throws Exception {
        int res = 0;
        if(auths !=null && auths.size() >0){
            for (TAuthority auth:auths) {
                TAuthority tmp = this.mapper.selectByPrimaryKey(auth.getResId());
                if(tmp !=null){
                    if(auth.getResUrl() !=null && auth.getResUrl().length() < tmp.getResUrl().length()){
                        auth.setResUrl(tmp.getResUrl());
                    }
                    if(auth.getOtherRes() !=null  && (tmp.getOtherRes() ==null ||auth.getOtherRes().length() < tmp.getOtherRes().length())){
                        auth.setOtherRes(tmp.getOtherRes());
                    }
                    res += this.mapper.updateByPrimaryKeySelective(auth);
                }else{
                    res +=this.mapper.insert(auth);
                }
            }
        }
        return 0;
    }
}
