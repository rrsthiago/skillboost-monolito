package mock;

import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.model.Professional;

import java.math.BigInteger;

public class ProfessionalMock {

    public static Professional getProfessional() {
        return Professional.builder()
                .id(BigInteger.ONE)
                .name("Professional name")
                .email("professional@email.com")
                .registerNumber("12345")
                .user(UserMock.getUser())
                .build();
    }

    public static ProfessionalDto getProfessionalDto() {
        return ProfessionalDto.builder()
                .id(BigInteger.ONE)
                .name("Professional name")
                .email("professional@email.com")
                .registerNumber("12345")
                .user(UserMock.getUserDto())
                .build();
    }

}
