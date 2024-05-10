package com.example.jobmarket.DTOs.JobPosting;

import com.example.jobmarket.Enums.ContractType;
import com.example.jobmarket.Enums.Status;
import com.example.jobmarket.Models.JobApplication;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Models.Skill;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class JobPostingReadDTO {
    private String description;
    private String title;
    private ContractType contractType;
    private Status status;
    private LocalDate postedAt;
    private Recruiter PostedBy;
    private Set<JobApplication> applications;
    private Set<Skill> skills;
}
