package com.hhnz.config.service.impl;

import com.hhnz.config.service.IAreaRDCConfigService;
import com.hhnz.crm.mapper.TDictMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TDictExample;
import com.hhnz.pub.mapper.AreaRDCMapper;
import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.pub.model.TArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 杨 on 2016/12/16.
 */
@Service
@Transactional
public class AreaRDCConfigServiceimpl implements IAreaRDCConfigService {


    @Autowired
    private TDictMapper mapper;

    @Autowired
    private AreaRDCMapper rdcMapper;

    @Autowired
    private TAreaMapper areaMapper;

    @Override
    public List<TDict> findDictByName(String virtual_warehouse_code) throws Exception {
        TDictExample ex = new TDictExample();
        ex.createCriteria().andColNameEqualTo(virtual_warehouse_code);
        return this.mapper.selectByExample(ex);
    }

    @Override
    public List<String> findRdcArea(String rdc) throws Exception {

        return this.rdcMapper.findRdcArea(rdc);
    }

    @Override
    public Integer rdcGrantArea(String id, List<String> auths) throws Exception {

        int res = this.rdcMapper.deleteByRdcCode(id);
        if(res != -1){
            for(String aid:auths){
                TArea area = this.areaMapper.selectByPrimaryKey(aid);
                if("2".equals(area.getAreaLevel())){
                    res +=this.rdcMapper.insert(id,aid);
                }
//                if("0000".equals(aid)){
//                    continue;
//                }else{
//
//                }
            }
        }
        return res;
    }
}
