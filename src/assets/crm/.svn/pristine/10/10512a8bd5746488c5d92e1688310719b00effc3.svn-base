package com.hhnz.config.service.impl;

import com.hhnz.config.mapper.ProcessDeployMapper;
import com.hhnz.config.model.ProcessDeploy;
import com.hhnz.config.model.ProcessDeployExample;
import com.hhnz.config.service.IProcessDefService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang on 2016-11-5.
 */
@Transactional
@Service
public class ProcessDefServiceImpl implements IProcessDefService {

    @Autowired
    private ProcessDeployMapper mapper;

    @Override
    public AjaxDTO findDeploy(AjaxDTO bean) throws Exception {

        ProcessDeployExample ex = new ProcessDeployExample();
        if (!(bean.getLimit() == 0 && bean.getOffset() == 0)) {
            Page page = new Page();
            page.setLimit(bean.getLimit());
            page.setOffset(bean.getOffset());
            ex.setPage(page);
        }
        ex.setOrderByClause("deploy_id desc");
        List<ProcessDeploy> list = this.mapper.selectByExample(ex);
        int total = this.mapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }
}
