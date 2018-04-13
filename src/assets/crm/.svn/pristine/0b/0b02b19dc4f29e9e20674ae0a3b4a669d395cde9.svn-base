package com.hhnz.customer.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseExample;
import com.hhnz.customer.dto.CMerchCustPriceMaintenanceDTO;
import com.hhnz.customer.dto.ImportCustPrice;
import com.hhnz.customer.enu.CustPriceBatchMaintainDirection;
import com.hhnz.customer.enu.CustPriceBatchMaintainType;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustPriceImportMapper;
import com.hhnz.customer.mapper.CMerchCustPriceMaintainDtoMapper;
import com.hhnz.customer.mapper.CMerchCustPriceMaintenanceMapper;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.customer.model.CMerchCustPriceImport;
import com.hhnz.customer.model.CMerchCustPriceMaintenance;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.service.ICustomerPriceService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.ValidationResult;
import com.hhnz.util.ValidationUtil;
import com.hhnz.util.io.excel.util.excel.ExcelCallback;
import com.hhnz.util.io.excel.util.excel.ExcelCheckResult;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

@Service
public class CustomerPriceServiceImpl implements ICustomerPriceService {
	private static Logger log = Logger.getLogger(CustomerPriceServiceImpl.class);
	@Resource
	private CMerchCustBaseMapper merchmapper;

	@Resource
	private TMaterialBaseMapper materialmapper;

	@Resource
	private CMerchCustPriceMaintenanceMapper pricemapper;

	@Resource
	private CMerchCustPriceMaintainDtoMapper mapper;
	@Resource
	private CMerchCustProductMapper productmapper;

	@Resource
	private IProcessService processService;

	@Value("${upload.file.path}")
	private String basePath;
	@Autowired
	private IAttachmentService attaService;
	
	/*@Autowired
    private TAttachmentMapper attachmentmapper;*/
	
	@Resource
	private CMerchCustPriceImportMapper  importmapper;
	@Override
	public AjaxDTO selectCustomerAll(String orgid) {
		AjaxDTO dto = new AjaxDTO();
		CMerchCustBaseExample ex = new CMerchCustBaseExample();
		CMerchCustBaseExample.Criteria ext = ex.createCriteria();
		ext.andOrganizationIdEqualTo(orgid);
		ext.andStatesEqualTo("3");
		ext.andCustTypeNotEqualTo("6");
		List<CMerchCustBase> merchs = this.merchmapper.selectByExample(ex);
		dto.setRows(merchs);
		return dto;
	}

	@Override
	public AjaxDTO selectMaterials() {
		AjaxDTO dto = new AjaxDTO();
		TMaterialBaseExample ex = new TMaterialBaseExample();
		// TMaterialBaseExample.Criteria ext = ex.createCriteria();
		List<TMaterialBase> materials = this.materialmapper.selectByExample(ex);
		dto.setRows(materials);
		return dto;
	}

	@Override
	public int addProductBatchMaintain(CMerchCustPriceMaintenance model) {
		return this.pricemapper.insert(model);
	}

	@Override
	public int editProductBatchMaintain(CMerchCustPriceMaintenance model) {
		CMerchCustPriceMaintenance ccpm = this.pricemapper.selectByPrimaryKey(model.getId());
		ccpm.setAreaId(model.getAreaId());
		ccpm.setbDate(model.getbDate());
		ccpm.seteDate(model.geteDate());
		ccpm.setBrand(model.getBrand());
		ccpm.setMaterialId(model.getMaterialId());
		ccpm.setMerchCustId(model.getMerchCustId());
		ccpm.setType(model.getType());
		ccpm.setReginId(model.getReginId());
		ccpm.setPrice(model.getPrice());
		ccpm.setOrgId(model.getOrgId());
		return this.pricemapper.updateByPrimaryKey(ccpm);
	}

	@Override
	public AjaxDTO selectBatchMaintainList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<CMerchCustPriceMaintenanceDTO> list = this.mapper.selectBatchMaintainList(params);
		int total = this.mapper.countBatchMaintainList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public Integer del(Long id) throws Exception {
		CMerchCustPriceMaintenance custprice = this.pricemapper.selectByPrimaryKey(id);
		if(custprice.getType().equals(CustPriceBatchMaintainType.IMPORT.getCode())) {
			//批量导入需删除附近
			List<TAttachment> attachments = attaService.findAttachment(CMerchCustPriceMaintenance.class.getSimpleName(), String.valueOf(id), null);
			if(attachments !=null && attachments.size() >0) {
				for(TAttachment att :attachments) {
					this.attaService.delAttachent(CMerchCustPriceMaintenance.class.getSimpleName(), id.toString(), att.getId());					
				}
			}
		}
		return this.pricemapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateStates(Long id, String states) {
		CMerchCustPriceMaintenance ccpm = this.pricemapper.selectByPrimaryKey(id);
		ccpm.setStates(states);
		this.pricemapper.updateByPrimaryKey(ccpm);
	}

	@Override
	public Integer startprocess(Long id, Map<String, Object> param, TEmployee user) {
		String processId = "";
		try {
			processId = this.processService.startProcess(param, "priceBatchMaintain", user.getLoginName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CMerchCustPriceMaintenance ccpm = this.pricemapper.selectByPrimaryKey(id);
		ccpm.setAttribute1(processId);
		ccpm.setStates("2");
		return this.pricemapper.updateByPrimaryKeySelective(ccpm) == 1 ? 200 : 500;
	}
	/**
	 * 获取批量维护详情客户物料列表
	 */
	@Override
	public AjaxDTO batchmaintainDetails(AjaxDTO bean,Long id) throws Exception {
		AjaxDTO dto = new AjaxDTO();
		CMerchCustPriceMaintenance ccpm = this.pricemapper.selectByPrimaryKey(id);
		List<CMerchCustPriceMaintenanceDTO> list = new ArrayList<CMerchCustPriceMaintenanceDTO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit() + 1);
		if(ccpm.getType().equals(CustPriceBatchMaintainType.IMPORT.getCode())) {
			//导入数据
			list = this.mapper.selectImportCustPrice(params);
		}else {
			if (StringUtils.isNullOrEmpty(ccpm.getMaterialId())) {
				// 物料等于空
				if (StringUtils.isNullOrEmpty(ccpm.getBrand())) {
					// 品牌为空
					// 维护指定客户的全部物料
					list = this.mapper.selectBatchMaintainMaterialsByMerchInfo(params);
				} else {
					// 维护指定客户的指定品牌物料
					list = this.mapper.selectBatchMaintainMaterialsByBrand(params);
				}
			} else {
				// 批量维护摸个物料
				list = this.mapper.selectBatchMaintainMaterialsByMaterialId(params);
			}
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	
	@Override
	public void updateMerchProduct(Long id) throws Exception {
		CMerchCustPriceMaintenance ccpm = this.pricemapper.selectByPrimaryKey(id);
		if(ccpm.getType().equals(CustPriceBatchMaintainType.IMPORT.getCode())) {
			//批量录入
			updateMerchProductByImport(id);
		}else {
			if (StringUtils.isNullOrEmpty(ccpm.getMaterialId())) {
				// 物料等于空
				if (StringUtils.isNullOrEmpty(ccpm.getBrand())) {
					// 品牌为空
					// 维护指定客户的全部物料
					updateMerchProductByMerchInfo(id);
				} else {
					// 维护指定客户的指定品牌物料
					updateMerchProductByBrand(id);
				}
			} else {
				// 批量维护摸个物料
				updateMerchProductByMaterial(id);
			}
		}
	}
	
	/**
	 * 导入客户价格
	 * @param batchMaintainId
	 * @throws Exception
	 */
	private void updateMerchProductByImport(Long batchMaintainId) throws Exception {
		List<CMerchCustPriceMaintenanceDTO> list = this.mapper.selectBatchMaintainMaterialsByImport(batchMaintainId);
		if (list != null && list.size() > 0) {
			for (CMerchCustPriceMaintenanceDTO dto : list) {
				insertNewProduct(dto);
			}
		}
	}
	
	/**
	 * 根据组织信息维护客户价格
	 * @param batchMaintainId
	 * @throws Exception
	 */
	private void updateMerchProductByMerchInfo(Long batchMaintainId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", batchMaintainId);
		List<CMerchCustPriceMaintenanceDTO> list = this.mapper.selectBatchMaintainMaterialsByMerchInfo(params);
		if (list != null && list.size() > 0) {
			for (CMerchCustPriceMaintenanceDTO dto : list) {
				insertNewProduct(dto);
			}
		}
	}

	/**
	 * 根据品牌维护客户价格
	 * @param batchMaintainId
	 * @throws Exception
	 */
	private void updateMerchProductByBrand(Long batchMaintainId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", batchMaintainId);
		List<CMerchCustPriceMaintenanceDTO> list = this.mapper.selectBatchMaintainMaterialsByBrand(params);
		if (list != null && list.size() > 0) {
			for (CMerchCustPriceMaintenanceDTO dto : list) {
				insertNewProduct(dto);
			}
		}
	}
	
	/**
	 * 根据物料编码维护客户价格
	 * @param batchMaintainId
	 * @throws Exception
	 */
	private void updateMerchProductByMaterial(Long batchMaintainId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", batchMaintainId);
		List<CMerchCustPriceMaintenanceDTO> list = this.mapper
				.selectBatchMaintainMaterialsByMaterialId(params);
		if (list != null && list.size() > 0) {
			for (CMerchCustPriceMaintenanceDTO dto : list) {
				if (dto.getAdjustDirection().equals(CustPriceBatchMaintainDirection.RANGE.getCode())) {
					// 幅度调整
					if (StringUtils.isNullOrEmpty(dto.getOldPriceId())) {
						dto.setPrice(BigDecimal.ZERO);
					} else {
						CMerchCustProduct product = this.productmapper
								.selectByPrimaryKey(Long.parseLong(dto.getOldPriceId()));
						dto.setPrice(product.gethPrice().add(dto.getPrice()));
					}
				}
				insertNewProduct(dto);
			}
		}
	}

	private void insertNewProduct(CMerchCustPriceMaintenanceDTO dto) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		CMerchCustProduct product = new CMerchCustProduct();
		product.setbDate(sf.parse(dto.getbDate()));
		product.setCifPrice(
				dto.getType().equals(CustPriceBatchMaintainType.CIFPRICE.getCode()) ? dto.getPrice() : BigDecimal.ZERO);
		product.setContractId(dto.getContractId());
		product.setContractLineid(dto.getContractLineId());
		product.setCreateOid(dto.getCreateOid());
		product.setCreateTs(new Date());
		product.seteDate(sf.parse(dto.geteDate()));
		if(dto.getAdjustDirection().equals(CustPriceBatchMaintainDirection.RANGE.getCode())) {
			//幅度调整
			product.sethPrice(dto.getType().equals(CustPriceBatchMaintainType.MERCHPRICE.getCode()) ?dto.getPrice() : BigDecimal.ZERO); 
		}else {
			//重新定价
			product.sethPrice(dto.getType().equals(CustPriceBatchMaintainType.MERCHPRICE.getCode())||dto.getType().equals(CustPriceBatchMaintainType.IMPORT.getCode()) ? dto.getPrice().subtract(dto.getBasePrice())
					: BigDecimal.ZERO);
		}
		product.setMaterialId(dto.getMaterialId());
		product.setMerchCustId(dto.getMerchCustId());
		product.setStates("6");// 挂起状态
		product.setOrganizationId(dto.getOrgid());
		if (!StringUtils.isNullOrEmpty(dto.getOldPriceId())) {
			product.setOldProductId(Long.parseLong(dto.getOldPriceId()));
		}
		this.productmapper.insert(product);
		// 更新状态
		this.mapper.updateProductStates(product.getId());
	}

	@SuppressWarnings("unused")
	@Override
	public void importCustPrice(CommonsMultipartFile file,  CMerchCustPriceMaintenance model) throws Exception {
		this.pricemapper.insert(model);
		// 解析excle
		ExcelType excelType = ExcelUtil.getExcelType(file.getOriginalFilename());
		InputStream inputStream = null;
		inputStream = file.getInputStream();
		final List<ImportCustPrice> importCustPrices = new ArrayList<ImportCustPrice>();
		ExcelResult importexcelResult = ExcelUtil.importExcelWithHeader(excelType, 1, inputStream,ImportCustPrice.class, new ExcelCallback<ImportCustPrice>() {
					@Override
					public ExcelCheckResult handleImportData(ImportCustPrice roid, int rowNum) {
						ExcelCheckResult cr = new ExcelCheckResult();
						try {
							ValidationResult vr = ValidationUtil.validateEntity(roid);
							cr.setSuccess(!vr.isHasErrors());
							cr.setErrorMsg(vr.getErrorMsg());
							ImportCustPrice custprice = new ImportCustPrice();
							custprice.setCrmId(roid.getCrmId());
							custprice.setCustname(roid.getCustname());
							custprice.setMaterialId(roid.getMaterialId());
							custprice.setPrice(roid.getPrice());
							// 验证导入的数据是否正确
							String remark = validateImportCustPrice(custprice);
							custprice.setRemark(remark);
							importCustPrices.add(custprice);
						} catch (Exception e) {
							log.error("Import Retail order error, info=" + roid, e);
						}
						return cr;
					}
				});
		//创建excel
		/*String fileName =UUID.randomUUID().toString()+ file.getOriginalFilename();
		String filePath = Files.standardFolderPath(basePath) + fileName;
		File resultFile = new File(filePath);
		if (!resultFile.getParentFile().exists()) {
			resultFile.getParentFile().mkdirs();
		}
		String[] headers = new String[] { "CRM客户编号", "客户名称", "物料编码", "客户价格", "备注" };
		Workbook wb = null;
		OutputStream stream = null;
		try {
			wb = new XSSFWorkbook();
			stream = new FileOutputStream(resultFile);
			Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
			// 写入头数据
			fillHeader(headers, sheet1);
			// 内容
			int rowIndex = 1;
			for (ImportCustPrice custprice : importCustPrices) {
				//插入行数据
				fillRow(sheet1, rowIndex++, custprice);

				//导入数据插入数据库
				CMerchCustPriceImport  custpriceImport = new CMerchCustPriceImport();
				custpriceImport.setCustMerchId(Long.valueOf(custprice.getCrmId()));
				custpriceImport.setCustName(custprice.getCustname());
				custpriceImport.setMaterialId(custprice.getMaterialId());
				custpriceImport.setPrice(BigDecimalASME.multiply(new BigDecimal(custprice.getPrice())));
				custpriceImport.setRemark(custprice.getRemark());
				custpriceImport.setMaintenanceId(model.getId());
				this.importmapper.insert(custpriceImport);
			}
			wb.write(stream);
		} finally {
			IOUtils.closeQuietly(stream);
			IOUtils.closeQuietly(wb);
		}
		TAttachment atta = new TAttachment();
		atta.setUploadOid(model.getCreateOid().toString());
		atta.setAttachmentName(fileName);
		atta.setFileName(fileName);
		atta.setFilePath(filePath);
		atta.setAttachmentType("2");
		atta.setObjectName(CMerchCustPriceMaintenance.class.getSimpleName());
		atta.setObjectKey(model.getId().toString());
		atta.setUploadTs(new Date());
		atta.setStat("0");
		int res = attachmentmapper.insert(atta);*/
		//插入行数据
		for (ImportCustPrice custprice : importCustPrices) {
			//导入数据插入数据库
			CMerchCustPriceImport  custpriceImport = new CMerchCustPriceImport();
			custpriceImport.setCustMerchId(Long.valueOf(custprice.getCrmId()));
			custpriceImport.setCustName(custprice.getCustname());
			custpriceImport.setMaterialId(custprice.getMaterialId());
			custpriceImport.setPrice(BigDecimalASME.multiply(new BigDecimal(custprice.getPrice())));
			custpriceImport.setRemark(custprice.getRemark());
			custpriceImport.setMaintenanceId(model.getId());
			this.importmapper.insert(custpriceImport);
		}
		//上传附件
		attaService.addAttachment(CMerchCustPriceMaintenance.class.getSimpleName(),
				String.valueOf(model.getId()), 
				String.valueOf(model.getCreateOid()),"2", file);
	}

	/**
	 * 验证导入的客户价格：验证客户名称，物料编码
	 * 
	 * @param custprice
	 * @return
	 */
	private String validateImportCustPrice(ImportCustPrice custprice) {
		// 验证客户信息
		String result="";
		CMerchCustBaseExample merchEx = new CMerchCustBaseExample();
		CMerchCustBaseExample.Criteria merchExt = merchEx.createCriteria();
		merchExt.andIdEqualTo(Long.valueOf(custprice.getCrmId()));
		merchExt.andNameEqualTo(custprice.getCustname());
		List<CMerchCustBase> merchs = this.merchmapper.selectByExample(merchEx);
		if (merchs == null || merchs.size() ==0) {
			result=result+ "未查询到该客户，请验证客户名称或者CRM编号是否正确;";
		} else if (merchs.size() > 1) {
			result=result+ "通过客户ID和客户名称查询到多个客户，请验证客户信息;";
		}
		// 验证物料信息
		TMaterialBaseExample materialEx = new TMaterialBaseExample();
		TMaterialBaseExample.Criteria materialExt = materialEx.createCriteria();
		materialExt.andSapIdEqualTo(custprice.getMaterialId());
		List<TMaterialBase> materials = this.materialmapper.selectByExample(materialEx);
		if (materials == null || materials.size() == 0) {
			result=result+ "没有查到对应的物料";
		}
		return result;
	}

	/*private void fillHeader(String[] headers, Sheet sheet1) {
		Row header = (Row) sheet1.createRow(0);
		for (int j = 0; j < headers.length; j++) {
			Cell cell = header.createCell(j);
			cell.setCellValue(headers[j]);
		}
	}

	private void fillRow(Sheet sheet, int rowIndex, ImportCustPrice custprice) {
		int cellIndex = 0;
		Row row = (Row) sheet.createRow(rowIndex);

		fillCell(row, cellIndex++, custprice.getCrmId().toString());
		fillCell(row, cellIndex++, custprice.getCustname());
		fillCell(row, cellIndex++, custprice.getMaterialId());
		fillCell(row, cellIndex++, custprice.getPrice().toString());
		fillCell(row, cellIndex++, custprice.getRemark());

	}

	private void fillCell(Row row, int cellIndex, String value) {
		Cell cell = row.createCell(cellIndex);
		cell.setCellValue(value);
	}*/
}
