package com.hivel.performance.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeReviewResponse {

    private Long employeeId;

    private String employeeName;

    private List<ReviewDetailsResponse> reviews;
}