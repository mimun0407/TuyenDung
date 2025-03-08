package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.EmailModelDto;
import com.example.tuyendung1.entity.EntityEmailModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityEmailModelMap {
    EntityEmailModel dtoToEntity(EmailModelDto dto);
    EmailModelDto entityToDto(EntityEmailModel entity);
}
