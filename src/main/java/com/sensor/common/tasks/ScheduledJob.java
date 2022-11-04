package com.sensor.common.tasks;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @author by apple
 */
@Slf4j
public class ScheduledJob implements Job{
    @Override
    public void execute(JobExecutionContext context) {
        log.info("定时业务");
    }
}
