package com.example.jobmarket.Respositories;

import com.example.jobmarket.Models.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
    Optional<Recruiter> findByEmail(String email);
}
