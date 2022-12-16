package com.amazon.service;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HistoryService {
    List<OrderItem> getOrderItemList(Order order);
}
