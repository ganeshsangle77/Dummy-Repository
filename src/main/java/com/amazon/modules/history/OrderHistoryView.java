package com.amazon.modules.history;

import com.amazon.entity.Order;
import com.amazon.entity.OrderItem;
import com.amazon.entity.ProductEntity;
import com.amazon.mvputil.BaseView;
import com.amazon.repository.OrderItemRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;

@Route(value = "history")
@SpringComponent
@UIScope
public class OrderHistoryView extends BaseView<OrderHistoryPresenter> {
    Grid<Order> orderGrid;
    Grid<OrderItem> orderItemGrid;
    @Autowired
    OrderItemRepository repository;

    @Override
    @Transactional
    protected void init() {

        orderGrid = new Grid<>();
        orderGrid.getStyle().set("left", "3%");
        orderGrid.setWidth("90%");
        Label label = new Label();
        label.setText("Order History");
        label.getStyle().set("color", "#0C86B2").set("margin-top", "1%").set("margin-left", "40%").set("font-size", "200%").set("font-weight", "bold").set("font-style", "italic");
        add(label, orderGrid);
        orderItemGrid = new Grid<>();
        orderItemGrid.setSizeUndefined();
        orderGrid.setItems(getPresenter().setItems());
        orderGrid.addColumn(order -> order.getOrderdate()).setHeader("Order Date");
        orderGrid.addColumn(order -> order.getOrderId()).setHeader("Order Id");
        orderGrid.addColumn(order -> order.getUserId().getFName() + " " + order.getUserId().getLName()).setHeader("User");
        orderGrid.addColumn(order -> order.getTotalAmout()).setHeader("Total Amount");
        orderGrid.addComponentColumn(event -> {
            Icon itemData = new Icon(VaadinIcon.RECORDS);
            itemData.addClickListener(click -> {
                setUpOrderItemGrid(event);
                openDialogBox(event);
            });
            return itemData;
        });
        Button back = new Button("Back");
        back.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        back.addClickListener(click -> {
            getUI().ifPresent(event -> event.navigate("homePage"));
        });
        add(label, orderGrid, back);
    }

    private void setUpOrderItemGrid(Order order) {
        orderItemGrid.addColumn(item -> item.getOrderItemId()).setHeader("Order Item Id");
//        orderItemGrid.addComponentColumn(event -> {
//            Integer id = event.getOrderItemId();
//            StreamResource sr = new StreamResource("ProductEntity", () -> {
//                ProductEntity attached = repository.findWithPropertyProductAttachedByOrderItemId(id);
//                return new ByteArrayInputStream(attached.getPicture());
//            });
//            sr.setContentType("image/png");
//            Image image = new Image(sr, "profile-picture");
//            image.setHeight("90px");
//            image.setWidth("90px");
//            return image;
//        }).setHeader("Photo");
        orderItemGrid.addColumn(item -> item.getProduct().getItemName()).setHeader("Product Name");
        orderItemGrid.addColumn(item -> item.getProduct().getBrand()).setHeader("Brand");
        orderItemGrid.addColumn(item -> item.getProduct().getPrize()).setHeader("Prize");
        orderItemGrid.addColumn(item -> item.getItemQty()).setHeader("Quantity");
        orderItemGrid.setItems(getPresenter().getOrderItemList(order));
    }

    private void openDialogBox(Order event) {
        Dialog orderItemsDialog = new Dialog();
        Label itemLabel = new Label("Item List");
        itemLabel.getStyle().set("color", "#0C86B2").set("margin-left", "45%").set("margin-top", "1%").set("font-size", "150%").set("font-weight", "bold").set("font-style", "italic");
        orderItemsDialog.setWidth("60%");
        orderItemsDialog.setResizable(true);
        orderItemsDialog.setDraggable(true);
        orderItemsDialog.add(itemLabel);
        orderItemsDialog.add(orderItemGrid);
        orderItemsDialog.add("Total Amount : " + event.getTotalAmout() + " Rs");
        orderItemsDialog.open();
    }
}
