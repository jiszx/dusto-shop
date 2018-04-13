package com.hhnz.customer.mapper;

import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.model.CMerchCustProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchCustProductMapper {
    int countByExample(CMerchCustProductExample example);

    int deleteByExample(CMerchCustProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CMerchCustProduct record);

    int insertSelective(CMerchCustProduct record);

    List<CMerchCustProduct> selectByExample(CMerchCustProductExample example);

    CMerchCustProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CMerchCustProduct record, @Param("example") CMerchCustProductExample example);

    int updateByExample(@Param("record") CMerchCustProduct record, @Param("example") CMerchCustProductExample example);

    int updateByPrimaryKeySelective(CMerchCustProduct record);

    int updateByPrimaryKey(CMerchCustProduct record);
    
    void saveProduct(Long id);
}