package com.edu.test.paginationdemo.controllers;

import com.edu.test.paginationdemo.entity.City;
import com.edu.test.paginationdemo.repositories.CityRepository;
import com.edu.test.paginationdemo.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin("*")
public class CityController {

    private final CityRepository cityRepository;

    @PostMapping("/")
    public void addCity()
    {
        for (int i=0;i<100;i++)
        {
            City city =City.builder()
                    .countryId(1L)
                    .cityCod("cit"+i+"Cod")
                    .cityDesc("cit"+i+"Desc")
                    .build();
            try{
                this.cityRepository.save(city);
            }catch (Exception e)
            {
                System.err.println(e.getMessage());
            }

        }
    }

    @GetMapping("/")
    ApiResponse<City>getAllCities()
    {
        try{
            return new ApiResponse<>(200,0,0L,cityRepository.findAll(),"success",0);
        }catch(Exception e){
            return new ApiResponse<>(400,0,0L,null,"error: "+e.getMessage(),0);
        }
    }

}
