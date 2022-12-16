package com.amazon.modules.mainpage;

import com.amazon.entity.ProductEntity;
import com.amazon.mvputil.BasePresenter;
import com.amazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPresenter extends BasePresenter<MainView> {
    @Autowired
    ProductService service;


    public List<ProductEntity> setItems() {
        return service.getAllProducts();
    }

}
