package com.hhnz.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Uninterruptibles;

public class Files {
  private static Logger logger = LoggerFactory.getLogger(Files.class);

  private static Set<String> imgSuffixs = initImgSuffix();
  private static Set<String> excelSuffixs = initExcelSuffix();
  private static Set<String> appSuffixs = initAppSuffix();
  
  private Files() {}

  public static String fileSuffix(String filename) {
    if (filename == null || filename.isEmpty()) {
      return null;
    }
    filename = filename.toLowerCase();
    int index = filename.lastIndexOf('.');
    return index < 0 ? null : filename.substring(index);
  }

  public static String appSuffix(String filename) {
    String suffix = fileSuffix(filename);
    return appSuffixs.contains(suffix) ? suffix : null;
  }

  public static String imgSuffix(String filename) {
    String suffix = fileSuffix(filename);
    return imgSuffixs.contains(suffix) ? suffix : null;
  }

  public static String excelSuffix(String filename) {
    String suffix = fileSuffix(filename);
    return excelSuffixs.contains(suffix) ? suffix : null;
  }

  public static String standardFolderPath(String folderPath) {
    if (folderPath != null && (folderPath.endsWith("/") || folderPath.endsWith("\\"))) {
      return folderPath;
    }
    return folderPath + "/";
  }

  public static boolean storeFile(MultipartFile file, String path) {
    boolean result = false;
    File newFile = new File(path);
    if (!newFile.getParentFile().exists()) {
      newFile.getParentFile().mkdirs();
    }
    try {
      file.transferTo(newFile);
      result = true;
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return result;
  }

  private static Set<String> initImgSuffix() {
    Set<String> imgs = new HashSet<>();
    imgs.add(".jpg");
    imgs.add(".jpeg");
    imgs.add(".png");
    imgs.add(".gif");
    imgs.add(".bmp");
    return Collections.unmodifiableSet(imgs);
  }

  private static Set<String> initExcelSuffix() {
    Set<String> imgs = new HashSet<>();
    imgs.add(".xls");
    imgs.add(".xlsx");
    return Collections.unmodifiableSet(imgs);
  }

  private static Set<String> initAppSuffix() {
    return ImmutableSet.of(".apk", ".ipa");
  }
  
  /**
   * 等待指定时间删除文件
   * @param file
   * @param time
   * @param unit
   */
  public static void deleteFileDelay(final File file, final long time, final TimeUnit unit){
    new Thread(new Runnable() {
      @Override
      public void run() {
        Uninterruptibles.sleepUninterruptibly(time, unit);
        file.delete();
      }
    }).start();
  }
  
  public static void makeDirIfAbsent(File file){
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
  }

}
