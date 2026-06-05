package com.hivel.performance.service;

import com.hivel.performance.dto.request.ReviewRequest;
import com.hivel.performance.dto.response.ReviewResponse;

public interface ReviewService {

    ReviewResponse submitReview(
            ReviewRequest request);
}