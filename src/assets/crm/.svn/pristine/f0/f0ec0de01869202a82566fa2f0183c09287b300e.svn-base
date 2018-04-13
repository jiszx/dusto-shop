package com.hhnz.chart.controller;

import com.hhnz.chart.dto.ProvinceCount;
import com.hhnz.chart.dto.RDCArea;
import com.hhnz.chart.service.IHeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 杨 on 2017/1/4.
 */
@Controller
@RequestMapping("/chart/heatMap")
public class HeatMapController {

    @Autowired
    private IHeatMapService service;

    @RequestMapping("enter.html")
    public String enter()throws Exception{
        return "chart/enter";

    }

    @RequestMapping("show.html")
    public String show()throws Exception{
        return "chart/show";

    }

    @RequestMapping("index.html")
    public String indexPage(String type)throws Exception{
        if(StringUtils.hasLength(type)){
            return "chart/index"+type;
        }else{
            return "chart/index";
        }

    }

    @RequestMapping("provCount")
    public @ResponseBody  List<ProvinceCount> provCount()throws Exception{
        List<ProvinceCount> list = this.service.findProvCount();
        for (ProvinceCount cc:list) {
            cc.setName(cc.getName().replace("省",""));
        }
        return list;
    }


    @RequestMapping("amtCount")
    public @ResponseBody  List<ProvinceCount> amtCount()throws Exception{
        List<ProvinceCount> list = this.service.countAmt();
        for (ProvinceCount cc:list) {
            cc.setName(cc.getName().replace("省","").replace("市",""));
        }
        return list;
    }



    @RequestMapping("listRDC")
    public @ResponseBody  List<RDCArea> listRDC()throws Exception{
        List<RDCArea> list = this.service.findRDCPoint();
        for (RDCArea cc:list) {
            cc.setFrom(cc.getFrom().replace("省","").replace("市","").replace("RDC",""));
            cc.setTo(cc.getTo().replace("省","").replace("市","").replace("RDC",""));
        }
        return list;
    }

    @RequestMapping("listOrderWeight")
    public @ResponseBody  List<RDCArea> listOrderWeight()throws Exception{
        List<RDCArea> list = this.service.findOrderRDC();
        for (RDCArea cc:list) {
            cc.setFrom(cc.getFrom().replace("省","").replace("市","").replace("RDC",""));
            cc.setTo(cc.getTo().replace("省","").replace("市","").replace("RDC",""));
        }
        return list;
    }

    @RequestMapping("company")
    public @ResponseBody  List<RDCArea> company()throws Exception{
        List<RDCArea> list = this.service.findCompany();
        for (RDCArea cc:list) {
            cc.setFrom(cc.getFrom().replace("省","").replace("市","").replace("RDC",""));
            cc.setTo(cc.getTo().replace("省","").replace("市",""));
        }
        return list;
    }
}
