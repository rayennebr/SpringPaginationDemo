package com.edu.test.paginationdemo.controllers;

import com.edu.test.paginationdemo.entity.Bill;
import com.edu.test.paginationdemo.entity.Customer;
import com.edu.test.paginationdemo.repositories.BillRepository;
import com.edu.test.paginationdemo.repositories.CustomerRepository;
import com.edu.test.paginationdemo.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bills")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BillController {

    private  final BillRepository billRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/")
    public void addBills()
    {
        Optional<Customer> customer=customerRepository.findById(1L);
        if(customer.isPresent())
        {
            for (int i=0;i<=10;i++)
            {
                Bill bill=Bill.builder()
                        .billAmount(2500L)
                        .customerId(customer.get().getCustomerId())
                        .billCode("bill0"+i)
                        .customer(customer.get())
                        .build();
                try{
                    billRepository.save(bill);
                }catch(Exception e)
                {
                    System.err.println(e.getMessage());
                }

            }
        }
    }

    @GetMapping("/{customerId}")
    ApiResponse<Bill>getAllCustomeBills(@PathVariable Long customerId)
    {
        try{
            return new ApiResponse<>(200,0,0L,billRepository.getBillByCustomerId(customerId),"success",0);
        }catch(Exception e){
            return new ApiResponse<>(400,0,0L,null,"error: "+e.getMessage(),0);
        }
    }
}
