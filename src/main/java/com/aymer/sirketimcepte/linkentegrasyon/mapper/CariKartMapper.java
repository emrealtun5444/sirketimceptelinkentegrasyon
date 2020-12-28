package com.aymer.sirketimcepte.linkentegrasyon.mapper;

import com.aymer.sirketimcepte.linkentegrasyon.dto.CariKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.enums.ECariTipi;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car002;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class CariKartMapper {

    String[] eTicaret = {"HB", "GG", "PTT", "AMZ", "N11", "TRY"};
    String[] perakende = {"ELİF"};

    @Mapping(target = "cariTipi", expression = "java(getCariTipi(car002.getOzelKod()))")
    public abstract CariKartDto cariKartToDto(Car002 car002);

    public abstract List<CariKartDto> carikartToDtoList(List<Car002> car002List);

    public ECariTipi getCariTipi(String ozelKod) {
        ECariTipi cariTipi = ECariTipi.BAGLANTILI_CALISAN;
        if (stringContainsItemFromList(ozelKod, eTicaret)) {
            cariTipi = ECariTipi.ETICARET;
        } else if (stringContainsItemFromList(ozelKod, perakende)) {
            cariTipi = ECariTipi.PERAKENDE;
        }
        return cariTipi;
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }
}
