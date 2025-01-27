package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.UserDto;
import com.rrsthiago.skillboost.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ProfileMapper profileMapper;

    public User dtoToModel(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .profile(profileMapper.dtoToModel(userDto.getProfile()))
                .build();
    }

    public UserDto modelToDto(User model) {
        return UserDto.builder()
                .id(model.getId())
                .email(model.getEmail())
                .password(model.getPassword())
                .profile(profileMapper.modelToDto(model.getProfile()))
                .build();
    }

}
