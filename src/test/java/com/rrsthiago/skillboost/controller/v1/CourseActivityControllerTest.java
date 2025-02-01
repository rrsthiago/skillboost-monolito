package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.CourseActivityMapper;
import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.model.CourseActivity;
import com.rrsthiago.skillboost.service.CourseActivityService;
import mock.CourseActivityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseActivityControllerTest {

    @InjectMocks
    private CourseActivityController courseActivityController;

    @Mock
    private CourseActivityService courseActivityService;

    @Spy
    private CourseActivityMapper courseActivityMapper;

    private CourseActivity courseActivity;
    private CourseActivityDto courseActivityDto;

    @BeforeEach
    void setUp() {
        courseActivity = CourseActivityMock.getCourseActivity();
        courseActivityDto = CourseActivityMock.getCourseActivityDto();
    }

    @Test
    void testList() {
        BigInteger courseId = BigInteger.ONE;
        when(courseActivityService.list(courseId)).thenReturn(List.of(courseActivity));

        ResponseEntity<?> response = courseActivityController.list(courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(courseActivity), response.getBody());
        verify(courseActivityService, times(1)).list(courseId);
    }

    @Test
    void testGet() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        when(courseActivityService.get(courseId, activityId)).thenReturn(courseActivity);

        ResponseEntity<?> response = courseActivityController.get(courseId, activityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseActivity, response.getBody());
        verify(courseActivityService, times(1)).get(courseId, activityId);
    }

    @Test
    void testCreate() {
        BigInteger courseId = BigInteger.ONE;
        when(courseActivityService.create(courseId, courseActivity)).thenReturn(courseActivity);
        doReturn(courseActivity).when(courseActivityMapper).dtoToModel(courseActivityDto);
        doReturn(courseActivityDto).when(courseActivityMapper).modelToDto(courseActivity);

        ResponseEntity<?> response = courseActivityController.create(courseId, courseActivityDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseActivityDto, response.getBody());
        verify(courseActivityService, times(1)).create(courseId, courseActivity);
    }

    @Test
    void testUpdate() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;
        when(courseActivityService.update(courseId, activityId, courseActivity)).thenReturn(courseActivity);
        doReturn(courseActivity).when(courseActivityMapper).dtoToModel(courseActivityDto);
        doReturn(courseActivityDto).when(courseActivityMapper).modelToDto(courseActivity);

        ResponseEntity<?> response = courseActivityController.update(courseId, activityId, courseActivityDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseActivityDto, response.getBody());
        verify(courseActivityService, times(1)).update(courseId, activityId, courseActivity);
    }

    @Test
    void testDelete() {
        BigInteger courseId = BigInteger.ONE;
        BigInteger activityId = BigInteger.ONE;

        ResponseEntity<?> response = courseActivityController.delete(courseId, activityId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(courseActivityService, times(1)).delete(courseId, activityId);
    }

}
