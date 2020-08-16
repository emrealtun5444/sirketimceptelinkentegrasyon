package com.aymer.sirketimcepte.linkentegrasyon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "STK005_IslemTarihi")
    private Integer islemTarihi;

    @Column(name = "STK005_CariHesapKodu")
    private String cariKodu;

    @Column(name = "STK005_EvrakSeriNo")
    private String evrakSeriNo;

    @Column(name = "STK005_MalKodu")
    private String malKodu;

    @Column(name = "STK005_SevkTarihi")
    private Integer sevkTarihi;

    @Column(name = "STK005_GC")
    private Long gcTipi;

    @Column(name = "STK005_Miktari")
    private Long miktar;

    @Column(name = "STK005_BirimFiyati")
    private BigDecimal birimFiyati;

    @Column(name = "STK005_Tutari")
    private BigDecimal tutar;

    @Column(name = "STK005_Iskonto")
    private BigDecimal iskonto;

    @Column(name = "STK005_KDVTutari")
    private BigDecimal kdvTutari;

    @Transient
    private String malAdi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stk005 stk005 = (Stk005) o;
        return id.equals(stk005.id) &&
            evrakSeriNo.equals(stk005.evrakSeriNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evrakSeriNo);
    }
}
