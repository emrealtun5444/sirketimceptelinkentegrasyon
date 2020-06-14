package com.aymer.sirketimcepte.linkentegrasyon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class StokKartDto implements Serializable {

    private Long id;

    private String stokKodu;

    private String aciklama;

    private BigDecimal birimFiyati;

    private Long miktar;

    private Long sirketId;

    public StokKartDto(Long id, String stokKodu, String aciklama, BigDecimal birimFiyati, Long miktar) {
        this.id = id;
        this.stokKodu = stokKodu;
        this.aciklama = aciklama;
        this.birimFiyati = birimFiyati;
        this.miktar = miktar;
        this.sirketId = 1L;
    }

    @Override
    public String toString() {
        return "StokKartDto{" +
                "id=" + id +
                ", stokKodu='" + stokKodu + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", birimFiyati=" + birimFiyati +
                ", miktar=" + miktar +
                '}';
    }
}
