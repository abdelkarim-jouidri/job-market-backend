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
    @ManyToOne
    private JobSeeker applicant;
    @OneToOne
    private JobPosting jobPosting;
}
