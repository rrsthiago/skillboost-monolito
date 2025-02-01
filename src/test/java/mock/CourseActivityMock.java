package mock;

import com.rrsthiago.skillboost.dto.CourseActivityDto;
import com.rrsthiago.skillboost.model.CourseActivity;

import java.math.BigInteger;

public class CourseActivityMock {

    public static CourseActivity getCourseActivity() {
        return CourseActivity.builder()
                .id(BigInteger.ONE)
                .score(100)
                .professional(ProfessionalMock.getProfessional())
                .activityType(CourseActivityTypeMock.getCourseActivityType())
                .build();
    }

    public static CourseActivityDto getCourseActivityDto() {
        return CourseActivityDto.builder()
                .id(BigInteger.ONE)
                .score(100)
                .professional(ProfessionalMock.getProfessionalDto())
                .activityType(CourseActivityTypeMock.getCourseActivityTypeDto())
                .build();
    }

}
