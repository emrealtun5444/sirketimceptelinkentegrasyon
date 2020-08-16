package com.aymer.sirketimcepte.linkentegrasyon.service;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;

public interface CariKartVisitor {

    void visit(CariKartDto cariKartDto);

}
