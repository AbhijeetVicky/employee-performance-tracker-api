package com.hivel.performance.repository;

import com.hivel.performance.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceReviewRepository
        extends JpaRepository<PerformanceReview, Long> {

    List<PerformanceReview> findByEmployeeId(Long employeeId);
    List<PerformanceReview> findByReviewCycleId(Long cycleId);

    @Query("""
            SELECT AVG(pr.rating)
            FROM PerformanceReview pr
            WHERE pr.reviewCycle.id = :cycleId
            """)
    Double getAverageRating(@Param("cycleId") Long cycleId);

    @Query("""
            SELECT pr.employee.id,
                   AVG(pr.rating)
            FROM PerformanceReview pr
            WHERE pr.reviewCycle.id = :cycleId
            GROUP BY pr.employee.id
            ORDER BY AVG(pr.rating) DESC
            """)
    List<Object[]> getTopPerformer(Long cycleId);

    @Query("""
            SELECT e
            FROM Employee e
            JOIN PerformanceReview pr
                ON e.id = pr.employee.id
            WHERE e.department = :department
            GROUP BY e
            HAVING AVG(pr.rating) >= :minRating
            """)
    List<com.hivel.performance.entity.Employee>
    findEmployeesByDepartmentAndRating(
            String department,
            Double minRating);
}