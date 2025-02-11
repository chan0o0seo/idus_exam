package com.example.idusexam.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserDto {

    @Getter
    public static class signUpDto {
        private final String name;
        private final String nickname;
        private final String password;
        private final String phone;
        private final String email;
        private Boolean gender;

        public signUpDto(String name, String nickname, String password, String phone, String email, Boolean gender) {
            this.name = name;
            this.nickname = nickname;
            this.password = password;
            this.phone = phone;
            this.email = email;
            this.gender = gender;
        }
        public signUpDto(String name, String nickname, String password, String phone, String email) {
            this.name = name;
            this.nickname = nickname;
            this.password = password;
            this.phone = phone;
            this.email = email;
            this.gender = null;
        }
    }
}
