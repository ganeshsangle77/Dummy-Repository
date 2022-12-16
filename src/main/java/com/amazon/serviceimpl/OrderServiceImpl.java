package com.amazon.serviceimpl;

import com.amazon.dto.User;
import com.amazon.entity.Order;
import com.amazon.entity.UserEntity;
import com.amazon.repository.OrderRepository;
import com.amazon.repositoryimpl.OrderRepositoryImpl;
import com.amazon.service.OrderService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;

    Double bill;

    public void setBill(Double bill) {
        this.bill = bill;
    }

    @Override
    public void saveOrder() {
        OrderRepositoryImpl.order.setUserId((UserEntity) VaadinSession.getCurrent().getAttribute("user"));
        OrderRepositoryImpl.order.setProductsList(OrderItemServiceImpl.orderItemList);
        OrderRepositoryImpl.order.setTotalAmout(bill);
        OrderRepositoryImpl.order.setOrderdate(LocalDate.now());
        repository.save(OrderRepositoryImpl.order);
    }

    @Override
    public List<Order> getOrderList() {
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        List<Order> orderList=new ArrayList<>();
        if (user != null) {
            orderList = repository.findByUserId(user);
            return orderList;
        }
        else {
            orderList.add(new Order());
            return orderList;
        }
    }
}
