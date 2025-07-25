// Declares the package location of this class
package com.example.Employeedetails.repository;

// Importing JpaRepository for CRUD operations
import com.example.Employeedetails.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Creating a Spring Data JPA repository interface for Employee entity
// JpaRepository provides built-in methods like save(), findAll(), deleteById(), etc.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailid(String emailid);
    boolean existsByEmailid(String emailid); // Corrected method name No need to write any method here unless you want custom queries
}
 //JPA is a Java specification for ORM that allows mapping Java objects to database tables.
// In Spring Boot, JpaRepository is used to simplify data access by providing built-in CRUD methods without writing SQL or boilerplate JDBC code.