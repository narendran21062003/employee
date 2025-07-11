package com.example.Employeedetails.service;

import com.example.Employeedetails.dto.SkillDto;
import com.example.Employeedetails.exception.SkillNotFoundException;
import com.example.Employeedetails.mapper.SkillMapper;
import com.example.Employeedetails.model.Skill;
import com.example.Employeedetails.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDto addSkill(SkillDto dto) {
        Skill skill = SkillMapper.toEntity(dto);
        return SkillMapper.toDto(skillRepository.save(skill));
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(SkillMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new SkillNotFoundException("Skill not found with ID: " + id));;
        return SkillMapper.toDto(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
