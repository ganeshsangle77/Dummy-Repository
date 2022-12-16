package com.amazon.modules.cart;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.entity.UserEntity;
import com.amazon.modules.mainpage.MainPresenter;
import com.amazon.mvputil.BaseView;
import com.amazon.service.OrderService;
import com.amazon.serviceimpl.OrderItemServiceImpl;
import com.amazon.serviceimpl.OrderServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "cart")
@SpringComponent
@UIScope
public class CartView extends BaseView<MainPresenter> {

    Grid<OrderItem> cartGrid;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderServiceImpl serviceImpl;

    @Override
    protected void init() {
//        getStyle().set("background-image", "url('images/wall.jpg')").set("background-size", "auto");
        cartGrid = new Grid<>();
        Label label = new Label();
        label.setText("Cart");
        label.getStyle().set("color", "#8CEFC4").set("margin-top", "1%").set("font-size", "250%").set("margin-left", "50%").set("font-weight", "bold").set("font-style", "italic");
        add(label, cartGrid);
//        cartGrid.getStyle().set("background-image", "url('images/wal.jpg')");
        cartGrid.setItems(OrderItemServiceImpl.orderItemList);
        cartGrid.addColumn(orderItem -> orderItem.getProduct().getItemName()).setHeader("Product");
        cartGrid.addColumn(orderItem -> orderItem.getProduct().getBrand()).setHeader("Brand");
        cartGrid.addColumn(orderItem -> orderItem.getProduct().getPrize()).setHeader("Price");
        cartGrid.addColumn(OrderItem::getItemQty).setHeader("Quantity");
        cartGrid.addColumn(orderItem -> orderItem.getProduct().getPrize() * orderItem.getItemQty()).setHeader("Total");
        cartGrid.addComponentColumn(event -> {
            Button delete = new Button("Delete");
            delete.addClickListener(click -> {
                OrderItemServiceImpl.orderItemList.remove(event);
                cartGrid.getDataProvider().refreshAll();
            });
            return delete;
        });
        Button back = new Button("Back");
        back.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.addClickListener(click -> {
            getUI().ifPresent(event -> event.navigate("homePage"));
        });

        Button place_order = new Button("Place Order");
        add(place_order, back);

        place_order.addClickListener(click -> {
            UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
            if (user == null) {
                Notification.show("For Shopping You need to Login or SignUp first").setPosition(Notification.Position.BOTTOM_CENTER);
                getUI().ifPresent(event -> event.navigate(""));
            }
            Dialog bill_Details = new Dialog();
            if (user != null) {
                bill_Details.open();
            }
            bill_Details.setDraggable(true);
            bill_Details.setHeaderTitle("Bill Details");
            Double bill = 0.0;
            for (OrderItem x : OrderItemServiceImpl.orderItemList) {
                bill = bill + x.getItemQty() * x.getProduct().getPrize();
            }
            serviceImpl.setBill(bill);
            bill_Details.setResizable(true);
            bill_Details.add("Total Amount : " + bill + " \n");
            Order.builder().totalAmout(bill).build();
            Button close = new Button("Confirm");
            close.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            close.addClickListener(clic -> {
                bill_Details.close();
                orderService.saveOrder();
                OrderItemServiceImpl.orderItemList.clear();
                getUI().ifPresent(event -> event.navigate("homePage"));
            });
            bill_Details.add(close);

        });

    }
}