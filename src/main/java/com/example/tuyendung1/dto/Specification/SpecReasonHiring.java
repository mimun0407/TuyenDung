package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.ReasonHiringDto;
import com.example.tuyendung1.entity.EntityReasonHiring;
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
public class SpecReasonHiring implements Specification<EntityReasonHiring> {
    ReasonHiringDto reasonHiringDto;

    @Override
    public Predicate toPredicate(Root<EntityReasonHiring> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (reasonHiringDto.getName()!=null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+reasonHiringDto.getName()+"%"));
        }
        if (reasonHiringDto.getCode() != null) {
            predicates.add(criteriaBuilder.like(root.get("code"), "%" + reasonHiringDto.getCode() + "%"));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}