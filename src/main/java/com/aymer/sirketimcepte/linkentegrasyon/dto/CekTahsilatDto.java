package com.aymer.sirketimcepte.linkentegrasyon.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class CekTahsilatDto extends TahsilatDto implements Serializable {

    private String banka;
    private String subeAdi;
    private String bankaHesapNo;
    private String bankaCekNo;
    private String borcluAdi;
    private String borcluAdresi;

}
