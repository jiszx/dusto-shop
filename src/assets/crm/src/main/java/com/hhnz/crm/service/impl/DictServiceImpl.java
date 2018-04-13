package com.hhnz.crm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.mapper.TDictMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TDictExample;
import com.hhnz.crm.service.IDictService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016/6/24.
 */
@Service
@Transactional
public class DictServiceImpl implements IDictService {

    @Autowired
    private TDictMapper mapper;

    @Override
    public AjaxDTO findDict(HashMap<String, Object> stringObjectHashMap, AjaxDTO bean) throws Exception {
        TDictExample ex =new TDictExample();
        if(!(bean.getLimit() == 0 && bean.getOffset() == 0)){
            Page page = new Page();
            page.setLimit(bean.getLimit());
            page.setOffset(bean.getOffset());

            ex.setPage(page);
        }
        ex.setOrderByClause("col_name desc,orders asc");
        if(StringUtils.hasLength(bean.getSearch())){
            ex.createCriteria().andColNameLike(bean.getSearch()+"%");
        }
        List<TDict> list = this.mapper.selectByExample(ex);
        int total = this.mapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public int addDict(TDict model) throws Exception {
        return this.mapper.insert(model);
    }

    @Override
    public Integer updateDict(TDict model) throws Exception {
        return this.mapper.updateByPrimaryKey(model);
    }

    @Override
    public Integer delDict(Long id) throws Exception {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public TDict findById(Long id) throws Exception {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TDict> findAll() throws Exception {
        TDictExample ex = new TDictExample();
        ex.createCriteria().andCanChooseEqualTo("1");
        ex.setOrderByClause("col_name asc,orders asc");
        return this.mapper.selectByExample(ex);
    }

	@Override
	public TDict findByCityId(Long cityId) {
		return mapper.findByCityId(cityId);
	}
	
	@Override
    public List<TDict> findByName(String name){
      TDictExample ex = new TDictExample();
      ex.createCriteria().andColNameEqualTo(name);
      return mapper.selectByExample(ex);
    }
	
	@Override
    public String code2Str(String code, List<TDict> dict) {
	  if(code==null){
	    return null;
	  }
      for (TDict di : dict) {
        if (code.equals(di.getChooseVal())) {
          return di.getShowText();
        }
      }
      return null;
    }
    
}
