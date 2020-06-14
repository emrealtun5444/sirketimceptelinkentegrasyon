package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.repository.StokKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class StokKartServiceImp implements StokKartService {

    @Autowired
    private StokKartRepository stokKartRepository;


}
