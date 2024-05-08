package com.example.jobmarket.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String location;

    @OneToOne
    private Recruiter recruiter;

}
