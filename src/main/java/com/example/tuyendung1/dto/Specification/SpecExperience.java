package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.ExperienceDto;
import com.example.tuyendung1.entity.EntityExperience;
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
public class SpecExperience implements Specification<EntityExperience> {
    ExperienceDto experienceDto;

    @Override
    public Predicate toPredicate(Root<EntityExperience> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (experienceDto.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + experienceDto.getName() + "%"));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
