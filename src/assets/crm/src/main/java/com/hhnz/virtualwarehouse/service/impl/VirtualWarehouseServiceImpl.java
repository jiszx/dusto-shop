package com.hhnz.virtualwarehouse.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.service.IDictService;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.Files;
import com.hhnz.util.ValidationUtil;
import com.hhnz.util.db.Page;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;
import com.hhnz.virtualwarehouse.service.IVirtualWarehouseService;

@Service
@Transactional
public class VirtualWarehouseServiceImpl implements IVirtualWarehouseService{
	private static final Log LOG  = LogFactory.getLog(VirtualWarehouseServiceImpl.class);
	@Resource
	private CrmVirtualWarehouseMapper vwMapper;
	@Resource
	private IDictService dictService;
	@Resource
    private IFactoryService factoryService;
	@Value("${upload.file.path}")
	private String basePath;
	
	@Override
	public BigDecimal addInventory(CrmVirtualWarehouse vw) {
		List<CrmVirtualWarehouse> vwList = this.find(vw);
		if(vwList != null && vwList.size() > 1){
			LOG.warn("可能存在错误的库存数据信息！");
			return BigDecimal.valueOf(-1l);
		}
		TEmployee e = ApplicationContextUtil.getCurrentUser();
		if(vwList == null || vwList.size() == 0){
			if(ValidationUtil.validate(vw)){
				vw.setCreateTs(new Date());
				vw.setCreateOid(e == null?0:e.getId());
				vw.setUpdateTs(vw.getCreateTs());
				vw.setUpdateOid(e == null?0:e.getId());
				vwMapper.insertSelective(vw);
				return vw.getAmt();
			}else{
				return BigDecimal.valueOf(-1l);
			}
		}else{
			CrmVirtualWarehouse existedVW = vwList.get(0);
			existedVW.setUpdateOid(e == null?0:e.getId());
			existedVW.setUpdateTs(new Date());
			BigDecimal newVal = existedVW.getAmt().add(vw.getAmt());
			existedVW.setAmt(newVal);
			vwMapper.updateByPrimaryKeySelective(existedVW);
			return newVal;
		}
	}
	
	@Override
	public BigDecimal minusInventory(CrmVirtualWarehouse vw) {
		List<CrmVirtualWarehouse> vwList = this.find(vw);
		if(vwList != null && vwList.size() > 1){
			LOG.warn("可能存在错误的库存数据信息！");
			return BigDecimal.valueOf(-1l);
		}
		TEmployee e = ApplicationContextUtil.getCurrentUser();
		if(vwList == null || vwList.size() == 0){
			return BigDecimal.valueOf(-1l);
		}else{
			CrmVirtualWarehouse existedVW = vwList.get(0);
			existedVW.setUpdateOid(e == null?0:e.getId());
			existedVW.setUpdateTs(new Date());
			if(existedVW.getAmt().compareTo(vw.getAmt()) < 0){
				return BigDecimal.valueOf(-1l);
			}
			BigDecimal newVal = existedVW.getAmt().subtract(vw.getAmt());
			existedVW.setAmt(newVal);
			vwMapper.updateByPrimaryKeySelective(existedVW);
			return newVal;
		}
	}
	
	@Override
	public boolean isInventoryEnough(CrmVirtualWarehouse vw) {
		List<CrmVirtualWarehouse> vwList = this.find(vw);
		if(vwList != null && vwList.size() > 1){
			LOG.warn("可能存在错误的库存数据信息！");
			return false;
		}
		if(vwList == null || vwList.size() == 0){
			return false;
		}else{
			CrmVirtualWarehouse existedVW = vwList.get(0);
			if(existedVW.getAmt().compareTo(vw.getAmt()) < 0){
				return false;
			}
			return true;
		}
	}

	@Override
	public List<CrmVirtualWarehouse> find(CrmVirtualWarehouse vw){
		CrmVirtualWarehouseExample ex = new CrmVirtualWarehouseExample();
		CrmVirtualWarehouseExample.Criteria c = ex.createCriteria();
		if(vw.getId() != null){
			c.andIdEqualTo(vw.getId());
		}
		if(StringUtils.isNotBlank(vw.getFactoryCode())){
			c.andFactoryCodeEqualTo(vw.getFactoryCode());
		}
		if(StringUtils.isNotBlank(vw.getMaterialId())){
			c.andMaterialIdEqualTo(vw.getMaterialId());
		}
		if(StringUtils.isNotBlank(vw.getCustType())){
			c.andCustTypeEqualTo(vw.getCustType());
		}
		return vwMapper.selectByExample(ex);
	}

	@Override
	public AjaxDTO find(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmVirtualWarehouseExample ex = new CrmVirtualWarehouseExample();
		CrmVirtualWarehouseExample.Criteria ext = ex.createCriteria();
		ex.setPage(page);
		ex.setOrderByClause("FACTORY_CODE ASC");
		if(StringUtils.isNotBlank(queryDto.getMaterialName())){
			ext.addCriterion("MATERIAL_NAME like", "%"+queryDto.getMaterialName()+"%", "materialName");
		}
		if(StringUtils.isNotBlank(queryDto.getMaterialCode())){
			ext.andMaterialIdEqualTo(queryDto.getMaterialCode());
		}
		//工厂
		if(StringUtils.isNotBlank(queryDto.getFactoryCode())){
			ext.andFactoryCodeEqualTo(queryDto.getFactoryCode());
		}
		//仓库代码
		if(StringUtils.isNotBlank(queryDto.getCustType())){
			ext.andCustTypeEqualTo(queryDto.getCustType());
		}
		List<CrmVirtualWarehouse> list = this.vwMapper.selectByExampleWithMaterial(ex);
		int total = this.vwMapper.countByExampleWithMaterial(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public CrmVirtualWarehouse findById(Long id) {
		return vwMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String generateExcel(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto) throws IOException{
	  CrmVirtualWarehouseExample ex = new CrmVirtualWarehouseExample();
      CrmVirtualWarehouseExample.Criteria ext = ex.createCriteria();
      ex.setOrderByClause("FACTORY_CODE ASC");
      List<CrmVirtualWarehouse> list = this.vwMapper.selectByExampleWithMaterial(ex);
      
      // 创建文件
      String fileName = UUID.randomUUID().toString() + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      String title[] = {"工厂", "产品名称", "产品编码", "仓库名称", "库存数量"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 写入头数据
        Row header = (Row) sheet1.createRow(0);
        for (int j = 0; j < title.length; j++) {
          Cell cell = header.createCell(j);
          cell.setCellValue(title[j]);
        }
        // 内容
        List<TDict> rdcs = dictService.findByName("VIRTUAL_WAREHOUSE_CODE");
        List<TFactory> factoryList = factoryService.findAll();
        int rowIndex = 1;
        for (CrmVirtualWarehouse vw : list) {
          fillRow(sheet1,rowIndex++, vw, rdcs, factoryList);
        }
        wb.write(stream);
      }finally{
        IOUtils.closeQuietly(stream);
        IOUtils.closeQuietly(wb);
      }
      
      return fileName;
	}
	
	private void fillRow(Sheet sheet, int rowIndex, CrmVirtualWarehouse vw, List<TDict> rdcs, List<TFactory> factorys) {
      int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Cell factory = row.createCell(cellIndex++);
      factory.setCellValue(factoryName(vw.getFactoryCode(), factorys));
      Cell sku = row.createCell(cellIndex++);
      sku.setCellValue(vw.getMaterialName());
      Cell materialId = row.createCell(cellIndex++);
      materialId.setCellValue(vw.getMaterialId());
      Cell rdc = row.createCell(cellIndex++);
      rdc.setCellValue(dictService.code2Str(vw.getCustType(), rdcs));
      Cell inv = row.createCell(cellIndex++);
      inv.setCellValue(vw.getAmt().toPlainString());
    }
	
	private String factoryName(String code, List<TFactory> factorys){
	  for(TFactory factory:factorys){
	    if(code.equals(factory.getId())){
	      return factory.getName();
	    }
	  }
	  return null;
	}
	
	
}
