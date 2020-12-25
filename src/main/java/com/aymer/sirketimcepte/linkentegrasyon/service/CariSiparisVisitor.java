package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.SiparisDto;
import com.aymer.sirketimcepte.linkentegrasyon.mapper.SiparisMapper;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk002;
import com.aymer.sirketimcepte.linkentegrasyon.repository.SiparisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CariSiparisVisitor implements CariKartVisitor {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private SiparisMapper siparisMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {
        List<Stk002> siparisList = siparisRepository.findAllByCariKodu(cariKartDto.getHesapKodu());
        List<SiparisDto> siparisDtos = siparisMapper.mapToDtoList(siparisList);
        siparisDtos = siparisDtos.stream().map(temp -> {
            if (temp.getTeslimMiktari() >= temp.getMiktar()) {
                temp.setSiparisDurumu(SiparisDto.ESiparisDurum.TAMAMLANDI);
            } else if (temp.getTeslimMiktari() > 0) {
                temp.setSiparisDurumu(SiparisDto.ESiparisDurum.KISMI_SEVKEDILDI);
            } else {
                temp.setSiparisDurumu(SiparisDto.ESiparisDurum.ACIK);
            }
            temp.setSiparisYonu(temp.getSiparisYonuId() == 1 ? SiparisDto.ESiparisYonu.ALINAN_SIPARIS : SiparisDto.ESiparisYonu.VERILEN_SIPARIS);
            return temp;
        }).collect(Collectors.toList());
        cariKartDto.setSiparisList(siparisDtos);
    }
}
