package com.example.jobmarket.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class JobApplication {
    @Id @GeneratedValue
    private Integer id;
}