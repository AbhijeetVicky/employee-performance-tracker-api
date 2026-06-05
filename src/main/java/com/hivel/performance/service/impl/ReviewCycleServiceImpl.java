package com.hivel.performance.service.impl;

import com.hivel.performance.dto.request.ReviewCycleRequest;
import com.hivel.performance.dto.response.CycleSummaryResponse;
import com.hivel.performance.dto.response.ReviewCycleResponse;
import com.hivel.performance.entity.Employee;
import com.hivel.performance.entity.GoalStatus;
import com.hivel.performance.entity.PerformanceReview;
import com.hivel.performance.entity.ReviewCycle;
import com.hivel.performance.exception.EmployeeNotFoundException;
import com.hivel.performance.exception.ReviewCycleNotFoundException;
import com.hivel.performance.repository.EmployeeRepository;
import com.hivel.performance.repository.GoalRepository;
import com.hivel.performance.repository.PerformanceReviewRepository;
import com.hivel.performance.repository.ReviewCycleRepository;
import com.hivel.performance.service.ReviewCycleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCycleServiceImpl
        implements ReviewCycleService {

    private final PerformanceReviewRepository reviewRepository;
    private final GoalRepository goalRepository;
    private final EmployeeRepository employeeRepository;
    private final ReviewCycleRepository reviewCycleRepository;


    @Override
    public ReviewCycleResponse createCycle(ReviewCycleRequest request) {

        ReviewCycle cycle = ReviewCycle.builder()
                .name(request.getName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        cycle = reviewCycleRepository.save(cycle);

        return ReviewCycleResponse.builder()
                .id(cycle.getId())
                .name(cycle.getName())
                .startDate(cycle.getStartDate())
                .endDate(cycle.getEndDate())
                .build();
    }

    @Override
    public List<ReviewCycleResponse> getAllCycles() {

        List<ReviewCycle> cycles =
                reviewCycleRepository.findAll();

        return cycles.stream()
                .map(cycle -> ReviewCycleResponse.builder()
                        .id(cycle.getId())
                        .name(cycle.getName())
                        .startDate(cycle.getStartDate())
                        .endDate(cycle.getEndDate())
                        .build()
                )
                .toList();
    }

    @Override
    public CycleSummaryResponse getSummary(Long cycleId) {

        log.info("Fetching summary for cycle id: {}", cycleId);

        // 1. Validate cycle exists
        ReviewCycle cycle = reviewCycleRepository.findById(cycleId)
                .orElseThrow(() ->
                        new ReviewCycleNotFoundException(
                                "Review cycle not found: " + cycleId));

        // 2. Get all reviews for cycle
        List<PerformanceReview> reviews =
                reviewRepository.findByReviewCycleId(cycleId);

        // 3. Calculate average rating
        double avgRating = reviews.stream()
                .mapToInt(PerformanceReview::getRating)
                .average()
                .orElse(0.0);

        // 4. Find top performer
        Employee topPerformer = reviews.stream()
                .max((r1, r2) -> Integer.compare(
                        r1.getRating(),
                        r2.getRating()))
                .map(PerformanceReview::getEmployee)
                .orElse(null);

        String topPerformerName =
                topPerformer != null ? topPerformer.getName() : "N/A";

        // 5. Goals stats
        long completedGoals = goalRepository
                .countByReviewCycleIdAndStatus(
                        cycleId, GoalStatus.COMPLETED);

        long missedGoals = goalRepository
                .countByReviewCycleIdAndStatus(
                        cycleId, GoalStatus.MISSED);

        // 6. Build response
        return CycleSummaryResponse.builder()
                .cycleId(cycle.getId())
                .cycleName(cycle.getName())
                .averageRating(avgRating)
                .topPerformer(topPerformerName)
                .completedGoals(completedGoals)
                .missedGoals(missedGoals)
                .build();
    }
}