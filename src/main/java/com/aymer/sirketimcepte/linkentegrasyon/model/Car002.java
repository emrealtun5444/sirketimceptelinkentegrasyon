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

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 07:38
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CAR002")
public class Car002 implements Serializable {

    @Id
    @Column(name = "CAR002_Row_ID", nullable = false)
    private Long id;

    @Column(name = "CAR002_HesapKodu")
    private String hesapKodu;

    @Column(name = "CAR002_Unvan1")
    private String unvan1;

    @Column(name = "CAR002_Unvan2")
    private String unvan2;

    @Column(name = "CAR002_Adres1")
    private String adres1;

    @Column(name = "CAR002_Adres2")
    private String adres2;

    @Column(name = "CAR002_Adres3")
    private String adres3;

    @Column(name = "CAR002_VergiDairesi")
    private String vergiDairesi;

    @Column(name = "CAR002_VergiHesapNo")
    private String vergiHesapNo;

    @Column(name = "CAR002_Telefon1")
    private String telefon1;

    @Column(name = "CAR002_EArsivTeslimEMailAdresi1")
    private String emailAdresi;

    @Column(name = "CAR002_OzelKodu")
    private String ozelKod;

}
