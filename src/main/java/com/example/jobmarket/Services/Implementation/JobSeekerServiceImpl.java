package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.Respositories.JobSeekerRepository;
import com.example.jobmarket.Services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService , UserDetailsService {
    private final JobSeekerRepository jobSeekerRepository;
    @Override
    public void register() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jobSeekerRepository.
                findByEmail(username).
                orElseThrow();
    }
}
