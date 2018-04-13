package com.hhnz.order.controller;

import java.io.File;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.hash.Hashing;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.Files;
import com.hhnz.util.exception.HHNZException;
import com.hhnz.util.io.excel.util.excel.ExcelResult;

@Controller
@RequestMapping("/order/retail")
public class RetailOrderController {
	@Resource
	private OrderUtilService utilService;
	@Resource
	private OrderService orderservice;
	@Resource
	private IAttachmentService attachmentService;
	@Value("${upload.file.path}")
    private String basePath;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(String batch, Model model) {
		model.addAttribute("batch", batch);
		return "order/retail";
	}

	// 送审 生成批次号 启动流程
	@ResponseBody
	@RequestMapping(value = "/censor", method = RequestMethod.POST)
	public int censor(String orderids, HttpServletRequest request) throws NumberFormatException, Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		return utilService.censorDist(orderids, user);
	}

	// 验证订单库存是否足够
	@ResponseBody
	@RequestMapping(value = "/verifyDepo", method = RequestMethod.POST)
	public ResponseResult verifyDepo(String orderids, HttpServletRequest request) {
		return utilService.validateDepo(orderids);
	}
	
	@ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importRetail(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
      TEmployee user = (TEmployee) request.getSession().getAttribute("user");
      final String importFileSuffix = "import";
	  String res = this.attachmentService.addAttachment(OmOrderHeadersAll.class.getSimpleName()+importFileSuffix, user.getLoginName(), file);
      String path = Files.standardFolderPath(basePath) + OmOrderHeadersAll.class.getSimpleName()+importFileSuffix + res;
      String fileHash = com.google.common.io.Files.hash(new File(path), Hashing.crc32()).toString();
      ExcelResult result = orderservice.importRetailOrders(path, user);
      int failureNum = result.getFailureNum();
	  int totalNum = result.getTotalNum();
	  if(!result.isSuccess() && failureNum == totalNum){
    	  throw new HHNZException("导入数据失败！请确认模板及数据！");
      }
      return fileHash+","+"共" + totalNum + "条数据, 成功" + result.getSuccessNum()
				+ "条, 失败" + failureNum + "条, "+( failureNum > 0?"失败的行数（去除标题行即开始导入数据的行为第0行，依次类推）:" + Arrays.toString(result.getFailureLines()):"") ;
    }
}
