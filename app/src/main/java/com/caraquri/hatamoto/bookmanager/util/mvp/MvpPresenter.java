package com.caraquri.hatamoto.bookmanager.util.mvp;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
}
