package com.amazon.modules.login;

import com.amazon.entity.UserEntity;
import com.amazon.mvputil.BaseView;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class LoginView extends BaseView<loginPresenter> {
    LoginForm form;

    public void openLoginView() {
        Dialog loginDialog = new Dialog();
        form = new LoginForm();
        loginDialog.setDraggable(true);
        form.addLoginListener(loginEvent -> {
            String username = loginEvent.getUsername();
            String password = loginEvent.getPassword();
            getPresenter().validateUser(username, password, loginDialog, form);

        });
        form.addForgotPasswordListener(forgotPasswordEvent -> {
            Notification.show("Please SignUp Again").setPosition(Notification.Position.MIDDLE);
        });
        loginDialog.add(form);
        loginDialog.open();
    }

    LoginForm getForm() {
        return form;
    }

    @Override
    protected void init() {

    }

    public void navigate(UserEntity user) {
        VaadinSession.getCurrent().setAttribute("user",user);
        getForm().getUI().ifPresent(event -> event.navigate("homePage"));
    }
}
