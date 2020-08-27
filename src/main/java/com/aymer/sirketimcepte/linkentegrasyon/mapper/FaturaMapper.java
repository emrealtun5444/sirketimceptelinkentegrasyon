package com.aymer.sirketimcepte.linkentegrasyon.mapper;

import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaKalemDto;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class FaturaMapper {

    @Mapping(target = "tutar", source = "faturaTutari")
    public abstract FaturaKalemDto mapToFaturaKalem(Car005 car005);

    public abstract List<FaturaKalemDto> mapToFaturaKalemList(List<Car005> car005List);
}
