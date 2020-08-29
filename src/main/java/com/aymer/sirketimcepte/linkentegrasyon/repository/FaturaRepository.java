package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface FaturaRepository extends JpaRepository<Car005, Long> {

    List<Car005> findAllByCariKoduAndCariIslemTipiAndFaturaTarihiGreaterThanEqual(String cariKodu, Integer cariIslemTipi, Date date);

}
