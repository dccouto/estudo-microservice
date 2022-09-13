package com.ead.authuser.dtos;

import com.ead.authuser.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)//quando serializar, campos nulos não serão mostrados
public class UserDto {

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface UserPut{}
        public static interface PasswordPut{}
        public static interface ImagePut{}

    }

    private UUID userId;

    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @JsonView(UserView.ImagePut.class)
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
