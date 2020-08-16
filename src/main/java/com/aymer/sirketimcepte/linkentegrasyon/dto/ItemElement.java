package com.aymer.sirketimcepte.linkentegrasyon.dto;

import com.aymer.sirketimcepte.linkentegrasyon.service.CariKartVisitor;

public interface ItemElement {

    public void accept(CariKartVisitor visitor);

}
