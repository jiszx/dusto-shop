package com.hhnz.config.service.impl;

import com.hhnz.config.service.IModelAndBrandService;
import com.hhnz.crm.mapper.TDictMapper;
import com.hhnz.crm.mapper.TMaterialBaseVMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TDictExample;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.customer.mapper.CrmBusinessModelMapper;
import com.hhnz.customer.model.CrmBusinessModel;
import com.hhnz.customer.model.CrmBusinessModelExample;
import com.hhnz.pub.model.TArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 杨 on 2016/12/20.
 */
@Service
@Transactional
public class ModelAndBrandServiceImpl implements IModelAndBrandService {

    @Autowired
    private TDictMapper mapper;

    @Autowired
    private TMaterialBaseVMapper brandMapper;

    @Autowired
    private CrmBusinessModelMapper businessModelMapper;

    @Override
    public List<TDict> findDictByName(String virtual_warehouse_code) throws Exception {
        TDictExample ex = new TDictExample();
        ex.createCriteria().andColNameEqualTo(virtual_warehouse_code);
        return this.mapper.selectByExample(ex);
    }

    @Override
    public List<String> findBrand() throws Exception {
        List<TMaterialBaseV> baseVs = this.brandMapper.selectVicebrand();
        List<String> list = new ArrayList<String>();
        for (TMaterialBaseV vv:baseVs) {
            list.add(vv.getBrand());
        }
        return list;
    }

    @Override
    public List<String> findModelBrand(String modelId) throws Exception {

        CrmBusinessModelExample ex = new CrmBusinessModelExample();
        ex.createCriteria().andModelIdEqualTo(modelId);
        List<CrmBusinessModel> models = this.businessModelMapper.selectByExample(ex);
        List<String> list = new ArrayList<String>();
        for (CrmBusinessModel vv:models) {
            list.add(vv.getBrand());
        }
        return list;
    }

    @Override
    public Integer modelGrand(String id, List<String> auths) throws Exception {
        CrmBusinessModelExample ex = new CrmBusinessModelExample();
        ex.createCriteria().andModelIdEqualTo(id);
        int res = this.businessModelMapper.deleteByExample(ex);
        if(res != -1){
            if(auths !=null && auths.size()>0){
                for(String aid:auths){
                    CrmBusinessModel model = new CrmBusinessModel();
                    model.setBrand(aid);
                    model.setModelId(id);
                    model.setCreateTs(new Date());
                    res +=this.businessModelMapper.insert(model);
                }
            }else{
                return res;
            }

        }
        return res;
    }
}
