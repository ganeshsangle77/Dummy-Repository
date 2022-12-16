package com.amazon.modules.signUp;

import com.amazon.entity.UserEntity;
import com.amazon.mvputil.BaseView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class signUpView extends BaseView<signUpPresenter> {

    Dialog signUpDialog;

    public void init() {
    }

    public void opensignUpView() {
        signUpDialog = new Dialog();
        signUpDialog.setWidth("25%");
        signUpDialog.setHeight("75%");
        VerticalLayout signUpLayout = new VerticalLayout();
        TextField name = new TextField("Name");
        name.setWidthFull();
        TextField sirname = new TextField("Sirname");
        sirname.setWidthFull();
        TextField password = new TextField("Password");
        password.setWidthFull();
        EmailField email = new EmailField("Email");
        email.setWidthFull();
        TextField age = new TextField("Age");
        age.setWidthFull();
        TextField mobile = new TextField("Mobile");
        mobile.setWidthFull();
        Button submit = new Button("Submit");
        submit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        Button cancel = new Button("cancel");
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        Label lable=new Label("SignUp");
        lable.getStyle().set("color", "#48CD63").set("margin-left", "5%").set("font-size", "200%").set("font-weight", "bold");
        signUpLayout.setSizeFull();
        signUpLayout.add(lable,name, sirname, email, mobile, age, password, submit, cancel);
        signUpDialog.add(signUpLayout);
        signUpDialog.setCloseOnEsc(true);
        signUpDialog.setDraggable(true);
        signUpDialog.open();

        getPresenter().validateUser(name, sirname, email, mobile, age, password, submit, cancel, signUpDialog);
    }


    public void signUpDone(UserEntity user) {
        signUpDialog.close();
        Notification.show("Dear " + user.getFName() + " Sir Youve Have Succesfully Signed In").setPosition(Notification.Position.MIDDLE);
    }
}
