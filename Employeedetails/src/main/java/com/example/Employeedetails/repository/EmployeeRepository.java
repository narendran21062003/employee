// Declares the package location of this class
package com.example.Employeedetails.repository;

// Importing JpaRepository for CRUD operations
import com.example.Employeedetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Creating a Spring Data JPA repository interface for Employee entity
// JpaRepository provides built-in methods like save(), findAll(), deleteById(), etc.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // No need to write any method here unless you want custom queries
}
 //JPA is a Java specification for ORM that allows mapping Java objects to database tables.
// In Spring Boot, JpaRepository is used to simplify data access by providing built-in CRUD methods without writing SQL or boilerplate JDBC code.