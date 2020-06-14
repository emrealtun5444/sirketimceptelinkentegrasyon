package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface StokKartRepository extends JpaRepository<Stk005, Long> {


    @Query(value = "select new com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto(" +
            "max(stk4.id), " +
            "stk4.malKodu, " +
            "max(stk4.aciklama), " +
            "max(stk5.birimFiyati), " +
            "((sum(case when stk5.gcTipi = 0 then stk5.miktar else 0 end)) - (sum(case when stk5.gcTipi = 1 then stk5.miktar else 0 end))) " +
            ") " +
            "from Stk004 stk4 , Stk005 stk5 " +
            "where stk4.malKodu = stk5.malKodu " +
            "group by stk4.malKodu")
    List<StokKartDto> findStokKartList();


}
