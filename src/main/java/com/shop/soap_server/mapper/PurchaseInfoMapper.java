package com.shop.soap_server.mapper;


import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.dto.PurchaseInfoDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PurchaseInfoMapper {

    PurchaseInfoMapper INSTANCE = Mappers.getMapper(PurchaseInfoMapper.class);

    PurchaseInfo dtoToEntity(PurchaseInfoDto dto);

     PurchaseInfoDto entityToDto(PurchaseInfo entity);
}
