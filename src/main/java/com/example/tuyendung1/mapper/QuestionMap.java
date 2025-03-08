package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.QuestionDto;
import com.example.tuyendung1.entity.EntityQuestion;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMap {
    QuestionDto entityToDto(EntityQuestion question);
    EntityQuestion dtoToEntity(QuestionDto dto);
}
