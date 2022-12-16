package com.amazon.modules.login;

import com.amazon.entity.UserEntity;
import com.amazon.mvputil.BasePresenter;
import com.amazon.service.UserService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginPresenter extends BasePresenter<LoginView> {
    @Autowired
    UserService service;

    public void validateUser(String username, String password, Dialog dialog, LoginForm form) {

        UserEntity validUser = service.getValidUser(username);

        if (validUser==null) {
            form.setError(true);
        } else if (validUser.getPassword().equals(password)) {
            Notification.show("You Have Succesfully Loged In").setPosition(Notification.Position.MIDDLE);
            dialog.close();
            getView().navigate(validUser);
        } else {
            form.setError(true);
        }
    }
}
