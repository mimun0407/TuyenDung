package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.InterviewQuestDto;
import com.example.tuyendung1.dto.QuestionDto;
import com.example.tuyendung1.dto.Specification.SpecQuestion;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityInterviewQuest;
import com.example.tuyendung1.entity.EntityQuestion;
import com.example.tuyendung1.mapper.InterviewQuestMap;
import com.example.tuyendung1.mapper.QuestionMap;
import com.example.tuyendung1.repository.QuestionRepo;
import com.example.tuyendung1.service.interfaceService.ServiceQuest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionImp implements ServiceQuest {
    QuestionMap questionMap;
    QuestionRepo questionRepo;
    InterviewQuestMap interviewQuestMap;

    @Override
    public ResponseId insert(QuestionDto questionDto) {
        EntityQuestion question= questionMap.dtoToEntity(questionDto);
        EntityInterviewQuest entityGroupReason= interviewQuestMap.dtoToEntity(questionDto.getInterviewQuest());
        question.setQuestion(entityGroupReason);
        questionRepo.save(question);
        return new ResponseId(question.getId());
    }

    @Override
    public ResponseId Update(QuestionDto questionDto) {
        EntityQuestion question= questionMap.dtoToEntity(questionDto);
        EntityInterviewQuest entityGroupReason= interviewQuestMap.dtoToEntity(questionDto.getInterviewQuest());
        question.setQuestion(entityGroupReason);
        questionRepo.save(question);
        return new ResponseId(question.getId());
    }

    @Override
    public void delete(Long id) {
        questionRepo.deleteById(id);
    }

    @Override
    public QuestionDto findById(Long id) {
        EntityQuestion question= questionRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find question"));
        QuestionDto questionDto=questionMap.entityToDto(question);
        InterviewQuestDto interviewQuestDto=interviewQuestMap.entityToDto(question.getQuestion());
        questionDto.setInterviewQuest(interviewQuestDto);
        return questionDto;
    }

    @Override
    public PageResponse<QuestionDto> findAll(int page, int size, QuestionDto questionDto) {
        Specification<EntityQuestion> specification = new SpecQuestion(questionDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = questionRepo.findAll(specification, pageable);

        return PageResponse.<QuestionDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(question -> {
                    QuestionDto dto = questionMap.entityToDto(question);
                    dto.setInterviewQuest(interviewQuestMap.entityToDto(question.getQuestion()));
                    return dto;
                }).toList())
                .build();
    }
}
