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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
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

    @Autowired
    private FaturaMapper faturaMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void visit(CariKartDto cariKartDto) {

        Date date = getDate();

        // fatura ve fatura detaylar olusturuluyor.
        List<Car005> faturaList = faturaRepository.findAllByCariKoduAndCariIslemTipiAndFaturaTarihiGreaterThanEqual(cariKartDto.getHesapKodu(),IConstants.CARI_ISLEM_TIPI_4,date);
        List<Stk005> faturaDetayList = faturaDetayRepository.findAllByCariKoduAndIslemTarihiGreaterThanEqual(cariKartDto.getHesapKodu(),date);

        Map<String, List<Car005>> faturaMap = faturaList.stream().collect(Collectors.groupingBy(Car005::getFaturaNo));
        Map<String, List<Stk005>> faturaDetayMap = faturaDetayList.stream().collect(Collectors.groupingBy(Stk005::getEvrakSeriNo));

        // fatura bazında listede dolaşmaya başlarız.
        for (Map.Entry<String, List<Car005>> entry : faturaMap.entrySet()) {

            FaturaDto faturaDto = addFatura(entry);

            addFaturaDetays(faturaDetayMap, faturaDto);

            cariKartDto.addFatura(faturaDto);
        }


    }

    private Date getDate() {
        LocalDate firstDayOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(firstDayOfYear.atStartOfDay(defaultZoneId).toInstant());
        return date;
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
                .iskonto(faturaKalem.get(IConstants.SATIR_TIPI_I) != null ? faturaKalem.get(IConstants.SATIR_TIPI_I) : BigDecimal.ZERO)
                .kdvTutari(faturaKalem.get(IConstants.SATIR_TIPI_K) != null ? faturaKalem.get(IConstants.SATIR_TIPI_K) : BigDecimal.ZERO)
                .malBedeli(faturaKalem.get(IConstants.SATIR_TIPI_T) != null ? faturaKalem.get(IConstants.SATIR_TIPI_T) : BigDecimal.ZERO)
                .netToplam(faturaKalem.get(IConstants.SATIR_TIPI_N) != null ? faturaKalem.get(IConstants.SATIR_TIPI_N) : BigDecimal.ZERO)
                .toplamTutar(faturaKalem.get(IConstants.SATIR_TIPI_G) != null ? faturaKalem.get(IConstants.SATIR_TIPI_G) : BigDecimal.ZERO)
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
                    .birimFiyati(fd.getBirimFiyati() != null ? fd.getBirimFiyati() : BigDecimal.ZERO)
                    .iskonto(fd.getIskonto() != null ? fd.getIskonto() : BigDecimal.ZERO)
                    .islemTarihi(fd.getIslemTarihi())
                    .kdvTutari(fd.getKdvTutari() != null ? fd.getKdvTutari() : BigDecimal.ZERO)
                    .miktar(fd.getMiktar() != null ? fd.getMiktar().intValue() : 0)
                    .tutar(fd.getTutar() != null ? fd.getTutar() : BigDecimal.ZERO)
                    .stokKodu(fd.getMalKodu())
                    .toplamTutar(fd.getTutar().add(fd.getKdvTutari()).subtract(fd.getIskonto()))
                    .build();

            faturaDto.addDetay(faturaDetayDto);
        }
    }

}
