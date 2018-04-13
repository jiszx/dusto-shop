package com.hhnz.customer.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.service.IDictService;
import com.hhnz.customer.mapper.CMerchCustContractLinesMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustContractVMapper;
import com.hhnz.customer.mapper.CrmBusinessModelMapper;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customer.model.CMerchCustContractExample.Criteria;
import com.hhnz.customer.model.CMerchCustContractLines;
import com.hhnz.customer.model.CMerchCustContractLinesExample;
import com.hhnz.customer.model.CMerchCustContractV;
import com.hhnz.customer.model.CMerchCustContractVExample;
import com.hhnz.customer.model.ContractDetail;
import com.hhnz.customer.model.CrmBusinessModel;
import com.hhnz.customer.model.CrmBusinessModelExample;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.model.TAreaExample;
import com.hhnz.sapdata.mapper.TProductCategoryMapper;
import com.hhnz.sapdata.model.TProductCategory;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.db.Page;

@Service
public class CustomerContractServiceImpl implements CustomerContractService {
	@Resource
	private CMerchCustContractVMapper contractVMapper;

	@Autowired
	private CMerchCustContractMapper mapper;

	@Autowired
	private TMaterialBaseMapper metrarialMapper;

	@Autowired
	private CMerchCustContractLinesMapper linesMapper;

	@Autowired
	private TProductCategoryMapper categoryMapper;
    
	@Resource
	private TAreaMapper  areamapper;
	
	@Resource
	private UtilMapper utilmapper;
	
	@Resource
	private CrmBusinessModelMapper  businessmapper;
	@Resource
    private IDictService dictService;
	@Resource
	private IorganizationService orgService;
	@Resource
	private CrmBusinessModelMapper businessModelMapper;
	@Override
	public AjaxDTO list(AjaxDTO page, CMerchCustContractV contract,List<Long> stationids) {
		CMerchCustContractVExample example = new CMerchCustContractVExample();
		Page p = new Page();
		p.setLimit(page.getLimit());
		p.setOffset(page.getOffset());
		example.setPage(p);
		example.setOrderByClause("id desc");
		CMerchCustContractVExample.Criteria param = example.createCriteria();
		if(StringUtils.isNotEmpty(contract.getCustname())){
			param.andCustnameLike("%"+contract.getCustname()+"%");	
		}
		if (StringUtils.isNotEmpty(contract.getMerchLevels())) {
			param.andMerchLevelsEqualTo(contract.getMerchLevels());
		}
		if (StringUtils.isNotEmpty(contract.getStates())) {
		  param.andStatesEqualTo(
					contract.getStates());
		}
		if(StringUtils.isNotEmpty(contract.getCustType())){
		  param.addCriterion("cust_type =", contract.getCustType(), "custtype");
		}
		if(StringUtils.isNotEmpty(contract.getSettleType())){
		  param.andSettleTypeEqualTo(contract.getSettleType());
		}
		if(StringUtils.isNotEmpty(contract.getOrganizationId()) && contract.getOrganizationId() !=null){
		  param.andOrganizationIdEqualTo(contract.getOrganizationId());
		}
		param.andStationIdIn(stationids);
		List<CMerchCustContractV> list = contractVMapper
				.selectByExample(example);
		int count = contractVMapper.countByExample(example);
		page.setRows(list);
		page.setTotal(count);
		return page;
	}
	
	@Override
	public ContractDetail merchContractdetail(Long merchid){
	  ContractDetail result = new ContractDetail();
	  String now = DateUtil.format(new Date(), "yyyy-MM-dd");
	  CMerchCustContractExample ex = new CMerchCustContractExample();
	  Criteria param = ex.createCriteria();
	  param.andMerchCustIdEqualTo(merchid);
	  param.andContractBdateLessThanOrEqualTo(now);
	  param.andContractEdateGreaterThanOrEqualTo(now);
	  List<CMerchCustContract> contracts = mapper.selectByExample(ex);
	  if(contracts.isEmpty()){
	    return result;
	  }
	  CMerchCustContract contract = contracts.get(0);
	  
	  String areas = contract.getAgentArea();
		if(!StringUtils.isEmpty(areas)){
			String  str="";
			String[] areais = areas.split(",");
			List<String> area = new ArrayList<String>();
			for(String areaid:areais){
				area.add(areaid.replaceAll(" ", ""));
			}
			TAreaExample  areaEx = new TAreaExample();
			areaEx.createCriteria().andIdIn(area);
			List<TArea> list = this.areamapper.selectByExample(areaEx);
			for(TArea tarea:list){
				str +=tarea.getName()+",";
			}
			String tmp = "";
			  if(str.length()>0){
				  tmp = str.substring(0, str.length() - 1);
			  }
			contract.setAgentArea(tmp);
		}
		
	  
	  List<CMerchCustContractLines> lines = getLines(contract.getId());

	  
	  for(int index = 0; index <lines.size();index++){
		  CMerchCustContractLines contractline = lines.get(index);
		  contractline.setqAmt(contractline.getqAmt().divide(new BigDecimal(100)));
		  contractline.setyAmt(contractline.getyAmt().divide(new BigDecimal(100)));
	  }
	  
	  StringBuilder saleBrand = new StringBuilder();
	  StringBuilder saleType = new StringBuilder();
	  StringBuilder saleSeries = new StringBuilder();
	  for(CMerchCustContractLines line : lines){
		  String type = line.getAgentType();
		  if("3".equals(type)){//品牌
			  String name = line.getName();
			  saleBrand.append(name+",");
		  }
		  if("2".equals(type)){//产品大类
			  String name = line.getName();
			  saleSeries.append(name+",");
		  }
		  if("1".equals(type)){//大类
			  String name = line.getName();
			  saleType.append(name+",");
		  }
	  }
	  String saleBrd = "";
	  if(saleBrand.length()>0){
		  saleBrd = saleBrand.substring(0, saleBrand.length() - 1);
	  }
	  String saleSer = "";
	  if(saleSeries.length()>0){
		  saleSer = saleSeries.substring(0, saleSeries.length() - 1);
	  }
	  String saleTp = "";
	  if(saleType.length()>0){
		  saleTp = saleType.substring(0, saleType.length() - 1);
	  }
	  
	  contract.setSaleBrand(saleBrd);
	  contract.setSaleType(saleSer);
	  contract.setSaleSeries(saleTp);
	  
	  result.setContract(contract);
	  result.setLines(lines);
	  return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Long addPaper(CMerchCustContract model,String lineData) throws Exception {
		if (model.getId() != null) {
			this.mapper.updateByPrimaryKeySelective(model);
		} else {
			int length = model.getOrganizationId().length();
			CrmSalesOrganization org = orgService.findById(length>5?model.getOrganizationId().substring(0, 5):model.getOrganizationId());
			model.setFactoryId(org.getSapId());
			model.setStates("1");
			
			this.mapper.insertSelective(model);
			
			/*Map<String,Object> params = new HashMap<String, Object>();
			params.put("contractId", model.getId());
			params.put("createOid", model.getCreateOid());
			params.put("agentType", model.getAgentType());*/
			//this.mapper.addContractLine(model);
			List<CMerchCustContractLines> lines = new ArrayList<CMerchCustContractLines>();
			JSONArray lineArray = JSONArray.fromObject(lineData);
			lines = lineData !=null?(List) JSONArray.toCollection(lineArray,CMerchCustContractLines.class):null;
			for(CMerchCustContractLines line:lines){
				line.setContractId(model.getId());
				line.setId(null);
				line.setyAmt(BigDecimalASME.multiply(line.getyAmt()));
				if(line.getqAmt()!=null){
					line.setqAmt(BigDecimalASME.multiply(line.getqAmt()));					
				}
				this.linesMapper.insert(line);
			}
		}
		
		return model.getId();
	}

	@Override
	public int addPaperLines(CMerchCustContractLines model) throws Exception {
		// 判断是否存在
		CMerchCustContractLinesExample ex = new CMerchCustContractLinesExample();
		ex.createCriteria().andAgentTypeEqualTo(model.getAgentType())
				.andAgentIdEqualTo(model.getAgentId()).andContractIdEqualTo(model.getContractId());
		List<CMerchCustContractLines> res = this.linesMapper
				.selectByExample(ex);
		if (res != null && res.size() > 0) {
			model.setName(res.get(0).getName());
			model.setId(res.get(0).getId());
			return this.linesMapper.updateByPrimaryKeySelective(model);
		} else {
			if ("1".equals(model.getAgentType())) {// 大磊
				TProductCategory tp = this.categoryMapper
						.selectByPrimaryKey(model.getAgentId());
				model.setName(tp.getName());
			}else if ("4".equals(model.getAgentType())) {// SKU
				TMaterialBase base = this.metrarialMapper
						.selectByPrimaryKey(model.getAgentId());
				model.setName(base.getSapId()+base.getSku());
			}else{
				model.setName(model.getAgentId());
			}
			/*else if ("2".equals(model.getAgentType())) {// 产品大磊
				TProductCategory tp = this.categoryMapper
						.selectByPrimaryKey(model.getAgentId());
				model.setName(tp.getName());
			} else if ("3".equals(model.getAgentType())) {// 品牌
				TProductCategory tp = this.categoryMapper
						.selectByPrimaryKey(model.getAgentId());
				model.setName(tp.getName());
			} */
			return this.linesMapper.insert(model);
		}
	}

	@Override
	public AjaxDTO listLines(Long id) throws Exception {
		List<CMerchCustContractLines> lines = getLines(id);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(lines);
		return dto;
	}
	
	@Override
	public List<CMerchCustContractLines> getLines(Long id){
	  CMerchCustContractLinesExample ex = new CMerchCustContractLinesExample();
      ex.createCriteria().andContractIdEqualTo(id);
      return this.linesMapper.selectByExample(ex);
	}

	@Override
	public int delLines(Long id) throws Exception {
		return this.linesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateLines(CMerchCustContractLines model) throws Exception {
	  if(model.getyAmt()!=null){
	    model.setyAmt(BigDecimalASME.multiply(model.getyAmt()));
	  }
	  if(model.getqAmt()!=null){
	    model.setqAmt(BigDecimalASME.multiply(model.getqAmt()));
	  }
	  return this.linesMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public CMerchCustContract findById(Long id) throws Exception {
		CMerchCustContract contract= new CMerchCustContract();
		contract= this.mapper.selectByPrimaryKey(id);
		String areas = contract.getAgentArea();
		if(!StringUtils.isEmpty(areas)){
			String  str="";
			String[] areais = areas.split(",");
			List<String> area = new ArrayList<String>();
			for(String areaid:areais){
				area.add(areaid.replaceAll(" ", ""));
			}
			TAreaExample  ex = new TAreaExample();
			ex.createCriteria().andIdIn(area);
			List<TArea> list = this.areamapper.selectByExample(ex);
			for(TArea tarea:list){
				str +=tarea.getName()+",";
			}
			contract.setAgentArea(str);
		}
		return contract;
	}

	@Override
	public String delContract(Long contractid) {
		int result = this.mapper.deleteByPrimaryKey(contractid);
		CMerchCustContractLinesExample  ex = new CMerchCustContractLinesExample();
		ex.createCriteria().andContractIdEqualTo(contractid);
		List<CMerchCustContractLines> list =new ArrayList<CMerchCustContractLines>();
		list=this.linesMapper.selectByExample(ex);
		if(list !=null && list.size()>0){
			for (CMerchCustContractLines line:list){
				this.linesMapper.deleteByPrimaryKey(line.getId());
			}
		}
		return result != 0 ? "200" : "500";
	}

	@Override
	public String updateContractStates(Long contractid, String states) {
		CMerchCustContract  contract = new CMerchCustContract();
		contract = this.mapper.selectByPrimaryKey(contractid);
		if(contract !=null){
			contract.setStates(states);
			return this.mapper.updateByPrimaryKeySelective(contract)==1?"200":"500";
		}else{			
			return "500";
		}
	}

	@Override
	public void updateContractProcess(Long contractid, String processId) {
		// TODO Auto-generated method stub
		CMerchCustContract  contract = new CMerchCustContract();
		contract = this.mapper.selectByPrimaryKey(contractid);
		contract.setAttribute1(processId);
		this.mapper.updateByPrimaryKeySelective(contract);
	}

	@Override
	public List<TFactoryV> getfactorys(String orgid) {
		// TODO Auto-generated method stub
		return this.utilmapper.getfactorysByOrgid(orgid);
	}

	@Override
	public List<CMerchCustContract> findByCustomerAndOrgId(Long customerId,
			String orgId) {
		CMerchCustContractExample ex = new CMerchCustContractExample();
		CMerchCustContractExample.Criteria c = ex.createCriteria();
		c.andMerchCustIdEqualTo(customerId);
		c.andOrganizationIdEqualTo(orgId);
		c.andStatesEqualTo("4");//生效的状态！
		return mapper.selectByExample(ex);
	}

	@Override
	public AjaxDTO getAgents(String agentType) {
		AjaxDTO dto = new AjaxDTO();
		CrmBusinessModelExample ex = new CrmBusinessModelExample();
		CrmBusinessModelExample.Criteria ext = ex.createCriteria();
		ext.andModelIdEqualTo(agentType);
		List<CrmBusinessModel> list =  this.businessmapper.selectByExample(ex);
		int total = this.businessmapper.countByExample(ex);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public int addLineByagentType(CMerchCustContract contract) {
		return this.mapper.addContractLine(contract);
	}

	@Override
	public String getvirtualWarehouse(Long merchId) {
		return this.contractVMapper.getvirtualWarehouse(merchId);
	}
	
	@Override
    public void fillAgentName(CMerchCustContract contract){
      List<TDict> modes = dictService.findByName("BUSINESS_MODEL");
      for(TDict dict:modes){
        if(dict.getChooseVal().equals(contract.getAgentType())){
          contract.setAgentName(dict.getShowText());
          break;
        }
      }
    }
	
	@Override
    public void periodCodeToTime(List<CMerchCustContractV> contracts){
      List<TDict> periods = dictService.findByName("MERCH_CONTRACT_ACCOUNT_PERIOD");
      for(CMerchCustContractV contract:contracts){
        if(StringUtils.isEmpty(contract.getaPeriod())){
          continue;
        }
        for(TDict period:periods){
          if(contract.getaPeriod().equals(period.getChooseVal())){
            contract.setaPeriod(period.getShowText());
            break;
          }
        }
      }
    }

	@Override
	public AjaxDTO selectBondsByModel(String model) {
		CrmBusinessModelExample ex = new CrmBusinessModelExample();
		ex.createCriteria().andModelIdEqualTo(model);
		List<CrmBusinessModel> models = this.businessModelMapper
				.selectByExample(ex);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(models);
		dto.setTotal(models.size());
		return dto;
	}

}
