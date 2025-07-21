package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.EmployeeDto;
import com.example.Employeedetails.dto.RoleDto;
import com.example.Employeedetails.service.EmployeeServiceInterface;
import jakarta.validation.Valid; // ✅ Enables validation of incoming DTOs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceInterface employeeService;

    // Get all employees
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAll();
    }

    // Create new employee
    @PostMapping
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto empDto) {
        return employeeService.create(empDto);
    }

    // Other existing methods...


    // 🔹 Update an existing employee (PUT /api/employees/{id})
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto updatedEmpDto) {
        // ✅ Only name, email, phone are updated (password skipped for now)
        return employeeService.update(id, updatedEmpDto);
    }
    @GetMapping("/{id}/role")
    public RoleDto getEmployeeRole(@PathVariable Long id) {
        EmployeeDto emp = employeeService.getById(id);
        return emp.getRole();
    }

    // 🔹 Delete an employee (DELETE /api/employees/{id})
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "Employee with ID " + id + " deleted successfully.";
    }
}
/*| Concept / Annotation                | Explanation                                                |
| ----------------------------------- | ---------------------------------------------------------- |
| `@RestController`                   | Tells Spring Boot this class will handle API requests      |
| `@RequestMapping("/api/employees")` | Base path for all methods inside controller                |
| `@Valid`                            | Triggers validation rules written in `EmployeeDto`         |
| `@RequestBody`                      | Automatically maps incoming JSON to a Java object          |
| `@PathVariable`                     | Captures values from the URL path like `/employees/{id}`   |
| `EmployeeDto`                       | Used in requests and responses — keeps it secure and clean |
| `ServiceInterface`                  | Keeps controller logic separate from business logic        |
 */