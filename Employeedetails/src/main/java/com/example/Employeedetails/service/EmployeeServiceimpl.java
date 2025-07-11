package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.EmployeeDto;
import com.example.Employeedetails.exception.EmployeeNotFoundException;
import com.example.Employeedetails.mapper.EmployeeMapper;
import com.example.Employeedetails.model.Department;
import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.model.IDCard;
import com.example.Employeedetails.model.Skill;
import com.example.Employeedetails.repository.DepartmentRepository;
import com.example.Employeedetails.repository.EmployeeRepository;
import com.example.Employeedetails.repository.IDCardRepository;
import com.example.Employeedetails.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private IDCardRepository idCardRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return EmployeeMapper.toDto(emp);
    }

    @Override
    public EmployeeDto create(EmployeeDto empDto) {
        Employee emp = EmployeeMapper.toEntity(empDto);

        // ✅ Handle department
        Long deptId;
        if (empDto.getDepartmentId() != null) {
            deptId = empDto.getDepartmentId();
        } else if (empDto.getDepartment() != null && empDto.getDepartment().getId() != null) {
            deptId = empDto.getDepartment().getId();
        } else {
            deptId = null;
        }

        if (deptId != null) {
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));
            emp.setDepartment(department);
        }

        // ✅ Handle ID card
        if (empDto.getIdCard() != null) {
            IDCard card = new IDCard();
            card.setCardNumber(empDto.getIdCard().getCardNumber());
            card.setIssuedDate(empDto.getIdCard().getIssuedDate());
            card.setEmployee(emp);
            emp.setIdCard(card);
        }

        // ✅ Handle skills
        if (empDto.getSkills() != null && !empDto.getSkills().isEmpty()) {
            Set<Skill> skillSet = empDto.getSkills().stream()
                    .map(skillDto -> skillRepository.findById(skillDto.getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + skillDto.getId())))
                    .collect(Collectors.toSet());
            emp.setSkills(skillSet);
        }

        Employee savedEmp = employeeRepository.save(emp);
        return EmployeeMapper.toDto(savedEmp);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto empDto) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        emp.setEmpname(empDto.getEmpname());
        emp.setEmailid(empDto.getEmailid());
        emp.setPhone_no(empDto.getPhone_no());

        // ✅ Update department
        Long deptId;
        if (empDto.getDepartmentId() != null) {
            deptId = empDto.getDepartmentId();
        } else if (empDto.getDepartment() != null && empDto.getDepartment().getId() != null) {
            deptId = empDto.getDepartment().getId();
        } else {
            deptId = null;
        }

        if (deptId != null) {
            Department department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));
            emp.setDepartment(department);
        }

        // ✅ Update skills
        if (empDto.getSkills() != null) {
            Set<Skill> skillSet = empDto.getSkills().stream()
                    .map(skillDto -> skillRepository.findById(skillDto.getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + skillDto.getId())))
                    .collect(Collectors.toSet());
            emp.setSkills(skillSet);
        }

        return EmployeeMapper.toDto(employeeRepository.save(emp));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
