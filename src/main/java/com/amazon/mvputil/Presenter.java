package com.amazon.mvputil;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeLeaveEvent;

public interface Presenter<V extends View> {

    V getView();

    void setView(V view);

    void beforeEnter(BeforeEnterEvent event);

    void beforeLeave(BeforeLeaveEvent event);

    void beforeViewInit();

    void afterViewInit();
}
