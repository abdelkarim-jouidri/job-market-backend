package com.example.jobmarket.Configuration;

import com.example.jobmarket.Models.Skill;
import com.example.jobmarket.Respositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final SkillRepository skillRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeSkills(){
        System.out.println("triggered");
        if (skillRepository.count() == 0) {
            // Define a list of IT skills you want to add
            List<Skill> itSkills = Arrays.asList(
                    new Skill(null, "Java"),
                    new Skill(null, "Python"),
                    new Skill(null, "JavaScript"),
                    new Skill(null, "TypeScript"),
                    new Skill(null, "C++"),
                    new Skill(null, "C#"),
                    new Skill(null, "PHP"),
                    new Skill(null, "Ruby"),
                    new Skill(null, "Swift"),
                    new Skill(null, "Kotlin"),
                    new Skill(null, "Go"),
                    new Skill(null, "Rust"),
                    new Skill(null, "Dart"),
                    new Skill(null, "R"),
                    new Skill(null, "MATLAB"),
                    new Skill(null, "HTML"),
                    new Skill(null, "CSS"),
                    new Skill(null, "SASS/SCSS"),
                    new Skill(null, "Less"),
                    new Skill(null, "React"),
                    new Skill(null, "Angular"),
                    new Skill(null, "Vue.js"),
                    new Skill(null, "jQuery"),
                    new Skill(null, "Node.js"),
                    new Skill(null, "Express.js"),
                    new Skill(null, "Django"));

            skillRepository.saveAll(itSkills);
        }
    }
}
