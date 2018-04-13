package com.hhnz.config.service;

import com.hhnz.config.model.TScheduleJob;

import java.util.List;

/**
 * Created by yang on 2016-11-6.
 */
public interface ISchedulJobService {
    List<TScheduleJob> getAllJob()throws Exception;
}
