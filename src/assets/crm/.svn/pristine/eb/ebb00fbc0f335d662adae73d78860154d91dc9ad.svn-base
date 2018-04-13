package com.hhnz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest extends TestBase {

  private static String ymFormat = "yyyy-MM";
  private static String ymdFormat = "yyyy-MM-dd";
  private static String ymdhmFormat = "yyyy-MM-dd HH:mm";

  @Test
  public void testPeriodNow() {
    String generate = DateUtil.periodNow();
    SimpleDateFormat format = new SimpleDateFormat(ymFormat);
    String expect = format.format(new Date());
    Assert.assertEquals(generate, expect);
  }

  @Test
  public void testFormat() {
    SimpleDateFormat format = new SimpleDateFormat(ymdhmFormat);
    Date date = null;
    try {
      date = format.parse("2017-01-12 15:02");
    } catch (ParseException e) {
      Assert.fail();
    }
    Assert.assertEquals("2017-01", DateUtil.format(date, ymFormat));
    Assert.assertEquals("2017-01-12", DateUtil.format(date, ymdFormat));
    Assert.assertEquals("2017-01-12 15:02", DateUtil.format(date, ymdhmFormat));
  }

  @Test
  public void testGetYear() {
    SimpleDateFormat format = new SimpleDateFormat(ymFormat);
    try {
      Assert.assertEquals(2017, DateUtil.getYear(format.parse("2017-01")));
      Assert.assertEquals(2017, DateUtil.getYear(format.parse("2017-02")));
      Assert.assertEquals(2017, DateUtil.getYear(format.parse("2017-12")));
      Assert.assertEquals(2018, DateUtil.getYear(format.parse("2018-01")));
    } catch (ParseException e) {
      Assert.fail();
    }
  }

}
