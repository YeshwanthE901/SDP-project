package com.homevalueplus.repository;

import com.homevalueplus.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT s.propertyType, COUNT(s) FROM Submission s GROUP BY s.propertyType")
    List<Object[]> countByPropertyTypes();

    @Query("SELECT s.propertyCondition, COUNT(s) FROM Submission s GROUP BY s.propertyCondition")
    List<Object[]> countByConditions();

    @Query("SELECT AVG(s.budget) FROM Submission s")
    Double averageBudget();

    long countBySubmissionDateAfter(LocalDateTime date);

    List<Submission> findAllByOrderBySubmissionDateDesc();
}
