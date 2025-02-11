package com.example.idusexam.order.model;


import com.example.idusexam.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(nullable = false, length = 12)
    private String orderId;

    @Column(nullable = false, length = 100)
    private String productName;

    private LocalDateTime orderTime;


    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
}
