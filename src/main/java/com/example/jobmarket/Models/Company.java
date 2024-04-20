package com.example.jobmarket.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Setter @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String location;

}
