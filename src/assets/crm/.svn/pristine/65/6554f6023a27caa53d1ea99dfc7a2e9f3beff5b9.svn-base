package com.hhnz.pub.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.service.impl.AdjustAccountServiceImpl;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.mapper.PowerOrgMapper;
import com.hhnz.pub.model.PowerOrg;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 用户可查看的销售组织Service
 * @author hhnz-skevin
 *
 */
@Service
@Transactional
public class PowerOrgServiceImpl implements IPowerOrgService {
	private static Logger logger = Logger.getLogger(AdjustAccountServiceImpl.class);
	@Resource
	private PowerOrgMapper mapper;
	@Resource
    private IorganizationService orgService;
	
	@Override
	public AjaxDTO powerOrg(List<UserJobs> jobs) {
		List<PowerOrg> orgs = new ArrayList<PowerOrg>();
		AjaxDTO dto = new AjaxDTO();
		List<String> list = new ArrayList<String>();
		if(jobs !=null && !jobs.isEmpty()){
			for(UserJobs job:jobs){
				list.add(job.getOrgid());
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("orgids", list);
			orgs = this.mapper.powerOrg(params);
			dto.setRows(orgs);
			dto.setTotal(orgs.size());
		}
		return dto;
	}
	
	/**
	 * 获取有权限的业务省id
	 * @param user
	 * @param jobs
	 * @param station
	 * @return
	 */
	@Override
    public List<String> powerProv(TEmployee user, List<UserJobs> jobs, UserStations station) {
      if ("1".equals(user.getIsSalesman()) && isSalesByJob(jobs)) {
        List<String> orgIds = new ArrayList<>();
        orgIds.add(station.getStationorgid());
        return orgIds;
      }
      return subOrgids(jobs);
    }
	
    private List<String> subOrgids(List<UserJobs> jobs) {
      List<String> ids = new ArrayList<>();
      for (UserJobs job : jobs) {
        List<UserStations> stations = stations(subOrgs(job));
        for (UserStations station : stations) {
          String stationorgid = station.getStationorgid();
          ids.add(stationorgid.length() > 8 ? stationorgid.substring(0, 9) : stationorgid);
        }
      }
      return ids;
    }
	
    private boolean isSalesByJob(List<UserJobs> jobs) {
      if (jobs == null || jobs.isEmpty()) {
        return true;
      }
      for (UserJobs job : jobs) {
        if ("1".equals(job.getJobType())) {// 1表示部门负责人
          return false;
        }
      }
      return true;
    }
	
    /**
     * 数据查询时获取操作人员可以查看的岗位ID
     */
	@Override
	public List<Long> getUserStations(TEmployee user,List<UserJobs> jobs ,UserStations station ) {
		List<Long> stationids = new ArrayList<Long>();
		Set<Long> stationIdSet = new HashSet<>();
		if("1".equals(user.getIsSalesman())){
    		//销售人员
    		if(jobs!=null&&jobs.size()>0){
    			stationids = stationids(jobs,user);
    		}
    		//获取当前岗位
    		if(station !=null){
    			stationids.add(station.getStationid());
    		}
    	}else{
    		stationids = stationids(jobs);
    	}
		if(stationids.isEmpty()){
			logger.warn(user.getName()+":客户当前职位下没有岗位");
			stationids.add(0L);
		}
		stationIdSet.addAll(stationids);
		return new ArrayList<Long>(stationIdSet);
	}
	
	/**
	 * 通过orgid查询岗位
	 * @param orgid职位对应的organization_id
	 * @return
	 */
	@Deprecated
	public List<UserStations> stations(String orgid) {
		throw new UnsupportedOperationException();
	}
	
	/**
     * 通过orgid查询岗位
     * @param orgid职位对应的organization_id
     * @return
     */
	private List<UserStations> stations(List<String> orgid) {
	  Map<String, Object> param = new HashMap<>();
	  param.put("orgid", orgid);
      return this.mapper.getUserStations(param);
    }
	
	/**
	 * 通过orgid,usreid查询销售组织
	 * @param orgid 职位对应的organization_id
	 * @param userid销售人员ID
	 * @return
	 */
	@Deprecated
	public List<UserStations> stations(String orgid,Long userid) {
	  throw new UnsupportedOperationException();
	}
	
	/**
     * 通过orgid,usreid查询销售组织
     * @param orgid 职位对应的organization_id
     * @param userid销售人员ID
     * @return
     */
	private List<UserStations> stations(List<String> orgid, Long userid){
	  Map<String,Object> params = new HashMap<String, Object>();
      params.put("orgid", orgid);
      params.put("usreid", userid);
      return this.mapper.getPowerStations(params);
	}
	
	/**
	 * 职位加销售人员查询岗位ID
	 * @param jobs
	 * @param user
	 * @return
	 */
	public List<Long>  stationids(List<UserJobs> jobs,TEmployee user){
		List<Long> ids = new ArrayList<Long>();
		for(UserJobs job:jobs){
		    List<String> orgid = subOrgs(job);
			Long userid = StringUtils.equals("1", job.getJobType())?null:user.getId();
			List<UserStations> stations = stations(orgid,userid);
			for(UserStations station:stations){
				ids.add(station.getStationid());
			}
		}
		return ids;
	}
	
	/**
	 * 职位查询岗位ID
	 * @param jobs
	 * @return
	 */
    public List<Long> stationids(List<UserJobs> jobs) {
      List<Long> ids = new ArrayList<Long>();
      if (jobs == null || jobs.isEmpty()) {
        return ids;
      }
      for (UserJobs job : jobs) {
        String orgid = job.getOrgid();
        List<String> orgs =null;
        if(!"1".equals(job.getJobType()) && orgid.length()>7){
          orgs = new ArrayList<>();
          orgs.add(orgid);
        }else{
          orgs = subOrgs(orgid);
        }
        List<UserStations> stations = stations(orgs);
        for (UserStations station : stations) {
          ids.add(station.getStationid());
        }
      }
      return ids;
    }
    
    /**
     * 获取用户可以查看的岗位
     * @param request
     * @return
     */
    @Override
    public List<Long> stations(HttpServletRequest request){
      @SuppressWarnings("unchecked")
      List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
      UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
      TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
      return getUserStations(user, userjobs, station);
    }
    
    private List<String> subOrgs(UserJobs job) {
      if (!"1".equals(job.getJobType())) {
        List<String> result = new ArrayList<>();
        result.add(job.getOrgid());
        return result;
      }
      return subOrgs(job.getOrgid());
    }
    
    private List<String> subOrgs(String orgid) {
      return mapper.subOrg(orgid);
    }
    
}
