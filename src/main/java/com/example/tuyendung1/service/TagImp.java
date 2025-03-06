package com.example.tuyendung1.service;
import com.example.tuyendung1.dto.Specification.SpecTag;
import com.example.tuyendung1.dto.TagDto;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityTag;
import com.example.tuyendung1.mapper.TagMapper;
import com.example.tuyendung1.repository.TagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagImp implements ServiceITag{
    private final TagRepo tagRepo;
    private final TagMapper tagMapper;

    @Override
    public ResponseId insert(TagDto tagDto) {
        EntityTag entityTag=new EntityTag();
        entityTag.setName(tagDto.getName());
        tagRepo.save(entityTag);
        return new ResponseId(entityTag.getId());
    }

    @Override
    public ResponseId Update(TagDto tagDto) {
        EntityTag entityTag=new EntityTag();
        entityTag.setName(tagDto.getName());
        entityTag.setId(tagDto.getId());
        entityTag.setIsActive(tagDto.getIsActive());
        tagRepo.save(entityTag);
        return new ResponseId(entityTag.getId());
    }

    @Override
    public void delete(Long id) {
        tagRepo.deleteById(id);
    }

    @Override
    public TagDto findById(Long id) {
       EntityTag entityTag= tagRepo.findById(id).orElseThrow();
       TagDto tagDto=new TagDto();
       tagDto.setId(entityTag.getId());
       tagDto.setName(entityTag.getName());
       tagDto.setIsActive(entityTag.getIsActive());
        return tagDto;
    }

    @Override
    public PageResponse<TagDto> findAll(int page, int size, TagDto tagDto) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Specification<EntityTag> specification =new SpecTag(tagDto);
        var pageData=tagRepo.findAll(specification, pageable);

        return PageResponse.<TagDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(tagMapper::entityTagToTagDTO).toList())
                .build();
    }
}

