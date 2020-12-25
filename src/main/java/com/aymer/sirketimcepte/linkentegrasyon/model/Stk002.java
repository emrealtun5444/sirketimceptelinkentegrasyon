package com.aymer.sirketimcepte.linkentegrasyon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 07:38
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "V_STK002")
public class Stk002 implements Serializable {

    @Id
    @Column(name = "STK002_Row_ID", nullable = false)
    private Long id;

    @Column(name = "STK002_MalKodu")
    private String malKodu;

    @Column(name = "islemTarihi")
    private Date islemTarihi;

    @Column(name = "STK002_GC")
    private Integer siparisYonuId;

    @Column(name = "STK002_CariHesapKodu")
    private String cariKodu;

    @Column(name = "STK002_EvrakSeriNo")
    private String siparisNo;

    @Column(name = "STK002_Miktari")
    private Long miktar;

    @Column(name = "STK002_BirimFiyati")
    private BigDecimal birimFiyati;

    @Column(name = "STK002_Tutari")
    private BigDecimal tutari;

    @Column(name = "STK002_Iskonto")
    private BigDecimal iskonto;

    @Column(name = "STK002_KDVTutari")
    private BigDecimal kdvTutari;

    @Column(name = "STK002_IrsaliyeNo")
    private String faturaNo;

    @Column(name = "STK002_TeslimMiktari")
    private Long teslimMiktari;

    @Column(name = "STK002_SipDurumu")
    private Long siparisDurumuId;

    @Column(name = "teslimTarihi")
    private Date teslimTarihi;

}
