package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.FormOfWorkDto;
import com.example.tuyendung1.dto.Specification.SpecFormOfWork;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityFormOfWork;
import com.example.tuyendung1.mapper.FormOfWorkMap;
import com.example.tuyendung1.repository.FormOfWorkRepo;
import com.example.tuyendung1.service.interfaceService.ServiceFormOfWork;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormOfWorkImp implements ServiceFormOfWork {

    private final FormOfWorkRepo formOfWorkRepo;
    private final FormOfWorkMap formOfWorkMap;

    @Override
    public ResponseId insert(FormOfWorkDto formOfWorkDto) {
        if (formOfWorkRepo.existsByCode(formOfWorkDto.getCode())) {
            throw new RuntimeException("This code already exists");
        }
        EntityFormOfWork formOfWork = formOfWorkMap.formOfWorkDtoToFormOfWork(formOfWorkDto);
        formOfWorkRepo.save(formOfWork);
        return new ResponseId(formOfWork.getId());
    }

    @Override
    public ResponseId Update(FormOfWorkDto formOfWorkDto) {
        EntityFormOfWork formOfWork = formOfWorkRepo.save(formOfWorkMap.formOfWorkDtoToFormOfWork(formOfWorkDto));
        return new ResponseId(formOfWork.getId());
    }

    @Override
    public void delete(Long id) {
        formOfWorkRepo.deleteById(id);
    }

    @Override
    public FormOfWorkDto findById(Long id) {
        EntityFormOfWork formOfWork = formOfWorkRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return formOfWorkMap.formOfWorkToFormOfWorkDto(formOfWork);
    }

    @Override
    public PageResponse<FormOfWorkDto> findAll(int page, int size, FormOfWorkDto formOfWorkDto) {
        Specification<EntityFormOfWork> specification = new SpecFormOfWork(formOfWorkDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<EntityFormOfWork> pageData = formOfWorkRepo.findAll(specification, pageable);

        return PageResponse.<FormOfWorkDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(formOfWorkMap::formOfWorkToFormOfWorkDto).toList())
                .build();
    }
}