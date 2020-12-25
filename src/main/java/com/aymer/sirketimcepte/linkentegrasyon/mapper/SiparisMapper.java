package com.aymer.sirketimcepte.linkentegrasyon.mapper;

import com.aymer.sirketimcepte.linkentegrasyon.dto.FaturaKalemDto;
import com.aymer.sirketimcepte.linkentegrasyon.dto.SiparisDto;
import com.aymer.sirketimcepte.linkentegrasyon.model.Car005;
import com.aymer.sirketimcepte.linkentegrasyon.model.Stk002;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class SiparisMapper {

    public abstract SiparisDto mapToDto(Stk002 stk002);

    public abstract List<SiparisDto> mapToDtoList(List<Stk002> stk002List);
}
