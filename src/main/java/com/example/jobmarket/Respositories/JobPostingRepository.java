package com.example.jobmarket.Respositories;

import com.example.jobmarket.Models.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Page<JobPosting> findAll(Pageable pageable);
}
