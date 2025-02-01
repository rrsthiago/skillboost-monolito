package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.model.Professional;
import mock.ProfessionalMock;
import mock.UserMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessionalMapperTest {

    @InjectMocks
    private ProfessionalMapper professionalMapper;

    @Mock
    private UserMapper userMapper;

    @Test
    void testDtoToModel() {
        ProfessionalDto professionalDto = ProfessionalMock.getProfessionalDto();
        when(userMapper.idDtoToModel(professionalDto.getUserId())).thenReturn(UserMock.getUser());

        Professional professional = professionalMapper.dtoToModel(professionalDto);

        assertEquals(professionalDto.getId(), professional.getId());
        assertEquals(professionalDto.getName(), professional.getName());
        assertEquals(professionalDto.getEmail(), professional.getEmail());
        assertEquals(professionalDto.getRegisterNumber(), professional.getRegisterNumber());
    }

    @Test
    void testModelToDto() {
        Professional professional = ProfessionalMock.getProfessional();
        when(userMapper.modelToDto(professional.getUser())).thenReturn(UserMock.getUserDto());

        ProfessionalDto professionalDto = professionalMapper.modelToDto(professional);

        assertEquals(professional.getId(), professionalDto.getId());
        assertEquals(professional.getName(), professionalDto.getName());
        assertEquals(professional.getEmail(), professionalDto.getEmail());
        assertEquals(professional.getRegisterNumber(), professionalDto.getRegisterNumber());
    }

    @Test
    void testIdDtoToModel() {
        BigInteger professionalId = BigInteger.ONE;

        Professional professional = professionalMapper.idDtoToModel(professionalId);

        assertEquals(professionalId, professional.getId());
    }

}
