package com.example.jobmarket.Services;

import com.example.jobmarket.DTOs.Requests.JobSeekerRegisterRequest;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public interface JobSeekerService {

    void register(JobSeekerRegisterRequest request);
    AuthenticationResponse authenticate(LoginRequest request);}
