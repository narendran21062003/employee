package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.DepartmentDto;
import com.example.Employeedetails.model.Department;
import com.example.Employeedetails.service.DepartmentServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceInterface departmentService;

    // ✅ Create a new department (simplified content type)
    @PostMapping("/add")
    public Department createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        return departmentService.createDepartment(department);
    }

    // ✅ Get all departments
    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // ✅ Get department with employees
    // DepartmentController.java
    @GetMapping("/{id}/employees")
    public DepartmentDto getDepartmentWithEmployees(@PathVariable Long id) {
        return departmentService.getDepartmentWithEmployees(id);
    }

    // ✅ Update a department by ID
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDto updatedDeptDto) {
        Department department = new Department();
        department.setName(updatedDeptDto.getName());
        return departmentService.updateDepartment(id, department);
    }

    // ✅ Delete a department by ID
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department with ID " + id + " deleted successfully.";
    }
}