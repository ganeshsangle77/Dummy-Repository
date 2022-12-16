package com.amazon.modules.signUp;

import com.amazon.entity.UserEntity;
import com.amazon.mvputil.BasePresenter;
import com.amazon.repository.UserRepository;
import com.amazon.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@UIScope
@SpringComponent
public class signUpPresenter extends BasePresenter<signUpView> {
    @Autowired
    UserService service;

    //Compulsory
    // for using the view components inside the logic


    public void validateUser(TextField name, TextField sirname, EmailField email, TextField mobile, TextField age, TextField password, Button submit, Button cancel, Dialog dialog) {
        Binder<UserEntity> binder = new Binder<>(UserEntity.class);
        UserEntity newUser = new UserEntity();
        binder.forField(name).withValidator(g -> g.length() > 2, "Invalid Name").bind(UserEntity::getFName, UserEntity::setFName);
        binder.forField(sirname).withValidator(g -> g.length() > 2, "Invalid sirname").bind(UserEntity::getLName, UserEntity::setLName);
        binder.forField(email).withValidator(g -> g.endsWith(".com"), "Invalid EmailId").bind(UserEntity::getMail, UserEntity::setMail);
        binder.forField(mobile).withValidator(g -> g.length() > 9, "Invalid Mobile Number").bind(UserEntity::getMobiNumber, UserEntity::setMobiNumber);
        binder.forField(age).withValidator(g -> g.length() < 3, "Invalid age").bind(UserEntity::getAge, UserEntity::setAge);
        binder.forField(password).withValidator(g -> g.length() > 2, "Invalid Password").bind(UserEntity::getPassword, UserEntity::setPassword);
        submit.addClickListener(click -> {
            if (binder.isValid()) {
                try {
                    binder.writeBean(newUser);
                    service.save(newUser);
                    getView().signUpDone(newUser);
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
