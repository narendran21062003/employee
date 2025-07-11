package com.example.Employeedetails.mapper;

import com.example.Employeedetails.dto.IDCardDto;
import com.example.Employeedetails.model.IDCard;

public class IDCardMapper {

    public static IDCardDto toDto(IDCard card) {
        if (card == null) return null;

        IDCardDto dto = new IDCardDto();
        dto.setCardNumber(card.getCardNumber());
        dto.setIssuedDate(card.getIssuedDate());

        // ✅ No employee mapping - this prevents "employee": null
        return dto;
    }

    public static IDCard toEntity(IDCardDto dto) {
        if (dto == null) return null;

        IDCard card = new IDCard();
        card.setCardNumber(dto.getCardNumber());
        card.setIssuedDate(dto.getIssuedDate());
        return card;
    }
}