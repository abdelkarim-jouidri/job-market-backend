package com.example.jobmarket.Services.Implementation;

import com.example.jobmarket.DTOs.JobPosting.JobPostingCreateDTO;
import com.example.jobmarket.DTOs.JobPosting.JobPostingReadDTO;
import com.example.jobmarket.Enums.ContractType;

import com.example.jobmarket.Models.JobPosting;
import com.example.jobmarket.Models.Recruiter;
import com.example.jobmarket.Respositories.JobPostingRepository;
import com.example.jobmarket.Services.JobPostingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class JobPostingServiceImplTest {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JobPostingServiceImpl jobPostingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJobPosting() {
        // Given
        JobPostingCreateDTO jobPostingCreateDTO = new JobPostingCreateDTO();
        jobPostingCreateDTO.setDescription("Test Description");
        jobPostingCreateDTO.setTitle("Test Title");
        jobPostingCreateDTO.setContractType(ContractType.CDI);
        jobPostingCreateDTO.setSkills(Set.of(1, 2, 3));

        JobPosting jobPosting = new JobPosting();
        jobPosting.setDescription(jobPostingCreateDTO.getDescription());
        jobPosting.setTitle(jobPostingCreateDTO.getTitle());
        jobPosting.setContractType(jobPostingCreateDTO.getContractType());

        Recruiter recruiter = new Recruiter();
        recruiter.setId(1);

        when(modelMapper.map(jobPostingCreateDTO, JobPosting.class)).thenReturn(jobPosting);
        // Mock Security Context
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(recruiter); // Provide the authenticated user
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(jobPostingRepository.save(Mockito.<JobPosting>any())).thenReturn(jobPosting);

        JobPostingReadDTO expectedReadDTO = new JobPostingReadDTO();
        when(modelMapper.map(Mockito.<JobPosting>any(), eq(JobPostingReadDTO.class)))
                .thenReturn(expectedReadDTO);

        // When
        JobPostingReadDTO result = jobPostingService.addJobPosting(jobPostingCreateDTO);

        // Then
        assertNotNull(result);
        verify(modelMapper).map(jobPostingCreateDTO, JobPosting.class);
        assertEquals(jobPostingCreateDTO.getDescription(), jobPosting.getDescription());
        assertEquals(jobPostingCreateDTO.getTitle(), jobPosting.getTitle());
        assertEquals(jobPostingCreateDTO.getContractType(), jobPosting.getContractType());

        assertEquals(LocalDate.now(), jobPosting.getPostedAt());
        assertEquals(recruiter, jobPosting.getPostedBy());

        verify(jobPostingRepository).save(jobPosting);
        verify(modelMapper).map(jobPosting, JobPostingReadDTO.class);
    }
}
