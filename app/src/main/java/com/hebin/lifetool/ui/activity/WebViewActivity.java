package com.hebin.lifetool.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.base.BaseMethod;
import com.hebin.lifetool.utils.NetUtils;
import com.hebin.lifetool.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.webview)
    WebView webview;

    String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("title"))) {
            tvPublicTitle.setText(getIntent().getStringExtra("title"));
            init();
        }
    }

    @OnClick({R.id.ll_back, R.id.webview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
                break;
        }
    }

    private void init() {
        if (!NetUtils.isNetworkConnected(this)) {
            noconnect();
            hideLoading();
        } else {
            showLoading();
            BaseMethod.webnature(webview);
            switch (getIntent().getStringExtra("title")) {
                case "文章详情":
                    url = getIntent().getStringExtra("url");
                    break;
            }
            webview.loadUrl(url);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    hideLoading();
                }
            });
        }
    }

    private void showLoading() {
        llLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        llLoading.setVisibility(View.GONE);
    }

    private void noconnect() {
        ToastUtils.getInstance().showNoNet();
    }

}
