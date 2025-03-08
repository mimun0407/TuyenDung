package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.FormOfWorkDto;
import com.example.tuyendung1.entity.EntityFormOfWork;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormOfWorkMap {
    EntityFormOfWork formOfWorkDtoToFormOfWork(FormOfWorkDto dto);
    FormOfWorkDto formOfWorkToFormOfWorkDto(EntityFormOfWork entity);
}