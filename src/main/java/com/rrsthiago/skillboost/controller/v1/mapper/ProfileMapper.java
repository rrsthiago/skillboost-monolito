package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.ProfileDto;
import com.rrsthiago.skillboost.model.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public Profile dtoToModel(ProfileDto profileDto) {
        return Profile.builder()
                .role(profileDto.getRole())
                .build();
    }

    public ProfileDto modelToDto(Profile model) {
        return ProfileDto.builder()
                .id(model.getId())
                .role(model.getRole())
                .build();
    }

}
