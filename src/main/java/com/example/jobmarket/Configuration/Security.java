package com.example.jobmarket.Configuration;

import com.example.jobmarket.Enums.Role;
import com.example.jobmarket.Filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;
    private final String[] AUTHORIZED_URLS = {
            "/api/recruiter/auth/**",
            "/api/jobseeker/auth/**",
            "api/jobs/all",
            "api/skills/all"

    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                cors(Customizer.withDefaults()).
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(req->
                        req.requestMatchers(
                                AUTHORIZED_URLS
                        ).permitAll().
                                anyRequest().authenticated()
                                ).
                sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authenticationProvider(authenticationProvider).
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
