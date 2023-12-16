package com.edu.test.paginationdemo.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class GenericSearch<T> {

    private final EntityManager entityManager;

    public List<T> findAllByCriteria(T searchRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) searchRequest.getClass());
        List<Predicate> predicates = new ArrayList<>();

        Root<T> root = criteriaQuery.from((Class<T>) searchRequest.getClass());

        Field[] fields = searchRequest.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(searchRequest);
                if (value != null) {
                    Predicate predicate = criteriaBuilder.like(root.get(field.getName()), "%" + value + "%");
                    predicates.add(predicate);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}