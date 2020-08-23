package com.aymer.sirketimcepte.linkentegrasyon.dto;

import com.aymer.sirketimcepte.linkentegrasyon.enums.ECariTipi;
import com.aymer.sirketimcepte.linkentegrasyon.service.CariKartVisitor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class CariKartDto implements Serializable, ItemElement {

    private Long id;
    private String hesapKodu;
    private ECariTipi cariTipi;
    private String unvan1;
    private String unvan2;
    private String adres1;
    private String adres2;
    private String adres3;
    private String vergiDairesi;
    private String vergiHesapNo;
    private String telefon1;
    private String emailAdresi;
    private String ozelKod;
    private BigDecimal toplamBorc;
    private BigDecimal toplamAlacak;
    private Long sirketId = 1L;

    // fatura detayları
    private List<FaturaDto> faturaList;

    public void addFatura(FaturaDto faturaDto) {
        if (CollectionUtils.isEmpty(this.faturaList)) {
            this.faturaList = new ArrayList<>();
        }
        this.faturaList.add(faturaDto);
    }

    @Override
    public void accept(CariKartVisitor visitor) {
        visitor.visit(this);
    }
}
