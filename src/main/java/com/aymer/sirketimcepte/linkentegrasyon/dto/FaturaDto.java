package com.aymer.sirketimcepte.linkentegrasyon.dto;


import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeYonu;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class FaturaDto implements Serializable {

    private Date faturaTarihi;
    private String faturaNo;
    private BigDecimal malBedeli;
    private BigDecimal iskonto;
    private BigDecimal netToplam;
    private BigDecimal kdvTutari;
    private BigDecimal toplamTutar;
    private EOdemeYonu faturaYonu;

    private List<FaturaDetayDto> faturaDetayList;

    public void addDetay(FaturaDetayDto faturaDetayDto) {
       if (CollectionUtils.isEmpty(this.faturaDetayList)) {
            this.faturaDetayList = new ArrayList<>();
       }
       this.faturaDetayList.add(faturaDetayDto);
    }

}
