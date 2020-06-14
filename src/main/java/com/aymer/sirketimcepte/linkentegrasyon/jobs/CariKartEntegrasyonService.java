package com.aymer.sirketimcepte.linkentegrasyon.jobs;

import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.producer.StokKartProducer;
import com.aymer.sirketimcepte.linkentegrasyon.repository.StokKartRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:44
 */
@Service
public class CariKartEntegrasyonService implements Tasklet {

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private StokKartProducer stokKartProducer;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("MyTaskOne start..");
        System.out.println("MyTaskOne start..");

        List<StokKartDto> stokKartList = stokKartRepository.findStokKartList();

        for (StokKartDto stokKartDto : stokKartList) {
            stokKartProducer.sendToQueue(stokKartDto);
        }

        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }
}
