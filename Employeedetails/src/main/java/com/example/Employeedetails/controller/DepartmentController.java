package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.DepartmentDto;
import com.example.Employeedetails.mapper.DepartmentMapper;
import com.example.Employeedetails.model.Department;
import com.example.Employeedetails.service.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceInterface departmentService;

    // ✅ Create a new department
    @PostMapping("/add")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    // ✅ Get all departments
    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // ✅ Get department with employees
    @GetMapping("/{id}/employees")
    public DepartmentDto getDepartmentWithEmployees(@PathVariable Long id) {
        Department dept = departmentService.getById(id);
        return DepartmentMapper.toDtoWithEmployees(dept);
    }

    // ✅ Update a department by ID (Fixed URL mapping)
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDept) {
        return departmentService.updateDepartment(id, updatedDept);
    }

    // ✅ Delete a department by ID
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department with ID " + id + " deleted successfully.";
    }
}