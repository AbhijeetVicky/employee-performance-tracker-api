package com.hivel.performance.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDetailsResponse {

    private String cycleName;

    private Integer rating;

    private String reviewerNotes;

    private LocalDateTime submittedAt;
}