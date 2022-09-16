package com.ead.authuser.services;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

    Optional<UserModel> findById(final UUID userId);

    void deleteUser(UUID userId);

    UserModel registerNewUser(UserDto userDto);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    UserModel save(UserModel userModel);
}
