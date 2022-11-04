package com.sensor.common.utils.quartz;

import org.springframework.stereotype.Service;

/**
 * @author by apple
 * @Classname QuartzJobListener
 * @Description 初始化加载定时任务
 */
@Service
public class QuartzJobListener {

    final QuartzManager quartzManager;

    public QuartzJobListener(QuartzManager quartzManager) {
        this.quartzManager = quartzManager;
    }

    public void contextInitialized() { /* TODO 待初始化添加定时任务 */ }

}