package com.ead.authuser.services;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();

    Optional<UserModel> findById(final UUID userId);

    void deleteUser(UUID userId);

    UserModel registerNewUser(UserDto userDto);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    UserModel save(UserModel userModel);
}
