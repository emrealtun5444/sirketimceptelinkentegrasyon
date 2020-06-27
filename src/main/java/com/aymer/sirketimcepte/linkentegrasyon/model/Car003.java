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
@Table(name = "CAR003")
public class Car003 implements Serializable {

    @Id
    @Column(name = "CAR003_Row_ID", nullable = false)
    private Long id;

    @Column(name = "CAR003_HesapKodu")
    private String hesapKodu;

    @Column(name = "CAR003_IslemTipi")
    private Integer islemTipi;

    @Column(name = "CAR003_BA")
    private Integer baTipi;

    @Column(name = "CAR003_Tutar")
    private BigDecimal tutar;

}
