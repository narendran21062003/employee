package com.example.Employeedetails.mapper;

import com.example.Employeedetails.Employee;
import com.example.Employeedetails.dto.EmployeeDto;

// This is a manual mapper class for converting between Employee Entity and EmployeeDto
public class EmployeeMapper {

    // 🔄 Convert DTO → Entity
    public static Employee toEntity(EmployeeDto dto) {
        Employee emp = new Employee();                        // Create a new Employee entity object
        emp.setEmpname(dto.getEmpname());                    // Set name from DTO to entity
        emp.setEmailid(dto.getEmailid());                    // Set email from DTO to entity
        emp.setPhone_no(dto.getPhone_no());                  // Set phone number from DTO to entity
        emp.setPassword(dto.getPassword());                  // Set password from DTO to entity
        return emp;                                          // Return the populated entity object
    }

    // 🔄 Convert Entity → DTO (for response to the client)
    public static EmployeeDto toDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();                 // Create a new DTO object
        dto.setEmpname(emp.getEmpname());                    // Set name from entity to DTO
        dto.setEmailid(emp.getEmailid());                    // Set email from entity to DTO
        dto.setPhone_no(emp.getPhone_no());                  // Set phone number from entity to DTO

        // ❌ Security Best Practice: Do NOT include password in the response DTO
        // dto.setPassword(emp.getPassword());               // <-- This line is commented to hide password

        return dto;                                          // Return the DTO object (safe to send to client)
    }
}
