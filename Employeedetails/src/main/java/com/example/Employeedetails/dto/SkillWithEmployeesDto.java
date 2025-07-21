package com.example.Employeedetails.dto;

import lombok.Data;

import java.util.List;
@Data
public class SkillWithEmployeesDto {
    private Long id;
    private String name;
    private List<EmployeeBasicDto> employees;
}
