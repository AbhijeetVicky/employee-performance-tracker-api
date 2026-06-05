package com.hivel.performance.controller;

import com.hivel.performance.dto.request.EmployeeRequest;
import com.hivel.performance.dto.response.EmployeeResponse;
import com.hivel.performance.dto.response.EmployeeReviewResponse;
import com.hivel.performance.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(request));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<EmployeeReviewResponse> getEmployeeReviews(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeReviews(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> filterEmployees(
            @RequestParam String department,
            @RequestParam Double minRating) {

        return ResponseEntity.ok(
                employeeService.filterEmployees(
                        department,
                        minRating));
    }
}