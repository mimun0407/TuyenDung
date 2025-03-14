package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.ExperienceDto;
import com.example.tuyendung1.dto.Specification.SpecExperience;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityExperience;
import com.example.tuyendung1.mapper.ExperienceMapper;
import com.example.tuyendung1.repository.ExperienceRepo;
import com.example.tuyendung1.service.interfaceService.ServiceIExperience;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExperienceImp implements ServiceIExperience {
    ExperienceRepo experienceRepo;
    ExperienceMapper experienceMapper;
    @Override
    public ResponseId insert(ExperienceDto experienceDto) {
        EntityExperience experience=experienceMapper.dtoToEntity(experienceDto);
        experienceRepo.save(experience);
        return new ResponseId(experience.getId());
    }

    @Override
    public ResponseId Update(ExperienceDto experienceDto) {
        EntityExperience experience=experienceMapper.dtoToEntity(experienceDto);
        experienceRepo.save(experience);
        return new ResponseId(experience.getId());
    }

    @Override
    public void delete(Long id) {
        experienceRepo.deleteById(id);
    }

    @Override
    public ExperienceDto findById(Long id) {
        EntityExperience experience=experienceRepo.findById(id).orElseThrow(()->new RuntimeException("Experience not found"));
        return experienceMapper.entityToDto(experience);
    }
// m non vl ra ( A Diep )
    @Override
    public PageResponse<ExperienceDto> findAll(int page, int size, ExperienceDto experienceDto) {
        Sort sort=Sort.by("createdAt").descending();
        Pageable pageable= PageRequest.of(page-1,size,sort);
        Specification<EntityExperience> specification=new SpecExperience(experienceDto);
        var pageData=experienceRepo.findAll(specification,pageable);
        return PageResponse.<ExperienceDto>builder()
                .size(size)
                .page(page)
                .sortBy(sort.toString())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .content(pageData.getContent().stream().map(experienceMapper::entityToDto).collect(Collectors.toList()))
                .build();
    }
}


