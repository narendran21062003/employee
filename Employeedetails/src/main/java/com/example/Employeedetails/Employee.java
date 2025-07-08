package com.example.Employeedetails;

import jakarta.persistence.*;
import lombok.Data;

@Entity  // Marks this class as a JPA entity, which maps to a table in the database.
@Data    // Lombok annotation that generates getters, setters, toString, equals, and hashCode.

public class Employee {

    @Id  // Specifies the primary key for the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-generates the primary key value using the database's identity column (auto-increment).
    private long id;

    @Column(nullable = false)
    // This field is required (NOT NULL in the database). Represents the employee's name.
    private String empname;

    @Column(nullable = false, unique = true)
    // Email must be unique and not null in the database.
    private String emailid;

    @Column(nullable = false, unique = true)
    // Phone number must be unique and not null.
    private String phone_no;

    @Column(nullable = false)
    // The Password field must not be null.
    private String password;
}

/*
Summary of annotations:
-----------------------

@Entity              = Makes the class a table.

@Id + @GeneratedValue = Auto-increment primary key.

@Column(nullable = false) = Mandatory fields.

unique = true = No duplicates allowed.

Lombok's @Data      = Saves time by auto-generating getters, setters, and other common methods.
*/
