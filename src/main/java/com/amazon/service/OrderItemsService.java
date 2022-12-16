package com.amazon.service;

import com.amazon.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemsService {
    void addToOrderItemList(ProductEntity event, Integer value);
}
