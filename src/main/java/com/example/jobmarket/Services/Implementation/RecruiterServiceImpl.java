package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.RecruiterRepository;
import com.example.jobmarket.Services.JwtService;
import com.example.jobmarket.Services.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService  {
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    @Override
    public void register(RecruiterRegisterRequest request) {
        Recruiter entity = Recruiter.
                builder().
                email(request.getEmail()).
                Password(encoder.encode(request.getPassword())).build();
        recruiterRepository.save(entity);
        String generatedToken = jwtService.generateToken(entity);

    }


}
