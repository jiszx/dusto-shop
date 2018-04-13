package com.hhnz.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
  private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
  private static final String PERIOD = "yyyy-MM";
  public static final String YYYYMMDD_CHN = "yyyy年MM月dd日";
  public static final String YYYYMMDD = "yyyyMMdd";
  
  /**
   * ISO-8601 with just the Date part, no time
   */
  public static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
  /**
   * date and time with symbols
   */
  public static final String DATE_TIME_FORMAT_STR_PLAIN = "yyyy-MM-dd HH:mm:ss";
  /**
   * date and time ,no symbol
   */
  public static final String DATE_TIME_FORMAT_STR = "yyyyMMdd HHmmss";

  private DateUtil() {}

  public static String periodNow() {
	return FastDateFormat.getInstance(PERIOD).format(new Date());
  }

  public static String format(Date date, String pattern) {
    return FastDateFormat.getInstance(pattern).format(date);
  }

  public static Date parse(String date, String pattern) {
    try {
      return FastDateFormat.getInstance(pattern).parse(date);
    } catch (ParseException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  public static int getYear(Date date) {
    return get(date, Calendar.YEAR);
  }
  
  public static int getMonth(Date date) {
	  return get(date, Calendar.MONTH);
  }
  
  private static int get(Date date, int field){
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  return c.get(field);
  }
  
  /**
   * 获取传入日期的当月最后一天
   * @author: chaoyang.ren 
   * @date:Jun 7, 2017  3:05:10 PM
   * @param date
   * @return
   */
  public static Date getLastDayOfMonth(Date date){
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	  c.set(Calendar.DAY_OF_MONTH, lastDay);
	  return c.getTime();
  }
  
  /**
   * 获取传入日期的当月第一天
   * @author: chaoyang.ren 
   * @date:Jun 7, 2017  3:05:10 PM
   * @param date
   * @return
   */
  public static Date getFirstDayOfMonth(Date date){
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  return c.getTime();
  }
  
  public static Date getBeginingOfDay(Date date){
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.set(Calendar.HOUR_OF_DAY, 0);
	  c.set(Calendar.MINUTE, 0);
	  c.set(Calendar.SECOND, 0);
	  return c.getTime();
  }
  
  public static Date getEndingOfDay(Date date){
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  c.set(Calendar.HOUR_OF_DAY, 23);
	  c.set(Calendar.MINUTE, 59);
	  c.set(Calendar.SECOND, 59);
	  return c.getTime();
  }
  
  /**
   * 获取日期期间的最后一天
   * 日期期间格式：{@link DateUtil#PERIOD}
   * @author: chaoyang.ren 
   * @date:Jun 7, 2017  3:04:00 PM
   * @param period
   * @return
   */
  public static Date getLastDayOfMonth(String period){
	  return getLastDayOfMonth(parse(period, PERIOD));
  }
  
  /**
   * 获取日期期间的第一天
   * 日期期间格式：{@link DateUtil#PERIOD}
   * @author: chaoyang.ren 
   * @date:Jun 7, 2017  3:04:00 PM
   * @param period
   * @return
   */
  public static Date getFirstDayOfMonth(String period){
	  return getFirstDayOfMonth(parse(period, PERIOD));
  }

  /**
   * 获取下一天
   * @author: chaoyang.ren 
   * @date:Jun 7, 2017  5:13:36 PM
   * @param lastDay
   * @return
   */
  public static Date getNextDay(Date lastDay) {
	  Calendar c = Calendar.getInstance();
	  c.add(Calendar.DAY_OF_MONTH, 1);
	  return c.getTime();
  }

}
