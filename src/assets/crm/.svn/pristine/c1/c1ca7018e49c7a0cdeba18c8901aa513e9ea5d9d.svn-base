package com.hhnz.receivable.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.receivable.dto.AccountReceivableDTO;
import com.hhnz.receivable.dto.AccountReceivableDetailsDTO;

public interface AccountReceivableMapper {

	List<AccountReceivableDTO> selectArBalancesList(Map<String, Object> params);

	int countArBalancesList(Map<String, Object> params);

	List<AccountReceivableDetailsDTO> selectArDetailsList(Map<String, Object> params);

	int countArDetials(Map<String, Object> params);

	String selectMaxPeriod();

	void updateByMerchId(Map<String, Object> map);

	void updateByPeriod(Map<String, Object> map);

}
