package com.hebin.lifetool.presenter;

import android.content.Context;

import com.hebin.lifetool.biz.ConstellationDayBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.NetUtils;

/**
 *
 */

public class ConstellationDayPresenter {

    private IBaseView baseView;
    private ConstellationDayBiz dayBiz;

    public ConstellationDayPresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.dayBiz = new ConstellationDayBiz();
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
