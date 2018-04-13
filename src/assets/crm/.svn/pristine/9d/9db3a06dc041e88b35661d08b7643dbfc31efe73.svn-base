package com.hhnz.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Excels {

  private Excels() {}

  public static void fillHeader(String[] headers, Sheet sheet) {
    Row header = (Row) sheet.createRow(0);
    for (int j = 0; j < headers.length; j++) {
      Cell cell = header.createCell(j);
      cell.setCellValue(headers[j]);
    }
  }
  
  public static void fillCell(Row row, int column, String value){
    Cell cell = row.createCell(column);
    cell.setCellValue(value);
  }

}
