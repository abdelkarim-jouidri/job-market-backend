package com.example.jobmarket.Controllers;

import com.example.jobmarket.DTOs.Requests.JobSeekerRegisterRequest;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Services.JobSeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/jobseeker")
@RequiredArgsConstructor
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody @Valid JobSeekerRegisterRequest request) {
        jobSeekerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }



    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            AuthenticationResponse authenticationResponse = jobSeekerService.authenticate(request);
            return ResponseEntity.ok(authenticationResponse);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/profile")
    public Authentication profile(Authentication authentication){
        System.out.println(authentication);
        return authentication;
    }

}
