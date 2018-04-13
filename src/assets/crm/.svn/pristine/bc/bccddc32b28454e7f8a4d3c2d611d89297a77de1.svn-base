package com.hhnz.combination.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.combination.dto.CombinationDTO;
import com.hhnz.combination.dto.CombinationLinesDTO;
import com.hhnz.combination.dto.CombinationProductDTO;
import com.hhnz.combination.mapper.CombinationUtilMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageHeaderMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageLinesMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageRebateMapper;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.model.CrmMaterialPackageHeaderExample;
import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageRebate;
import com.hhnz.combination.model.CrmMaterialPackageRebateExample;
import com.hhnz.combination.service.CombinationService;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseExample;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class CombinationServiceImpl implements CombinationService {
	
	@Resource
	private CrmMaterialPackageHeaderMapper headmapper;
	@Resource
	private  CrmMaterialPackageLinesMapper linemapper;
	@Resource
	private CrmMaterialPackageRebateMapper rebatemapper;
	
	@Resource
	private CombinationUtilMapper  uitlmapper;
	
	@Resource
	private TMaterialBaseMapper  materialMapper;
	@Override
	public String addOrUpdateCombination(CombinationDTO model,TEmployee user) throws Exception{
		CrmMaterialPackageHeader  header = model.getHeader();
		if(header.getId() !=null){
			return updateCombination(model, user);
		}else{
			return addCombination(model, user);
		}
		
	}
	private String updateCombination(CombinationDTO model,TEmployee user) throws Exception{
		CrmMaterialPackageHeader  header = model.getHeader();
		header.setUpdateTs(new Date());
		header.setUpdateOid(user.getId());
		header.setPrice(BigDecimalASME.multiply(header.getPrice()));
		this.headmapper.updateByPrimaryKeySelective(header);
		if(model.getLines().size()>0 && model.getLines()!=null){
			for(CrmMaterialPackageLines line :model.getLines()){
				line.setHeaderId(header.getId());
				CrmMaterialPackageLines l = this.linemapper.selectByPrimaryKey(line.getId());
				line.setPrice(BigDecimalASME.multiply(line.getPrice()));
				if(l!=null){
					this.linemapper.updateByPrimaryKeySelective(line);
				}else{
					line.setId(null);
					this.linemapper.insert(line);
				}
			}
		}
		if(model.getRebates().size()>0 && model.getRebates() !=null){
			for(CrmMaterialPackageRebate rebate :model.getRebates()){
				CrmMaterialPackageRebate r= this.rebatemapper.selectByPrimaryKey(rebate.getId());
				if(r !=null){
					rebate.setPrice(BigDecimalASME.multiply(rebate.getPrice()));
					this.rebatemapper.updateByPrimaryKeySelective(rebate);
				}else{
					rebate.setHeaderId(header.getId());
					rebate.setId(null);
					rebate.setPrice(BigDecimalASME.multiply(rebate.getPrice()));
					this.rebatemapper.insert(rebate);
				}
			}
		}
		return "S";
	}
	private String addCombination(CombinationDTO model,TEmployee user) throws Exception{
		CrmMaterialPackageHeader  header = model.getHeader();
		header.setCreateTs(new Date());
		header.setCreateOid(user.getId());
		header.setStates("1");
		header.setUpdateTs(new Date());
		header.setUpdateOid(user.getId());
		header.setPrice(BigDecimalASME.multiply(header.getPrice()));
		this.headmapper.insert(header);
		if(model.getLines().size()>0 && model.getLines()!=null){
			for(CrmMaterialPackageLines line :model.getLines()){
				line.setHeaderId(header.getId());
				line.setId(null);
				line.setPrice(BigDecimalASME.multiply(line.getPrice()));
				this.linemapper.insert(line);
			}
		}
		if(model.getRebates().size()>0 && model.getRebates() !=null){
			for(CrmMaterialPackageRebate rebate :model.getRebates()){
				rebate.setHeaderId(header.getId());
				rebate.setId(null);
				rebate.setPrice(BigDecimalASME.multiply(rebate.getPrice()));
				this.rebatemapper.insert(rebate);
			}
		}
		return "S";
	}
	@Override
	public AjaxDTO getProduct(AjaxDTO bean, String modelType,String materialId)throws Exception {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		params.put("modelType", modelType);
		params.put("materialId", materialId);
		params.put("search", bean.getSearch());
		List<CombinationProductDTO> list = this.uitlmapper.selectProduct(params);
		int total = this.uitlmapper.countProduct(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getRebateMaterial() throws Exception{
		AjaxDTO dto = new AjaxDTO();
		TMaterialBaseExample mateiralEx = new TMaterialBaseExample();
		TMaterialBaseExample.Criteria mateiralExt= mateiralEx.createCriteria();
		mateiralExt.andActiveEqualTo("1");
		List<TMaterialBase> list = this.materialMapper.selectByExample(mateiralEx);
		int total = this.materialMapper.countByExample(mateiralEx);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getCombinationList(AjaxDTO bean)throws Exception {
		AjaxDTO dto = new AjaxDTO();
		Page page = new Page();
		page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CrmMaterialPackageHeaderExample ex = new CrmMaterialPackageHeaderExample();
        ex.createCriteria().andStatesNotEqualTo("3");
        ex.setPage(page);
        ex.setOrderByClause(" create_ts desc");
        List<CrmMaterialPackageHeader> list = this.headmapper.selectByExample(ex);
        int total = this.headmapper.countByExample(ex);
        dto.setRows(list);
        dto.setTotal(total);
		return dto;
	}

	@Override
	public CrmMaterialPackageHeader getHeaderById(Long id) throws Exception{
		return this.headmapper.selectByPrimaryKey(id);
	}

	@Override
	public AjaxDTO getRebateData(Long headerId)throws Exception {
		AjaxDTO dto = new AjaxDTO();
		CrmMaterialPackageRebateExample ex = new CrmMaterialPackageRebateExample();
		CrmMaterialPackageRebateExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(headerId);
		List<CrmMaterialPackageRebate> list = this.rebatemapper.selectByExample(ex);
		int total = this.rebatemapper.countByExample(ex);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getLinesData(Long headerId)throws Exception {
		AjaxDTO dto = new AjaxDTO();
		List<CombinationLinesDTO> list = this.uitlmapper.selectLinesByHeaderId(headerId);
		int total = this.uitlmapper.countLinesByHeaderId(headerId);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public String delById(Long id)throws Exception {
		CrmMaterialPackageHeader header = this.headmapper.selectByPrimaryKey(id);
		header.setStates("3");
		this.headmapper.updateByPrimaryKeySelective(header);
		return "S";
	}

	@Override
	public String addProductLine(CrmMaterialPackageLines line)throws Exception {
		line.setId(null);
		this.linemapper.insert(line);
		return "S";
	}

	@Override
	public String delProductLine(Long id) throws Exception{
		 this.linemapper.deleteByPrimaryKey(id);
		 return "S";
	}
	@Override
	public String delRebateById(Long id) {
		this.rebatemapper.deleteByPrimaryKey(id);
		return "S";
	}
	@Override
	public String audit(Long id) {
		CrmMaterialPackageHeader header = this.headmapper.selectByPrimaryKey(id);
		header.setStates("2");
		this.headmapper.updateByPrimaryKeySelective(header);
		return "S";
	}
	@Override
	public String validateCode(String code) {
		CrmMaterialPackageHeaderExample ex = new CrmMaterialPackageHeaderExample();
		CrmMaterialPackageHeaderExample.Criteria ext = ex.createCriteria();
		ext.andCodeEqualTo(code);
		List<CrmMaterialPackageHeader> list = this.headmapper.selectByExample(ex);
		return list.size()==0||list ==null ?"S":"E";
	}
	@Override
	public String addRebateLine(CrmMaterialPackageRebate line) {
		line.setId(null);
		this.rebatemapper.insert(line);
		return "S";
	}
	@Override
	public String updatePrice(Long id, BigDecimal amt) {
		CrmMaterialPackageHeader  header = this.headmapper.selectByPrimaryKey(id);
		if(header !=null){
			header.setPrice(BigDecimalASME.multiply(amt));
			this.headmapper.updateByPrimaryKeySelective(header);
			return "S";
		}
		return "E";
	}
	@Override
	public String editProductLine(CrmMaterialPackageLines line) {
		line.setPrice(BigDecimalASME.multiply(line.getPrice()));
		return this.linemapper.updateByPrimaryKeySelective(line)==1?"S":"E";
	}
}
