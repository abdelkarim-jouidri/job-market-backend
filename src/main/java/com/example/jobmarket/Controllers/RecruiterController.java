package com.example.jobmarket.Controllers;

import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Services.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/recruiter/")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @GetMapping("/auth/test")
    public String test(){
        return "test works";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid RecruiterRegisterRequest request){
            recruiterService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");


    }



    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            AuthenticationResponse authenticationResponse = recruiterService.authenticate(request);
            return ResponseEntity.ok(authenticationResponse);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @PreAuthorize("hasAuthority('RECRUTER')")
    @GetMapping("/profile")
    public Authentication profile(Authentication authentication){
        System.out.println(authentication);
        return authentication;
    }

    @GetMapping("/myjobs")
    public ResponseEntity<List<JobPostingReadDTO>> jobsByRecruiter(

            ){
        try {
            Recruiter recruiter = (Recruiter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ResponseEntity.ok(recruiterService.jobsByRecruiter(recruiter.getId()));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            // Handle other potential exceptions (e.g., database errors)
            return ResponseEntity.status(500).body(null);
        }
    }
}
