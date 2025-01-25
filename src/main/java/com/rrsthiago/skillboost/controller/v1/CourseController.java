package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.CourseMapper;
import com.rrsthiago.skillboost.dto.CourseDto;
import com.rrsthiago.skillboost.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class CourseController {

    private static final String PATH = "/v1/courses";
    private static final String ID = "id";
    private static final String PATH_ID = "/v1/courses/{id}";

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @GetMapping(PATH)
    public ResponseEntity<?> list() {
        var courses = courseService.list();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courses);
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<?> get(@PathVariable(ID) BigInteger id) {
        var course = courseService.get(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(course);
    }

    @PostMapping(PATH)
    public ResponseEntity<?> create(@RequestBody @Valid CourseDto course) {
        var createdCourse = courseService.create(courseMapper.dtoToModel(course));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseMapper.modelToDto(createdCourse));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<?> update(@PathVariable(ID) BigInteger id,
                                    @RequestBody @Valid CourseDto course) {
        var updatedCourse = courseService.update(id, courseMapper.dtoToModel(course));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseMapper.modelToDto(updatedCourse));
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<?> delete(@PathVariable(ID) BigInteger id) {
        courseService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
