package com.example.Employeedetails.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name must not be blank")
    private String name;

    private List<EmployeeBasicDto> employees;
}
