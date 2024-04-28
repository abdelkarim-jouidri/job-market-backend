package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.Requests.JobSeekerRegisterRequest;
import com.example.jobmarket.Models.JobSeeker;
import com.example.jobmarket.Respositories.JobSeekerRepository;
import com.example.jobmarket.Services.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobSeekerServiceImplTest {
    @InjectMocks
    private JobSeekerServiceImpl jobSeekerService;

//    Mocking of the service dependencies
    @Mock
    private  JobSeekerRepository jobSeekerRepository;
    @Mock
    private  PasswordEncoder encoder;
    @Mock
    private  JwtService jwtService;
    @Mock
    private  AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterAJobSeekerUserSuccessfully() {

//        Given
//        JobSeeker jobSeeker = JobSeeker.
//                builder().
//                email("jobseeker1@gmail.com").
//                lastName("Doe").
//                firstName("mike").
//                password("123").
//                build();
        JobSeekerRegisterRequest registerRequest = JobSeekerRegisterRequest.
                builder().
                email("jobseeker1@gmail.com").
                password("123").
                lastname("Doe").
                firstName("mike").
                build();
        //            Mock the dependecies
        String rawPassword = "123";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        when(encoder.encode(rawPassword)).thenReturn(encodedPassword);
//        WHEN
        jobSeekerService.register(registerRequest);
//        Then
        verify(jobSeekerRepository, times(1)).save(any(JobSeeker.class));

    }

    @Test
    void authenticate() {
    }
}