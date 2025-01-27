package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.model.Professional;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {

    public Professional dtoToModel(ProfessionalDto professionalDto) {
        return Professional.builder()
                .id(professionalDto.getId())
                .name(professionalDto.getName())
                .build();
    }

    public ProfessionalDto modelToDto(Professional professional) {
        return ProfessionalDto.builder()
                .id(professional.getId())
                .name(professional.getName())
                .build();
    }

}
