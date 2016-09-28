package com.hebin.lifetool.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static ToastUtils mToastUtils;
    private static Context context;
    private Toast mToast;

    public static ToastUtils getInstance() {
        return mToastUtils;
    }

    public static void init(Context context) {
        mToastUtils = new ToastUtils(context);
    }

    private ToastUtils(Context context) {
        ToastUtils.context = context;
    }

    /**
    * 显示没有网络连接的提示
    * */
    public void showNoNet() {
        if (mToast == null) {
            mToast = Toast.makeText(context, "网络连接失败，请检查网络连接~", Toast.LENGTH_SHORT);
        } else {
            mToast.setText("网络连接失败，请检查网络连接~");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
    * 显示请求服务器失败的提示
    * */
    public void showNoResponse() {
        if (mToast == null) {
            mToast = Toast.makeText(context, "访问失败，请稍后重试~", Toast.LENGTH_SHORT);
        } else {
            mToast.setText("访问失败，请稍后重试~");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    /**
    * 实现自定义文本的Toast提示
    * */
    public void showToast(String mText) {
        if (mToast == null) {
            mToast = Toast.makeText(context, mText, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(mText);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
    * 取消Toast提示
    * */
    public void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
