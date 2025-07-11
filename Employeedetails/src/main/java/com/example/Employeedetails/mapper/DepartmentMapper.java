package com.example.Employeedetails.mapper;

import com.example.Employeedetails.dto.DepartmentDto;
import com.example.Employeedetails.dto.EmployeeBasicDto;
import com.example.Employeedetails.model.Department;
import com.example.Employeedetails.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentDto toDtoWithEmployees(Department dept) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());

        if (dept.getEmployees() != null) {
            List<EmployeeBasicDto> employeeDtos = dept.getEmployees().stream()
                    .map(employee -> {
                        EmployeeBasicDto basicDto = new EmployeeBasicDto();
                        basicDto.setEmpname(employee.getEmpname());
                        basicDto.setEmailid(employee.getEmailid());
                        basicDto.setPhone_no(employee.getPhone_no());
                        return basicDto;
                    })
                    .collect(Collectors.toList());

            dto.setEmployees(employeeDtos);
        }

        return dto;
    }
}