package com.hhnz.api.cache;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhnz.util.TestBase;

public class CacheServiceTest extends TestBase {

  @Autowired
  private CacheService cacheService;

  @Test
  public void testPutIfAbsent() {
    String key = "test123";
    cacheService.delete(key);
    boolean expectTrue = cacheService.putIfAbsent(key, "1");
    boolean expectFalse = cacheService.putIfAbsent(key, "2");
    Assert.assertTrue(expectTrue);
    Assert.assertFalse(expectFalse);
    cacheService.delete(key);
  }

}
