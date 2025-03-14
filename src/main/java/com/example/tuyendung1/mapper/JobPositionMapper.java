package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", imports = Position.class)
public interface JobPositionMapper {
    JobPosition toEntity(JobPositionDto dto);
    JobPositionDto toDto(JobPosition entity);
}