package com.hhnz.pub.service;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.util.AjaxDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by yang on 2016-7-27.
 */
public interface IAttachmentService {
    /**
     * 添加附件
     * @param simpleName 模块名称
     * @param key 主键
     * @param creator 创建人
     * @return 更新时间
     * @throws Exception
     */
    int addAttachment(String simpleName, String key,String creator,MultipartFile[] files)throws Exception;

    List<TAttachment> findAttachment(String simpleName, String s, AjaxDTO page)throws Exception;

    /**
     * 删除附件
     * @param simpleName 模块名称
     * @param key 主键
     * @param id 附件ID
     * @return
     * @throws Exception
     */
    Integer delAttachent(String simpleName, String key, Long id)throws Exception;

    String addAttachment(String module,String creator,MultipartFile file)throws Exception;

    String addAttachment(String module, String key, String creator, String type, MultipartFile file)
        throws Exception;
}
