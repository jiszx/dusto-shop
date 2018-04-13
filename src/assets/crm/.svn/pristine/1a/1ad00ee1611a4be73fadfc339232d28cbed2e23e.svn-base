package com.hhnz.config.service.impl;

import com.hhnz.config.service.IodineService;
import com.hhnz.crm.mapper.TMaterialBaseVMapper;
import com.hhnz.pub.model.TArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 杨 on 2016/12/20.
 */
@Service
@Transactional
public class IodineServiceImpl implements IodineService {

    @Autowired
    private TMaterialBaseVMapper mapper;

    @Override
    public List<String> findAllIodins() throws Exception {
        return this.mapper.selectIodines();
    }

    @Override
    public List<String> findIodineById(String id) throws Exception {
        return this.mapper.selectAreaByIodine(id);
    }

    @Override
    public Integer iodineGrantArea(String id, List<String> auths) throws Exception {

        int res = this.mapper.deleteAreaByIodine(id);
        if(res != -1){
            if(auths !=null && auths.size() >0 ){
                for(String aid:auths){
                    res +=this.mapper.insertIodineArea(id,aid);
                }
            }else{
                return res;
            }

        }
        return res;
    }
}
