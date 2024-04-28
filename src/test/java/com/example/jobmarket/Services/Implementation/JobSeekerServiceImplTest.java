package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.Requests.JobSeekerRegisterRequest;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobSeekerServiceImplTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
        JobSeekerRegisterRequest registerRequest = JobSeekerRegisterRequest.
                builder().
                email("jobseeker1@gmail.com").
                password("123").
                lastname("Doe").
                firstName("mike").
                build();
//            Mock the dependecies
        String rawPassword = "123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        when(encoder.encode(rawPassword)).thenReturn(encodedPassword);
//        WHEN
        jobSeekerService.register(registerRequest);
//        Then
        verify(jobSeekerRepository, times(1)).save(any(JobSeeker.class));

    }


    @Test
    void authenticate() {
//        Given
        LoginRequest request = LoginRequest.
                builder().
                email("jobseeker@gmail.com").
                password("123").
                build();

        JobSeeker jobSeeker = JobSeeker.
                builder().
                firstName("john").
                lastName("jobseeker").
                password(passwordEncoder.encode("123")).
                email("jobseeker@gmail.com").
                build();

//        When
        when(jobSeekerRepository.findByEmail("jobseeker@gmail.com")).thenReturn(Optional.of(jobSeeker));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(jwtService.generateToken(jobSeeker)).thenReturn("token");

//      Then
        AuthenticationResponse authenticationResponse = jobSeekerService.authenticate(request);

//        Assert

        assertNotNull(authenticationResponse.getToken());

    }

    @Test
    public void testAuthenticateInvalidUser() {
        // Given
        String email = "test@example.com";
        String password = "password123";

        // Mocking behavior of jobSeekerRepository.findByEmail() to return empty optional (user not found)
        when(jobSeekerRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        // When / Then
        // Verify that attempting to authenticate with invalid credentials throws UsernameNotFoundException
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        assertThrows(UsernameNotFoundException.class, () -> {
            jobSeekerService.authenticate(loginRequest);
        });
    }
}