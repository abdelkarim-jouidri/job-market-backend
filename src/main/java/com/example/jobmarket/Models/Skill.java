package com.example.jobmarket.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Skill {
    @Id @GeneratedValue
    private Integer id;
    private String name;
}
