package com.hebin.lifetool.base;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hebin.lifetool.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class BaseMethod {

    public static void setRecyclerView(Context context, XRecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setArrowImageView(R.mipmap.ic_downgrey);
    }


    //webview的各种属性
    public static void webnature(final WebView webview) {
        //webview支持JS
        webview.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕高宽
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        //设置 缓存模式
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webview.getSettings().setDomStorageEnabled(true);
        //自动清除历史跟缓存
        webview.clearCache(true);
        webview.clearHistory();
        //背景透明，也可修改背景色
        webview.setBackgroundColor(0);
        //在webview中打开网页
        webview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (!webview.getSettings().getLoadsImagesAutomatically()) {
                    webview.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 19) {
            webview.getSettings().setLoadsImagesAutomatically(true);
        } else {
            webview.getSettings().setLoadsImagesAutomatically(false);
        }
    }


    /**
     * 正则判断，是否为手机登录
     */
    public static boolean isMobileNO(String mobiles) {
        /**
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         联通：130、131、132、152、155、156、185、186
         电信：133、153、180、189、（1349卫通）
         总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         * */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getSwidth(Activity activity) {
        int Swidth;
        WindowManager windowManager = activity.getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Swidth = display.getWidth();
        return Swidth;
    }
}
