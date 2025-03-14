package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.InterviewQuestDto;
import com.example.tuyendung1.dto.Specification.SpecInterviewQuest;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityInterviewQuest;
import com.example.tuyendung1.mapper.InterviewQuestMap;
import com.example.tuyendung1.repository.InterviewQuestRepo;
import com.example.tuyendung1.service.interfaceService.ServiceInterviewQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterviewQuestImp implements ServiceInterviewQuest {

    private final InterviewQuestRepo interviewQuestRepo;
    private final InterviewQuestMap interviewQuestMap;

    @Override
    public ResponseId insert(InterviewQuestDto interviewQuestDto) {
        if (interviewQuestRepo.existsByCode(interviewQuestDto.getCode())) {
            throw new RuntimeException("This code already exists");
        }
        EntityInterviewQuest entityInterviewQuest = interviewQuestMap.dtoToEntity(interviewQuestDto);
        interviewQuestRepo.save(entityInterviewQuest);
        return new ResponseId(entityInterviewQuest.getId());
    }

    @Override
    public ResponseId Update(InterviewQuestDto interviewQuestDto) {
        EntityInterviewQuest entityInterviewQuest = interviewQuestRepo.save(interviewQuestMap.dtoToEntity(interviewQuestDto));
        return new ResponseId(entityInterviewQuest.getId());
    }

    @Override
    public void delete(Long id) {
        interviewQuestRepo.deleteById(id);
    }

    @Override
    public InterviewQuestDto findById(Long id) {
        EntityInterviewQuest entityInterviewQuest = interviewQuestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return interviewQuestMap.entityToDto(entityInterviewQuest);
    }

    @Override
    public PageResponse<InterviewQuestDto> findAll(int page, int size, InterviewQuestDto interviewQuestDto) {
        Specification<EntityInterviewQuest> specification = new SpecInterviewQuest(interviewQuestDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<EntityInterviewQuest> pageData = interviewQuestRepo.findAll(specification, pageable);

        return PageResponse.<InterviewQuestDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(interviewQuestMap::entityToDto).toList())
                .build();
    }
}