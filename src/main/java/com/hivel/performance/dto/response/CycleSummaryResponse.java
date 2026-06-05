package com.hivel.performance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CycleSummaryResponse {

    private Long cycleId;

    private String cycleName;

    private Double averageRating;

    private String topPerformer;

    private Long completedGoals;

    private Long missedGoals;
}