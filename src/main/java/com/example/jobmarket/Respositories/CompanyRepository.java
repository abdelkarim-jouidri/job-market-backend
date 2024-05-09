package com.example.jobmarket.Respositories;

import com.example.jobmarket.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Integer, Company> {
}
