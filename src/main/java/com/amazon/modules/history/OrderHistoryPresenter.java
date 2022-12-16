package com.amazon.modules.history;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.mvputil.BasePresenter;
import com.amazon.service.HistoryService;
import com.amazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryPresenter extends BasePresenter<OrderHistoryView> {
    @Autowired
    OrderService service;
    @Autowired
    HistoryService historyService;

    public List<Order> setItems() {
        return service.getOrderList();
    }

    public List<OrderItem> getOrderItemList(Order order) {
        return historyService.getOrderItemList(order);
    }
}
