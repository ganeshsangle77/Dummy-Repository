package com.amazon.service;

import com.amazon.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void saveOrder();

    List<Order> getOrderList();
}
