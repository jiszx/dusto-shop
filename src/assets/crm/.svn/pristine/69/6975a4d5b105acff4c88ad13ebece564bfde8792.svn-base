package com.hhnz.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.crm.service.IProductService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Files;


@Controller
@RequestMapping("/product")
public class ProductController {
  private static Logger logger = Logger.getLogger(ProductController.class);
  @Value("${upload.file.path}")
  private String basePath;
  @Resource
  private IAttachmentService attachmentService;

  @Resource
  private IProductService productService;

  
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO getProducts(AjaxDTO bean, TMaterialBaseV model, String isFilter) {
    return this.productService.findProducts(bean, model, isFilter);
  }

  @ResponseBody
  @RequestMapping(value = "/attachment", method = RequestMethod.GET)
  public AjaxDTO findAttachment(String id) throws Exception {
    return this.productService.findAttachment(id);
  }

  @ResponseBody
  @RequestMapping(value = "/series", method = RequestMethod.GET)
  public AjaxDTO getSeries() {
    return this.productService.findSeries();
  }

  @ResponseBody
  @RequestMapping(value = "/material/edit", method = RequestMethod.POST)
  public int editMaterial(TMaterialBase material) {
    return this.productService.editMaterial(material);
  }

  @ResponseBody
  @RequestMapping(value = "/attachment/delete/{id}", method = RequestMethod.POST)
  public int deleteAttachment(@PathVariable Long id) {
    return this.productService.deleteAttachment(id);
  }

  @ResponseBody
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String fileUpload(@RequestParam("file") CommonsMultipartFile file, TAttachment annex,
      HttpServletRequest request) throws Exception {
    TEmployee user = (TEmployee) request.getSession().getAttribute("user");
    String suffix = Files.imgSuffix(file.getOriginalFilename());
    if (suffix == null) {
      return "0";
    }
    return attachmentService.addAttachment(TMaterialBase.class.getSimpleName(), annex.getObjectName(), String.valueOf(user.getId()), annex.getAttachmentType(), file);
//    String url = attachmentService.addAttachment("product", user.getId()+"", file);
  }
  @RequestMapping(value="/brand", method = RequestMethod.GET)
  @ResponseBody
  public AjaxDTO getbrand(){
	  return this.productService.findBrand();
  }
  @RequestMapping(value="/iPackage", method = RequestMethod.GET)
  @ResponseBody
  public AjaxDTO getiPackage(){
	  return this.productService.findiPackage();
  }
  
  @ResponseBody
  @RequestMapping(value="/vicebrand", method = RequestMethod.GET)
  public AjaxDTO getVicebrand(){
      return productService.findViceBrand();
  }
}
