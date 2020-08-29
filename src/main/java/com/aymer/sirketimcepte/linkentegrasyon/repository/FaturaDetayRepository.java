package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
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
public interface FaturaDetayRepository extends JpaRepository<Stk005, Long> {

    List<Stk005> findAllByCariKoduAndIslemTarihiGreaterThanEqual(String cariKodu, Date date);

}
