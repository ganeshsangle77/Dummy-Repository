package com.amazon.repository;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderId(Order order);

    ProductEntity findWithPropertyPictureAttachedByProduct(OrderItem event);

//    ProductEntity findWithPropertyProductAttachedByOrderItemId(Integer id);
}
