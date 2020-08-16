package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.mapper.CariKartMapper;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car002;
import com.aymer.sirketimcepte.linkentegrasyon.repository.CariKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class CariKartServiceImp implements CariKartService {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private CariKartMapper cariKartMapper;

    @Autowired
    private CariVisitorFactory cariVisitorFactory;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CariKartDto> findCariKartByPage(Pageable pageable) {
        List<CariKartVisitor> cariKartVisitorList = cariVisitorFactory.getCariVisitorList();

        List<Car002> car002BList = cariKartRepository.findCar002BList(pageable);
        List<CariKartDto> cariKartDtoList = cariKartMapper.carikartToDtoList(car002BList);
        cariKartDtoList.forEach(cariKartDto -> {
            cariKartVisitorList.forEach(cariKartDto::accept);
        });

        return cariKartDtoList;
    }




}
