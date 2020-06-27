package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car002;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car003;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface CariKartRepository extends JpaRepository<Car002, Long> {

    @Query("select c from Car002 c where c.ozelKod is not null and c.ozelKod <> ''")
    List<Car002> findCar002BList(Pageable pageable);

    @Query("select c from Car003 c where c.hesapKodu = :prmHesapKodu")
    List<Car003> findAllCar003ByHesapKodu(@Param("prmHesapKodu") String hesapKodu);

}
