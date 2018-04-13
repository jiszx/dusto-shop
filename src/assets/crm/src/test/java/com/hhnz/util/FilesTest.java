package com.hhnz.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.util.concurrent.Uninterruptibles;

public class FilesTest {

  @Test
  public void testFilesuffix() {
    Assert.assertEquals(null, Files.fileSuffix(null));
    Assert.assertEquals(null, Files.fileSuffix("abc"));
    Assert.assertEquals(".jpg", Files.fileSuffix("不知道.jpg"));
    Assert.assertEquals(".jpg", Files.fileSuffix("abc.b.c.jpg"));
    Assert.assertEquals(".nos", Files.fileSuffix("abc.nos"));
  }

  @Test
  public void testAppsuffix() {
    Assert.assertEquals(null, Files.appSuffix("abc.ios"));
    Assert.assertEquals(null, Files.appSuffix("abc"));
    Assert.assertEquals(null, Files.appSuffix(null));
    Assert.assertEquals(null, Files.appSuffix("abc.b.c.jpg"));
    Assert.assertEquals(".apk", Files.appSuffix("abc.apk"));
  }

  @Test
  public void testImageuffix() {
    Assert.assertEquals(null, Files.imgSuffix(null));
    Assert.assertEquals(null, Files.imgSuffix("abc"));
    Assert.assertEquals(null, Files.imgSuffix("abc.apk"));
    Assert.assertEquals(".jpg", Files.imgSuffix("abc.b.c.jpg"));
    Assert.assertEquals(".png", Files.imgSuffix("abc.png"));
    Assert.assertEquals(".png", Files.imgSuffix("abc.PnG"));
    Assert.assertEquals(".bmp", Files.imgSuffix("abc.bMp"));
  }

  @Test
  public void testExcelsuffix() {
    Assert.assertEquals(null, Files.excelSuffix(null));
    Assert.assertEquals(null, Files.excelSuffix("abc"));
    Assert.assertEquals(null, Files.excelSuffix("abc.apk"));
    Assert.assertEquals(null, Files.excelSuffix("abc.b.c.jpg"));
    Assert.assertEquals(null, Files.excelSuffix("abc.png"));
    Assert.assertEquals(".xls", Files.excelSuffix("abc.xls"));
    Assert.assertEquals(".xlsx", Files.excelSuffix("abc.xlsx"));
    Assert.assertEquals(".xls", Files.excelSuffix("abc.xLs"));
    Assert.assertEquals(".xlsx", Files.excelSuffix("abc.xLSx"));
  }

  @Test
  public void testStandarForlder() {
    Assert.assertEquals("abc/", Files.standardFolderPath("abc"));
    Assert.assertEquals("abc/a/c/", Files.standardFolderPath("abc/a/c"));
    Assert.assertEquals("d:/", Files.standardFolderPath("d:"));
  }
  
  @Test
  public void testDeleteFileDelay(){
    File file = new File("D:/test.txt");
    Assert.assertFalse(file.exists());
    try {
      com.google.common.io.Files.write("this a delete file test!", file, StandardCharsets.UTF_8);
    } catch (IOException e) {
      Assert.fail("create file error");
    }
    Files.deleteFileDelay(file, 5, TimeUnit.SECONDS);
    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    Assert.assertTrue(file.exists());
    Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    Assert.assertFalse(file.exists());
  }

}
