package com.amazon.service;

import com.amazon.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getAllUser();

    UserEntity getValidUser(String username);

    void save(UserEntity newUser);
}
