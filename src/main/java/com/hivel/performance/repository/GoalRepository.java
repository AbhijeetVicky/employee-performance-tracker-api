package com.hivel.performance.repository;

import com.hivel.performance.entity.Goal;
import com.hivel.performance.entity.GoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository
        extends JpaRepository<Goal, Long> {

    long countByReviewCycleIdAndStatus(
            Long reviewCycleId,
            GoalStatus status);


}