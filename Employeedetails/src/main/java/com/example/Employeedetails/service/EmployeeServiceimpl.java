package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.*;
import com.example.Employeedetails.exception.EmployeeNotFoundException;
import com.example.Employeedetails.model.*;
import com.example.Employeedetails.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return convertToDto(emp);
    }

    @Override
    public EmployeeDto create(EmployeeDto empDto) {
        Employee emp = new Employee();
        emp.setEmpname(empDto.getEmpname());
        emp.setEmailid(empDto.getEmailid());
        emp.setPhoneNo(empDto.getPhone_no());
        emp.setPassword(empDto.getPassword());

        if (empDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(empDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            emp.setDepartment(department);
        }

        if (empDto.getSkills() != null) {
            Set<Skill> skills = empDto.getSkills().stream()
                    .map(skillDto -> skillRepository.findById(skillDto.getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found")))
                    .collect(Collectors.toSet());
            emp.setSkills(skills);
        }
        if (empDto.getRole() != null && empDto.getRole().getId() != null) {
            Role role = roleRepository.findById(empDto.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            emp.setRole(role);
        }


        Employee savedEmp = employeeRepository.save(emp);
        return convertToDto(savedEmp);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto empDto) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        emp.setEmpname(empDto.getEmpname());
        emp.setEmailid(empDto.getEmailid());
        emp.setPhoneNo(empDto.getPhone_no());

        if (empDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(empDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            emp.setDepartment(department);
        }

        if (empDto.getSkills() != null) {
            Set<Skill> skills = empDto.getSkills().stream()
                    .map(skillDto -> skillRepository.findById(skillDto.getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found")))
                    .collect(Collectors.toSet());
            emp.setSkills(skills);
        }
        if (empDto.getRole() != null && empDto.getRole().getId() != null) {
            Role role = roleRepository.findById(empDto.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            emp.setRole(role);
        }


        return convertToDto(employeeRepository.save(emp));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDto convertToDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmpname(emp.getEmpname());
        dto.setEmailid(emp.getEmailid());
        dto.setPhone_no(emp.getPhoneNo());
        dto.setPassword(null); // Never return password

        if (emp.getDepartment() != null) {
            DepartmentDto deptDto = new DepartmentDto();
            deptDto.setId(emp.getDepartment().getId());
            deptDto.setName(emp.getDepartment().getName());
            dto.setDepartment(deptDto);
            dto.setDepartmentId(emp.getDepartment().getId());
        }

        if (emp.getSkills() != null) {
            Set<SkillDto> skillDtos = emp.getSkills().stream()
                    .map(skill -> {
                        SkillDto skillDto = new SkillDto();
                        skillDto.setId(skill.getId());
                        skillDto.setName(skill.getName());
                        return skillDto;
                    })
                    .collect(Collectors.toSet());
            dto.setSkills(skillDtos);
        }
        if (emp.getRole() != null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(emp.getRole().getId());
            roleDto.setRolename(emp.getRole().getRolename());
            dto.setRole(roleDto);
        }


        return dto;
    }
}