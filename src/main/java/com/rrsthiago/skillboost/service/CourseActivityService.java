package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Course;
import com.rrsthiago.skillboost.model.CourseActivity;
import com.rrsthiago.skillboost.repository.CourseActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CourseActivityService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseActivityRepository courseActivityRepository;

    public CourseActivity create(BigInteger courseId, CourseActivity courseActivity) {
        courseService.get(courseId);
        courseActivity.setCourse(Course.builder()
                .id(courseId)
                .build());

        return courseActivityRepository.save(courseActivity);
    }

    public CourseActivity get(BigInteger courseId, BigInteger activityId) {
        return findCourseActivityById(courseId, activityId);
    }

    public List<CourseActivity> list(BigInteger courseId) {
        return courseActivityRepository.findAllByCourseId(courseId);
    }

    public CourseActivity update(BigInteger courseId, BigInteger activityId, CourseActivity courseActivity) {
        var existingCourseActivity = findCourseActivityById(courseId, activityId);
        existingCourseActivity.setScore(courseActivity.getScore());
        existingCourseActivity.setProfessional(courseActivity.getProfessional());
        existingCourseActivity.setActivityType(courseActivity.getActivityType());

        return courseActivityRepository.save(existingCourseActivity);
    }

    public void delete(BigInteger courseId, BigInteger activityId) {
        courseActivityRepository.deleteById(activityId);
    }

    private CourseActivity findCourseActivityById(BigInteger courseId, BigInteger activityId) {
        return courseActivityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException(activityId));
    }

}
