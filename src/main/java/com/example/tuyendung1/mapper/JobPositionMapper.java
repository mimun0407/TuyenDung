package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.model.Department;
import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Position.class)
public interface JobPositionMapper {

    default Line mapJobPositionMapToLine(JobPositionMap jobPositionMap) {
        if (jobPositionMap == null) {
            return null;
        }
        Department department = new Department(jobPositionMap.getDepartmentId());
        Set<Position> positionSet = jobPositionMap.getPositionIds() == null ?
                Collections.emptySet() : jobPositionMap.getPositionIds().stream()
                .map(Position::new)
                .collect(Collectors.toSet());
        return new Line(department, positionSet);
    }

    @Mapping(target = "industry", source = "industry")
    JobPosition toEntity(JobPositionDto dto);


    @Mapping(target = "positionIds", expression = "java(line.getPositionSet() == null ? java.util.Collections.emptyList() : line.getPositionSet().stream().map(Position::getId).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "jobPosition", source = "jobPosition")
    @Mapping(target = "departmentId", source = "line.department.id")
    JobPositionMap toJobPositionMap(Line line, JobPosition jobPosition);

}