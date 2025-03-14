//package com.example.tuyendung1.dto.Specification;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.lang.reflect.Field;
//import java.util.List;
//public class SpecApp<T> implements Specification<T> {
//    List<String> c;
//    T t;
//    public SpecApp(List<String> c, T t) {
//        this.c = c;
//        this.t = t;
//    }
//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        Class<?> s=t.getClass();
//        Field[] allFields = s.getDeclaredFields();
//         for (int i = 0; i < allFields.length; i++) {
//             Field f = allFields[i];
//             if(f.getName().equals(c.get(i))){
//
//         }
//        }
//    }
//}
