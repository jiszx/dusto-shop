package com.hhnz.encrypt.service.impl;

import com.hhnz.encrypt.mapper.TEncryptKeyMapper;
import com.hhnz.encrypt.model.TEncryptKey;
import com.hhnz.encrypt.model.TEncryptKeyExample;
import com.hhnz.encrypt.service.IEncryptService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 杨 on 2017/1/7.
 */
@Service
@Transactional
public class EncryptServiceImpl implements IEncryptService {

    @Autowired
    private TEncryptKeyMapper mapper;

    @Override
    public AjaxDTO findEncrypts(HashMap<String, Object> stringObjectHashMap) throws Exception {
        AjaxDTO bean = new AjaxDTO();
        TEncryptKeyExample ex =new TEncryptKeyExample();
//        if(!(bean.getLimit() == 0 && bean.getOffset() == 0)){
//            Page page = new Page();
//            page.setLimit(bean.getLimit());
//            page.setOffset(bean.getOffset());
//            ex.setPage(page);
//        }
        ex.setOrderByClause("id desc");
//        if(StringUtils.hasLength(bean.getSearch())){
//            ex.createCriteria().andKeyNameLike(bean.getSearch()+"%");
//        }
        List<TEncryptKey> list = this.mapper.selectByExample(ex);
        int total = this.mapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }
}
