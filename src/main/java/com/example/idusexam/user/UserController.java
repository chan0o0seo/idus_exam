package com.example.idusexam.user;


import com.example.idusexam.user.model.User;
import com.example.idusexam.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/idus")
@RestController
@RequiredArgsConstructor
@Tag(name = "User API", description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 회원가입", description = "이름, 별명, 비밀번호, 전화번호, 이메일, 성별을 받아 회원가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.signUpDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setNickname(dto.getNickname());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());

        if(userService.save(user) != null) {
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("error");
        }
    }
}
