package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.EmployeeDto;
import java.util.List;

public interface EmployeeServiceInterface {

    List<EmployeeDto> getAll();                // Return list of DTOs
    EmployeeDto getById(Long id);              // Return DTO by ID
    EmployeeDto create(EmployeeDto empDto);    // Accept and return DTO
    EmployeeDto update(Long id, EmployeeDto empDto); // Update and return DTO
    void delete(Long id);                      // Just delete, no return
}
