package com.amazon.mvputil;

public interface View<P extends Presenter> {
    P getPresenter();

}
