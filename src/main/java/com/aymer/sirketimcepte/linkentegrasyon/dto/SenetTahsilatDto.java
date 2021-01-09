package com.aymer.sirketimcepte.linkentegrasyon.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class SenetTahsilatDto extends TahsilatDto implements Serializable {

    private String borcluAdi;
    private String borcluAdresi;

}
