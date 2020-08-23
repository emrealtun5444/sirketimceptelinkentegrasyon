package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.constants.IConstants;
import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaDetayDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaDto;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeYonu;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import com.aymer.sirketimcepte.linkentegrasyon.repository.FaturaDetayRepository;
import com.aymer.sirketimcepte.linkentegrasyon.repository.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CariFaturaVisitor implements CariKartVisitor {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaDetayRepository faturaDetayRepository;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {
        // fatura ve fatura detaylar olusturuluyor.
        List<Car005> faturaList = faturaRepository.findAllByCariKoduAndSatirTipiNot(cariKartDto.getHesapKodu(), IConstants.SATIR_TIPI_Z);
        List<Stk005> faturaDetayList = faturaDetayRepository.findAllByCariKodu(cariKartDto.getHesapKodu());

        Map<String, List<Car005>> faturaMap = faturaList.stream().collect(Collectors.groupingBy(Car005::getFaturaNo));
        Map<String, List<Stk005>> faturaDetayMap = faturaDetayList.stream().collect(Collectors.groupingBy(Stk005::getEvrakSeriNo));

        // fatura bazında listede dolaşmaya başlarız.
        for (Map.Entry<String, List<Car005>> entry : faturaMap.entrySet()) {

            FaturaDto faturaDto = addFatura(entry);

            addFaturaDetays(faturaDetayMap, entry, faturaDto);

            cariKartDto.addFatura(faturaDto);
        }


    }

    private FaturaDto addFatura(Map.Entry<String, List<Car005>> entry) {
        Car005 faturaRow = entry.getValue().get(0);

        // fatura kalemlerini entity map ederiz.
        Map<String, BigDecimal> faturaKalem = entry.getValue().stream().collect(Collectors.toMap( Car005::getSatirTipi , Car005::getFaturaTutari));
        FaturaDto faturaDto = FaturaDto.builder()
            .faturaNo(entry.getKey())
            .faturaTarihi(new Date(faturaRow.getFaturaTarihi()))
            .iskonto(faturaKalem.get(IConstants.SATIR_TIPI_I))
            .kdvTutari(faturaKalem.get(IConstants.SATIR_TIPI_K))
            .malBedeli(faturaKalem.get(IConstants.SATIR_TIPI_T))
            .netToplam(faturaKalem.get(IConstants.SATIR_TIPI_N))
            .toplamTutar(faturaKalem.get(IConstants.SATIR_TIPI_G))
            .faturaYonu(faturaRow.getBorcAlacakTipi().equalsIgnoreCase(IConstants.BORC) ? EOdemeYonu.BORC : EOdemeYonu.ALACAK)
            .build();
        return faturaDto;
    }

    private void addFaturaDetays(Map<String, List<Stk005>> faturaDetayMap, Map.Entry<String, List<Car005>> entry, FaturaDto faturaDto) {
        List<Stk005> faturaWithDetayList = faturaDetayMap.get(entry.getKey());
        for (Stk005 fd : faturaWithDetayList) {
            FaturaDetayDto faturaDetayDto = FaturaDetayDto.builder()
                .birimFiyati(fd.getBirimFiyati())
                .iskonto(fd.getIskonto())
                .islemTarihi(new Date(fd.getIslemTarihi()))
                .kdvTutari(fd.getKdvTutari())
                .miktar(fd.getMiktar().intValue())
                .tutar(fd.getTutar())
                .stokKodu(fd.getMalKodu())
                .build();

            faturaDto.addDetay(faturaDetayDto);
        }
    }

}
