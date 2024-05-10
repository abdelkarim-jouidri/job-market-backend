package com.example.jobmarket.Controllers;

import com.example.jobmarket.Models.Skill;
import com.example.jobmarket.Respositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillRepository skillRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> allSkills(){
        return ResponseEntity.ok(skillRepository.findAll());
    }
}
