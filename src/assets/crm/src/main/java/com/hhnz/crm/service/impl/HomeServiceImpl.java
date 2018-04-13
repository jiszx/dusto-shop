package com.hhnz.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.mapper.TAuthorityMapper;
import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TAuthorityExample;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TEmployeeExample;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.TreeDTO;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.mapper.TJobUserVMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.TJobUserV;
import com.hhnz.organization.model.TJobUserVExample;
import com.hhnz.process.mapper.VTaskMapper;
import com.hhnz.process.model.VTaskExample;

/**
 * Created by yang on 2016/6/24.
 */
@Service
@Transactional
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private TAuthorityMapper mapper;
	@Resource
	private TEmployeeMapper usermapper;

	@Resource
	private CrmSalesOrganizationMapper orgmapper;

	@Autowired
	private TJobUserVMapper jobMapper;

	@Resource
	private UtilMapper utilmapper;

	@Autowired
	private VTaskMapper taskMapper;

	public List<TreeDTO<TAuthority>> findAllAuthority() throws Exception {
		List<TreeDTO<TAuthority>> nodes = new ArrayList<TreeDTO<TAuthority>>();
		TAuthorityExample top = new TAuthorityExample();
		top.createCriteria().andPidIsNull().andXtypeEqualTo("0");
		top.setOrderByClause("orders asc");
		List<TAuthority> moduls = this.mapper.selectByExample(top);
		for (TAuthority auth : moduls) {
			TAuthorityExample ex = new TAuthorityExample();
			ex.createCriteria().andPidEqualTo(auth.getResId());
			ex.setOrderByClause("orders asc");
			List<TAuthority> list = this.mapper.selectByExample(ex);
			if (list != null && list.size() > 0) {
				TreeDTO<TAuthority> node = new TreeDTO<TAuthority>();
				node.setNode(auth);
				node.setNodes(list);
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public List<TreeDTO<TAuthority>> findAllAuthority(Integer rid)
			throws Exception {
		List<TreeDTO<TAuthority>> nodes = new ArrayList<TreeDTO<TAuthority>>();
		if (rid == 1) {
			return this.findAllAuthority();
		} else {
			List<TAuthority> auths = this.mapper.findAuthByRid(rid, 0L);
			for (TAuthority auth : auths) {
				List<TAuthority> list = this.mapper.findAuthByRid(rid,
						auth.getResId());
				if (list != null && list.size() > 0) {
					TreeDTO<TAuthority> node = new TreeDTO<TAuthority>();
					node.setNode(auth);
					node.setNodes(list);
					nodes.add(node);
				}
			}
			return nodes;
		}
	}

	@Override
	public Map<String, Object> findUserSessionInfo(String name)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 个人信息
		TEmployeeExample ex = new TEmployeeExample();
		ex.createCriteria().andLoginNameEqualTo(name);
		List<TEmployee> temployee = this.usermapper.selectByExample(ex);
		TEmployee user = temployee.get(0);
		map.put(SessionKey.USER_INFO, temployee.get(0));
		// 销售组织
		/*
		 * CrmSalesOrganizationExample org = new CrmSalesOrganizationExample();
		 * org
		 * .createCriteria().andIdEqualTo(temployee.get(0).getOrganizationId());
		 * List<CrmSalesOrganization> organization =
		 * this.orgmapper.selectByExample(org); map.put(SessionKey.ORG_INFO,
		 * organization.get(0));
		 */
		// 获取用户能查看数据的销售组织
		List<CrmSalesOrganization> orglist = new ArrayList<CrmSalesOrganization>();
		// this.utilmapper.getuserorgs(user.getId());
		List<String> orgids = new ArrayList<String>();
		// 岗位信息
		List<UserStations> userstations = new ArrayList<UserStations>();
		List<UserJobs> userjobs = new ArrayList<UserJobs>();
		if ("1".equals(user.getIsSalesman())) {
			// TODO 谌凯--查询用户岗位信息，多个按照创建时间倒序
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", user.getId());
			userstations = this.utilmapper.getUserStations(params);
		}
		userjobs = this.utilmapper.getUserJobs(user.getId());
		if (userjobs != null && userjobs.size() > 0) {
			map.put(SessionKey.JOBS, userjobs);
			// 通过职位查询销售组织权限
			for (UserJobs job : userjobs) {
				orgids.add(job.getOrgid());
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orgids", orgids);
			orglist = this.utilmapper.getuserorgs(params);
		}
		map.put(SessionKey.ORG_INFO, orglist);
		if (userstations != null && userstations.size() > 0) {
			map.put(SessionKey.STATIONS, userstations);
			map.put(SessionKey.CURR_STATION, userstations.get(0));
		}
		return map;
	}

	@Override
	public Long getCustNumByStationId(Long stationid) {
		// TODO Auto-generated method stub
		return this.utilmapper.getCustNumByStationId(stationid);
	}

	@Override
	public Long getauditOrder(Long stationid) {
		// TODO Auto-generated method stub
		return this.utilmapper.getauditOrder(stationid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Integer mytaskNum(TEmployee user) {
		// TODO Auto-generated method stub
		List users = new ArrayList<String>();
		users.add(user.getLoginName());
		
		TJobUserVExample eex = new TJobUserVExample();
		eex.createCriteria().andIdEqualTo(user.getId());
		List<TJobUserV> jobs = this.jobMapper.selectByExample(eex);
		for (TJobUserV tJobUserV : jobs) {
			if (StringUtils.hasLength(tJobUserV.getStationname())) {
				users.add(tJobUserV.getStationname());
			}
		}
		
		VTaskExample ex = new VTaskExample();
		ex.createCriteria().andEndTimeIsNull().andAssigneeIn(users);
		return this.taskMapper.countByExample(ex);
	}

	@Override
	public String getUserOrgInfo(String stationorgid) {
		// TODO Auto-generated method stub
		return this.utilmapper.getUserOrgInfo(stationorgid);
	}
}
