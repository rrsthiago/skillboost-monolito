package mock;

import com.rrsthiago.skillboost.dto.CourseActivityTypeDto;
import com.rrsthiago.skillboost.model.CourseActivityType;

import java.math.BigInteger;

public class CourseActivityTypeMock {

    public static CourseActivityType getCourseActivityType() {
        return CourseActivityType.builder()
                .id(BigInteger.ONE)
                .type("Course activity type name")
                .build();
    }

    public static CourseActivityTypeDto getCourseActivityTypeDto() {
        return CourseActivityTypeDto.builder()
                .id(BigInteger.ONE)
                .type("Course activity type name")
                .build();
    }

}
