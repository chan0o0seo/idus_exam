package com.example.idusexam.order.model;


import com.example.idusexam.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Schema(description = "주문정보 저장 테이블")
public class Order {

    @Id
    @Column(nullable = false, length = 12, unique = true)
    @Schema(description = "주문번호, 중복이 불가능한 임의의 영문 대문자, 숫자 조합", example = "B4D2E9H1K3A7")
    private String orderId;

    @Column(nullable = false, length = 100)
    @Schema(description = "제품명, emoji를 포함한 모든 문자", example = "삼성갤럭시북🎈")
    private String productName;

    @Schema(description = "결제일시, TimeZone을 고려한 시간 정보", example = "UTC 2025-02-11")
    private OffsetDateTime orderTime;



    @Schema(description = "user테이블의 email과 연결되는 foriegn key")
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @PrePersist
    public void prePersist() {
        if (this.orderId == null) {
            this.orderId = generateOrderId();
        }
        if (this.orderTime == null) {
            this.orderTime = OffsetDateTime.now(ZoneOffset.UTC); // UTC 시간 기준
        }
    }

    private String generateOrderId() {
        // 랜덤으로 12자리 문자열 생성
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase(); // 대문자 UUID
        return uuid.substring(0, 12); // 첫 12자만 사용
    }
}
