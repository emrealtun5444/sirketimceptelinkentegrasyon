package com.aymer.sirketimcepte.linkentegrasyon.dto;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiparisDto implements Serializable {
    private String malKodu;
    private Date islemTarihi;
    private Integer siparisYonuId;
    private ESiparisYonu siparisYonu;
    private String cariKodu;
    private String siparisNo;
    private Long miktar;
    private BigDecimal birimFiyati;
    private BigDecimal tutari;
    private BigDecimal iskonto;
    private BigDecimal kdvTutari;
    private String faturaNo;
    private Long teslimMiktari;
    private ESiparisDurum siparisDurumu;
    private Date teslimTarihi;

    public enum ESiparisDurum {
        ACIK, KISMI_SEVKEDILDI, TAMAMLANDI
    }

    public enum ESiparisYonu {
        ALINAN_SIPARIS, VERILEN_SIPARIS
    }
}
