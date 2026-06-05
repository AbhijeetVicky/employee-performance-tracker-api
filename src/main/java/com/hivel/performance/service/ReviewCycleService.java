package com.hivel.performance.service;

import com.hivel.performance.dto.request.ReviewCycleRequest;
import com.hivel.performance.dto.response.CycleSummaryResponse;
import com.hivel.performance.dto.response.ReviewCycleResponse;

import java.util.List;

public interface ReviewCycleService {

    ReviewCycleResponse createCycle(ReviewCycleRequest request);

    List<ReviewCycleResponse> getAllCycles();

    CycleSummaryResponse getSummary(Long cycleId);
}