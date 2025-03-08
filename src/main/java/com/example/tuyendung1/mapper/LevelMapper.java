package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.LevelDto;
import com.example.tuyendung1.entity.EntityLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    EntityLevel dtoToEntity(LevelDto dto);
    LevelDto entityToDto(EntityLevel entityLevel);
}
