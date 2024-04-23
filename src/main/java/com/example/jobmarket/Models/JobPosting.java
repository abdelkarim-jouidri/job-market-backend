package com.example.jobmarket.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class JobPosting {
    @Id @GeneratedValue
    private Integer id;
    private String description;
//    @ManyToOne
//    private Recruiter PostedBy;
    @OneToMany
    private Set<JobApplication> applications;
}
