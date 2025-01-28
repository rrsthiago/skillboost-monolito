package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.CourseActivityMapper;
import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.service.CourseActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class CourseActivityController {

    private static final String PATH = "/v1/courses/{courseId}/activities";
    private static final String COURSE_ID = "courseId";
    private static final String ACTIVITY_ID = "activityId";
    private static final String PATH_ID = "/v1/courses/{courseId}/activities/{activityId}";

    @Autowired
    private CourseActivityService courseActivityService;

    @Autowired
    private CourseActivityMapper courseActivityMapper;

    @GetMapping(PATH)
    public ResponseEntity<?> list(@PathVariable(COURSE_ID) BigInteger courseId) {
        var courseActivities = courseActivityService.list(courseId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseActivities);
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<?> get(@PathVariable(COURSE_ID) BigInteger courseId,
                                 @PathVariable(ACTIVITY_ID) BigInteger activityId) {
        var courseActivity = courseActivityService.get(courseId, activityId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseActivity);
    }

    @PostMapping(PATH)
    public ResponseEntity<?> create(@PathVariable(COURSE_ID) BigInteger courseId,
                                    @RequestBody CourseActivityDto courseActivityDto) {
        var createdCourseActivity = courseActivityService.create(courseId, courseActivityMapper.dtoToModel(courseActivityDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseActivityMapper.modelToDto(createdCourseActivity));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<?> update(@PathVariable(COURSE_ID) BigInteger courseId,
                                    @RequestBody CourseActivityDto courseActivityDto) {
        var updatedCourseActivity = courseActivityService.update(courseId, courseActivityMapper.dtoToModel(courseActivityDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseActivityMapper.modelToDto(updatedCourseActivity));
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<?> delete(@PathVariable(COURSE_ID) BigInteger courseId,
                                    @PathVariable(ACTIVITY_ID) BigInteger activityId) {
        courseActivityService.delete(courseId, activityId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
