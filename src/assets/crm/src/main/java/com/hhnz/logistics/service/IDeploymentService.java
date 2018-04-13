package com.hhnz.logistics.service;

import java.util.List;
import java.util.Map;

import com.hhnz.logistics.dto.DeploymentDTO;
import com.hhnz.util.AjaxDTO;

public interface IDeploymentService {

	AjaxDTO SelectDeploymentList(Map<String, Object> params);

	List<DeploymentDTO> exportListAll(Map<String, Object> params);

}
