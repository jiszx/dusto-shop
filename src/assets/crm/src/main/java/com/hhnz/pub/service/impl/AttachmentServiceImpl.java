package com.hhnz.pub.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Resources;
import com.hhnz.crm.mapper.TAttachmentMapper;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TAttachmentExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-7-27.
 */
@Service
@Transactional
public class AttachmentServiceImpl implements IAttachmentService {

    @Autowired
    private TAttachmentMapper mapper;

    @Value("${upload.file.path}")
    private String realPath;
    @Override
    public int addAttachment(String simpleName, String key,String creator, MultipartFile[] files) throws Exception {
        if(files !=null && files.length > 0){
        	String filePath=realPath+File.separator+simpleName;
            File path = new File(filePath);
            if(!path.exists()){
                path.mkdirs();
            }
            int res = 0;
            if(!filePath.startsWith("/") && !filePath.startsWith("\\")){
            	filePath = File.separator+filePath;
            }
            for (MultipartFile f:files) {
                if(f.getSize()>0){
                    //System.err.println(f.getContentType());
                    String type = getFileType(f.getContentType());
                    String ext = f.getOriginalFilename().substring(f.getOriginalFilename().indexOf("."));
                    String name = UUID.randomUUID().toString();
                    String fileName = key+name+ext;
                    File newFile = new File(filePath+fileName);
                    f.transferTo(newFile);
                    //Thumbnails.of()
                    if("1".equals(type)){//图片处理
                        //添加水印
                        watermark(newFile, simpleName);
                        Thumbnails.of(newFile).size(180,180).toFile(filePath+"sm"+fileName);
                        Thumbnails.of(newFile).size(400,400).toFile(filePath+"md"+fileName);
                    }
                    TAttachment atta = new TAttachment();
                    atta.setUploadOid(creator);
                    atta.setAttachmentName(f.getOriginalFilename());
                    atta.setFileName(fileName);
                    atta.setFilePath(filePath+fileName);
                    atta.setAttachmentType(type);
                    atta.setObjectName(simpleName);
                    atta.setObjectKey(key);
                    atta.setUploadTs(new Date());
                    atta.setStat("0");
                    res += this.mapper.insert(atta);

                }else{
                    continue;
                }
            }
            return res;
        }else{
            return 0;
        }
    }
    
    static boolean watermark(File image, String moduleName) throws IOException{
      URL watermark = getWatermark(moduleName);
      if(watermark==null){
        return false;
      }
      Thumbnails.of(image).scale(1.0)
          .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(watermark), 0.5f).toFile(image);
      return true;
    }
    
    static URL getWatermark(String moduleName){
      if(CMerchCustBase.class.getSimpleName().equals(moduleName)){
        return Resources.getResource("watermark/customer.png");
      }
      return null;
    }

    private String getFileType(String contentType) {
        //excel = application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType)){
            return "2";
        }
        if("application/vnd.ms-excel".equals(contentType)){
            return "2";
        }
        if("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType)){
            return "3";
        }
        if("image/jpeg".equals(contentType)){
            return "1";
        }
        if("image/png".equals(contentType)){
            return "1";
        }
        return "0";
    }

    @Override
    public List<TAttachment> findAttachment(String simpleName, String key, AjaxDTO page) throws Exception {
        TAttachmentExample ex = new TAttachmentExample();
        ex.createCriteria().andObjectKeyEqualTo(key).andObjectNameEqualTo(simpleName);
        if(page !=null){
            page.setLimit(page.getLimit());
            page.setOffset(page.getOffset());
        }
        ex.setOrderByClause("upload_ts desc");
        return this.mapper.selectByExample(ex);
    }

    @Override
    public Integer delAttachent(String simpleName, String s, Long id) throws Exception {
        TAttachmentExample ex =new TAttachmentExample();
        ex.createCriteria().andObjectNameEqualTo(simpleName).andObjectKeyEqualTo(s).andIdEqualTo(id);
        return this.mapper.deleteByExample(ex);
    }

    @Override
    public String addAttachment(String module, String creator, MultipartFile f) throws Exception {
        if(f.getSize()>0){
//            //System.err.println(f.getContentType());
//            String type = getFileType(f.getContentType());
//            String ext = f.getOriginalFilename().substring(f.getOriginalFilename().indexOf("."));
//            String name = UUID.randomUUID().toString();
//            File path = new File(realPath+File.separator+module);
//            if(!path.exists()){
//                path.mkdirs();
//            }
//            //f.transferTo(new File(realPath+File.separator+module+File.separator+name+ext));
//            File newFile = new File(realPath+File.separator+module+File.separator+name+ext);
//            f.transferTo(newFile);
//            //Thumbnails.of()
//            if("1".equals(type)){//图片处理
//                //添加水印
//                Thumbnails.of(newFile).size(180,180).toFile(realPath+File.separator+module+File.separator+"sm"+name+ext);
//                Thumbnails.of(newFile).size(400,400).toFile(realPath+File.separator+module+File.separator+"md"+name+ext);
//            }
//            TAttachment atta = new TAttachment();
//            atta.setUploadOid(creator);
//            atta.setAttachmentName(f.getOriginalFilename());
//            atta.setFileName("upload/"+module+"/"+name+ext);
//            atta.setFilePath(realPath+File.separator+module+File.separator+name+ext);
//            atta.setAttachmentType(type);
//            atta.setObjectName(module);
//            atta.setObjectKey("0");
//            atta.setUploadTs(new Date());
//            atta.setStat("0");
//            this.mapper.insert(atta);
          return addAttachment(module, "-1", creator, null, f);
        }else{
            throw new Exception("文件上传失败");
        }
    }
    
  @Override  
  public String addAttachment(String module, String key, String creator, String type, MultipartFile file)
      throws Exception {
    if (file.getSize() > 0) {
      String filePath = realPath + File.separator + module;
      File path = new File(filePath);
      if (!path.exists()) {
        path.mkdirs();
      }
//      filePath = File.separator + filePath;
      type = StringUtils.isEmpty(type) ? getFileType(file.getContentType()) : type;
      String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
      String name = UUID.randomUUID().toString();
      String fileName = name + ext;
      File newFile = new File(filePath + fileName);
      file.transferTo(newFile);
      if ("1".equals(type)) {// 图片处理
        // 添加水印
        Thumbnails.of(newFile).size(180, 180).toFile(filePath + "sm" + fileName);
        Thumbnails.of(newFile).size(400, 400).toFile(filePath + "md" + fileName);
      }
      TAttachment atta = new TAttachment();
      atta.setUploadOid(creator);
      atta.setAttachmentName(file.getOriginalFilename());
      atta.setFileName(fileName);
      atta.setFilePath(filePath + fileName);
      atta.setAttachmentType(type);
      atta.setObjectName(module);
      atta.setObjectKey(key);
      atta.setUploadTs(new Date());
      atta.setStat("0");
      int res = mapper.insert(atta);
      if(res<1){
        throw new RuntimeException("附件表增加数据失败！");
      }
      return atta.getFileName();
    } else {
      throw new Exception("文件上传失败");
    }

  }
}
