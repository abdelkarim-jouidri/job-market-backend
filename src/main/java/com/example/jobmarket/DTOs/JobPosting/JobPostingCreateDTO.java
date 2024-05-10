package com.example.jobmarket.DTOs.JobPosting;


import com.example.jobmarket.Enums.ContractType;
import com.example.jobmarket.Models.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class JobPostingCreateDTO {
    @NotBlank(message = "the description should not be blank")
    @NotEmpty(message = "the description is required")
    private String description;
    @NotBlank(message = "the title should not be blank")
    @NotEmpty(message = "the title is required")
    private String title;
    @NotNull(message = "the contract type is required")
    private ContractType contractType;
    private Set<Integer> skills;
}
