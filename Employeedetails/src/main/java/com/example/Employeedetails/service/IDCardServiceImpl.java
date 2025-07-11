package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.IDCardDto;
import com.example.Employeedetails.mapper.IDCardMapper;
import com.example.Employeedetails.model.Employee;
import com.example.Employeedetails.model.IDCard;
import com.example.Employeedetails.repository.EmployeeRepository;
import com.example.Employeedetails.repository.IDCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDCardServiceImpl implements IDCardServiceInterface {

    @Autowired
    private IDCardRepository idCardRepository;

    @Autowired
    private EmployeeRepository employeeRepository; // ✅ Used to fetch employee and link it

    // ✅ Fetch IDCard with employee details by ID
    @Override
    public IDCardDto getById(Long id) {
        IDCard card = idCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IDCard not found with ID: " + id));

        if (card.getEmployee() == null) {
            throw new RuntimeException("IDCard exists but no employee is linked.");
        }

        return IDCardMapper.toDto(card); // ✅ Convert to DTO and return
    }

    // ✅ Create a new IDCard and link to employee
    @Override
    public IDCardDto create(IDCardDto dto, Long empId) {
        // 🛡️ Ensure employee exists
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Cannot create IDCard: Employee ID " + empId + " not found."));

        // ✅ Convert DTO to Entity
        IDCard card = IDCardMapper.toEntity(dto);

        // 🔁 Set the employee to establish one-to-one link
        card.setEmployee(employee);

        // 💾 Save and return
        IDCard savedCard = idCardRepository.save(card);
        return IDCardMapper.toDto(savedCard);
    }
}
