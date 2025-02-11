package com.example.idusexam.user.model;


import com.example.idusexam.order.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원 정보를 저장하는 테이블")
public class User {

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "이름은 한글과 영문 대소문자만 포함해야 합니다.")
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "^[a-z]+$", message = "별명은 영문 소문자만 포함해야 합니다.")
    @Schema(description = "별명", example = "HJD")
    private String nickname;

    @Column(nullable = false)
    @Size(min = 10)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
            message = "비밀번호는 영어 대소문자, 숫자, 특수 문자를 각각 하나 이상 포함해야 합니다.")
    @Schema(description = "비밀번호", example = "Q!w2e3r4")
    private String password;

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 포함해야 합니다.")
    @Schema(description = "전화번호", example = "01012345678")
    private String phone;

    @Id
    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 형식을 입력하세요.")
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @Schema(description = "성별", example = "false")
    private Boolean gender;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private List<Order> orders;
}
