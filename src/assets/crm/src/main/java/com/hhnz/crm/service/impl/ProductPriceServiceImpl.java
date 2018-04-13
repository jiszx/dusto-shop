package com.hhnz.crm.service.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.dto.ProductPriceAdjustDTO;
import com.hhnz.crm.dto.ProductPriceAdjustEditDTO;
import com.hhnz.crm.dto.ProductPriceDTO;
import com.hhnz.crm.dto.ProductPriceQueryDTO;
import com.hhnz.crm.enu.PriceAdjustOperation;
import com.hhnz.crm.enu.PriceAdjustStatus;
import com.hhnz.crm.enu.PriceAdjustType;
import com.hhnz.crm.mapper.TMaterialPriceAdjustMapper;
import com.hhnz.crm.mapper.TMaterialPriceAdjustRecordMapper;
import com.hhnz.crm.mapper.TMaterialPriceMapper;
import com.hhnz.crm.mapper.TPriceAdjustApprovedMapper;
import com.hhnz.crm.model.TMaterialPrice;
import com.hhnz.crm.model.TMaterialPriceAdjust;
import com.hhnz.crm.model.TMaterialPriceAdjustExample;
import com.hhnz.crm.model.TMaterialPriceAdjustExample.Criteria;
import com.hhnz.crm.model.TMaterialPriceAdjustRecord;
import com.hhnz.crm.model.TMaterialPriceAdjustRecordExample;
import com.hhnz.crm.model.TPriceAdjustApproved;
import com.hhnz.crm.model.TPriceAdjustApprovedExample;
import com.hhnz.crm.service.IProductPriceService;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.DateUtil;
import com.hhnz.util.ValidationResult;
import com.hhnz.util.ValidationUtil;
import com.hhnz.util.db.Page;
import com.hhnz.util.exception.HHNZException;
import com.hhnz.util.io.excel.util.excel.ExcelCallback;
import com.hhnz.util.io.excel.util.excel.ExcelCheckResult;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;


/**
 * @author: chaoyang.ren  
 * @date:Sep 14, 2017  
 * @time:10:52:20 AM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
@Service
@Transactional
public class ProductPriceServiceImpl implements IProductPriceService{
	private static final Log LOG = LogFactory.getLog(ProductPriceServiceImpl.class);
	@Resource
	private TMaterialPriceAdjustMapper materialPriceAdjustMapper;
	@Resource
	private TMaterialPriceAdjustRecordMapper materialPriceAdjustRecordMapper;
	@Resource
	private TMaterialPriceMapper materialPriceMapper;
	@Resource
	private TPriceAdjustApprovedMapper priceAdjustApprovedMapper;
	
	@Override
	public ExcelResult importPrice(CommonsMultipartFile file) {
		ExcelType excelType = ExcelUtil.getExcelType(file.getOriginalFilename());
		ExcelResult excelResult = null;
		try {
			InputStream inputStream = null;
			inputStream = file.getInputStream();
			final List<TMaterialPriceAdjustRecord> prices = new ArrayList<TMaterialPriceAdjustRecord>();
			excelResult = ExcelUtil.importExcelWithHeader(excelType, 1, inputStream, ProductPriceDTO.class, new ExcelCallback<ProductPriceDTO>() {
				@Override
				public ExcelCheckResult handleImportData(ProductPriceDTO ppid, int rowNum) {
					ExcelCheckResult cr = new ExcelCheckResult();
					try {
						ValidationResult vr = ValidationUtil.validateEntity(ppid);
						cr.setSuccess(!vr.isHasErrors());
						cr.setErrorMsg(vr.getErrorMsg());
						
						TMaterialPriceAdjustRecord mpar = new TMaterialPriceAdjustRecord();
						BeanUtils.copyProperties(ppid,mpar);
						mpar.setCreateTs(new Date());
						mpar.setPrice(BigDecimalUtil.multiply100(BigDecimal.valueOf(ppid.getPrice())));
						mpar.setOpt(null);
						mpar.setAdjustId(0l);
						String materialId = ppid.getMaterialId();
						String organizationId = ppid.getOrganizationId();
						String channel = ppid.getChannel();
						TMaterialPrice mp = findActiveByMaterialIdAndOrgIdAndChannel(materialId, organizationId, channel);
						if(mp != null){
							mpar.setOadjustPrice(mp.getAdjustPrice());
							mpar.setObdate(mp.getBdate());
							mpar.setOedate(mp.getEdate());
							mpar.setOprice(mp.getPrice());
						}
						prices.add(mpar);
					} catch (Exception e) {
						cr.setSuccess(false);
						LOG.error("Import product price error, info=" + ppid, e);
					}
					return cr;
				}
			});
			TMaterialPriceAdjust mpa = new TMaterialPriceAdjust();
			mpa.setCreateTs(new Date());
			mpa.setAdjustType(PriceAdjustType.STANDARD);
			mpa.setStatus(PriceAdjustStatus.DRAFT);
			mpa.setAdjustDesc(TMaterialPriceAdjust.STANDARD_IMPORT);
			materialPriceAdjustMapper.insertSelective(mpa);
			if(prices.isEmpty()){
				throw new HHNZException("没有解析到数据.");
			}
			for (TMaterialPriceAdjustRecord r : prices) {
				r.setAdjustId(mpa.getId());
			}
			materialPriceAdjustRecordMapper.insertBatch(prices);
			inputStream.close();
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			LOG.error("Import product price error", e);
		} 
		return excelResult;
	}

	@Override
	public AjaxDTO findPriceAdjusts(AjaxDTO bean, TMaterialPriceAdjust model) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		TMaterialPriceAdjustExample ex = new TMaterialPriceAdjustExample();
		Criteria param = ex.createCriteria();
		if (StringUtils.isNotEmpty(model.getAdjustType())) {
		  param.andAdjustTypeEqualTo(model.getAdjustType());
		}
		if (StringUtils.isNotEmpty(model.getStatus())) {
		  param.andStatusEqualTo(model.getStatus());
		}
		if (model.getBdate() != null) {
			param.andCreateTsGreaterThanOrEqualTo(model.getBdate());
		}
		if (model.getEdate() != null) {
			param.andCreateTsLessThanOrEqualTo(DateUtil.getEndingOfDay(model.getEdate()));
		}
		if(StringUtils.isNotBlank(model.getRelatedMaterialId())){
			param.andRelatedMaterialIdEqualTo(model.getRelatedMaterialId());
			ex.setWithMaterial(true);
		}
		ex.setOrderByClause("ID DESC ");
		ex.setPage(page);
		List<TMaterialPriceAdjust> list = this.materialPriceAdjustMapper.selectByExample(ex);
		int total = this.materialPriceAdjustMapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO findPriceAdjustDetails(AjaxDTO bean, Long adjustId) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		TMaterialPriceAdjustRecordExample ex = new TMaterialPriceAdjustRecordExample();
		com.hhnz.crm.model.TMaterialPriceAdjustRecordExample.Criteria param = ex.createCriteria();
		param.andAdjustIdEqualTo(adjustId);
		ex.setOrderByClause("ID ");
		ex.setPage(page);
		List<TMaterialPriceAdjustRecord> list = this.materialPriceAdjustRecordMapper.selectByExample(ex);
		int total = this.materialPriceAdjustRecordMapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}
	
	@Override
	public List<TMaterialPriceAdjustRecord> findPriceAdjustDetails(Long adjustId) {
		TMaterialPriceAdjustRecordExample ex = new TMaterialPriceAdjustRecordExample();
		com.hhnz.crm.model.TMaterialPriceAdjustRecordExample.Criteria param = ex.createCriteria();
		param.andAdjustIdEqualTo(adjustId);
		ex.setOrderByClause("ID ");
		List<TMaterialPriceAdjustRecord> list = this.materialPriceAdjustRecordMapper.selectByExample(ex);
		return list;
	}

	@Override
	public void editPrice(ProductPriceDTO model) {
		TMaterialPriceAdjustRecord record = new TMaterialPriceAdjustRecord();
		BeanUtils.copyProperties(model,record);
		record.setCreateTs(new Date());
		record.setPrice(BigDecimalUtil.multiply100(BigDecimal.valueOf(model.getPrice())));
		
		String materialId = record.getMaterialId();
		String channel = record.getChannel();
		String organizationId = record.getOrganizationId();
		TMaterialPrice mp = findActiveByMaterialIdAndOrgIdAndChannel(materialId, organizationId, channel);
		Assert.notNull(mp,"没有找到对应的物料价格信息，无法调整!");
		record.setOadjustPrice(mp.getAdjustPrice());
		record.setObdate(mp.getBdate());
		record.setOedate(mp.getEdate());
		record.setOprice(mp.getPrice());
		TMaterialPriceAdjust mpa = new TMaterialPriceAdjust();
		mpa.setCreateTs(new Date());
		mpa.setAdjustType(PriceAdjustType.STANDARD);
		mpa.setStatus(PriceAdjustStatus.DRAFT);
		mpa.setAdjustDesc(TMaterialPriceAdjust.STANDARD_EDIT);
		mpa.setAdjustOpt(PriceAdjustOperation.OVERRIDE);
		mpa.setAdjustVal(record.getPrice());
		mpa.setBdate(model.getBdate());
		mpa.setBdate(model.getEdate());
		materialPriceAdjustMapper.insertSelective(mpa);
		record.setAdjustId(mpa.getId());
		materialPriceAdjustRecordMapper.insertSelective(record);
	}

	@Override
	public void editAdjustPrice(ProductPriceAdjustEditDTO model) {
		TMaterialPriceAdjustRecord record = new TMaterialPriceAdjustRecord();
		BeanUtils.copyProperties(model,record);
		record.setCreateTs(new Date());
		record.setPrice(BigDecimalUtil.multiply100(model.getAdjustVal()));
		record.setOpt(model.getAdjustOpt());
		TMaterialPriceAdjust mpa = new TMaterialPriceAdjust();
		mpa.setCreateTs(new Date());
		mpa.setAdjustType(PriceAdjustType.ADJUSTED);
		mpa.setStatus(PriceAdjustStatus.DRAFT);
		mpa.setAdjustDesc(TMaterialPriceAdjust.ADJUSTED_EDIT);
		mpa.setAdjustOpt(model.getAdjustOptEnu());
		mpa.setAdjustVal(record.getPrice());
		mpa.setBdate(model.getBdate());
		mpa.setBdate(model.getEdate());
		materialPriceAdjustMapper.insertSelective(mpa);
		record.setAdjustId(mpa.getId());
		String materialId = record.getMaterialId();
		String channel = record.getChannel();
		String organizationId = record.getOrganizationId();
		TMaterialPrice mp = findActiveByMaterialIdAndOrgIdAndChannel(materialId, organizationId, channel);
		Assert.notNull(mp,"没有找到对应的物料价格信息，无法调整!");
		record.setOadjustPrice(mp.getAdjustPrice());
		record.setObdate(mp.getBdate());
		record.setOedate(mp.getEdate());
		record.setOprice(mp.getPrice());
		materialPriceAdjustRecordMapper.insertSelective(record);
	}

	@Override
	public void editAdjustBatchPrice(ProductPriceAdjustDTO model) {
		String domainProperty = model.getAdjustCategoryEnu().getDomainProperty();
		ProductPriceQueryDTO mb = new ProductPriceQueryDTO();
		try {
			PropertyDescriptor pd = new PropertyDescriptor(domainProperty, ProductPriceQueryDTO.class);  
			Method setMethod = pd.getWriteMethod();
			Class<?> fieldType = ProductPriceQueryDTO.class.getDeclaredField(domainProperty).getType();
			Object paramSplits;
			if(Collection.class.isAssignableFrom(fieldType)){
				String[] sapIdArray = model.getAdjustParam().split(",");
				paramSplits = Arrays.asList(sapIdArray);
			}else{
				paramSplits = model.getAdjustParam();
			}
			setMethod.invoke(mb,paramSplits);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | IntrospectionException | NoSuchFieldException | SecurityException e) {
			LOG.error("批量处理调整价时查询参数处理异常！", e);
			throw new HHNZException("批量处理调整价时查询参数处理异常！");
		}
		List<TMaterialPrice> materials = materialPriceMapper.selectByMaterialBase(mb);
		if(materials.size() == 0){
			throw new HHNZException("没有该条件下的物料价格可以调整！");
		}
		TMaterialPriceAdjust mpa = new TMaterialPriceAdjust();
		BeanUtils.copyProperties(model, mpa);
		mpa.setCreateTs(new Date());
		mpa.setAdjustType(PriceAdjustType.ADJUSTED);
		mpa.setStatus(PriceAdjustStatus.DRAFT);
		mpa.setAdjustDesc(TMaterialPriceAdjust.ADJUSTED_BATCH);
		mpa.setBdate(model.getBdate());
		mpa.setBdate(model.getEdate());
		materialPriceAdjustMapper.insertSelective(mpa);
		
		List<TMaterialPriceAdjustRecord> records = new ArrayList<>();
		for (TMaterialPrice mp : materials) {
			TMaterialPriceAdjustRecord record = new TMaterialPriceAdjustRecord();
			String materialId = mp.getMaterialId();
			String channel = mp.getChannel();
			String organizationId = mp.getOrganizationId();
			
			record.setOpt(model.getAdjustOpt());
			record.setMaterialId(materialId);
			record.setOrganizationId(organizationId);
			record.setChannel(channel);
			record.setBdate(model.getBdate());
			record.setEdate(model.getEdate());
			record.setCreateTs(mpa.getCreateTs());
			record.setPrice(BigDecimalUtil.multiply100(model.getAdjustVal()));
			record.setAdjustId(mpa.getId());
			
			TMaterialPrice tmp = findActiveByMaterialIdAndOrgIdAndChannel(materialId, organizationId, channel);
			if(tmp != null){
				record.setOadjustPrice(tmp.getAdjustPrice());
				record.setObdate(tmp.getBdate());
				record.setOedate(tmp.getEdate());
				record.setOprice(tmp.getPrice());
			}
			
			records.add(record);
		}
		materialPriceAdjustRecordMapper.insertBatch(records);
	}

	@Override
	public TMaterialPriceAdjust findAdjustById(Long adjustId) {
		return materialPriceAdjustMapper.selectByPrimaryKey(adjustId);
	}

	@Override
	public void deleteAdjustPrice(Long adjustId) {
		TMaterialPriceAdjustRecordExample ex = new TMaterialPriceAdjustRecordExample();
		com.hhnz.crm.model.TMaterialPriceAdjustRecordExample.Criteria param = ex.createCriteria();
		param.andAdjustIdEqualTo(adjustId);
		materialPriceAdjustRecordMapper.deleteByExample(ex);
		materialPriceAdjustMapper.deleteByPrimaryKey(adjustId);
	}
	
	@Override
	public TMaterialPrice findActiveByMaterialIdAndOrgIdAndChannel(String materialId, String orgId, String channel){
		List<TMaterialPrice> mps = materialPriceMapper.selectActiveByMaterialIdAndOrgIdAndChannel(materialId, orgId, channel);
		if(!mps.isEmpty()){
			return mps.get(0);
		}
		return null;
	}
	
	@Override
	public List<TMaterialPrice> findAllActive() {
		return materialPriceMapper.selectActiveByMaterialIdAndOrgIdAndChannel(null, null, null);
	}
	
	@Override
	public TPriceAdjustApproved findActiveAdjustByMaterialIdAndOrgIdAndChannel(Date date,String materialId, String orgId, String channel){
		Assert.notNull(date);
		Assert.notNull(materialId);
		Assert.notNull(orgId);
		Assert.notNull(channel);
		TPriceAdjustApprovedExample example = new TPriceAdjustApprovedExample();
		com.hhnz.crm.model.TPriceAdjustApprovedExample.Criteria criteria = example.createCriteria();
		criteria.andBdateLessThanOrEqualTo(date);
		criteria.andEdateGreaterThanOrEqualTo(date);
		criteria.andMaterialIdEqualTo(materialId);
		criteria.andOrganizationIdEqualTo(orgId);
		criteria.andChannelEqualTo(channel);
		List<TPriceAdjustApproved> results = priceAdjustApprovedMapper.selectByExample(example);
		if(!results.isEmpty()){
			return results.get(0);
		}
		return null;
	}
	
	@Override
	public void updateAdjustPrice(TMaterialPriceAdjust priceAdjust){
		materialPriceAdjustMapper.updateByPrimaryKeySelective(priceAdjust);
	}

	@Override
	public void insert(TMaterialPrice materialPrice) {
		materialPriceMapper.insertSelective(materialPrice);
	}
	
	@Override
	public void insert(List<TMaterialPrice> materialPrices) {
		materialPriceMapper.insertBatch(materialPrices);
	}
	
	@Override
	public void update(List<TMaterialPrice> materialPrices) {
		materialPriceMapper.updateBatch(materialPrices);
	}

	@Override
	public List<TMaterialPrice> findLaterByMaterialIdAndOrgIdAndChannel(String materialId, String orgId, String channel) {
		return materialPriceMapper.selectLaterByMaterialIdAndOrgIdAndChannel(materialId, orgId, channel);
	}

	@Override
	public void update(TMaterialPrice materialPrice) {
		materialPriceMapper.updateByPrimaryKeySelective(materialPrice);
	}

	@Override
	public void insertApprovedAdjustBatch(List<TPriceAdjustApproved> adjustApprovedList) {
		// 必须要有明细，防止mapper产生错误的sql语句 begin; end;
		if (adjustApprovedList != null && adjustApprovedList.size() > 0) {
			priceAdjustApprovedMapper.insertApprovedAdjustBatch(adjustApprovedList);
		}
	}
}

