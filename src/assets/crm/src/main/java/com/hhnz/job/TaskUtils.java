package com.hhnz.job;

import com.hhnz.config.model.TScheduleJob;
import org.apache.log4j.Logger;
import org.springframework.asm.commons.Method;
import org.springframework.util.StringUtils;

/**
 * Created by yang on 2016-11-6.
 */
public class TaskUtils {
    public final static Logger log = Logger.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    public static void invokMethod(TScheduleJob scheduleJob) {
        IScheduler object = null;
        //springId不为空先按springId查找bean
        if (StringUtils.hasLength(scheduleJob.getSpringid())) {
//            Object
//            object = SpringUtils.getBean(scheduleJob.getSpringId());
            //获取SpringBean
            object = null;
        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            //object.runTask();
            return;
        }



    }
}