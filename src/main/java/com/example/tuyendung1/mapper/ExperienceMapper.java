package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.ExperienceDto;
import com.example.tuyendung1.entity.EntityExperience;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    ExperienceDto entityToDto(EntityExperience entityExperience);
    EntityExperience dtoToEntity(ExperienceDto experienceDto);
}
