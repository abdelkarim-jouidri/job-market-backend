package com.example.jobmarket.Services;

import com.example.jobmarket.DTOs.JobPosting.JobPostingCreateDTO;
import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import org.springframework.stereotype.Component;

@Component
public interface JobPostingService {
    JobPostingReadDTO addJobPosting(JobPostingCreateDTO jobPosting);
}
