package com.hhnz.organization.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.customer.mapper.CMerchCustStationMapper;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.customer.model.CMerchCustStationExample;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.mapper.CrmStationMapper;
import com.hhnz.organization.mapper.CrmStationVMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.model.CrmStationExample;
import com.hhnz.organization.model.CrmStationV;
import com.hhnz.organization.model.CrmStationVExample;
import com.hhnz.organization.model.CrmStationVExample.Criteria;
import com.hhnz.organization.service.IStationService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
public class StationServiceImpl implements IStationService {
  @Resource
  private CrmStationMapper stationMapper;
  @Resource
  private CrmStationVMapper stationVMapper;
  @Resource
  private UtilMapper utilMapper;
  @Resource
  private ICustomerService customerService;
  @Resource
  private CMerchCustStationMapper merchCustStationMapper;
  @Resource
  private CrmSalesOrganizationMapper orgMapper;

  @Override
  public AjaxDTO findStations(AjaxDTO bean, CrmStation station, String orgid) {
    String reginid = "";
    Page page = new Page();
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    CrmStationVExample ex = new CrmStationVExample();
    ex.setPage(page);
    ex.setOrderByClause("organization_id asc");
    Criteria param = ex.createCriteria();
    if (StringUtils.isNotEmpty(orgid) && orgid.length() > 9) {
      reginid = orgid.substring(9);
      orgid = orgid.substring(0, 9);
    }
    if (StringUtils.isNotEmpty(orgid)) {
      param.andOrganizationIdLike(orgid + "%");
    }
    if (StringUtils.isNotEmpty(bean.getSearch())) {
      param.andNameLike("%" + bean.getSearch() + "%");
    }
    if (station != null && station.getSalesrepId() != null && station.getSalesrepId() != 0) {
      param.andSalesrepIdEqualTo(station.getSalesrepId());
    }
    if (StringUtils.isNotEmpty(reginid)) {
      param.andOrgAreaIdLike(reginid + "%");
    }
    List<CrmStationV> list = this.stationVMapper.selectByExample(ex);
    int total = this.stationVMapper.countByExample(ex);
    bean.setRows(list);
    bean.setTotal(total);
    return bean;
  }

  @Override
  public List<UserStations> findUserStations(Long userid) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", userid);
    List<UserStations> stations = utilMapper.getUserStations(params);
    for(UserStations station:stations){
      station.setStationname(station.getProvName()+"-"+station.getStationname());
    }
    return stations;
  }

  @Override
  public int edit(CrmStation model) {
    return stationMapper.updateByPrimaryKeySelective(model);
  }

  @Override
  public int save(CrmStation model) {
    return stationMapper.insert(model);
  }

  @Override
  public int relateCustomers(Long stationId, List<Long> customerIds, TEmployee user) {
    int result = 0;
    deleteRelated(stationId);
    if (customerIds == null) {
      return 1;
    }
    for (Long customerId : customerIds) {
      CMerchCustStation custStation = new CMerchCustStation();
      custStation.setUpdateOid(user.getId());
      custStation.setUpdateTs(new Date());
      custStation.setCreateOid(user.getId());
      custStation.setCreateTs(new Date());
      custStation.setMerchCustId(customerId);
      custStation.setStates("1");
      custStation.setStationId(stationId);
      customerService.setPosition(custStation);
      result++;
    }
    return result;
  }

  private int deleteRelated(Long stationId) {
    CMerchCustStationExample ex = new CMerchCustStationExample();
    ex.createCriteria().andStationIdEqualTo(stationId);
    return merchCustStationMapper.deleteByExample(ex);
  }

  @Override
  public AjaxDTO relatedCustomers(Long stationId) {
    CMerchCustStationExample ex = new CMerchCustStationExample();
    ex.createCriteria().andStationIdEqualTo(stationId);
    List<CMerchCustStation> list = merchCustStationMapper.selectByExample(ex);
    AjaxDTO dto = new AjaxDTO();
    dto.setRows(list);
    return dto;
  }


  @Override
  public int delete(Long id) {
    return stationMapper.deleteByPrimaryKey(id);
  }

  @Override
  public List<CrmStation> findStationsByorgid(String orgid) {
    // TODO Auto-generated method stub
    CrmStationExample ex = new CrmStationExample();
    CrmStationExample.Criteria ext = ex.createCriteria();
    ext.andOrganizationIdLike(orgid + "%");
    return this.stationMapper.selectByExample(ex);
  }

}
