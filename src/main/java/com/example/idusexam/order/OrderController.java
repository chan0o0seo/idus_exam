package com.example.idusexam.order;


import com.example.idusexam.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final int pageSize = 3;
    private final int pageNumber = 6;


    @GetMapping("/latest")
    public ResponseEntity<Page<Order>> getLatestOrdersForAllUsers() {
        Page<Order> orders = orderService.getLatestOrdersForAllUsers(pageSize, pageNumber);
        return ResponseEntity.ok(orders); // 모든 사용자의 최신 주문 정보 조회
    }


}
