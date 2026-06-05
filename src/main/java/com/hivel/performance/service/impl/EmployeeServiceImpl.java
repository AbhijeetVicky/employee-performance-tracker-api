package com.hivel.performance.service.impl;

import com.hivel.performance.dto.request.EmployeeRequest;
import com.hivel.performance.dto.response.EmployeeResponse;
import com.hivel.performance.dto.response.EmployeeReviewResponse;
import com.hivel.performance.dto.response.ReviewDetailsResponse;
import com.hivel.performance.entity.Employee;
import com.hivel.performance.entity.PerformanceReview;
import com.hivel.performance.exception.EmployeeNotFoundException;
import com.hivel.performance.repository.EmployeeRepository;
import com.hivel.performance.repository.PerformanceReviewRepository;
import com.hivel.performance.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl
        implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository reviewRepository;

    @Transactional
    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request){

        Employee employee = Employee.builder()
                .name(request.getName())
                .department(request.getDepartment())
                .role(request.getRole())
                .joiningDate(request.getJoiningDate())
                .build();

        employee = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .role(employee.getRole())
                .build();
    }

    @Override
    public EmployeeReviewResponse getEmployeeReviews(
            Long employeeId) {

        Employee employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found : "
                                                + employeeId));

        List<PerformanceReview> reviews =
                reviewRepository.findByEmployeeId(
                        employeeId);

        List<ReviewDetailsResponse> reviewResponses =
                reviews.stream()
                        .map(review ->
                                ReviewDetailsResponse.builder()
                                        .cycleName(
                                                review.getReviewCycle().getName())
                                        .rating(
                                                review.getRating())
                                        .reviewerNotes(
                                                review.getReviewerNotes())
                                        .submittedAt(
                                                review.getSubmittedAt())
                                        .build())
                        .toList();

        return EmployeeReviewResponse.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getName())
                .reviews(reviewResponses)
                .build();
    }

    @Override
    public List<EmployeeResponse> filterEmployees(
            String department,
            Double minRating) {

        return reviewRepository
                .findEmployeesByDepartmentAndRating(
                        department,
                        minRating)
                .stream()
                .map(employee ->
                        EmployeeResponse.builder()
                                .id(employee.getId())
                                .name(employee.getName())
                                .department(employee.getDepartment())
                                .role(employee.getRole())
                                .build())
                .toList();
    }
}