package com.example.jobmarket.DTOs.JobPosting;


import com.example.jobmarket.Enums.ContractType;
import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class JobPostingCreateDTO {
    private Integer id;
    private String description;
    private String title;
    private ContractType contractType;
}
