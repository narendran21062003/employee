package com.example.Employeedetails.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IDCardDto {

    private String cardNumber;
    private LocalDate issuedDate;


}
