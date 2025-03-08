package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.ApplicationFormDto;
import com.example.tuyendung1.entity.EntityApplicationForm;
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
public class SpecApplicationForm implements Specification<EntityApplicationForm> {
    ApplicationFormDto applicationFormDto;

    @Override
    public Predicate toPredicate(Root<EntityApplicationForm> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (applicationFormDto.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + applicationFormDto.getName() + "%"));
        }

        if (applicationFormDto.getCode() != null) {
            predicates.add(criteriaBuilder.equal(root.get("code"), applicationFormDto.getCode()));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
