package com.hivel.performance.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReviewCycleResponse {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}