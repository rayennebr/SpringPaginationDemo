package com.edu.test.paginationdemo.controllers;

import com.edu.test.paginationdemo.entity.Country;
import com.edu.test.paginationdemo.repositories.CountryRepository;
import com.edu.test.paginationdemo.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/country")
@CrossOrigin("*")
public class CountryController {

    private final CountryRepository countryRepository;

    @GetMapping("/")
    ApiResponse<Country> getAllCountires() {
        try {
            return new ApiResponse<>(200, 0, 0L, countryRepository.findAll(), "success", 0);
        } catch (Exception e) {
            return new ApiResponse<>(404, 0, 0L, null, "error" + e.getMessage(), 0);
        }
    }
}
