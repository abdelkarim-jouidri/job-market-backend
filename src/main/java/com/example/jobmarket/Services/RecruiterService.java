package com.example.jobmarket.Services;

import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public interface RecruiterService {
    void register(RecruiterRegisterRequest request);
    AuthenticationResponse authenticate(LoginRequest request);
}
