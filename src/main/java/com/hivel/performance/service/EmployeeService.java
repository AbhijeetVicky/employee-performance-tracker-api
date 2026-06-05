package com.hivel.performance.service;

import com.hivel.performance.dto.request.EmployeeRequest;
import com.hivel.performance.dto.response.EmployeeResponse;
import com.hivel.performance.dto.response.EmployeeReviewResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(
            EmployeeRequest request);

    EmployeeReviewResponse getEmployeeReviews(
            Long employeeId);

    List<EmployeeResponse> filterEmployees(
            String department,
            Double minRating);
}