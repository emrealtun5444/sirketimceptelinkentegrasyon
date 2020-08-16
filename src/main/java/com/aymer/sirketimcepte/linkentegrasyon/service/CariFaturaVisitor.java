package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaViewHolder;
import com.aymer.sirketimcepte.linkentegrasyon.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CariFaturaVisitor implements CariKartVisitor {

    @Autowired
    private FaturaRepository faturaRepository;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {
        // fatura ve fatura detaylar olusturuluyor.
        List<FaturaViewHolder> faturaViewHolders = faturaRepository.findFaturasByCariKod(cariKartDto.getHesapKodu());



    }

}
