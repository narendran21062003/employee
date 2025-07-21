package com.example.Employeedetails.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillDto {
    private Long id;

    @NotBlank(message = "Skill name cannot be blank")
    private String name;
}
