package com.example.Employeedetails.service;
import com.example.Employeedetails.exception.EmployeeNotFoundException;

// Importing the entity (database table) class
import com.example.Employeedetails.Employee;

// Importing the DTO class (used to transfer data between layers securely)
import com.example.Employeedetails.dto.EmployeeDto;

// Mapper class to convert between DTO and Entity
import com.example.Employeedetails.mapper.EmployeeMapper;

// Repository interface to interact with the database
import com.example.Employeedetails.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired; // For automatic dependency injection
import org.springframework.stereotype.Service;              // Marks this class as a Service layer

import java.util.List;
import java.util.stream.Collectors;

@Service // 🔹 Marks this class as a Spring Service Component (business logic layer)
public class EmployeeServiceimpl implements EmployeeServiceInterface {

    @Autowired // 🔹 Injects the EmployeeRepository instance automatically (no need to use `new`)
    private EmployeeRepository employeeRepository;

    // 🔹 Get All Employees from the database and return as a list of DTOs
    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepository.findAll(); // ⏩ Fetches all employee records
        return employees.stream()                               // ⏩ Converts the list to a stream
                .map(EmployeeMapper::toDto)                     // ⏩ Maps each Employee → EmployeeDto
                .collect(Collectors.toList());                  // ⏩ Collects results into a List
    }

    // 🔹 Get a single employee by ID and return it as DTO
    @Override
    public EmployeeDto getById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        // ⛔ If not found, throws custom exception

        return EmployeeMapper.toDto(emp);
        // ✅ Converts the entity to DTO before sending back to client
    }

    // 🔹 Create (Insert) a new employee into the database
    @Override
    public EmployeeDto create(EmployeeDto empDto) {
        Employee emp = EmployeeMapper.toEntity(empDto);       // ✅ Convert DTO → Entity
        Employee savedEmp = employeeRepository.save(emp);     // 💾 Save the entity to the DB
        return EmployeeMapper.toDto(savedEmp);                // ✅ Return DTO version of saved record
    }

    // 🔹 Update existing employee details (name, email, phone)
    @Override
    public EmployeeDto update(Long id, EmployeeDto empDto) {
        Employee existing = employeeRepository.findById(id)         // 🔍 Find existing record
                .orElseThrow(() -> new RuntimeException("Employee not found")); // ⛔ Error if not found

        // ⏬ Update only the allowed fields from DTO to Entity
        existing.setEmpname(empDto.getEmpname());       // Update name
        existing.setEmailid(empDto.getEmailid());       // Update email
        existing.setPhone_no(empDto.getPhone_no());     // Update phone

        // ⚠️ Not updating password here for security (optional to include conditionally)

        Employee updatedEmp = employeeRepository.save(existing);   // 💾 Save updated entity
        return EmployeeMapper.toDto(updatedEmp);                   // ✅ Return updated DTO
    }

    // 🔹 Delete an employee by ID
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);   // ❌ Deletes the record if it exists
    }
}
/*| Keyword/Annotation               | Meaning                                             |
| -------------------------------- | --------------------------------------------------- |
| `@Service`                       | Marks this class as a service layer component       |
| `@Autowired`                     | Automatically injects the required bean             |
| `employeeRepository`             | Interface that talks to the database                |
| `Mapper`                         | Converts between DTO and Entity                     |
| `DTO`                            | Data Transfer Object — only carries data, not logic |
| `Entity`                         | Java class mapped to a DB table                     |
| `findById`, `save`               | Built-in Spring Data JPA methods                    |
| `stream().map(...).collect(...)` | Converts a list of entities into a list of DTOs     |
 */