package com.hhnz.sapdata.service.impl;

import com.hhnz.sapdata.mapper.TProductCategoryMapper;
import com.hhnz.sapdata.model.TProductCategory;
import com.hhnz.sapdata.model.TProductCategoryExample;
import com.hhnz.sapdata.service.ISAPDataService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by yang on 2016-7-26.
 */
@Service
@Transactional
public class SAPDataServiceImpl implements ISAPDataService {

    @Autowired
    private TProductCategoryMapper cateMapper;

    @Override
    public List<TProductCategory> findCategoryByPid(String id) throws Exception {
        TProductCategoryExample ex = new TProductCategoryExample();
        if(StringUtils.hasLength(id)){
            ex.createCriteria().andPidEqualTo(id);
        }else{
            ex.createCriteria().andPidIsNull();
        }
        ex.setOrderByClause("id");
        return this.cateMapper.selectByExample(ex);
    }
    
    @Override
    public List<TProductCategory> findAllCategoryByPid() throws Exception {
      TProductCategoryExample ex = new TProductCategoryExample();
      return this.cateMapper.selectByExample(ex);
    }

    @Override
    public Integer addCate(TProductCategory model) throws Exception {
        TProductCategoryExample ex = new TProductCategoryExample();
        ex.createCriteria().andPidEqualTo(model.getPid());
        ex.setOrderByClause("id desc");
        List<TProductCategory> orgs = this.cateMapper.selectByExample(ex);
        if(orgs.size()>0){
            TProductCategory maxOrg = orgs.get(0);
            String maxId = maxOrg.getId();
            String order = maxId.replaceAll(model.getPid(),"");
            if(order.startsWith("0")){
            	order=order.replaceAll("0","");
            }
            Integer orderNum = Integer.parseInt(order);
            int newId = orderNum+1;
            if(newId > 10){
            	String id = model.getPid()+String.format("%02d",newId);
                model.setId(id);
            }else{
            	String id = model.getPid()+String.format("%2d",newId);
                model.setId(id);
            }
            String id = model.getPid()+String.format("%02d",newId);
            model.setId(id);
        }else{
            model.setId(model.getPid()+"01");
        }
        return this.cateMapper.insert(model);
    }

    @Override
    public Integer updateCate(TProductCategory model) throws Exception {
        return this.cateMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Integer delCate(String id) throws Exception {
        TProductCategoryExample ex = new TProductCategoryExample();
        ex.createCriteria().andIdLike(id+"%");
        return this.cateMapper.deleteByExample(ex);
    }
    
    @Override
    public List<TProductCategory> findCategorys(){
      return cateMapper.getCategorys();
    }

	@Override
	public TProductCategory findCategoryById(String name) {
		TProductCategoryExample ex = new TProductCategoryExample();
		ex.createCriteria().andIdEqualTo(name);
	    return cateMapper.selectByExample(ex).get(0);
	}
}
