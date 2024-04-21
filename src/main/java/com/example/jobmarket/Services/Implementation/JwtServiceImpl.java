package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.Services.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtServiceImpl implements JwtService {
    @Override
    public String extractUsername(String jwt) {
        return null;
    }

    @Override
    public Claims extractAllClaims(String jwt) {
        return null;
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return false;
    }

    @Override
    public boolean isTokenExpired(String jwt) {
        return false;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return null;
    }
}
