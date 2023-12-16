package com.edu.test.paginationdemo.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{
    private int status;
    private int totalPages;
    private Long totalElements;
    private List<T> data;
    private String message;
    private int currentPage;
}
