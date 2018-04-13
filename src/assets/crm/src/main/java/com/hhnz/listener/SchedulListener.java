package com.hhnz.listener;

import com.hhnz.config.enums.ScheduleJob;
import com.hhnz.config.model.TScheduleJob;
import com.hhnz.config.service.ISchedulJobService;
import com.hhnz.job.QuartzJobFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by 杨成锡 on 2016-11-5.
 */
public class SchedulListener implements ServletContextListener {

    private Scheduler scheduler;

    @Autowired
    private ISchedulJobService service;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        SchedulerFactoryBean schedulerFactoryBean = webApplicationContext.getBean(SchedulerFactoryBean.class);
        scheduler = schedulerFactoryBean.getScheduler();
        service = webApplicationContext.getBean(ISchedulJobService.class);
        // 这里从数据库中获取任务信息数据
        List<TScheduleJob> jobList = null;
        try {
            jobList = service.getAllJob();
            for (TScheduleJob job : jobList) {
                addJob(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    public void addJob(TScheduleJob job) throws SchedulerException {
//        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getStatus())) {
//            return;
//        }
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),job.getJobType());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            //Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getJobName(),job.getJobType()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobType()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

}
