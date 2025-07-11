package com.example.Employeedetails.mapper;

import com.example.Employeedetails.dto.SkillDto;
import com.example.Employeedetails.model.Skill;

public class SkillMapper {

    // Convert Entity → DTO
    public static SkillDto toDto(Skill skill) {
        if (skill == null) return null;

        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }

    // Convert DTO → Entity
    public static Skill toEntity(SkillDto dto) {
        if (dto == null) return null;

        Skill skill = new Skill();
        skill.setId(dto.getId());   // ⚠️ Set ID only if you're updating or attaching existing ones
        skill.setName(dto.getName());
        return skill;
    }
}
