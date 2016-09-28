package com.hebin.lifetool.presenter;

import android.content.Context;

import com.hebin.lifetool.biz.PhoneLocationBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.DialogUtil;
import com.hebin.lifetool.utils.NetUtils;

/**
 * 手机归属地查询
 */

public class PhoneLocationPresenter {

    private IBaseView baseView;
    private PhoneLocationBiz locationBiz;

    public PhoneLocationPresenter(IBaseView baseView) {
        this.baseView = baseView;
        locationBiz = new PhoneLocationBiz();
    }

    public void getData(Context context) {
        if (!NetUtils.isNetworkConnected(context)) {
            baseView.getFailed(404, null);
            baseView.hideLoading();
        } else {
            baseView.showLoading();
            locationBiz.getData(context, baseView.getData(), new IBaseOnListener() {
                @Override
                public void getSuccess(Object T) {
                    baseView.hideLoading();
                    baseView.getSuccess(1, T);
                }

                @Override
                public void getFailed(Object T) {
                    baseView.hideLoading();
                    int type = (int) T;
                    baseView.getFailed(type, null);
                }
            });
        }
    }
}
