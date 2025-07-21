package com.example.Employeedetails.dto;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.Set;

@Data  // Lombok: Automatically generates getters, setters, toString, equals, and hashCode methods
public class EmployeeDto {

    @NotBlank(message = "Employee name is required")
    // Ensures empname is not null, empty, or just whitespace
    private String empname;

    @Email(message = "Please enter a valid email address")
    // Validates the email format (e.g., example@gmail.com)
    @NotBlank(message = "Email is required")
    // Ensures emailid is not null or empty
    private String emailid;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    // Ensures phone_no has exactly 10 numeric digits
    @NotBlank(message = "Phone number is required")
    // Ensures phone_no is not null or empty
    private String phone_no;

    @Size(min = 6, message = "Password must be at least 6 characters")
    // Validates minimum length for password
    @NotBlank(message = "Password is required")
    // Ensures password is not null or empty
    private String password;



    private DepartmentDto department;

    // ✅ ADD: Accept departmentId for easier API usage
    private Long departmentId;

    private Set<SkillDto> skills;

    private RoleDto role;

}