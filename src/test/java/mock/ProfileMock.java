package mock;

import com.rrsthiago.skillboost.dto.ProfileDto;
import com.rrsthiago.skillboost.model.Profile;

import java.math.BigInteger;

public class ProfileMock {

    public static Profile getProfile() {
        return Profile.builder()
                .id(BigInteger.ONE)
                .role("Profile role")
                .build();
    }

    public static ProfileDto getProfileDto() {
        return ProfileDto.builder()
                .id(BigInteger.ONE)
                .role("Profile role")
                .build();
    }

}
