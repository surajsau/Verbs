package com.halfplatepoha.verbs.base;

public class BasePresenter<V extends IView> implements IPresenter {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
