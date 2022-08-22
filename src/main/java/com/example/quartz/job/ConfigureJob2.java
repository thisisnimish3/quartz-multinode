package com.example.quartz.job;

import com.example.quartz.service.AService;
import org.quartz.CronScheduleBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureJob2 {


    @Bean
    public JobDetail jobADetails() {
        return JobBuilder.newJob(AJob.class).withIdentity("sampleJobA")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobATrigger(JobDetail jobADetails) {

        return TriggerBuilder.newTrigger().forJob(jobADetails)

                .withIdentity("sampleTriggerA")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * ? * * *"))
                .build();
    }
    @DisallowConcurrentExecution
    public class AJob implements Job {

        @Autowired
        private AService aService;

        @Override
        public void execute(JobExecutionContext context) {
            aService.printTime();
        }
    }



    @Bean
    public JobDetail jobBDetails() {
        return JobBuilder.newJob(CJob.class).withIdentity("sampleJobC").storeDurably(true)
                .build();
    }

    @Bean
    public Trigger jobBTrigger(JobDetail jobBDetails) {

        return TriggerBuilder.newTrigger().forJob(jobBDetails)

                .withIdentity("sampleTriggerC")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * ? * * *"))
                .build();
    }

    public class CJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Job C says hi !");
        }
    }


}
