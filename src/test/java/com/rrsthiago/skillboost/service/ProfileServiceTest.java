package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Profile;
import com.rrsthiago.skillboost.repository.ProfileRepository;
import mock.ProfileMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    public void testCreate() {
        Profile profile = ProfileMock.getProfile();
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile createdProfile = profileService.create(profile);

        assertEquals(profile, createdProfile);
    }

    @Test
    public void testGet() {
        Profile profile = ProfileMock.getProfile();
        when(profileRepository.findById(any(BigInteger.class))).thenReturn(Optional.of(profile));

        Profile foundProfile = profileService.get(BigInteger.ONE);

        assertEquals(profile, foundProfile);
    }

    @Test
    public void testList() {
        List<Profile> profiles = List.of(ProfileMock.getProfile());
        when(profileRepository.findAll()).thenReturn(profiles);

        List<Profile> foundProfiles = profileService.list();

        assertEquals(profiles, foundProfiles);
    }

    @Test
    public void testUpdate() {
        Profile profile = ProfileMock.getProfile();
        when(profileRepository.findById(any(BigInteger.class))).thenReturn(Optional.of(profile));
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile updatedProfile = profileService.update(BigInteger.ONE, profile);

        assertEquals(profile, updatedProfile);
    }

    @Test
    public void testDelete() {
        doNothing().when(profileRepository).deleteById(any(BigInteger.class));

        profileService.delete(BigInteger.ONE);

        verify(profileRepository, times(1)).deleteById(BigInteger.ONE);
    }

    @Test
    public void testGetProfileNotFound() {
        when(profileRepository.findById(any(BigInteger.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> profileService.get(BigInteger.ONE));
    }

    @Test
    public void testUpdateProfileNotFound() {
        when(profileRepository.findById(any(BigInteger.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> profileService.update(BigInteger.ONE, ProfileMock.getProfile()));
    }

}
