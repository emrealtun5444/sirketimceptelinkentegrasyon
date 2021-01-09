package com.aymer.sirketimcepte.linkentegrasyon.dto;

import com.aymer.sirketimcepte.linkentegrasyon.enums.EKdvOrani;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeTipi;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeYonu;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TahsilatDto implements Serializable {

    public String hesapKodu;

    public Date islemTarihi;

    public String evrakNo;

    public String aciklama;

    public EOdemeYonu odemeYonu;

    public EOdemeTipi odemeTipi;

    public BigDecimal tutar;

    public Date vadeTarihi;

    public EKdvOrani kdvOrani;

}
