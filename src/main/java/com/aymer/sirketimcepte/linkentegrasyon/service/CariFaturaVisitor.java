package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.constants.IConstants;
import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaDetayDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaKalemDto;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeYonu;
import com.aymer.sirketimcepte.linkentegrasyon.mapper.FaturaMapper;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import com.aymer.sirketimcepte.linkentegrasyon.repository.FaturaDetayRepository;
import com.aymer.sirketimcepte.linkentegrasyon.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CariFaturaVisitor implements CariKartVisitor {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaDetayRepository faturaDetayRepository;

    @Autowired
    private FaturaMapper faturaMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {

        // fatura ve fatura detaylar olusturuluyor.
        List<Car005> faturaList = faturaRepository.findAllByCariKoduAndCariIslemTipi(cariKartDto.getHesapKodu(),IConstants.CARI_ISLEM_TIPI_4);
        List<Stk005> faturaDetayList = faturaDetayRepository.findAllByCariKodu(cariKartDto.getHesapKodu());

        Map<String, List<Car005>> faturaMap = faturaList.stream().collect(Collectors.groupingBy(Car005::getFaturaNo));
        Map<String, List<Stk005>> faturaDetayMap = faturaDetayList.stream().collect(Collectors.groupingBy(Stk005::getEvrakSeriNo));

        // fatura bazında listede dolaşmaya başlarız.
        for (Map.Entry<String, List<Car005>> entry : faturaMap.entrySet()) {

            FaturaDto faturaDto = addFatura(entry);

            addFaturaDetays(faturaDetayMap, faturaDto);

            cariKartDto.addFatura(faturaDto);
        }


    }

    private FaturaDto addFatura(Map.Entry<String, List<Car005>> entry) {
        Car005 faturaRow = entry.getValue().get(0);
        List<FaturaKalemDto> faturaKalemDtoList = faturaMapper.mapToFaturaKalemList(entry.getValue());

        // fatura kalemlerini entity map ederiz.
        String[] arr = {IConstants.SATIR_TIPI_Z, IConstants.SATIR_TIPI_J, IConstants.SATIR_TIPI_A};
        List<String> blackList = Arrays.asList(arr);
        Map<String, BigDecimal> faturaKalem = entry.getValue().stream().filter(rw -> !blackList.contains(rw.getSatirTipi())).collect(Collectors.groupingBy(Car005::getSatirTipi, Collectors.reducing(BigDecimal.ZERO, Car005::getFaturaTutari, BigDecimal::add)));

        FaturaDto faturaDto = FaturaDto.builder()
                .faturaNo(entry.getKey())
                .faturaTarihi(faturaRow.getFaturaTarihi())
                .iskonto(faturaKalem.get(IConstants.SATIR_TIPI_I))
                .kdvTutari(faturaKalem.get(IConstants.SATIR_TIPI_K))
                .malBedeli(faturaKalem.get(IConstants.SATIR_TIPI_T))
                .netToplam(faturaKalem.get(IConstants.SATIR_TIPI_N))
                .toplamTutar(faturaKalem.get(IConstants.SATIR_TIPI_G))
                .faturaYonu(faturaRow.getBorcAlacakTipi().equalsIgnoreCase(IConstants.BORC) ? EOdemeYonu.BORC : EOdemeYonu.ALACAK)
                .faturaKalemList(faturaKalemDtoList)
                .build();
        return faturaDto;
    }

    private void addFaturaDetays(Map<String, List<Stk005>> faturaDetayMap, FaturaDto faturaDto) {
        List<Stk005> faturaWithDetayList = faturaDetayMap.get(faturaDto.getFaturaNo());
        if (CollectionUtils.isEmpty(faturaWithDetayList)) return;

        for (Stk005 fd : faturaWithDetayList) {
            FaturaDetayDto faturaDetayDto = FaturaDetayDto.builder()
                    .birimFiyati(fd.getBirimFiyati())
                    .iskonto(fd.getIskonto())
                    .islemTarihi(fd.getIslemTarihi())
                    .kdvTutari(fd.getKdvTutari())
                    .miktar(fd.getMiktar().intValue())
                    .tutar(fd.getTutar())
                    .stokKodu(fd.getMalKodu())
                    .toplamTutar(fd.getTutar().add(fd.getKdvTutari()).subtract(fd.getIskonto()))
                    .build();

            faturaDto.addDetay(faturaDetayDto);
        }
    }

}
