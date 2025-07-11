package com.example.Employeedetails.service;

import com.example.Employeedetails.model.Department;
import java.util.List;

public interface DepartmentServiceInterface {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
    Department getDepartmentWithEmployees(Long id);
    Department getById(Long id);
}
