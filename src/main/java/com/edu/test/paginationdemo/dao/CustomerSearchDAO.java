package com.edu.test.paginationdemo.dao;

import com.edu.test.paginationdemo.dto.CustomerDTO;
import com.edu.test.paginationdemo.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerSearchDAO {

    private final EntityManager entityManager;

    public List<Customer>findAllByCriteria(
            CustomerDTO customerRequest
    ){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer>criteriaQuery=criteriaBuilder.createQuery(Customer.class);
        List<Predicate>predicates=new ArrayList<>();

        //select * from Customer
        Root<Customer>root=criteriaQuery.from(Customer.class);
        if(customerRequest.getName()!=null)
        {
            Predicate namePredicate=criteriaBuilder
                    .like(root.get("name"),"%"+customerRequest.getName()+"%");
            predicates.add(namePredicate);
        }

        if(customerRequest.getActivity()!=null)
        {
            Predicate activityPredicate=criteriaBuilder
                    .like(root.get("activity"),"%"+customerRequest.getActivity()+"%");
            predicates.add(activityPredicate);
        }

        if(customerRequest.getCompany()!=null)
        {
            Predicate companyPredicate=criteriaBuilder
                    .like(root.get("company"),"%"+customerRequest.getCompany()+"%");
            predicates.add(companyPredicate);
        }

        if(customerRequest.getGender()!=null)
        {
            Predicate genderPredicate=criteriaBuilder
                    .like(root.get("gender"),"%"+customerRequest.getGender()+"%");
            predicates.add(genderPredicate);
        }

        //where clause
        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Customer>query=entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
