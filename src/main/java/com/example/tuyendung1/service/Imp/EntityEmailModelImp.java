package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.EmailModelDto;
import com.example.tuyendung1.dto.Specification.SpecEntityEmailModel;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityEmailModel;
import com.example.tuyendung1.mapper.EntityEmailModelMap;
import com.example.tuyendung1.repository.EntityEmailModelRepo;
import com.example.tuyendung1.service.interfaceService.ServiceEntityEmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EntityEmailModelImp implements ServiceEntityEmailModel {

    private final EntityEmailModelRepo entityEmailModelRepo;
    private final EntityEmailModelMap entityEmailModelMap;

    @Override
    public ResponseId insert(EmailModelDto entityEmailModelDto) {
        if (entityEmailModelRepo.existsByTitle(entityEmailModelDto.getTitle())) {
            throw new RuntimeException("This title already exists");
        }
        EntityEmailModel entityEmailModel = entityEmailModelMap.dtoToEntity(entityEmailModelDto);
        entityEmailModelRepo.save(entityEmailModel);
        return new ResponseId(entityEmailModel.getId());
    }

    @Override
    public ResponseId Update(EmailModelDto entityEmailModelDto) {
        EntityEmailModel entityEmailModel = entityEmailModelRepo.save(entityEmailModelMap.dtoToEntity(entityEmailModelDto));
        return new ResponseId(entityEmailModel.getId());
    }

    @Override
    public void delete(Long id) {
        entityEmailModelRepo.deleteById(id);
    }

    @Override
    public EmailModelDto findById(Long id) {
        EntityEmailModel entityEmailModel = entityEmailModelRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return entityEmailModelMap.entityToDto(entityEmailModel);
    }

    @Override
    public PageResponse<EmailModelDto> findAll(int page, int size, EmailModelDto entityEmailModelDto) {
        Specification<EntityEmailModel> specification = new SpecEntityEmailModel(entityEmailModelDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<EntityEmailModel> pageData = entityEmailModelRepo.findAll(specification, pageable);

        return PageResponse.<EmailModelDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(entityEmailModelMap::entityToDto).toList())
                .build();
    }
}