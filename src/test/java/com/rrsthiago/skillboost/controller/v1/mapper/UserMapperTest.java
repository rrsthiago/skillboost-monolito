package com.rrsthiago.skillboost.controller.v1.mapper;

import com.rrsthiago.skillboost.dto.UserDto;
import com.rrsthiago.skillboost.model.User;
import mock.ProfileMock;
import mock.UserMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private ProfileMapper profileMapper;

    @Test
    public void testDtoToModel() {
        UserDto userDto = UserMock.getUserDto();
        when(profileMapper.idDtoToModel(userDto.getProfileId())).thenReturn(ProfileMock.getProfile());

        User user = userMapper.dtoToModel(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
    }

    @Test
    public void testModelToDto() {
        User user = UserMock.getUser();

        UserDto userDto = userMapper.modelToDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
    }

    @Test
    public void testIdDtoToModel() {
        BigInteger userId = BigInteger.ONE;

        User user = userMapper.idDtoToModel(userId);

        assertEquals(userId, user.getId());
    }

}
