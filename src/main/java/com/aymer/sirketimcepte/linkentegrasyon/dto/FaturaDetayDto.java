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
public class FaturaDetayDto implements Serializable {

    private String stokKodu;
    private Date islemTarihi;
    private Integer miktar;
    private BigDecimal birimFiyati;
    private BigDecimal tutar;
    private BigDecimal iskonto;
    private BigDecimal kdvTutari;

}
