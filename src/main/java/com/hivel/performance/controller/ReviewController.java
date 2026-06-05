package com.hivel.performance.controller;

import com.hivel.performance.dto.request.ReviewRequest;
import com.hivel.performance.dto.response.ReviewResponse;
import com.hivel.performance.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> submitReview(
            @Valid @RequestBody ReviewRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.submitReview(request));
    }
}