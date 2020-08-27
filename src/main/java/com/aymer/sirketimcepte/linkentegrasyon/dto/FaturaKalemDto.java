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
public class FaturaKalemDto implements Serializable {

    private Integer satirNo;
    private String satirTipi;
    private String satirAciklama;
    private BigDecimal tutar;
    private BigDecimal kdvOrani;

}
