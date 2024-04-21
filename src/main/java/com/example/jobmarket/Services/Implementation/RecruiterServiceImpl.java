package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.Respositories.RecruiterRepository;
import com.example.jobmarket.Services.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService , UserDetailsService {
    private final RecruiterRepository recruiterRepository;
    @Override
    public void register() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recruiterRepository.
                findByEmail(username).
                orElseThrow();
    }
}
