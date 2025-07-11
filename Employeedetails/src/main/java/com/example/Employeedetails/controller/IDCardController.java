package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.IDCardDto;
import com.example.Employeedetails.service.IDCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/idcards")
public class IDCardController {

    @Autowired
    private IDCardServiceInterface idCardService;

    // ✅ GET ID card with its employee details (keep your original approach)
    @GetMapping("/{id}")
    public IDCardDto getIdCardById(@PathVariable Long id) {
        return idCardService.getById(id);
    }
}