package com.example.Employeedetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long id;
    private String name;

    private List<EmployeeBasicDto> employees;
}
