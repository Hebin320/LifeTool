package com.hebin.lifetool.presenter.constellation;

import android.content.Context;

import com.hebin.lifetool.biz.base.ConstellationMonthBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.NetUtils;

/**
 *
 */

public class ConstellationMonthPresenter {

    private IBaseView baseView;
    private ConstellationMonthBiz dayBiz;

    public ConstellationMonthPresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.dayBiz = new ConstellationMonthBiz();
    }

    public void getData(Context context) {
        if (!NetUtils.isNetworkConnected(context)) {
            baseView.getFailed(404, null);
        } else {
            dayBiz.getData(context, baseView.getData(), new IBaseOnListener() {
                @Override
                public void getSuccess(Object T) {
                    baseView.hideLoading();
                    baseView.getSuccess(1, T);
                }

                @Override
                public void getFailed(Object T) {
                    baseView.getFailed(404, null);
                }
            });
        }
    }
}
