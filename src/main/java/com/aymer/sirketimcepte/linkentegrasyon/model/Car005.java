package com.aymer.sirketimcepte.linkentegrasyon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "V_CAR005")
public class Car005 implements Serializable ,ICari{

    @Id
    @Column(name = "CAR005_Row_ID", nullable = false)
    private Long id;

    @Column(name = "CAR005_CHKodu")
    private String cariKodu;

    @Column(name = "faturaTarihi")
    private Date faturaTarihi;

    @Column(name = "CAR005_FaturaNo")
    private String faturaNo;

    @Column(name = "CAR005_Tutar")
    private BigDecimal faturaTutari;

    @Column(name = "CAR005_Oran")
    private BigDecimal kdvOrani;

    @Column(name = "CAR005_SatirTipi")
    private String satirTipi;

    @Column(name = "CAR005_BA")
    private String borcAlacakTipi;

    @Column(name = "CAR005_CariIslemTipi")
    private Integer cariIslemTipi;

    @Column(name = "CAR005_SatirAciklama")
    private String satirAciklama;

    @Column(name = "CAR005_SatirNo")
    private Integer satirNo;

    @Column(name = "CAR005_EFaturaTipi")
    private Integer eFaturaTipi;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car005 car005 = (Car005) o;
        return id.equals(car005.id) &&
            faturaNo.equals(car005.faturaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faturaNo);
    }
}
