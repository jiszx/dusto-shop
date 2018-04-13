package com.hhnz.config.service.impl;

import com.hhnz.config.mapper.TScheduleJobMapper;
import com.hhnz.config.model.TScheduleJob;
import com.hhnz.config.model.TScheduleJobExample;
import com.hhnz.config.service.ISchedulJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang on 2016-11-6.
 */
@Service
@Transactional
public class SchedulJobServiceImpl implements ISchedulJobService {

    @Autowired
    private TScheduleJobMapper mapper;


    @Override
    public List<TScheduleJob> getAllJob() throws Exception {
        return this.mapper.selectByExample(new TScheduleJobExample());
    }
}
