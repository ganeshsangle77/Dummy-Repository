package com.amazon.repositoryimpl;

import com.amazon.entity.Order;
import com.amazon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    OrderRepository repo;
    public static Order order = new Order();


    public List<Order> orderList() {
        return repo.findAll();
    }

    OrderRepositoryImpl() {
    }
}
