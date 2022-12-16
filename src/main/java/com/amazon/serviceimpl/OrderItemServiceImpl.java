package com.amazon.serviceimpl;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.entity.ProductEntity;
import com.amazon.entity.UserEntity;
import com.amazon.repository.OrderItemRepository;
import com.amazon.repository.OrderRepository;
import com.amazon.repositoryimpl.OrderRepositoryImpl;
import com.amazon.service.OrderItemsService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemsService {
    @Autowired
    OrderItemRepository repository;
    @Autowired
    OrderRepository orderRepository;

    public void init() {
    }

    public static List<OrderItem> orderItemList = new ArrayList<>();


    @Override
    public void addToOrderItemList(ProductEntity event, Integer value) {

        OrderItem item = new OrderItem();
        item.setItemQty(value);
        item.setUser((UserEntity) VaadinSession.getCurrent().getAttribute("user"));
        item.setProduct(event);
        item.setOrderId((OrderRepositoryImpl.order));
        orderItemList.add(item);
    }
}
