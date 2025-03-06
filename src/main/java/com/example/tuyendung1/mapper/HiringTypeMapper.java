package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.HiringTypeDto;
import com.example.tuyendung1.entity.EntityHiringType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HiringTypeMapper {
    EntityHiringType dtoToEntity(HiringTypeDto dto);
    HiringTypeDto entityToDto(EntityHiringType entity);
}
