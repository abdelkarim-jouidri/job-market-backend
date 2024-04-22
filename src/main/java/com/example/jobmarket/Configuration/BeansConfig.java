package com.example.jobmarket.Configuration;

import com.example.jobmarket.Models.JobSeeker;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.JobSeekerRepository;
import com.example.jobmarket.Respositories.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final RecruiterRepository recruiterRepository;
    private final JobSeekerRepository jobSeekerRepository;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Optional<JobSeeker> jobSeekerbyEmail = jobSeekerRepository.findByEmail(username);
            if (jobSeekerbyEmail.isPresent()){
                return jobSeekerbyEmail.get();
            }
//            Optional<Recruiter> recruiterbyEmail = recruiterRepository.findByEmail(username);
//            if (recruiterbyEmail.isPresent()){
//                return recruiterbyEmail.get();
//            }
            throw new UsernameNotFoundException("There is no user with such credentials");
        };
    }


}
