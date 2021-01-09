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
@Table(name = "V_SCS003")
public class Scs003 implements Serializable {

    @Id
    @Column(name = "SCS003_Row_ID", nullable = false)
    private Long id;

    @Column(name = "tarih")
    private Date tarih;

    @Column(name = "SCS003_HesapKodu")
    private String hesapKodu;

    @Column(name = "borclu")
    private String borcluAdi;

    @Column(name = "SCS003_CekNo")
    private String evrakNo;

    @Column(name = "SCS003_Aciklama")
    private String aciklama;

    @Column(name = "SCS003_Tutar")
    private BigDecimal tutar;

    @Column(name = "vadeTarihi")
    private Date vadeTarihi;

    @Column(name = "SCS003_Banka")
    private String banka;

    @Column(name = "SCS003_Sube")
    private String sube;

    @Column(name = "SCS003_BankaHesapNo")
    private String bankaHesapNo;

    @Column(name = "SCS003_BankaCekNo")
    private String bankaCekNo;

}
