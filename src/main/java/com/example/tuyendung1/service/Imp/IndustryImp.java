package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.IndustryDto;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.dto.Specification.SpecIndustry;
import com.example.tuyendung1.entity.Industry;
import com.example.tuyendung1.mapper.IndustryMap;
import com.example.tuyendung1.repository.IndustryRepo;
import com.example.tuyendung1.service.interfaceService.ServiceIndustry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IndustryImp implements ServiceIndustry {

   private final IndustryRepo industryRepo;

   private final IndustryMap industryMap;

    @Override
    public ResponseId insert(IndustryDto industryDto) {
        if (industryRepo.existsByCode(industryDto.getCode())){
            throw new RuntimeException("this code already exist");
        }
        Industry industry=industryMap.industryDtoToIndustry(industryDto);
        industryRepo.save(industry);
        ResponseId responseId=new ResponseId();
        responseId.setId(industry.getId());
        return responseId;
    }

    @Override
    public ResponseId Update(IndustryDto industryDto) {
        Industry industry=industryRepo.save(industryMap.industryDtoToIndustry(industryDto));
        ResponseId responseId=new ResponseId();
        responseId.setId(industry.getId());
        return responseId;
    }

    @Override
    public void delete(Long id) {
        industryRepo.deleteById(id);
    }

    @Override
    public IndustryDto findById(Long id) {
        Industry industry=industryRepo.findById(id).get();
        return industryMap.industryToIndustryDto(industry);
    }

    @Override
    public PageResponse<IndustryDto> findAll(int page, int size,IndustryDto industryDto) {
        Specification<Industry> specification=new SpecIndustry(industryDto);
        Sort sort= Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page-1, size, sort);
        var pageData=industryRepo.findAll(specification, pageable);
        return PageResponse.<IndustryDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(industryMap::industryToIndustryDto).toList())
                .build();
    }
}