package com.example.idusexam.user.model;


import com.example.idusexam.order.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class User {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    @Size(min = 10)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Id
    @Column(nullable = false, length = 100)
    private String email;

    private Boolean gender;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
