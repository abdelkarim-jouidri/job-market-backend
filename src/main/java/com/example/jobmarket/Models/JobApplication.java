package com.example.jobmarket.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class JobApplication {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate dateOfApplication;
    private String cv;
    @ManyToOne
    private JobSeeker applicant;
    @ManyToOne
    private JobPosting jobPosting;
}
