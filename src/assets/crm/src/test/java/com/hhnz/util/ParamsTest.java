package com.hhnz.util;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class ParamsTest {

  @Test
  public void testCreateParamMap() {
    Integer id = null;
    Date date = new Date();
    Date nullDate = null;
    List<Long> nullList = null;
    List<Long> emptyList = Collections.emptyList();
    List<Long> list = ImmutableList.of(1L);
    
    Map<String, Object> param = Params.builder().add("name", "tom").add("emptyStr", "")
        .add("id", 23).add("nullId", id).add("date", date).add("nullDate", nullDate)
        .add("nullList", nullList).add("emptyList", emptyList).add("list", list).buid();
    Assert.assertEquals(param.get("name"), "tom");
    Assert.assertEquals(param.get("date"), date);
    Assert.assertEquals(param.get("id"), 23);

    Assert.assertFalse(param.containsKey("nullId"));
    Assert.assertFalse(param.containsKey("nullDate"));
    Assert.assertFalse(param.containsKey("emptyStr"));
    
    Assert.assertFalse(param.containsKey("nullList"));
    Assert.assertFalse(param.containsKey("emptyList"));
    Assert.assertTrue(param.containsKey("list"));
  }

}
