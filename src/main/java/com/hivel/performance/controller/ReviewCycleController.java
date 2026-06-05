package com.hivel.performance.controller;

import com.hivel.performance.dto.request.ReviewCycleRequest;
import com.hivel.performance.dto.response.CycleSummaryResponse;
import com.hivel.performance.dto.response.ReviewCycleResponse;
import com.hivel.performance.service.ReviewCycleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cycles")
@RequiredArgsConstructor
public class ReviewCycleController {

    private final ReviewCycleService reviewCycleService;

    @PostMapping
    public ResponseEntity<ReviewCycleResponse> createCycle(
            @Valid @RequestBody ReviewCycleRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCycleService.createCycle(request));
    }

    @GetMapping
    public ResponseEntity<List<ReviewCycleResponse>> getAllCycles() {
        return ResponseEntity.ok(reviewCycleService.getAllCycles());
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<CycleSummaryResponse> getSummary(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewCycleService.getSummary(id));
    }
}