package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.JobPosting.JobPostingCreateDTO;
import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import com.example.jobmarket.Models.JobPosting;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Models.Skill;
import com.example.jobmarket.Respositories.JobPostingRepository;
import com.example.jobmarket.Respositories.SkillRepository;
import com.example.jobmarket.Services.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {
    private final JobPostingRepository jobPostingRepository;
    private final ModelMapper modelMapper;
    private final SkillRepository skillRepository;

    @Override
    public JobPostingReadDTO addJobPosting(JobPostingCreateDTO jobPosting) {
        JobPosting mappedEntity = modelMapper.map(jobPosting, JobPosting.class);
        Recruiter user = (Recruiter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mappedEntity.setPostedBy(user);
        mappedEntity.setPostedAt(LocalDate.now());
        Set<Skill> skills = new HashSet<>();
        jobPosting.getSkills().forEach(skillId->{
            Skill skill = skillRepository.
                            findById(skillId).
                            orElseThrow(() -> new NoSuchElementException());
            skills.add(skill);
        });
        mappedEntity.setSkills(skills);
        JobPosting savedEntity = jobPostingRepository.save(mappedEntity);
        return modelMapper.map(savedEntity, JobPostingReadDTO.class);
    }


    @Override
    public Page<JobPostingReadDTO> getPageableJobPostings(Pageable pageable) {
        Page<JobPosting> all = jobPostingRepository.findAll(pageable);
        return all.map(jobPosting -> modelMapper.map(jobPosting, JobPostingReadDTO.class));
    }
}
