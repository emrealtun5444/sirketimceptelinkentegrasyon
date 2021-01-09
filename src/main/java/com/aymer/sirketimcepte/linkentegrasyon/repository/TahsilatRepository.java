package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.model.Car003;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs002;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs003;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk002;
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
public interface TahsilatRepository extends JpaRepository<Car003, Long> {

    @Query("select c from Car003 c where c.hesapKodu = :cariKodu and c.islemTipi = 1 and c.baTipi = 1")
    List<Car003> findNakitTahsilat(@Param("cariKodu") String cariKodu);

    @Query("select s from Scs002 s where s.hesapKodu = :cariKodu")
    List<Scs002> findSenetTahsilat(@Param("cariKodu") String cariKodu);

    @Query("select s from Scs003 s where s.hesapKodu = :cariKodu")
    List<Scs003> findCekTahsilat(@Param("cariKodu") String cariKodu);


}
