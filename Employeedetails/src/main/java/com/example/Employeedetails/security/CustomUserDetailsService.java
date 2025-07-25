package com.example.Employeedetails.security;

import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmailid(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                employee.getEmailid(),
                employee.getPassword(),
                Collections.emptyList());
    }
}