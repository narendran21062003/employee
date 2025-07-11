package com.example.Employeedetails.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate; // ✅ Import added

@Entity
@Data
public class IDCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String cardNumber;

    private LocalDate issuedDate; // ✅ Correct type

    // 👇 One-to-One back link to Employee
    @OneToOne(mappedBy = "idCard")
    private Employee employee;
}
