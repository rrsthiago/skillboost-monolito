package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.model.Professional;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ProfessionalMapper {

    public Professional dtoToModel(ProfessionalDto professionalDto) {
        return Professional.builder()
                .id(professionalDto.getId())
                .name(professionalDto.getName())
                .email(professionalDto.getEmail())
                .registerNumber(professionalDto.getRegisterNumber())
                .build();
    }

    public ProfessionalDto modelToDto(Professional professional) {
        return ProfessionalDto.builder()
                .id(professional.getId())
                .name(professional.getName())
                .email(professional.getEmail())
                .registerNumber(professional.getRegisterNumber())
                .build();
    }

    public Professional idDtoToModel(BigInteger professionalId) {
        return Professional.builder()
                .id(professionalId)
                .build();
    }

}
