package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.SkillDto;
import com.example.Employeedetails.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // 🔹 Add a new skill (POST)
    @PostMapping("/add")
    public SkillDto addSkill(@Valid @RequestBody SkillDto skillDto) {
        return skillService.addSkill(skillDto);
    }

    // 🔹 Get all skills (GET)
    @GetMapping("/all")
    public List<SkillDto> getAllSkills() {
        return skillService.getAllSkills();
    }

    // 🔹 Get single skill by ID (GET)
    @GetMapping("/{id}")
    public SkillDto getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    // 🔹 Delete a skill by ID (DELETE)
    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return "Skill with ID " + id + " deleted successfully.";
    }
}
