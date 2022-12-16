package com.amazon.repository;

import com.amazon.entity.Order;
import com.amazon.entity.ProductEntity;
import com.amazon.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(UserEntity userId);
}
