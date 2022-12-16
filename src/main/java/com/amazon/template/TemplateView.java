package com.amazon.template;

import com.amazon.entity.UserEntity;
import com.amazon.modules.mainpage.MainPresenter;
import com.amazon.modules.mainpage.MainView;
import com.amazon.service.ProductService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class TemplateView extends AppLayout {
    Tabs tabs;
    Tab tab1;
    Tab tab2;
    Tab tab3;
    Tab tab4;
    @Autowired
    ProductService productsService;
    @Autowired
    MainView mainView;
    @Autowired
    MainPresenter presenter;

    @PostConstruct
    void setUp() {
        tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tab1 = new Tab("Home");
        tab2 = new Tab("Men");
        tab3 = new Tab("Women");
        tab4 = new Tab("Kid's");
        tabs.add(tab1, tab2, tab3, tab4);
        setNavBar();
    }

    private void setNavBar() {
        VerticalLayout verticalNavBar = new VerticalLayout();
        TextField searchField = new TextField();
        searchField.setWidth("90%");
        searchField.getStyle().set("color", "#F1FCF7");
        searchField.setAutocorrect(true);
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
//        searchField.getStyle().set("", "");

        Icon cart = new Icon(VaadinIcon.CART);
//        cart.getStyle().set("position", "absolute").set("right", "5%");
        cart.addClickListener(click -> {
            getUI().ifPresent(event -> event.navigate("cart"));
        });
        Icon history = new Icon(VaadinIcon.TIME_BACKWARD);
//        history.getStyle().set("position", "absolute").set("right", "0%");


        Icon profile = new Icon(VaadinIcon.USER);
        profile.getStyle().set("position", "absolute").set("left", "30%").set("top", "45");
        H4 username = new H4();
        username.getStyle().set("bottom", "50%");
        UserEntity user = (UserEntity) VaadinSession.getCurrent().getAttribute("user");
        if (user != null) {
            username.add(user.getFName() + " " + user.getLName());
        } else {
            username.add("User");
        }
        Icon logOut = new Icon(VaadinIcon.POWER_OFF);
        logOut.addClickListener(click -> {VaadinSession.getCurrent().setAttribute("user", null);
        getUI().ifPresent(event->event.navigate(""));

        }
        );
        HorizontalLayout hori1 = new HorizontalLayout();
        HorizontalLayout hori2 = new HorizontalLayout();
        hori1.add(profile, username);
        hori2.add(history, cart, logOut);
        hori1.getStyle().set("position", "absolute").set("left", "2%");
        hori2.getStyle().set("position", "absolute").set("right", "2%");
        //        username.getStyle().set("position", "absolute").set("left", "15%");
        profile.addClickListener(click -> {
            getUI().ifPresent(event -> event.navigate(""));
        });


        history.addClickListener(click -> getUI().ifPresent(event -> event.navigate("history")));
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidth("55%");
        Button search = new Button("Search");
        searchLayout.add(searchField, search);
        tabs.setWidth("26%");
        verticalNavBar.add(searchLayout, tabs);
        verticalNavBar.setWidthFull();
        verticalNavBar.setHeightFull();
        verticalNavBar.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalNavBar.setPadding(false);
        verticalNavBar.setSpacing(false);
        verticalNavBar.getStyle().set("background-color", "#8CEFC4");
        addToNavbar(hori1, verticalNavBar, hori2);

        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab().equals(tab1)) {

                MainView.productsGrid.setItems(presenter.setItems());
                mainView.productsGrid.getDataProvider().refreshAll();

            } else if (event.getSelectedTab().equals(tab2)) {

                MainView.productsGrid.setItems(productsService.findByGenderCategory("men"));
                mainView.productsGrid.getDataProvider().refreshAll();

            } else if (event.getSelectedTab().equals(tab3)) {

                MainView.productsGrid.setItems(productsService.findByGenderCategory("women"));
                mainView.productsGrid.getDataProvider().refreshAll();

            } else if (event.getSelectedTab().equals(tab4)) {
                MainView.productsGrid.setItems(productsService.findByGenderCategory("kids"));
                mainView.productsGrid.getDataProvider().refreshAll();

            }
        });
    }

}
