package com.ead.authuser.services.impl;

import com.ead.authuser.controllers.UserController;
import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        Page<UserModel> userModels = userRepository.findAll(spec, pageable);
        if (!userModels.isEmpty()){
            for (UserModel user : userModels.toList()){
                user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
            }
        }
        return userModels;
    }

    @Override
    public Optional<UserModel> findById(final UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserModel registerNewUser(UserDto userDto) {
        UserModel userModel = userDto.convertToEntity();
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        
        return this.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

}
