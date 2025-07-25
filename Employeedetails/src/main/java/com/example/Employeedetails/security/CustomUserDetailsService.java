package com.example.Employeedetails.security;

import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        // Use the simplified build method
        return UserDetailsImpl.build(employee);

        // Alternatively, you could use:
        // return new UserDetailsImpl(
        //        employee.getEmailid(),
        //        employee.getPassword(),
        //        employee.getId()
        // );
    }
}