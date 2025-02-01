package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.ProfileDto;
import com.rrsthiago.skillboost.model.Profile;
import mock.ProfileMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProfileMapperTest {

    @InjectMocks
    private ProfileMapper profileMapper;

    @Test
    public void testDtoToModel() {
        ProfileDto profileDto = ProfileMock.getProfileDto();
        Profile profile = profileMapper.dtoToModel(profileDto);

        assertEquals(profileDto.getRole(), profile.getRole());
    }

    @Test
    public void testModelToDto() {
        Profile profile = ProfileMock.getProfile();
        ProfileDto profileDto = profileMapper.modelToDto(profile);

        assertEquals(profile.getId(), profileDto.getId());
        assertEquals(profile.getRole(), profileDto.getRole());
    }

    @Test
    public void testIdDtoToModel() {
        BigInteger profileId = BigInteger.ONE;
        Profile profile = profileMapper.idDtoToModel(profileId);

        assertEquals(profileId, profile.getId());
    }

}
