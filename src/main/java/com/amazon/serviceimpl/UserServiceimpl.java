package com.amazon.serviceimpl;

import com.amazon.entity.UserEntity;
import com.amazon.repository.UserRepository;
import com.amazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public List<UserEntity> getAllUser() {
        return repository.findAll();
    }

    @Override
    public UserEntity getValidUser(String username) {
        UserEntity user = repository.findByMail(username);
        return user;
    }

    @Override
    public void save(UserEntity newUser) {
        repository.save(newUser);
    }
}
