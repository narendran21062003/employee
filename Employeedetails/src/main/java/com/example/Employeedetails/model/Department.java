package com.example.Employeedetails.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity // ✅ Marks this as a JPA Entity (table)
@Data   // ✅ Lombok: generates getters, setters, etc.
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // ✅ Department name (must be unique)

    // ✅ One Department → Many Employees
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
