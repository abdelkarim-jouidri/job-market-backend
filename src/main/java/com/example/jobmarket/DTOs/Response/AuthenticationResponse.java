package com.example.jobmarket.DTOs.Response;

import lombok.*;

@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
