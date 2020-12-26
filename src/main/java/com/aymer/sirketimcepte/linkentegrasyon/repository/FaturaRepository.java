package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select c from Car005 c " +
        "where c.cariKodu = :cariKodu " +
        "and c.faturaTarihi >= :faturaTarihi ")
    List<Car005> findAllFaturas(@Param("cariKodu") String cariKodu, @Param("faturaTarihi") Date date);

}
