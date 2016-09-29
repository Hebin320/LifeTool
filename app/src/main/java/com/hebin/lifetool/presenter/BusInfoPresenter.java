package com.hebin.lifetool.presenter;

import android.content.Context;

import com.hebin.lifetool.biz.BusInfoBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.NetUtils;

/**
 * 长途汽车信息
 */

public class BusInfoPresenter {
    private IBaseView baseView;
    private BusInfoBiz infoBiz;

    public BusInfoPresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.infoBiz = new BusInfoBiz();
    }

    public void getList(Context context) {
        if (!NetUtils.isNetworkConnected(context)) {
            baseView.getFailed(404, null);
        } else {
            infoBiz.getData(context, baseView.getData(), new IBaseOnListener() {
                @Override
                public void getSuccess(Object T) {
                    baseView.hideLoading();
                    baseView.getSuccess(1, T);
                }

                @Override
                public void getFailed(Object T) {
                    int type = (int) T;
                    switch (type) {
                        case 404:
                            baseView.getFailed(404, null);
                            break;
                        case 208202:
                            baseView.getFailed(208202, null);
                            break;
                    }
                }
            });
        }
    }
}
