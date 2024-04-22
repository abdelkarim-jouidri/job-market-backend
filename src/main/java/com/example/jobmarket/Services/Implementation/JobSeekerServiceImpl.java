package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.Requests.JobSeekerRegisterRequest;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Models.JobSeeker;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.JobSeekerRepository;
import com.example.jobmarket.Services.JobSeekerService;
import com.example.jobmarket.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService  {
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(JobSeekerRegisterRequest request) {
        JobSeeker entity = JobSeeker.
                builder().
                email(request.getEmail()).
                firstName(request.getFirstName()).
                lastName(request.getLastname()).
                password(encoder.encode(request.getPassword())).
                build();
        jobSeekerRepository.save(entity);

    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        JobSeeker jobSeekerUser = jobSeekerRepository.
                findByEmail(request.
                        getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("Wrong Credentials"));

        var jwt = jwtService.generateToken(jobSeekerUser);
        return AuthenticationResponse.
                builder().
                token(jwt).
                build();
    }
}
