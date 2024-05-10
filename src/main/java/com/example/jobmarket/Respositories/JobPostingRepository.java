package com.example.jobmarket.Respositories;

import com.example.jobmarket.Models.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Page<JobPosting> findAll(Pageable pageable);
//    List<JobPosting> findAllByPostedById(Integer id);
//    List<JobPosting> findJobPostingByPostedBy(Integer id);
    @Query("SELECT jp FROM JobPosting jp WHERE jp.PostedBy.id = :recruiterId")
    List<JobPosting> findJobPostingsByRecruiterId(@Param("recruiterId") Integer recruiterId);

}
