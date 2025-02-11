package com.example.idusexam.order;


import com.example.idusexam.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.orderTime = (SELECT MAX(o2.orderTime) FROM Order o2 WHERE o2.user.email = o.user.email)")
    Page<Order> findLatestOrdersForAllUsers(Pageable pageable);

}
