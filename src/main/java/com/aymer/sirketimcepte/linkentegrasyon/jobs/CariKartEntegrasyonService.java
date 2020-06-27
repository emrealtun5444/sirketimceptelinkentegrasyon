package com.aymer.sirketimcepte.linkentegrasyon.jobs;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.producer.CariKartProducer;
import com.aymer.sirketimcepte.linkentegrasyon.producer.StokKartProducer;
import com.aymer.sirketimcepte.linkentegrasyon.repository.StokKartRepository;
import com.aymer.sirketimcepte.linkentegrasyon.service.CariKartService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * User: ealtun
 * Date: 13.06.2020
 * Time: 17:44
 */
@Service
public class CariKartEntegrasyonService implements Tasklet {

    @Autowired
    private CariKartService cariKartService;

    @Autowired
    private CariKartProducer cariKartProducer;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        int page = 0, size = 100;
        List<CariKartDto> cariKartDtoList;
        do {
            cariKartDtoList = cariKartService.findCariKartByPage(PageRequest.of(page, size, Sort.by("id").ascending()));
            cariKartDtoList.forEach(cariKartDto -> cariKartProducer.sendToQueue(cariKartDto));
            page++;
        } while (!CollectionUtils.isEmpty(cariKartDtoList));

        return RepeatStatus.FINISHED;
    }
}
