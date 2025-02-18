package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.User;
import com.rrsthiago.skillboost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(BigInteger id) {
        return findUserById(id);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User update(BigInteger id, User user) {
        var existingUser = findUserById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setProfile(user.getProfile());

        return userRepository.save(existingUser);
    }

    public void delete(BigInteger id) {
        userRepository.deleteById(id);
    }

    private User findUserById(BigInteger id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
