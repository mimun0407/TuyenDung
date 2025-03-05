package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.IndustryDto;
import com.example.tuyendung1.entity.Industry;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface IndustryMap {
    IndustryDto industryToIndustryDto(Industry industry);
    Industry industryDtoToIndustry(IndustryDto industryDto);
}

