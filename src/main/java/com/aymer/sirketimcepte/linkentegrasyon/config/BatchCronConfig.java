package com.aymer.sirketimcepte.linkentegrasyon.config;

import com.aymer.sirketimcepte.linkentegrasyon.jobs.CariKartEntegrasyonService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

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

    @Scheduled(cron = "0 0 * * * *")
    public void cariKartEntegrasyonCronConf() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        Job job = context.getBean("cariKartEntegrasyonJob", Job.class);
        JobExecution jobExecution = jobLauncher.run(job, params);
    }


}
