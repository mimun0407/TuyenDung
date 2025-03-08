package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.InterviewQuestDto;
import com.example.tuyendung1.entity.EntityInterviewQuest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewQuestMap {
    EntityInterviewQuest dtoToEntity(InterviewQuestDto dto);
    InterviewQuestDto entityToDto(EntityInterviewQuest entity);
}