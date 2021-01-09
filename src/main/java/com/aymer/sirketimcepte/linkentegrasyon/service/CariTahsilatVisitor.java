package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.*;
import com.aymer.sirketimcepte.linkentegrasyon.mapper.TahsilatMapper;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car003;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs002;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs003;
import com.aymer.sirketimcepte.linkentegrasyon.repository.TahsilatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CariTahsilatVisitor implements CariKartVisitor {

    @Autowired
    private TahsilatRepository tahsilatRepository;

    @Autowired
    private TahsilatMapper tahsilatMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {

        // nakit tahsilatlar çekiliyor.
        List<Car003> car003List = tahsilatRepository.findNakitTahsilat(cariKartDto.getHesapKodu());
        List<NakitTahsilatDto> nakitTahsilatList = tahsilatMapper.mapToNakitDto(car003List);
        List<TahsilatDto> allTahsilats = new ArrayList<>(nakitTahsilatList);

        // cek tahsilatları çekiliyor.
        List<Scs002> scs002List = tahsilatRepository.findSenetTahsilat(cariKartDto.getHesapKodu());
        List<SenetTahsilatDto> senetTahsilatDtoList = tahsilatMapper.mapToSenetDto(scs002List);
        allTahsilats.addAll(senetTahsilatDtoList);

        // senet tahsilatlari çekiliyor.
        List<Scs003> scs003List = tahsilatRepository.findCekTahsilat(cariKartDto.getHesapKodu());
        List<CekTahsilatDto> cekTahsilatDtoList = tahsilatMapper.mapToCekDto(scs003List);
        allTahsilats.addAll(cekTahsilatDtoList);

        // tahsilat kayıtları set ediliyor.
        cariKartDto.setTahsilatList(allTahsilats);
    }
}
