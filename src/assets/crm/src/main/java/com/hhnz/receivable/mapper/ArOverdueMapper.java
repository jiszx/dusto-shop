package com.hhnz.receivable.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.receivable.dto.ArOverdueDTO;
import com.hhnz.receivable.dto.ArOverdueDetailsDTO;

public interface ArOverdueMapper {

	List<ArOverdueDTO> selectArOverdueList(Map<String, Object> params);

	int countArOverdue(Map<String, Object> params);

	List<ArOverdueDetailsDTO> selectArOverdueDetials(Map<String, Object> params);

	int countArOverdueDetails(Map<String, Object> params);

}
