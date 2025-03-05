package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.entity.JobPosition;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecJobPosition implements Specification<JobPosition> {
    JobPositionDto jobPositionDto;
    @Override
    public Predicate toPredicate(Root<JobPosition> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (jobPositionDto.getName()!=null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+jobPositionDto.getName()+"%"));
        }
        if (jobPositionDto.getCode()!=null){
            predicates.add(criteriaBuilder.equal(root.get("code"), jobPositionDto.getCode()));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
