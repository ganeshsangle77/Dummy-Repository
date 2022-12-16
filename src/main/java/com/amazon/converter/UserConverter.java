package com.amazon.converter;

import com.amazon.dto.User;
import com.amazon.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static User convertToDto(UserEntity employeeEntity) {
        return User.builder()
                .userId(employeeEntity.getUserId())
                .fName(employeeEntity.getFName())
                .lName(employeeEntity.getLName())
                .build();
    }
    public static UserEntity convertToEntity(User User) {
        return UserEntity.builder()
                .userId(User.getUserId())
                .fName(User.getFName())
                .lName(User.getLName())
                .build();
    }

    public static List<UserEntity> convertToEntities(List<User> Users) {
        return Users.stream()
                .map(UserConverter::convertToEntity)
                .collect(Collectors.toList());
    }

    public static List<User> convertToDtos(List<UserEntity> UserEntityEntities) {
        return UserEntityEntities.stream()
                .map(UserConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
