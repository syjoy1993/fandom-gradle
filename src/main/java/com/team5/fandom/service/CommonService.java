package com.team5.fandom.service;

import com.team5.fandom.dto.UserDto;
import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Level;
import com.team5.fandom.entity.value.Role;
import com.team5.fandom.repository.FandomRepository;
import com.team5.fandom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
/*
    public UserDto authenticate(String email, String password) {
        // 데이터베이스에서 사용자 정보 조회
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // 사용자 존재 여부와 비밀번호 검증
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            return UserDto.toUserDto(optionalUser.get()); // User 엔티티를 UserDto로 변환하여 반환
        }
        return null; // 인증 실패 시 null 반환
    }*/


    public void registerUser(UserDto userDto) {

//
//        // 동일 ID(UserName) 확인
//        if (userRepository.existsByUserName(userDto.getUserName())) {
//            throw new IllegalArgumentException("사용자 이름 '" + userDto.getUserName() + "'은(는) 이미 존재합니다.");
//        }

        // 동일 이메일 확인
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("이메일 '" + userDto.getEmail() + "'은(는) 이미 사용 중입니다.");
        }

        Integer fandomID = userDto.getFandomDto().getFandomId();


        Fandom fandom = fandomRepository.findById(fandomID)
                .orElseThrow(() -> new IllegalArgumentException("Fandom Not Found"));


        // User 엔티티 생성
        User user = User.of(
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getPassword(),
                Role.USER,
                Level.BRONZE,
                0,
                fandom
        );

        // User 엔티티 저장
        User savedUser = userRepository.save(user);
    }
}
