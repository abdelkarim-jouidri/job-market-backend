package com.example.jobmarket.Respositories;

import com.example.jobmarket.Models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
    Optional<JobSeeker> findByEmail(String email);
}
