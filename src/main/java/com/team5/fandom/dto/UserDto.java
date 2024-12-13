package com.team5.fandom.dto;

import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Level;


import com.team5.fandom.entity.value.Role;
import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class UserDto {
    private Integer userId;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private Level fanLevel;
    private Integer fanExp;
    private FandomDto fandomDto;


    public static UserDto of(Integer userId,String userName,String email,String password,Role role) {
        return UserDto.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .role(role)
                .build();

    }




    // Entity -> DTO 변환
    public static UserDto toUserDto(User user) {
        return new UserDto(
        user.getUserId(),
        user.getUserName(),
        user.getEmail(),
        user.getPassword(),
        user.getRole(),
        user.getFanLevel(),
        user.getFanExp(),
        FandomDto.toFandomDto(user.getFandom()));
    }

    // DTO -> Entity 변환
    public User toEntity(Fandom fandom) {
        return User.of(
                userName,
                email,
                password,
                role,
                fanLevel,
                fanExp,
                fandom
        );
    }
}