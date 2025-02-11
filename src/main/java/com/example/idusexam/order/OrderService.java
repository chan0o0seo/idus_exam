package com.example.idusexam.order;

import com.example.idusexam.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository orderRepository;


    public String generateOrderId() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase(); // 대문자, 하이픈 제거
        return uuid.substring(0, 12); // 12자리의 고유한 orderId
    }

    public Order saveOrder(Order order) {
        order.setOrderId(generateOrderId());
        return orderRepository.save(order);
    }
}
