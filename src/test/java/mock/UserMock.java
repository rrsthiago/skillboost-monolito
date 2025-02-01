package mock;

import com.rrsthiago.skillboost.dto.UserDto;
import com.rrsthiago.skillboost.model.User;

import java.math.BigInteger;

public class UserMock {

    public static User getUser() {
        return User.builder()
                .id(BigInteger.ONE)
                .email("user@email.com")
                .password("password")
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .id(BigInteger.ONE)
                .email("user@email.com")
                .password("password")
                .build();
    }

}
