package com.aymer.sirketimcepte.linkentegrasyon.config;

import com.aymer.sirketimcepte.linkentegrasyon.jobs.CariKartEntegrasyonService;
import com.aymer.sirketimcepte.linkentegrasyon.service.StokKartService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:41
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ApplicationContext context;

    @Override
    public void setDataSource(DataSource dataSource) {
        // override to do not set datasource even if a datasource exist.
        // initialize will use a Map based JobRepository (instead of database)
    }

    @Bean
    public Step cariKartEntegrasyonStep(){
        Tasklet service = context.getBean("cariKartEntegrasyonService", Tasklet.class);
        return steps.get("cariKartEntegrasyonStep")
                .tasklet(service)
                .build();
    }

    @Bean(name = "cariKartEntegrasyonJob")
    public Job cariKartEntegrasyonJob(){
        return jobs.get("cariKartEntegrasyonJob")
                .incrementer(new RunIdIncrementer())
                .start(cariKartEntegrasyonStep())
                .build();
    }


}
