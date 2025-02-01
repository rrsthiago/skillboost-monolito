package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.dto.CourseActivityTypeDto;
import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.model.CourseActivity;
import com.rrsthiago.skillboost.model.CourseActivityType;
import com.rrsthiago.skillboost.model.Professional;
import mock.CourseActivityMock;
import mock.CourseActivityTypeMock;
import mock.ProfessionalMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseActivityMapperTest {

    @InjectMocks
    private CourseActivityMapper courseActivityMapper;

    @Mock
    private ProfessionalMapper professionalMapper;

    @Mock
    private CourseActivityTypeMapper courseActivityTypeMapper;

    @Test
    public void testDtoToModel() {
        CourseActivityDto courseActivityDto = CourseActivityMock.getCourseActivityDto();
        Professional professional = ProfessionalMock.getProfessional();
        CourseActivityType courseActivityType = CourseActivityTypeMock.getCourseActivityType();
        when(professionalMapper.idDtoToModel(any())).thenReturn(professional);
        when(courseActivityTypeMapper.idDtoToModel(any())).thenReturn(courseActivityType);

        CourseActivity courseActivity = courseActivityMapper.dtoToModel(courseActivityDto);

        assertEquals(courseActivityDto.getId(), courseActivity.getId());
        assertEquals(courseActivityDto.getScore(), courseActivity.getScore());
        assertEquals(courseActivityDto.getProfessional().getId(), courseActivity.getProfessional().getId());
        assertEquals(courseActivityDto.getActivityType().getId(), courseActivity.getActivityType().getId());
    }

    @Test
    public void testModelToDto() {
        CourseActivity courseActivity = CourseActivityMock.getCourseActivity();
        ProfessionalDto professional = ProfessionalMock.getProfessionalDto();
        CourseActivityTypeDto courseActivityType = CourseActivityTypeMock.getCourseActivityTypeDto();
        when(professionalMapper.modelToDto(any())).thenReturn(professional);
        when(courseActivityTypeMapper.modelToDto(any())).thenReturn(courseActivityType);

        CourseActivityDto courseActivityDto = courseActivityMapper.modelToDto(courseActivity);

        assertEquals(courseActivity.getId(), courseActivityDto.getId());
        assertEquals(courseActivity.getScore(), courseActivityDto.getScore());
        assertEquals(courseActivity.getProfessional().getId(), courseActivityDto.getProfessional().getId());
        assertEquals(courseActivity.getActivityType().getId(), courseActivityDto.getActivityType().getId());
    }

}
