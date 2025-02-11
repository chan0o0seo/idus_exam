package com.example.idusexam.order;


import com.example.idusexam.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.email IN :emails ORDER BY o.orderTime DESC")
    Page<Order> findTopByUserEmailOrderByOrderTimeDesc(List<String> email, Pageable pageable);

}
