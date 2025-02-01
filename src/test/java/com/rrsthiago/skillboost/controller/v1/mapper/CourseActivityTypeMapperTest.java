package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.CourseActivityTypeDto;
import com.rrsthiago.skillboost.model.CourseActivityType;
import mock.CourseActivityTypeMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourseActivityTypeMapperTest {

    @InjectMocks
    private CourseActivityTypeMapper courseActivityTypeMapper;

    @Test
    public void testDtoToModel() {
        CourseActivityTypeDto courseActivityTypeDto = CourseActivityTypeMock.getCourseActivityTypeDto();
        CourseActivityType courseActivityType = courseActivityTypeMapper.dtoToModel(courseActivityTypeDto);

        assertEquals(courseActivityTypeDto.getId(), courseActivityType.getId());
        assertEquals(courseActivityTypeDto.getType(), courseActivityType.getType());
    }

    @Test
    public void testModelToDto() {
        CourseActivityType courseActivityType = CourseActivityTypeMock.getCourseActivityType();
        CourseActivityTypeDto courseActivityTypeDto = courseActivityTypeMapper.modelToDto(courseActivityType);

        assertEquals(courseActivityType.getId(), courseActivityTypeDto.getId());
        assertEquals(courseActivityType.getType(), courseActivityTypeDto.getType());
    }

    @Test
    public void testIdDtoToModel() {
        BigInteger courseActivityTypeId = BigInteger.ONE;
        CourseActivityType courseActivityType = courseActivityTypeMapper.idDtoToModel(courseActivityTypeId);

        assertEquals(courseActivityTypeId, courseActivityType.getId());
    }

}
