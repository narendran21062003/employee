package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.SkillDto;
import com.example.Employeedetails.dto.SkillWithEmployeesDto;

import java.util.List;

public interface SkillService {
    SkillDto addSkill(SkillDto dto);
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
    void deleteSkill(Long id);

    SkillWithEmployeesDto getSkillWithEmployees(Long id);
}
