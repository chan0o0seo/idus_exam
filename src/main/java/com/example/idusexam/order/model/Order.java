package com.example.idusexam.order.model;


import com.example.idusexam.user.model.User;
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
public class Order {

    @Id
    @Column(nullable = false, length = 12, unique = true)
    private String orderId;

    @Column(nullable = false, length = 100)
    private String productName;

    private OffsetDateTime orderTime;


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
