package com.hebin.lifetool.biz.base;

import android.content.Context;

public interface IBaseBiz<T> {

    void getData(Context context, T T, IBaseOnListener onListener);
}
