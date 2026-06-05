package com.hivel.performance.service.impl;

import com.hivel.performance.dto.request.ReviewRequest;
import com.hivel.performance.dto.response.ReviewResponse;
import com.hivel.performance.entity.Employee;
import com.hivel.performance.entity.PerformanceReview;
import com.hivel.performance.entity.ReviewCycle;
import com.hivel.performance.exception.EmployeeNotFoundException;
import com.hivel.performance.exception.ReviewCycleNotFoundException;
import com.hivel.performance.repository.EmployeeRepository;
import com.hivel.performance.repository.PerformanceReviewRepository;
import com.hivel.performance.repository.ReviewCycleRepository;
import com.hivel.performance.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl
        implements ReviewService {

    private final EmployeeRepository employeeRepository;
    private final ReviewCycleRepository cycleRepository;
    private final PerformanceReviewRepository reviewRepository;


    @Transactional
    @Override
    public ReviewResponse submitReview(
            ReviewRequest request) {

        Employee employee =
                employeeRepository.findById(
                                request.getEmployeeId())
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found"));

        ReviewCycle cycle =
                cycleRepository.findById(
                                request.getReviewCycleId())
                        .orElseThrow(() ->
                                new ReviewCycleNotFoundException(
                                        "Review cycle not found"));

        PerformanceReview review =
                PerformanceReview.builder()
                        .employee(employee)
                        .reviewCycle(cycle)
                        .rating(request.getRating())
                        .reviewerNotes(
                                request.getReviewerNotes())
                        .submittedAt(
                                LocalDateTime.now())
                        .build();

        review = reviewRepository.save(review);

        return ReviewResponse.builder()
                .id(review.getId())
                .rating(review.getRating())
                .reviewerNotes(
                        review.getReviewerNotes())
                .build();
    }
}