package com.example.Employeedetails.mapper;

import com.example.Employeedetails.dto.*;
import com.example.Employeedetails.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee emp) {
        if (emp == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setEmpname(emp.getEmpname());
        dto.setEmailid(emp.getEmailid());
        dto.setPhone_no(emp.getPhone_no());
        dto.setPassword(null); // ✅ Always exclude password from response

        // ✅ Map department information
        if (emp.getDepartment() != null) {
            DepartmentDto deptDto = new DepartmentDto();
            deptDto.setId(emp.getDepartment().getId());
            deptDto.setName(emp.getDepartment().getName());
            dto.setDepartment(deptDto);
        }

        // ✅ Map ID card information (without employee back-reference)
        if (emp.getIdCard() != null) {
            IDCardDto cardDto = new IDCardDto();
            cardDto.setCardNumber(emp.getIdCard().getCardNumber());
            cardDto.setIssuedDate(emp.getIdCard().getIssuedDate());
            dto.setIdCard(cardDto);
        }
        if (emp.getSkills() != null) {
            Set<SkillDto> skillDto = emp.getSkills().stream().map(skill -> {
                SkillDto skilldto = new SkillDto();
                skilldto.setId(skill.getId());
                skilldto.setName(skill.getName());
                return skilldto;
            }).collect(Collectors.toSet());
            dto.setSkills(skillDto);
        }

        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee emp = new Employee();
        emp.setEmpname(dto.getEmpname());
        emp.setEmailid(dto.getEmailid());
        emp.setPhone_no(dto.getPhone_no());
        emp.setPassword(dto.getPassword());

        if (dto.getSkills() != null) {
            Set<Skill> skills = dto.getSkills().stream().map(skillDto -> {
                Skill skill = new Skill();
                skill.setId(skillDto.getId());
                return skill;
            }).collect(Collectors.toSet());
            emp.setSkills(skills);
        }


        return emp;
    }
}