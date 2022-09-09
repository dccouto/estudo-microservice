package com.ead.authuser.dtos;

import com.ead.authuser.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)//quando serializar, campos nulos não serão mostrados
public class UserDto {
    private UUID userId;
    private String username;
    private String email;
    private String password;
    private String oldPassword;
    private String fullName;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;

    public UserModel convertToEntity(){
        return UserModel.builder()
                .cpf(cpf)
                .userId(userId)
                .password(password)
                .fullName(fullName)
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .imageUrl(imageUrl)
                .build();
    }
}
