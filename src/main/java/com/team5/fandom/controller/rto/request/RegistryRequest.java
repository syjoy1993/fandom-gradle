package com.team5.fandom.controller.rto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public class RegistryRequest {

        @NotBlank(message = "사용자 이름은 필수 입력 사항입니다.")
        @Size(min = 3, max = 50, message = "사용자 이름은 3자 이상 50자 이하여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "사용자 이름은 영문자와 숫자만 포함할 수 있습니다.")
        private String userName;

        @NotBlank(message = "이메일은 필수 입력 사항입니다.")
        @Email(message = "유효하지 않은 이메일 형식입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
        private String password;
        private String fandomName;
    }





