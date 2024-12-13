package com.team5.fandom.security;

import com.team5.fandom.dto.UserDto;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of="email")
public class FanUserDetails implements UserDetails {

    private Integer userId;
    private String userName;
    private String email;
    private String password;
    private Role role;

    public static FanUserDetails of(Integer userId, String userName, String email,String password,Role role) {
        return new FanUserDetails(userId, userName, email, password, role);
    }

    public static FanUserDetails toFUDto(UserDto userDto) {
        return FanUserDetails.of(userDto.getUserId(),
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());
    }

    public UserDto toUserDto() {
        return UserDto.of(
                userId,
                userName,
                email,
                password,
                role
        );
    }
    public static User of(FanUserDetails.FanUserDetailsBuilder builder) {
        FanUserDetails fanUserDetails = builder.build();

        // FanUserDetails의 정보를 User로 변환
        return new User(
                fanUserDetails.getUserId(),
                fanUserDetails.getUsername(),
                fanUserDetails.getEmail(),
                fanUserDetails.getPassword(),
                fanUserDetails.getRole()
        );

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권힌
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> role.getRoleName());
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
        );
    }
    public static User of(FanUserDetails fanUserDetails) {
        return new User(
                fanUserDetails.getUserId(),
                fanUserDetails.getUsername(),
                fanUserDetails.getEmail(),
                fanUserDetails.getPassword(),
                fanUserDetails.getRole()
        );
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
