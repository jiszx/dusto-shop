package com.hhnz.cost.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.cost.model.CrmCostBalancesV;
import com.hhnz.cost.service.ICostBalanceService;

/**
 * Created by yang on 2016-7-8.
 */
@Controller
@RequestMapping("/cost/")
public class CostController {
	@Resource
	private ICostBalanceService service;
	
    //管理页面

    @RequestMapping("/index.html")
    public String indexPage()throws Exception{
        return "cost/index";
    }

    @RequestMapping("pool.html")
    public String poolPage()throws Exception{
        return "cost/pool";
    }

    //录入页面

    //申请页面

    //核销页面

    //调整页面

    //对账明细
    @RequestMapping("/perid.html")
    public String peridPoolPage()throws Exception{
        return "cost/peridPool";
    }
    
    /**
     * 费用池统计明细
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("costPeriod.html")
    public ModelAndView costPeriod(Long id) throws Exception{
    	ModelAndView mv= new ModelAndView("cost/costPeriodDetails");
    	CrmCostBalancesV  balance = this.service.getCostBalanceById(id);
    	mv.addObject("balance", balance);
    	mv.addObject("id", id);
    	return mv;
    }
    
    
}
