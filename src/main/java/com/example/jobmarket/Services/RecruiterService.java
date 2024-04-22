package com.example.jobmarket.Services;

import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public interface RecruiterService {
    void register(RecruiterRegisterRequest request);
}
