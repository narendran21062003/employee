package com.example.Employeedetails.repository;

import com.example.Employeedetails.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}