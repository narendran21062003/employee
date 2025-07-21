package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.DepartmentDto;
import com.example.Employeedetails.model.Department;

import java.util.List;

public interface DepartmentServiceInterface {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Department getById(Long id);
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
    DepartmentDto getDepartmentWithEmployees(Long id);  // Changed return type
}