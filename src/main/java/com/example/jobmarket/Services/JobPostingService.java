package com.example.jobmarket.Services;

import com.example.jobmarket.DTOs.JobPosting.JobPostingCreateDTO;
import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface JobPostingService {
    JobPostingReadDTO addJobPosting(JobPostingCreateDTO jobPosting);

    Page<JobPostingReadDTO> getPageableJobPostings(Pageable pageable);
}
