package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Profile;
import com.rrsthiago.skillboost.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile get(BigInteger id) {
        return findProfileById(id);
    }

    public List<Profile> list() {
        return profileRepository.findAll();
    }

    public Profile update(BigInteger id, Profile profile) {
        var existingProfile = findProfileById(id);
        existingProfile.setRole(profile.getRole());

        return profileRepository.save(existingProfile);
    }

    public void delete(BigInteger id) {
        profileRepository.deleteById(id);
    }

    private Profile findProfileById(BigInteger id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
