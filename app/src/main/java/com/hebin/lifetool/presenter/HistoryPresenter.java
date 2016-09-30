package com.hebin.lifetool.presenter;

import android.content.Context;

import com.hebin.lifetool.biz.HistoryBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.utils.NetUtils;

/**
 * 历史上的今天
 */

public class HistoryPresenter {
    private IBaseView baseView;
    private HistoryBiz historyBiz;

    public HistoryPresenter(IBaseView baseView) {
        this.baseView = baseView;
        this.historyBiz = new HistoryBiz();
    }

    public void getList(Context context) {
        if (!NetUtils.isNetworkConnected(context)) {
            baseView.getFailed(404, null);
        } else {
            historyBiz.getData(context, baseView.getData(), new IBaseOnListener() {
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
