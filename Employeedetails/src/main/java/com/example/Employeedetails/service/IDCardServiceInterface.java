package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.IDCardDto;

public interface IDCardServiceInterface {
    IDCardDto getById(Long id);

    // ✅ Create a new IDCard and link to employee
    IDCardDto create(IDCardDto dto, Long empId);
}
