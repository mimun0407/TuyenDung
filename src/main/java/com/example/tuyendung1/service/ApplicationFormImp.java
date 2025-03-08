package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.ApplicationFormDto;
import com.example.tuyendung1.dto.Specification.SpecApplicationForm;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityApplicationForm;
import com.example.tuyendung1.mapper.ApplicationFormMap;
import com.example.tuyendung1.repository.ApplicationFormRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationFormImp implements ServiceApplicationForm {

    private final ApplicationFormRepo applicationFormRepo;
    private final ApplicationFormMap applicationFormMap;

    @Override
    public ResponseId insert(ApplicationFormDto applicationFormDto) {
        if (applicationFormRepo.existsByCode(applicationFormDto.getCode())) {
            throw new RuntimeException("This code already exists");
        }
        EntityApplicationForm entityApplicationForm = applicationFormMap.dtoToEntity(applicationFormDto);
        applicationFormRepo.save(entityApplicationForm);
        return new ResponseId(entityApplicationForm.getId());
    }

    @Override
    public ResponseId Update(ApplicationFormDto applicationFormDto) {
        EntityApplicationForm entityApplicationForm = applicationFormRepo.save(applicationFormMap.dtoToEntity(applicationFormDto));
        return new ResponseId(entityApplicationForm.getId());
    }

    @Override
    public void delete(Long id) {
        applicationFormRepo.deleteById(id);
    }

    @Override
    public ApplicationFormDto findById(Long id) {
        EntityApplicationForm entityApplicationForm = applicationFormRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return applicationFormMap.entityToDto(entityApplicationForm);
    }

    @Override
    public PageResponse<ApplicationFormDto> findAll(int page, int size, ApplicationFormDto applicationFormDto) {
        Specification<EntityApplicationForm> specification = new SpecApplicationForm(applicationFormDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<EntityApplicationForm> pageData = applicationFormRepo.findAll(specification, pageable);

        return PageResponse.<ApplicationFormDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(applicationFormMap::entityToDto).toList())
                .build();
    }
}
