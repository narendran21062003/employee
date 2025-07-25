package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.EmployeeBasicDto;
import com.example.Employeedetails.dto.SkillDto;
import com.example.Employeedetails.dto.SkillWithEmployeesDto;
import com.example.Employeedetails.exception.SkillNotFoundException;
import com.example.Employeedetails.model.Skill;
import com.example.Employeedetails.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDto addSkill(SkillDto dto) {
        Skill skill = new Skill();
        skill.setName(dto.getName());
        return convertToDto(skillRepository.save(skill));
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found"));
        return convertToDto(skill);
    }
    @Override
    public SkillWithEmployeesDto getSkillWithEmployees(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        SkillWithEmployeesDto dto = new SkillWithEmployeesDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());

        List<EmployeeBasicDto> employees = skill.getEmployees().stream()
                .map(emp -> {
                    EmployeeBasicDto basicDto = new EmployeeBasicDto();
                    basicDto.setEmpname(emp.getEmpname());
                    basicDto.setEmailid(emp.getEmailid());
                    basicDto.setPhone_no(emp.getPhoneNo());
                    return basicDto;
                }).toList();

        dto.setEmployees(employees);
        return dto;
    }


    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillDto convertToDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }
}