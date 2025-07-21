package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.RoleDto;
import com.example.Employeedetails.model.Role;
import com.example.Employeedetails.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleServiceInterface {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = new Role();
        role.setRolename(roleDto.getRolename());
        return convertToDto(roleRepository.save(role));
    }

    @Override
    public RoleDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return convertToDto(role);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRolename(roleDto.getRolename());
        return convertToDto(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    private RoleDto convertToDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setRolename(role.getRolename());
        return dto;
    }
}