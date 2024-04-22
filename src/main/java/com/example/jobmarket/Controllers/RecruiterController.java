package com.example.jobmarket.Controllers;

import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.Services.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/recruiter/")
@RequiredArgsConstructor
public class RecruiterController {
    private RecruiterService recruiterService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(RecruiterRegisterRequest request){
        recruiterService.register(request);
        return ResponseEntity.accepted().build();
    }

}
