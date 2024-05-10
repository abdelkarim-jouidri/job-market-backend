package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import com.example.jobmarket.DTOs.Requests.LoginRequest;
import com.example.jobmarket.DTOs.Requests.RecruiterRegisterRequest;
import com.example.jobmarket.DTOs.Response.AuthenticationResponse;
import com.example.jobmarket.Enums.Role;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.JobPostingRepository;
import com.example.jobmarket.Respositories.RecruiterRepository;
import com.example.jobmarket.Services.JwtService;
import com.example.jobmarket.Services.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService  {
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final JobPostingRepository jobPostingRepository;
    @Override
    public void register(RecruiterRegisterRequest request) {
        Recruiter entity = Recruiter.
                builder().
                email(request.getEmail()).
                Password(encoder.encode(request.getPassword())).
                role(Role.RECRUTER).
                build();
        recruiterRepository.save(entity);

    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Recruiter recruiterUser = recruiterRepository.
                findByEmail(request.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("Wrong credentials"));

        var jwt = jwtService.generateToken(recruiterUser);
        return AuthenticationResponse.
                builder().
                token(jwt).
                build();
    }

    @Override
    public List<JobPostingReadDTO> jobsByRecruiter(Integer id) {
        recruiterRepository.
                findById(id).
                orElseThrow(()->new NoSuchElementException("No such recruiter for this id"));

        return jobPostingRepository.
                findJobPostingsByRecruiterId(id).
                stream().
                map(jobPosting -> modelMapper.map(jobPosting, JobPostingReadDTO.class)).
                collect(Collectors.toList());
    }


}
