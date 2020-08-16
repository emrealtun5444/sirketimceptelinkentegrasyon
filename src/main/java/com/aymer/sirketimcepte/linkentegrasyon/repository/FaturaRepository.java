package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaViewHolder;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface FaturaRepository extends JpaRepository<Stk005, Long> {

    @Query(value = "select new com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaViewHolder(" +
        "ftr, " +
        "ftrd " +
        ") " +
        "from Car005 ftr , Stk005 ftrd " +
        "where ftr.faturaNo = ftrd.evrakSeriNo " +
        "and ftr.cariKodu = :cariKodu")
    List<FaturaViewHolder> findFaturasByCariKod(@Param("cariKodu") String cariKodu);

}
