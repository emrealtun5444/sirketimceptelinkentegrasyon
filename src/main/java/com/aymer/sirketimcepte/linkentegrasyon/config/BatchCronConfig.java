package com.aymer.sirketimcepte.linkentegrasyon.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:41
 */
@EnableScheduling
@Configuration
public class BatchCronConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext context;

    @Scheduled(cron = "0 0/30 6-20 * * *")
   public void stokKartEntegrasyonCronConf() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        Job job = context.getBean("stokKartEntegrasyonJob", Job.class);
        JobExecution jobExecution = jobLauncher.run(job, params);
    }

    //@Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0/30 6-20 * * *")
    public void cariKartEntegrasyonCronConf() throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addString("JobID", String.valueOf(System.currentTimeMillis()))
            .toJobParameters();

        Job job = context.getBean("cariKartEntegrasyonJob", Job.class);
        JobExecution jobExecution = jobLauncher.run(job, params);
    }


}
