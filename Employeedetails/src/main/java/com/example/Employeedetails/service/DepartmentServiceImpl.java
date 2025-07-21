package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.DepartmentDto;
import com.example.Employeedetails.dto.EmployeeBasicDto;
import com.example.Employeedetails.model.Department;
import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentServiceInterface {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existingDept = getById(id);
        existingDept.setName(department.getName());
        return departmentRepository.save(existingDept);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDto getDepartmentWithEmployees(Long id) {
        Department dept = getById(id);
        DepartmentDto dto = new DepartmentDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());

        if (dept.getEmployees() != null) {
            List<EmployeeBasicDto> employeeDtos = dept.getEmployees().stream()
                    .map(emp -> {
                        EmployeeBasicDto basicDto = new EmployeeBasicDto();
                        basicDto.setEmpname(emp.getEmpname());
                        basicDto.setEmailid(emp.getEmailid());
                        basicDto.setPhone_no(emp.getPhone_no());
                        return basicDto;
                    })
                    .collect(Collectors.toList());
            dto.setEmployees(employeeDtos);
        }

        return dto;

    }
}