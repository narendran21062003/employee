package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.RegisterDto;
import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(EmployeeRepository employeeRepository,
                       PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterDto registerDto) {
        // Check if email already exists
        if (employeeRepository.existsByEmailid(registerDto.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create new employee
        Employee employee = new Employee();

        employee.setEmpname(registerDto.getName());
        employee.setEmailid(registerDto.getEmail());
        employee.setPhoneNo(registerDto.getPhone());
        employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        employeeRepository.save(employee);

        return "User registered successfully!";
    }
}