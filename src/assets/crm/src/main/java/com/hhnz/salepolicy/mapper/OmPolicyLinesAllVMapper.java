package com.hhnz.salepolicy.mapper;

import java.util.List;

import com.hhnz.salepolicy.model.OmPolicyLinesAllV;
import com.hhnz.salepolicy.model.OmPolicyLinesAllVExample;

public interface OmPolicyLinesAllVMapper {
    
    int countByExample(OmPolicyLinesAllVExample example);

   
    List<OmPolicyLinesAllV> selectByExample(OmPolicyLinesAllVExample example);

   
}