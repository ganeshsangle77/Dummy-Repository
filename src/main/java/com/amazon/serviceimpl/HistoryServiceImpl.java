package com.amazon.serviceimpl;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.repository.OrderItemRepository;
import com.amazon.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    OrderItemRepository repository;

    @Override
    public List<OrderItem> getOrderItemList(Order order) {
        List<OrderItem> items = repository.findByOrderId(order);
        return items;
    }
}
