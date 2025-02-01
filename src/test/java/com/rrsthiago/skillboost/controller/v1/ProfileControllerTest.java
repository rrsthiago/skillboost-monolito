package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.ProfileMapper;
import com.rrsthiago.skillboost.dto.ProfileDto;
import com.rrsthiago.skillboost.model.Profile;
import com.rrsthiago.skillboost.service.ProfileService;
import mock.ProfileMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    @Spy
    private ProfileMapper profileMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testList() {
        List<Profile> profiles = List.of(ProfileMock.getProfile());
        when(profileService.list()).thenReturn(profiles);

        ResponseEntity<?> response = profileController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profiles, response.getBody());
    }

    @Test
    public void testGet() {
        Profile profile = ProfileMock.getProfile();
        when(profileService.get(any(BigInteger.class))).thenReturn(profile);

        ResponseEntity<?> response = profileController.get(BigInteger.ONE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profile, response.getBody());
    }

    @Test
    public void testCreate() {
        Profile profile = ProfileMock.getProfile();
        ProfileDto profileDto = ProfileMock.getProfileDto();
        when(profileService.create(any(Profile.class))).thenReturn(profile);

        ResponseEntity<?> response = profileController.create(profileDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profileDto, response.getBody());
    }

    @Test
    public void testUpdate() {
        Profile profile = ProfileMock.getProfile();
        ProfileDto profileDto = ProfileMock.getProfileDto();
        when(profileService.update(any(BigInteger.class), any(Profile.class))).thenReturn(profile);

        ResponseEntity<?> response = profileController.update(BigInteger.ONE, profileDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profileDto, response.getBody());
    }

    @Test
    public void testDelete() {
        doNothing().when(profileService).delete(any(BigInteger.class));

        ResponseEntity<?> response = profileController.delete(BigInteger.ONE);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
