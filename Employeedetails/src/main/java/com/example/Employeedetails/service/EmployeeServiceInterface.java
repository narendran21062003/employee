package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.EmployeeDto;
import java.util.List;

public interface EmployeeServiceInterface {
    List<EmployeeDto> getAll();
    EmployeeDto getById(Long id);
    EmployeeDto create(EmployeeDto empDto);
    EmployeeDto update(Long id, EmployeeDto empDto);
    void delete(Long id);
}