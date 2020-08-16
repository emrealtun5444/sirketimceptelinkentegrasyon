package com.aymer.sirketimcepte.linkentegrasyon.dto;

import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk005;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FaturaViewHolder {

  private Car005 fatura;
  private Stk005 faturaDetay;

    public FaturaViewHolder(Car005 fatura, Stk005 faturaDetay) {
        this.fatura = fatura;
        this.faturaDetay = faturaDetay;
    }
}
