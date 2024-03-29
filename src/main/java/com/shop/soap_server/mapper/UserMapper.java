package com.shop.soap_server.mapper;

import com.shop.soap_server.model.User;

import com.shop.soap_server.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserDto dto);

    UserDto entityToDto(User entity);
}
