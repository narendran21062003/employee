package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.EmployeeDto;
import com.example.Employeedetails.service.EmployeeServiceInterface;
import jakarta.validation.Valid; // ✅ Enables validation of incoming DTOs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // ✅ Makes this class handle HTTP requests like GET, POST, etc.
@RequestMapping("/api/employees") // ✅ Base URL path for this controller
public class EmployeeController {

    @Autowired // ✅ Injects the service layer into the controller
    private EmployeeServiceInterface employeeService;

    // 🔹 Get all employees (GET /api/employees/all)
    @GetMapping("/all")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAll(); // ✅ Service returns List<EmployeeDto>
    }

    // 🔹 Get a single employee by ID (GET /api/employees/{id})
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id); // ✅ Returns DTO (password excluded)
    }

    // 🔹 Add a new employee (POST /api/employees/add)
    @PostMapping("/add")
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto empDto) {
        // ✅ @Valid triggers validation based on annotations in EmployeeDto
        return employeeService.create(empDto); // ✅ Clean creation with mapped entity
    }

    // 🔹 Update an existing employee (PUT /api/employees/{id})
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto updatedEmpDto) {
        // ✅ Only name, email, phone are updated (password skipped for now)
        return employeeService.update(id, updatedEmpDto);
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