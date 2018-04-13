package com.hhnz.logistics.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.logistics.dto.DeploymentDTO;

public interface DeploymentMapper {

	List<DeploymentDTO> selectDeploymentList(Map<String, Object> params);

	int countDeploymentList(Map<String, Object> params);

	List<DeploymentDTO> exportListAll(Map<String, Object> params);

}
