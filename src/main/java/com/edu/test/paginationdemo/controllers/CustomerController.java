package com.edu.test.paginationdemo.controllers;

import com.edu.test.paginationdemo.dao.CustomerSearchDAO;
import com.edu.test.paginationdemo.dao.GenericSearch;
import com.edu.test.paginationdemo.dto.CustomerDTO;
import com.edu.test.paginationdemo.entity.Customer;
import com.edu.test.paginationdemo.repositories.CustomerRepository;
import com.edu.test.paginationdemo.services.CustomerService;
import com.edu.test.paginationdemo.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CustomerSearchDAO customerSearchDAO;

    private final GenericSearch<Customer>genericSearch;



    @GetMapping("/{page}/{size}")
    public ApiResponse<Customer> getCustomer(
            @PathVariable int page,// @RequestParam(defaultValue = "lastName,asc") String sort
            @PathVariable int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);//, Sort.by(sort)
            Page<Customer> pageableCustomer = customerService.getAllCustomer(pageable);
            return new ApiResponse<>(200,
                    pageableCustomer.getTotalPages(),
                    pageableCustomer.getTotalElements(),
                    pageableCustomer.getContent(),
                    "success",
                    pageableCustomer.getNumber());
        } catch (Exception e) {
            return new ApiResponse<>(200,
                    0,
                    0L,
                    null,
                    "error with message " + e.getMessage(),
                    0);
        }
    }

    @GetMapping("/getSortCustomer/{page}/{size}/{sortDirection}/{sortProperty}")
    public ApiResponse<Customer> getSortCustomer(
            @PathVariable int page,// @RequestParam(defaultValue = "lastName,asc") String sort
            @PathVariable int size,
            @PathVariable String sortDirection,
            @PathVariable String sortProperty
    ) {
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortProperty);
            Pageable pageable = PageRequest.of(page, size,sort);
            Page<Customer> pageableCustomer = customerService.getAllCustomer(pageable);
            return new ApiResponse<>(200,
                    pageableCustomer.getTotalPages(),
                    pageableCustomer.getTotalElements(),
                    pageableCustomer.getContent(),
                    "success",
                    pageableCustomer.getNumber());
        } catch (Exception e) {
            return new ApiResponse<>(200,
                    0,
                    0L,
                    null,
                    "error with message " + e.getMessage(),
                    0);
        }
    }

    @PostMapping("/")
    public void addCustomer() {
        for (int i = 14000; i < 16000; i++) {
            Customer customer = Customer.builder()
                    .name("customer"+i )
                    .activity("activity" + i)
                    .countryId(1L)
                    .date(new Date())
                    .company("company"+i)
                    .gender("homme")
                    .status("activé")
                    .build();
            customerRepository.save(customer);
        }
    }

    /***
     * search api
     */
    @PostMapping("/search")
    List<Customer>searchCustomer(@RequestBody Customer customerDTO){
       // return customerSearchDAO.findAllByCriteria(customerDTO);
        return genericSearch.findAllByCriteria(customerDTO);
    }

}
