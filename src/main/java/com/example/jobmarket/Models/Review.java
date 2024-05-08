package com.example.jobmarket.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer rating;
    private String comment;
    @ManyToOne
    private Company company;
    @ManyToOne
    private JobSeeker applicant;
}
