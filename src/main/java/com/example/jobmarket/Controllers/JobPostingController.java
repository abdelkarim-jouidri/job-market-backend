package com.example.jobmarket.Controllers;

import com.example.jobmarket.DTOs.JobPosting.JobPostingCreateDTO;
import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import com.example.jobmarket.Services.JobPostingService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobPostingController {
    private final JobPostingService jobPostingService;
    @GetMapping("/all")
    public ResponseEntity<Page<JobPostingReadDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<JobPostingReadDTO> pageableJobPostings = jobPostingService.getPageableJobPostings(pageable);
        return ResponseEntity.ok(pageableJobPostings);
    }

    @PreAuthorize("hasAnyAuthority('RECRU')")
    @PostMapping
    public ResponseEntity<JobPostingReadDTO> addJobPosting(
            @Valid @RequestBody JobPostingCreateDTO jobPostingDTO
            ){
        JobPostingReadDTO saved = jobPostingService.addJobPosting(jobPostingDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user")
    public Object currentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
