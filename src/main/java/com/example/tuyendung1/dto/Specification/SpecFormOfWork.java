package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.FormOfWorkDto;
import com.example.tuyendung1.entity.EntityFormOfWork;
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
public class SpecFormOfWork implements Specification<EntityFormOfWork> {
    FormOfWorkDto formOfWorkDto;

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<EntityFormOfWork> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (formOfWorkDto.getCode() != null) {
            predicates.add(criteriaBuilder.equal(root.get("code"), formOfWorkDto.getCode()));
        }
        if (formOfWorkDto.getDescription() != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + formOfWorkDto.getDescription() + "%"));
        }
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}