package com.zach.common.utils.quartz;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author by apple
 * @Classname ApplicationQuartzRunner
 * @Description 初始化定时任务入口
 */
@Component
public class ApplicationQuartzRunner implements ApplicationRunner {

    final QuartzJobListener quartzJobListener;

    public ApplicationQuartzRunner(QuartzJobListener quartzJobListener) {
        this.quartzJobListener = quartzJobListener;
    }

    @Override
    public void run(ApplicationArguments args) {
        quartzJobListener.contextInitialized();
    }
}
