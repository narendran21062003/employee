package com.example.Employeedetails.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String empname;

    @Column(nullable = false, unique = true)
    private String emailid;

    @Column(nullable = false, unique = true)
    private String phoneNo; // Changed from phone_no to phoneNo

    // Update getter and setter names to match:
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "department_id", insertable = false, updatable = false)
    private Long departmentId;

    @ManyToMany
    @JoinTable(
            name = "employee_skills",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonManagedReference
    private Set<Skill> skills = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonManagedReference
    private Role role;

    @PrePersist
    @PreUpdate
    public void hashPassword() {
        if (this.password != null && !this.password.startsWith("$2a$")) {
            this.password = new BCryptPasswordEncoder().encode(this.password);
        }
    }
}