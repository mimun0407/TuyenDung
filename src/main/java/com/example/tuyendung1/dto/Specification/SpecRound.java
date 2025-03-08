package com.example.tuyendung1.dto.Specification;


import com.example.tuyendung1.dto.RoundDto;
import com.example.tuyendung1.entity.EntityRound;
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
public class SpecRound implements Specification<EntityRound> {
    RoundDto roundDto;
    @Override
    public Predicate toPredicate(Root<EntityRound> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (roundDto.getName()!=null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+roundDto.getName()+"%"));
        }
        if (roundDto.getCode()!=null){
            predicates.add(criteriaBuilder.equal(root.get("code"), roundDto.getCode()));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
