package com.hebin.lifetool.presenter;

import android.content.Context;

import com.hebin.lifetool.biz.WeChatBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.NetUtils;

/**
 * 微信精选的Presenter
 */

public class WeChatPresenter {

    private IBaseView baseView;
    private WeChatBiz weChatBiz;

    public WeChatPresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.weChatBiz = new WeChatBiz();
    }

    public void getList(Context context) {
        if (!NetUtils.isNetworkConnected(context)) {
            baseView.getFailed(404, null);
        } else {
            weChatBiz.getData(context, baseView.getData(), new IBaseOnListener() {
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
