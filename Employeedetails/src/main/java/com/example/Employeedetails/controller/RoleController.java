package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.EmployeeDto;
import com.example.Employeedetails.dto.RoleDto;
import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.model.Role;
import com.example.Employeedetails.repository.RoleRepository;
import com.example.Employeedetails.service.EmployeeServiceInterface;
import com.example.Employeedetails.service.RoleServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleServiceInterface roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/{id}/employee")
    public EmployeeDto getEmployeeByRole(@PathVariable Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (role.getEmployee() == null) {
            throw new RuntimeException("No employee is assigned to this role.");
        }

        Employee employee = role.getEmployee();
        EmployeeDto dto = new EmployeeDto();
        dto.setEmpname(employee.getEmpname());  // Changed to match DTO field name
        dto.setEmailid(employee.getEmailid());
        dto.setPhone_no(employee.getPhone_no());
        dto.setPassword(employee.getPassword()); // Note: Consider security implications
        // Set other fields as needed
        return dto;
    }

    @PostMapping("/add")
    public RoleDto createRole(@Valid @RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping("/all")
    public List<RoleDto> getAllRoles() {
        return roleService.getAll();
    }

    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        return roleService.update(id, roleDto);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return "Role with ID " + id + " deleted successfully.";
    }
}
