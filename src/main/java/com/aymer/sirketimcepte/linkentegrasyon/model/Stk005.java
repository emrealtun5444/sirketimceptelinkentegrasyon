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
@Table(name = "STK005")
public class Stk005 implements Serializable {

    @Id
    @Column(name = "STK005_Row_ID", nullable = false)
    private Long id;

    @Column(name = "STK005_MalKodu")
    private String malKodu;

    @Transient
    private String malAdi;

    @Column(name = "STK005_SevkTarihi")
    private Integer sevkTarihi;

    @Column(name = "STK005_BirimFiyati")
    private BigDecimal birimFiyati;

    @Column(name = "STK005_GC")
    private Long gcTipi;

    @Column(name = "STK005_Miktari")
    private Long miktar;

}
