package com.example.jobmarket.Controllers;

import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Services.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return ResponseEntity.accepted().build();
    }



    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            AuthenticationResponse authenticationResponse = recruiterService.authenticate(request);
            return ResponseEntity.ok(authenticationResponse);
        }catch (ResponseStatusException e){
            throw e;
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (AuthenticationException e){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED, "Invalid credentials")
            System.out.println("something went wrong");
            throw e;
        }
    }


    @GetMapping("/profile")
    public Authentication profile(Authentication authentication){
        System.out.println(authentication);
        return authentication;
    }
}
