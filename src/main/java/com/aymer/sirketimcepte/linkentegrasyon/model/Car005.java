package com.aymer.sirketimcepte.linkentegrasyon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 07:38
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CAR005")
public class Car005 implements Serializable {

    @Id
    @Column(name = "CAR005_Row_ID", nullable = false)
    private Long id;

    @Column(name = "CAR005_CHKodu")
    private String cariKodu;

    @Column(name = "CAR005_FaturaTarihi")
    private Integer faturaTarihi;

    @Column(name = "CAR005_FaturaNo")
    private String faturaNo;

    @Column(name = "CAR005_Tutar")
    private BigDecimal faturaTutari;

    @Column(name = "CAR005_SatirTipi")
    private String satirTipi;

    @Column(name = "CAR002_OzelKodu")
    private String ozelKodu;
}
