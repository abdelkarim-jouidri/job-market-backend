package com.example.jobmarket.DTOs.Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please Enter a valid email format")
    private String email;
    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
