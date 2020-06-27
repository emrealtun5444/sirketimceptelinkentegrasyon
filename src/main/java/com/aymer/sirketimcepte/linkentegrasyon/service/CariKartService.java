package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface CariKartService {

     List<CariKartDto> findCariKartByPage(Pageable pageable);


}
