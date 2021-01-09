package com.aymer.sirketimcepte.linkentegrasyon.mapper;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CekTahsilatDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.NakitTahsilatDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.SenetTahsilatDto;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EKdvOrani;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeTipi;
import com.aymer.sirketimcepte.linkentegrasyon.enums.EOdemeYonu;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car003;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs002;
import com.aymer.sirketimcepte.linkentegrasyon.model.Scs003;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class TahsilatMapper {

    @Mappings({
            @Mapping(target = "islemTarihi", source = "tarih"),
            @Mapping(target = "odemeYonu", expression = "java(getOdemeYonu())"),
            @Mapping(target = "odemeTipi", expression = "java(getNakitOdemeTipi())"),
            @Mapping(target = "kdvOrani", expression = "java(getKdvOrani())")
    })
    public abstract NakitTahsilatDto mapToNakitDto(Car003 car003);
    public abstract List<NakitTahsilatDto> mapToNakitDto(List<Car003> car003List);

    @Mappings({
            @Mapping(target = "islemTarihi", source = "tarih"),
            @Mapping(target = "odemeYonu", expression = "java(getOdemeYonu())"),
            @Mapping(target = "odemeTipi", expression = "java(getSenetOdemeTipi())"),
            @Mapping(target = "kdvOrani", expression = "java(getKdvOrani())")
    })
    public abstract SenetTahsilatDto mapToSenetDto(Scs002 scs002);
    public abstract List<SenetTahsilatDto> mapToSenetDto(List<Scs002> scs002List);


    @Mappings({
            @Mapping(target = "islemTarihi", source = "tarih"),
            @Mapping(target = "odemeYonu", expression = "java(getOdemeYonu())"),
            @Mapping(target = "odemeTipi", expression = "java(getCekOdemeTipi())"),
            @Mapping(target = "kdvOrani", expression = "java(getKdvOrani())")
    })
    public abstract CekTahsilatDto mapToCekDto(Scs003 scs003);
    public abstract List<CekTahsilatDto> mapToCekDto(List<Scs003> scs003List);

    public EOdemeYonu getOdemeYonu() {
        return EOdemeYonu.ALACAK;
    }

    public EOdemeTipi getNakitOdemeTipi() {
        return EOdemeTipi.NAKIT;
    }

    public EOdemeTipi getSenetOdemeTipi() {
        return EOdemeTipi.SENET;
    }

    public EOdemeTipi getCekOdemeTipi() {
        return EOdemeTipi.CEK;
    }

    public EKdvOrani getKdvOrani() {
        return EKdvOrani.KDV_ORANI_18;
    }
}
