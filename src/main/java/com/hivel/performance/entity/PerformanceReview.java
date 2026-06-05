package com.hivel.performance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "employee_id",
            nullable = false
    )
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "review_cycle_id",
            nullable = false
    )
    private ReviewCycle reviewCycle;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "reviewer_notes")
    private String reviewerNotes;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
}