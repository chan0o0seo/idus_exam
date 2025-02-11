package com.example.idusexam.order;

import com.example.idusexam.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public Page<Order> getLatestOrdersForAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findLatestOrdersForAllUsers(pageable); // 모든 사용자의 최신 주문 가져오기
    }
}
