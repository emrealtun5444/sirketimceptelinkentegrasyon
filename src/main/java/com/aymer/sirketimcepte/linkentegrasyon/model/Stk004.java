package com.aymer.sirketimcepte.linkentegrasyon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 07:38
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "STK004")
public class Stk004 implements Serializable {

    @Id
    @Column(name = "STK004_Row_ID", nullable = false)
    private Long id;

    @Column(name = "STK004_MalKodu")
    private String malKodu;

    @Column(name = "STK004_Aciklama")
    private String aciklama;


}
