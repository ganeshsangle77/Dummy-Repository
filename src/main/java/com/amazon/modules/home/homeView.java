package com.amazon.modules.home;

import com.amazon.modules.login.LoginView;
import com.amazon.modules.signUp.signUpView;
import com.amazon.mvputil.BaseView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


@Route("")
@UIScope
@SpringComponent
public class homeView extends BaseView<homePresenter> {
    @Autowired
    signUpView signUp;
    @Autowired
    LoginView loginView;

    homeView() {
    }

    @Override
    public void init() {
        Label label = new Label();
        label.setText("Shoppers Plaza");
        label.getStyle().set("color", "#F1FCF7").set("margin-top", "5%").set("font-size", "300%").set("font-weight", "bold").set("font-style", "italic");

        getStyle().set("background-image", "url('images/background.jpg')").set("background-size", "cover");

//        setSizeFull();
        Button login = new Button("Login");

        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        login.getStyle().set("margin-top", "10%").set("size", "large");
        login.addClickListener(click -> {
            loginView.openLoginView();
        });
        Button signIn = new Button("Sign-In");
        signIn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        signIn.getStyle().set("margin-bottom", "1%");
        setAlignItems(Alignment.CENTER);
        signIn.addClickListener(click -> {
            signUp.opensignUpView();
        });
        Button shop = new Button("Shop");
        shop.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        shop.getStyle().set("margin-bottom", "19%");

        shop.addClickListener(click -> {
            getUI().ifPresent(event -> event.navigate("homePage"));
        });
        add(label, login, signIn, shop);

    }


}
