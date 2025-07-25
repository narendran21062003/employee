package com.example.Employeedetails.security;

import com.example.Employeedetails.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final Long employeeId;
    private final Collection<? extends GrantedAuthority> authorities;

    // Public constructor with authorities
    public UserDetailsImpl(String username, String password, Long employeeId,
                           Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.authorities = authorities;
    }

    // Public constructor without authorities (uses empty list)
    public UserDetailsImpl(String username, String password, Long employeeId) {
        this(username, password, employeeId, Collections.emptyList());
    }

    public static UserDetailsImpl build(Employee employee) {
        return new UserDetailsImpl(
                employee.getEmailid(),
                employee.getPassword(),
                employee.getId()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}