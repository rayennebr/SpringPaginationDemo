package com.edu.test.paginationdemo.repositories;

import com.edu.test.paginationdemo.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {

    List<Bill> getBillByCustomerId(Long customerId);
}
