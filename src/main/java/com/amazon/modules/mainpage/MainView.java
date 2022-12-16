package com.amazon.modules.mainpage;

import com.amazon.entity.ProductEntity;
import com.amazon.mvputil.BaseView;
import com.amazon.repository.ProductRepository;
import com.amazon.service.OrderItemsService;
import com.amazon.service.OrderService;
import com.amazon.template.TemplateView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;

@Route(value = "homePage", layout = TemplateView.class)
@SpringComponent
@UIScope
public class MainView extends BaseView<MainPresenter> {
    //    UI.getCurrent().getPage().addStyleSheet("/mainPage/style.css");
    public static Grid<ProductEntity> productsGrid;
    @Autowired
    OrderItemsService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void init() {
        productsGrid = new Grid();
//        List<ProductEntity> allProducts=new ArrayList<>();
//        allProducts.add()
        productsGrid.setItems(getPresenter().setItems());
        productsGrid.getStyle().set("left", "2%");
        productsGrid.setWidth("90%");
        productsGrid.setHeight("90%");
        productsGrid.addComponentColumn(event -> {
            Integer id = event.getItemId();
            StreamResource sr = new StreamResource("ProductEntity", () -> {
                ProductEntity attached = productRepository.findWithPropertyPictureAttachedByItemId(id);
                return new ByteArrayInputStream(attached.getPicture());
            });
            sr.setContentType("image/png");
            Image image = new Image(sr, "profile-picture");
            image.setHeight("100px");
            image.setWidth("100px");
            return image;
        }).setHeader("Photo");
        productsGrid.addColumn(ProductEntity::getItemName).setHeader("Item Name");
        productsGrid.addColumn(ProductEntity::getBrand).setHeader("Brand");
        productsGrid.addColumn(ProductEntity::getPrize).setHeader("Price");
        productsGrid.addComponentColumn(event -> {
            Button buy = new Button("Add To Cart");
            buy.addClickListener(click -> {
                Dialog dia = new Dialog();
                dia.open();
                IntegerField qty = new IntegerField();
                qty.setStep(1);
                qty.setLabel("Enter Quantity");
                dia.setDraggable(true);
                Button next = new Button("Next");
                next.addClickListener(x -> {
                    dia.close();
                    orderItemService.addToOrderItemList(event, qty.getValue());
                });
                dia.add(qty, next);
            });
            return buy;
        });

        add(productsGrid);
        setHeightFull();
    }

}
//    To be Added later
//        productsGrid.addComponentColumn(src->{
//            Image image=new Image();
//            System.out.println(src.getImage());
//            return image;
//        }).setHeader("Photo");
//        productsGrid.addComponentColumn(click -> Buy);
