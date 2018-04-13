package com.hhnz.chart.service.impl;

import com.hhnz.chart.dto.ProvinceCount;
import com.hhnz.chart.dto.RDCArea;
import com.hhnz.chart.mapper.ChartMapper;
import com.hhnz.chart.service.IHeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 杨 on 2017/1/4.
 */
@Service
@Transactional
public class HeatMapServiceImpl implements IHeatMapService {

    @Autowired
    private ChartMapper mapper;

    @Override
    public List<ProvinceCount> findProvCount() throws Exception {

        return this.mapper.findProvCount();
    }

    @Override
    public List<RDCArea> findRDCPoint() throws Exception {
        return this.mapper.findRDCArea();
    }

    @Override
    public List<ProvinceCount> countAmt() throws Exception {
        return this.mapper.countAmt();
    }

    @Override
    public List<RDCArea> findCompany() throws Exception {
        return this.mapper.findCompany();
    }

    @Override
    public List<RDCArea> findOrderRDC() throws Exception {
        return this.mapper.findRdcOrder();
    }
}
