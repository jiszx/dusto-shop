package com.hhnz.jco;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

/**
 * 与SAP连接配置
 *
 * @author wy
 */
public class SAPConn {
    private static final Log LOG = LogFactory.getLog(SAPConn.class); // 初始化日志对象
    private static final String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
    static {
        Properties connectProperties;
		try {
			connectProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/jco.properties"));
		} catch (IOException e) {
			LOG.error("sap连接配置文件读取失败！");
			connectProperties = new Properties();
		}
//		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "10.0.0.241");//服务器
//		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");        //系统编号
//		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "102");       //SAP集团
//		connectProperties.setProperty(DestinationDataProvider.JCO_USER, "WANGZH");  //SAP用户名
//		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "zhenghua");     //密码
//		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "zh");        //登录语言
//		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");  //最大连接数
//		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");     //最大连接线程
		createDataFile(ABAP_AS_POOLED, "jcoDestination", connectProperties);
    }
    /**
     * 创建SAP接口属性文件。
     *
     * @param name       ABAP管道名称
     * @param suffix     属性文件后缀
     * @param properties 属性文件内容
     */
    private static void createDataFile(String name, String suffix, Properties properties) {
        File cfg = new File(name + "." + suffix);
        if (cfg.exists()) {
            cfg.deleteOnExit();
        }
        try {
            FileOutputStream fos = new FileOutputStream(cfg, false);
            properties.store(fos, "for tests only !");
            fos.close();
        } catch (Exception e) {
        	LOG.error("Create Data file fault, error msg: " + e.toString());
            throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
        }
    }

    /**
     * 获取SAP连接
     *
     * @return SAP连接对象
     */
    public static JCoDestination connect() {
        JCoDestination destination = null;
        try {
            destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        } catch (JCoException e) {
        	LOG.error("Connect SAP fault, error msg: " + e.toString());
        }
        LOG.info("=====获取sap destination连接完成！");
        return destination;
    }

}

