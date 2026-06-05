package com.hivel.performance.repository;

import com.hivel.performance.entity.ReviewCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCycleRepository
        extends JpaRepository<ReviewCycle, Long> {
}