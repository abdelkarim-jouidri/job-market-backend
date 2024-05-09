package com.example.jobmarket.Models;

import com.example.jobmarket.Enums.ContractType;
import com.example.jobmarket.Enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class JobPosting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String title;
    private ContractType contractType;
    private Status status;
    private LocalDate postedAt;
    @ManyToOne
    private Recruiter PostedBy;
    @OneToMany
    private Set<JobApplication> applications;
    @ManyToMany
    @JoinTable(
            name = "job_posting_skill",
            joinColumns = @JoinColumn(name = "job_posting_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;
}
