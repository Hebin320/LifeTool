package com.hebin.lifetool.biz.base;

public interface IBaseOnListener<T> {

    void  getSuccess(T T);

    void  getFailed(T T);
}

