package com.example.idusexam.user.model;

import com.example.idusexam.order.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserDto {

    @Getter
    @Schema(description = "회원가입할 때 양식 저장하는 Dto")
    public static class SignUpDto {
        private final String name;
        private final String nickname;
        private final String password;
        private final String phone;
        private final String email;
        private Boolean gender;

        public SignUpDto(String name, String nickname, String password, String phone, String email, Boolean gender) {
            this.name = name;
            this.nickname = nickname;
            this.password = password;
            this.phone = phone;
            this.email = email;
            this.gender = gender;
        }
        public SignUpDto(String name, String nickname, String password, String phone, String email) {
            this.name = name;
            this.nickname = nickname;
            this.password = password;
            this.phone = phone;
            this.email = email;
            this.gender = null;
        }
    }

    @Getter
    @RequiredArgsConstructor
    @Schema(description = "로그인할 때 양식 저장하는 Dto")
    public static class LoginDto {
        private final String email;
        private final String password;
    }

}
