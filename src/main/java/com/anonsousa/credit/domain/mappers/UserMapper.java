package com.anonsousa.credit.domain.mappers;

import com.anonsousa.credit.domain.dtos.user.RequestUserDto;
import com.anonsousa.credit.domain.dtos.user.ResponseUserDto;
import com.anonsousa.credit.domain.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    UserEntity userDtoToUserEntity(RequestUserDto requestUserDto);


    ResponseUserDto userEntityToUserDto(UserEntity userEntity);
}
