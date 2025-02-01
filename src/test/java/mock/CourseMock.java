package mock;

import com.rrsthiago.skillboost.dto.CourseDto;
import com.rrsthiago.skillboost.model.Course;

import java.math.BigInteger;

public class CourseMock {

    public static Course getCourse() {
        return Course.builder()
                .id(BigInteger.ONE)
                .name("Course name")
                .description("Course description")
                .syllabus("Course syllabus")
                .totalHours(100)
                .goalPoint(100)
                .build();
    }

    public static CourseDto getCourseDto() {
        return CourseDto.builder()
                .id(BigInteger.ONE)
                .name("Course name")
                .description("Course description")
                .syllabus("Course syllabus")
                .totalHours(100)
                .goalPoint(100)
                .build();
    }

}
