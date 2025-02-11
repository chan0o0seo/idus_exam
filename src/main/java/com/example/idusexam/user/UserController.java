package com.example.idusexam.user;


import com.example.idusexam.user.model.User;
import com.example.idusexam.user.model.UserDto;
import com.example.idusexam.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/idus")
@RestController
@RequiredArgsConstructor
@Tag(name = "User API", description = "유저 관련 API")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "회원 가입", description = "이름, 별명, 비밀번호, 전화번호, 이메일, 성별을 받아 회원가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.SignUpDto dto) {

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

    @Operation(summary = "회원 로그인(인증)", description = "인증을 하면서 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDto.LoginDto dto) {
        Map<String,Object> map = new HashMap<>();
        // 인증을 위한 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = null;
        // 인증을 시도
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            map.put("error", e.getMessage());
            return ResponseEntity.ok(map);
        }
        // 인증이 성공하면 JWT 토큰 생성
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("authenticated");
            String token =  JWTUtil.generateToken(dto.getEmail()); // JWT 토큰 생성 후 반환
            map.put("token", token);
            return ResponseEntity.ok(map);
        } else {
            System.out.println("authenticated fail");
            map.put("error","authenticated fail");
            return ResponseEntity.ok(map);
        }
    }



    @Operation(summary = "단일 회원 상세 정보 조회", description = "email로 단일 회원 상세 정보를 조회합니다.")
    @GetMapping("/findEmail")
    public ResponseEntity<User> signup(String email) {

        Optional<User> optionalUser = userService.findByEmail(email);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
