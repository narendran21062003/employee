package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.RoleDto;
import java.util.List;

public interface RoleServiceInterface {
    RoleDto create(RoleDto roleDto);
    RoleDto getById(Long id);
    List<RoleDto> getAll();
    RoleDto update(Long id, RoleDto roleDto);
    void delete(Long id);
}
