package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.Models.JobSeeker;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.JobSeekerRepository;
import com.example.jobmarket.Respositories.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final JobSeekerRepository jobSeekerRepository;
    private final RecruiterRepository recruiterRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JobSeeker> jobSeekerbyEmail = jobSeekerRepository.findByEmail(username);
        if (jobSeekerbyEmail.isPresent()){
            return jobSeekerbyEmail.get();
        }
        Optional<Recruiter> recruiterbyEmail = recruiterRepository.findByEmail(username);
        if (recruiterbyEmail.isPresent()){
            return recruiterbyEmail.get();
        }
        throw new UsernameNotFoundException("There is no user with such credentials");
    }
}
