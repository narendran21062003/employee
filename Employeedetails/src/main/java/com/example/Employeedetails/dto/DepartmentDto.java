package com.example.Employeedetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name must not be blank")
    private String name;

    private List<EmployeeBasicDto> employees;
}
