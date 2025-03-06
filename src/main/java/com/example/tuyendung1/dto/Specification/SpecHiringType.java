package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.HiringTypeDto;
import com.example.tuyendung1.entity.EntityHiringType;
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
public class SpecHiringType implements Specification<EntityHiringType> {
    HiringTypeDto hiringTypeDto;
    @Override
    public Predicate toPredicate(Root<EntityHiringType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (hiringTypeDto.getName()!=null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+hiringTypeDto.getName()+"%"));
        }
        if (hiringTypeDto.getCode()!=null){
            predicates.add(criteriaBuilder.equal(root.get("code"), hiringTypeDto.getCode()));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
