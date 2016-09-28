package com.hebin.lifetool.biz.base;

public interface IBaseView<T> {

    T getData();

    void getSuccess(int type, T T);

    void getFailed(int type, T T);

    void showLoading();

    void hideLoading();

    void noConnect();

    void isConnect();

}
