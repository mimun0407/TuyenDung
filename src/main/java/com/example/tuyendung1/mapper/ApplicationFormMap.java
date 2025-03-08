package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.ApplicationFormDto;
import com.example.tuyendung1.entity.EntityApplicationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationFormMap {
    EntityApplicationForm dtoToEntity(ApplicationFormDto dto);
    ApplicationFormDto entityToDto(EntityApplicationForm entity);
}