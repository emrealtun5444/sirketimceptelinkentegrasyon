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
@Table(name = "V_SCS002")
public class Scs002 implements Serializable {

    @Id
    @Column(name = "SCS002_Row_ID", nullable = false)
    private Long id;

    @Column(name = "tarih")
    private Date tarih;

    @Column(name = "SCS002_HesapKodu")
    private String hesapKodu;

    @Column(name = "borclu")
    private String borcluAdi;

    @Column(name = "borcluAdres")
    private String borcluAdresi;

    @Column(name = "SCS002_SenetNo")
    private String evrakNo;

    @Column(name = "SCS002_Aciklama")
    private String aciklama;

    @Column(name = "SCS002_Tutar")
    private BigDecimal tutar;

    @Column(name = "vadeTarihi")
    private Date vadeTarihi;

}
