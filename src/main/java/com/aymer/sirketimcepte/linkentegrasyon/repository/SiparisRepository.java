package com.aymer.sirketimcepte.linkentegrasyon.repository;

import com.aymer.sirketimcepte.linkentegrasyon.model.Stk002;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface SiparisRepository extends JpaRepository<Stk002, Long> {

    List<Stk002> findAllByCariKodu(String cariKodu);
}
