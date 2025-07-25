package com.example.Employeedetails.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phone;

    @NotBlank
    @Size(min = 6)
    private String password;
}