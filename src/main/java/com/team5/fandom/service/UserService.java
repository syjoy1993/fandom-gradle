package com.team5.fandom.service;


import com.team5.fandom.dto.UserDto;
import com.team5.fandom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public Optional<UserDto> searchUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDto::toUserDto);
    }





}
