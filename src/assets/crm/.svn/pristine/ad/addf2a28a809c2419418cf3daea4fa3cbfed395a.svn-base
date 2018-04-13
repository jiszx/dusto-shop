package com.hhnz.monitor.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.google.gson.reflect.TypeToken;
import com.hhnz.monitor.mapper.CrmExportMapper;
import com.hhnz.monitor.model.CrmExport;
import com.hhnz.monitor.model.CrmExport.KeyValuePair;
import com.hhnz.monitor.service.CommonExportService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.DateUtil;
import com.hhnz.util.Files;
import com.hhnz.util.JsonUtil;

@Service
public class CommonExportServiceImpl implements CommonExportService {
  private static Joiner joiner = Joiner.on(',').skipNulls();

  @Autowired
  private CrmExportMapper exportMapper;
  @Value("${upload.file.path}")
  private String basePath;

  @Override
  public AjaxDTO list(AjaxDTO page) {
    Map<String, Object> param = new HashMap<>();
    param.put("begin", page.getOffset());
    param.put("end", page.getOffset() + page.getLimit());
    List<CrmExport> records = exportMapper.findAll(param);
    int total = exportMapper.countFindAll(param);
    page.setTotal(total);
    page.setRows(records);
    return page;
  }

  @Override
  public int saveOrUpdate(CrmExport bean) {
    if (bean.getId() != 0) {
      return exportMapper.update(bean);
    }
    return exportMapper.insert(bean);
  }

  @Override
  public int deleteExportConfig(CrmExport bean) {
    return exportMapper.deleteById(bean.getId());
  }

  @Override
  public String generateExcel(String exportKey, List<Long> stations) throws IOException {
    CrmExport exportBean = exportMapper.findByKey(exportKey);
    List<KeyValuePair> mapping = JsonUtil.fromJSON(exportBean.getMapping(),
        new TypeToken<List<KeyValuePair>>() {}.getType());
    String sql = exportBean.getSql();
    if (sql.indexOf("##") >= 0) {
      if (stations == null) {
        sql = sql.replaceAll("##.*##", " ");
      } else {
        String stationStr = joiner.join(stations);
        sql = sql.replace("##", " ").replace("#{station}", stationStr);
      }
    }
    List<Map<String, ?>> detail = exportMapper.executeSql(sql);

    // 创建文件
    String fileName = exportKey+DateUtil.format(new Date(), "yyyy-MM-dd_HH-mm-ss") + ".xlsx";
    String filePath = Files.standardFolderPath(basePath) + fileName;
    File file = new File(filePath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    Workbook wb = null;
    OutputStream stream = null;
    try {
      wb = new XSSFWorkbook();
      stream = new FileOutputStream(file);
      Sheet sheet1 = (Sheet) wb.createSheet("sheet1");

      Row header = (Row) sheet1.createRow(0);
      // 写入头数据
      for (int i = 0; i < mapping.size(); i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(mapping.get(i).getHead());
      }
      // 内容
      for (int i = 0; i < detail.size(); i++) {
        Row row = (Row) sheet1.createRow(i + 1);
        fillRow(row, detail.get(i), mapping);
      }
      wb.write(stream);
      Files.deleteFileDelay(file, 3, TimeUnit.MINUTES);
    } finally {
      IOUtils.closeQuietly(stream);
      IOUtils.closeQuietly(wb);
    }
    return fileName;
  }

  void fillRow(Row row, Map<String, ?> record, List<KeyValuePair> mapping) {
    for (int i = 0; i < mapping.size(); i++) {
      Cell cell = row.createCell(i);
      String recordKey = Objects.requireNonNull(mapping.get(i).getColumn());
      Object value = record.get(recordKey.toUpperCase());
      cell.setCellValue(value == null ? "" : String.valueOf(value));
    }
  }

}
